<script lang="ts" setup>
  import { computed, onMounted, onUnmounted, Ref, ref } from 'vue';
  import { SvgIcon } from '@/components/common';
  import { v4 as uuidv4 } from 'uuid';
  import { chat } from '@/api/chat';
  import { Message as AiMessage } from '@/typings/chat';
  import Message from './message/message.vue';
  import Sider from './sider/index.vue';
  import { useDialog, useMessage } from 'naive-ui';
  import { useBasicLayout } from '@/hooks/useBasicLayout';
  import { useScroll } from './store/useScroll';
  import { useChatStore } from './store/useChatStore';
  import { useRouter } from 'vue-router';

  const router = useRouter();
  const dialog = useDialog();
  const ms = useMessage();
  const { isMobile } = useBasicLayout();
  const { scrollRef, contentRef, scrollToBottom, scrollToBottomIfAtBottom } = useScroll();
  const chatStore = useChatStore();
  let controller = new AbortController();

  const loading = ref<boolean>(false);
  const prompt = ref<string>('');
  const promptId = ref<string>('');
  const parentRefId = ref<string>('');
  const inputRef = ref<Ref | null>(null);

  // 初始化加载数据
  chatStore.loadData();
  const dataSources = computed(() => {
    // 获取当前聊天窗口的数据
    scrollToBottom();
    return chatStore.messages;
  });

  async function handleSubmit() {
    let message = prompt.value;
    if (loading.value) {
      return;
    }
    if (!message || message.trim() === '') {
      return;
    }
    controller = new AbortController();

    // 用户输入的聊天
    promptId.value = uuidv4();
    parentRefId.value = uuidv4();
    const isSend = await chatStore.addMessage(message, 'user', promptId.value, parentRefId.value);
    if (!isSend) {
      ms.warning('请先创建App会话应用');
      return;
    }

    loading.value = true;
    prompt.value = '';

    // AI回答，注意：AI消息和用户消息的promptId和parentRefId是反转设置的
    await chatStore.addMessage('', 'assistant', parentRefId.value, promptId.value);
    await scrollToBottom();

    await onConversation(message, false, promptId.value, parentRefId.value);
  }

  async function onConversation(
    message: string,
    isRegenerate: boolean,
    promptId: string,
    parentRefId: string
  ) {
    try {
      // 定义接口
      const fetchChatAPIOnce = async () => {
        await chat(
          {
            // promptId: promptId,
            // parentRefId: parentRefId,
            content: message,
            // conversationId: chatStore.curConversation?.id,
          },
          ({ event }) => {
            const list = event.target.responseText.split('\n\n');

            let text = '';
            list.forEach((i: any) => {
              if (!i.startsWith('data:{')) {
                return;
              }

              const { done, content } = JSON.parse(i.substring(5, i.length));
              if (done) {
                return;
              }
              text += content;
            });
            // 只更新AI回答，promptId要反转
            chatStore.updateMessage(parentRefId, text);
            scrollToBottomIfAtBottom();
          }
        ).catch(() => {});
      };

      // 调用接口
      await fetchChatAPIOnce();
    } finally {
      loading.value = false;
    }
  }

  // 重新生成
  async function onRegenerate(item: AiMessage) {
    if (loading.value) {
      return;
    }
    const index = chatStore.messages.findIndex((i) => i.promptId == item.parentRefId);
    if (index === -1) {
      ms.warning('数据异常，无法重新生成');
      return;
    }
    loading.value = true;
    await chatStore.updateMessage(item.promptId, '');

    const message = String(chatStore.messages[index].content);
    // 对于AI的回答重新生成，promptId要反转设置
    await onConversation(message, true, item.parentRefId, item.promptId);
  }

  // 删除
  function handleDelete(item: AiMessage) {
    if (loading.value) {
      return;
    }

    dialog.warning({
      title: '删除消息',
      content: '确认删除消息',
      positiveText: '是',
      negativeText: '否',
      onPositiveClick: () => {
        chatStore.delMessage(item);
      },
    });
    promptId.value = '';
  }

  // 清除
  function handleClear() {
    if (loading.value) {
      return;
    }
    dialog.warning({
      title: '清除聊天',
      content: '确认清除聊天',
      positiveText: '是',
      negativeText: '否',
      onPositiveClick: async () => {
        console.log('清除聊天');
      },
    });
    promptId.value = '';
  }

  function handleEnter(event: KeyboardEvent) {
    if (!isMobile.value) {
      if (event.key === 'Enter' && !event.shiftKey) {
        event.preventDefault();
        handleSubmit();
      }
    } else {
      if (event.key === 'Enter' && event.ctrlKey) {
        event.preventDefault();
        handleSubmit();
      }
    }
  }

  function handleStop() {
    if (loading.value) {
      controller.abort();
      loading.value = false;
    }
  }

  const buttonDisabled = computed(() => {
    return loading.value;
  });

  const footerClass = computed(() => {
    let classes = ['p-4 pt-0'];
    if (isMobile.value) {
      classes = ['sticky', 'left-0', 'bottom-0', 'right-0', 'p-2', 'pr-3', 'overflow-hidden'];
    }
    return classes;
  });

  const chatIsLoading = computed(() => {
    return chatStore.chatIsLoading;
  });

  const collapsed = computed(() => chatStore.siderCollapsed);

  const getContainerClass = computed(() => {
    return ['h-full', { 'pl-[260px]': !isMobile.value && !collapsed.value }];
  });

  onMounted(() => {
    if (inputRef.value && !isMobile.value) {
      inputRef.value?.focus();
    }
  });

  onUnmounted(() => {
    if (loading.value) {
      controller.abort();
    }
  });
</script>

<template>
  <div class="transition-all overflow-hidden h-full">
    <n-layout class="z-40 transition" :class="getContainerClass" has-sider>
      <!-- Sider -->
      <Sider />

      <!-- Main -->
      <n-layout-content class="h-full">
        <div class="flex flex-col w-full h-full">
          <header
            v-if="isMobile"
            @click="chatStore.setSiderCollapsed(false)"
            class="sticky pl-2 pr-2 z-30 border-b dark:border-neutral-800 bg-white/80 dark:bg-black/20 backdrop-blur"
          >
            <div class="relative flex items-center justify-between min-w-0 overflow-hidden h-14">
              <div class="flex items-center">
                <n-button text>
                  <SvgIcon class="text-2xl" icon="solar:list-bold-duotone" />
                </n-button>
              </div>
              <h1
                class="flex-1 px-4 pr-6 overflow-hidden cursor-pointer select-none text-ellipsis whitespace-nowrap"
                v-if="chatStore.curConversation"
              >
                {{ chatStore.curConversation!.title }}
              </h1>
              <div class="flex items-center space-x-2">
                <n-button text>
                  <SvgIcon icon="ri:stop-circle-line" />
                </n-button>
              </div>
            </div>
          </header>

          <div
            v-else
            class="w-full p-3 pl-6 pr-8 border-b border-b-[#e5e7eb] dark:border-b-[#1e1e20] flex justify-between items-center"
          >
            <div v-if="chatStore.curConversation">{{ chatStore.curConversation!.title }}</div>
            <div class="flex justify-center items-center">
              <n-button text><SvgIcon class="text-lg" icon="material-symbols:download" /></n-button>
            </div>
          </div>

          <!-- 聊天记录窗口 -->
          <main class="flex-1 overflow-hidden">
            <div ref="contentRef" class="h-full overflow-hidden overflow-y-auto">
              <div v-if="chatIsLoading" class="w-full h-full flex items-center justify-center">
                <n-spin :show="chatIsLoading" size="large" />
              </div>
              <div v-else ref="scrollRef" class="w-full m-auto" :class="[isMobile ? 'p-2' : 'p-5']">
                <Message
                  v-for="(item, index) of dataSources"
                  :key="index"
                  :date-time="item.createTime"
                  :text="item.content"
                  :inversion="item.role !== 'assistant'"
                  :error="item.isError"
                  :loading="loading"
                  @regenerate="onRegenerate(item)"
                  @delete="handleDelete(item)"
                />
                <div class="sticky bottom-0 left-0 flex justify-center">
                  <NButton v-if="loading" type="warning" @click="handleStop">
                    <template #icon>
                      <SvgIcon icon="ri:stop-circle-line" />
                    </template>
                    Stop Responding
                  </NButton>
                </div>
              </div>
            </div>
          </main>

          <!-- 底部 -->
          <footer :class="footerClass">
            <div class="w-full m-auto">
              <div class="flex items-center justify-between space-x-2 w-full">
                <n-input
                  ref="inputRef"
                  v-model:value="prompt"
                  type="textarea"
                  :autosize="{ minRows: 3, maxRows: isMobile ? 4 : 8 }"
                  @keypress="handleEnter"
                  placeholder="请输入您的问题...（Shift + Enter 换行，按下 Enter 发送）"
                >
                  <template #suffix>
                    <div
                      class="flex justify-between align-center absolute w-full bottom-0 left-0 p-2"
                    >
                      <div></div>
                      <n-button
                        type="primary"
                        size="small"
                        :disabled="buttonDisabled"
                        @click="handleSubmit"
                        secondary
                        class="z-10"
                      >
                        <template #icon>
                          <SvgIcon icon="ri:send-plane-fill" />
                        </template>
                      </n-button>
                    </div>
                  </template>
                </n-input>
              </div>
            </div>
          </footer>
        </div>
      </n-layout-content>
    </n-layout>
  </div>
</template>

<style lang="less" scoped>
  ::v-deep(.n-input__textarea) {
    height: calc(100% - 30px);
  }
  ::v-deep(.n-scrollbar-rail) {
    height: calc(100% - 33px);
  }
</style>
