<script lang="ts" setup>
  import { computed, onMounted, onUnmounted, onUpdated, Ref, ref } from 'vue';
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
  import Header from './Header.vue';
  import { addMessage } from '@/api/conversation';
  import { t } from '@/locales';

  const router = useRouter();
  const dialog = useDialog();
  const ms = useMessage();
  const { isMobile } = useBasicLayout();
  const { scrollRef, contentRef, scrollToBottom, scrollToBottomIfAtBottom } = useScroll();
  const chatStore = useChatStore();
  let controller = new AbortController();

  const loading = ref<boolean>(false);
  const prompt = ref<string>('');
  const chatId = ref<string>('');
  const aiChatId = ref<string>('');
  const inputRef = ref<Ref | null>(null);

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

    // user
    chatId.value = uuidv4();
    const messageData = await chatStore.addMessage(message, 'user', chatId.value);
    const data = await addMessage(messageData);

    loading.value = true;
    prompt.value = '';

    if (chatStore.conversations.length == 0) {
      await chatStore.loadData();
    }

    // ai
    aiChatId.value = uuidv4();
    await chatStore.addMessage('', 'assistant', aiChatId.value);
    if (chatStore.curConversation?.id == undefined) {
      chatStore.curConversation = { id: String(messageData.conversationId) };
      await chatStore.selectConversation({ id: messageData.conversationId });
      console.log(chatStore.active);
    }
    await scrollToBottom();
    await onChat(message);
  }

  async function onChat(message: string) {
    try {
      // 定义接口
      const fetchChatAPIOnce = async () => {
        await chat(
          {
            chatId: chatId.value,
            content: message,
            role: 'user',
            conversationId: chatStore.curConversation?.id,
          },
          async ({ event }) => {
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
            await chatStore.updateMessage(aiChatId.value, text, false);
            await scrollToBottomIfAtBottom();
          }
        ).catch(() => {});
      };

      // 调用接口
      await fetchChatAPIOnce();
    } finally {
      loading.value = false;
    }
  }

  // 删除
  function handleDelete(item: AiMessage) {
    if (loading.value) {
      return;
    }

    dialog.warning({
      title: t('chat.deleteMessage'),
      content: t('chat.deleteMessageConfirm'),
      positiveText: t('common.yes'),
      negativeText: t('common.no'),
      onPositiveClick: () => {
        chatStore.delMessage(item);
      },
    });
    chatId.value = '';
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
    let classes = ['p-8 pt-0'];
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
    chatStore.loadData();
    if (inputRef.value && !isMobile.value) {
      inputRef.value?.focus();
    }
  });

  onUnmounted(() => {
    if (loading.value) {
      controller.abort();
    }
  });

  onUpdated(() => {
    scrollToBottomIfAtBottom();
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
          <Header />

          <!-- chat -->
          <main class="flex-1 overflow-hidden">
            <div ref="contentRef" class="h-full overflow-hidden overflow-y-auto" id="image-wrapper">
              <div v-if="chatIsLoading" class="w-full h-full flex items-center justify-center">
                <n-spin :show="chatIsLoading" size="large" />
              </div>
              <div v-else ref="scrollRef" class="w-full m-auto" :class="[isMobile ? 'p-2' : 'p-8']">
                <Message
                  v-for="(item, index) of dataSources"
                  :key="index"
                  :date-time="item.createTime"
                  :text="item.content"
                  :inversion="item.role !== 'assistant'"
                  :error="item.isError"
                  :loading="loading"
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

          <footer :class="footerClass">
            <div class="w-full m-auto">
              <div class="flex items-center justify-between space-x-2 w-full">
                <n-input
                  ref="inputRef"
                  v-model:value="prompt"
                  type="textarea"
                  :autosize="{ minRows: 3, maxRows: isMobile ? 4 : 8 }"
                  @keypress="handleEnter"
                  :placeholder="t('chat.placeholder')"
                >
                  <template #suffix>
                    <div
                      class="flex justify-end align-center absolute w-full bottom-0 left-0 p-2 gap-2"
                    >
                      <n-popover trigger="hover">
                        <template #trigger>
                          <n-button
                            type="info"
                            size="small"
                            :disabled="buttonDisabled"
                            @click="handleSubmit"
                            secondary
                            class="z-10"
                          >
                            <template #icon>
                              <SvgIcon icon="ic:round-plus" />
                            </template>
                          </n-button>
                        </template>
                        <span>{{ t('chat.filePlaceholder') }}</span>
                      </n-popover>

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
