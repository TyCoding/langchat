<script lang="ts" setup>
  import { computed, onMounted, onUnmounted, onUpdated, Ref, ref } from 'vue';
  import { SvgIcon } from '@/components/common';
  import { v4 as uuidv4 } from 'uuid';
  import { chat } from '@/api/chat';
  import { Message as AiMessage } from '@/typings/chat';
  import Message from './message/message.vue';
  import Sider from './sider/index.vue';
  import { useDialog } from 'naive-ui';
  import { useBasicLayout } from '@/hooks/useBasicLayout';
  import { useScroll } from './store/useScroll';
  import { useChatStore } from './store/useChatStore';
  import Header from './Header.vue';
  import { addMessage } from '@/api/conversation';
  import { t } from '@/locales';

  const dialog = useDialog();
  const { isMobile } = useBasicLayout();
  const { scrollRef, contentRef, scrollToBottom, scrollToBottomIfAtBottom } = useScroll();
  const chatStore = useChatStore();
  let controller = new AbortController();

  const loading = ref<boolean>(false);
  const prompt = ref<string>('');
  const chatId = ref<string>('');
  const aiChatId = ref<string>('');
  const inputRef = ref();

  onMounted(async () => {
    if (inputRef.value && !isMobile.value) {
      inputRef.value?.focus();
    }
    await chatStore.loadData();
    if (chatStore.conversations.length == 0) {
      await chatStore.addConversation({ title: 'New Chat' });
      await chatStore.loadData();
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
    const data = await chatStore.addMessage(message, 'user', chatId.value);

    loading.value = true;
    prompt.value = '';

    // ai
    await scrollToBottom();
    const { conversationId } = await addMessage(data);
    aiChatId.value = uuidv4();
    await scrollToBottom();
    await chatStore.addMessage('', 'assistant', aiChatId.value);
    await scrollToBottomIfAtBottom();
    await onChat(message, conversationId);
  }

  async function onChat(message: string, conversationId?: string) {
    try {
      let promptText = undefined;
      if (chatStore.selectPromptId !== null && chatStore.selectPromptId !== '') {
        const arr = chatStore.prompts.filter((i) => i.id === chatStore.selectPromptId);
        if (arr.length) {
          promptText = arr[0].prompt;
        }
      }

      await chat(
        {
          chatId: chatId.value,
          message,
          role: 'user',
          model: chatStore.model,
          conversationId: conversationId,
          promptId: chatStore.selectPromptId,
          promptText,
        },
        async ({ event }) => {
          const list = event.target.responseText.split('\n\n');

          let text = '';
          let isRun = true;
          list.forEach((i: any) => {
            if (i.startsWith('data:Error')) {
              isRun = false;
              text += i.substring(5, i.length);
              chatStore.updateMessage(aiChatId.value, text, true);
              return;
            }
            if (!i.startsWith('data:{')) {
              return;
            }

            const { done, message } = JSON.parse(i.substring(5, i.length));
            if (done) {
              if (chatStore.curConversation?.id == undefined) {
                chatStore.curConversation = { id: String(conversationId) };
                chatStore.selectConversation({ id: conversationId });
              }
              return;
            }
            text += message;
          });
          if (!isRun) {
            await scrollToBottomIfAtBottom();
            return;
          }
          await chatStore.updateMessage(aiChatId.value, text, false);
          await scrollToBottomIfAtBottom();
        }
      )
        .catch((e: any) => {
          loading.value = false;
          if (e.message !== undefined) {
            chatStore.updateMessage(aiChatId.value, e.message, true);
            return;
          }
          if (e.startsWith('data:Error')) {
            chatStore.updateMessage(aiChatId.value, e.substring(5, e.length), true);
            return;
          }
        })
        .finally(() => {
          scrollToBottomIfAtBottom();
        });
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

  const menuOptions = ref([
    {
      label: 'Upload File',
      value: 'Upload File',
    },
  ]);

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
</script>

<template>
  <div class="transition-all overflow-hidden h-full">
    <n-layout :class="getContainerClass" class="z-40 transition" has-sider>
      <!-- Sider -->
      <Sider />

      <!-- Main -->
      <n-layout-content class="h-full">
        <div class="flex flex-col w-full h-full">
          <Header />

          <!-- chat -->
          <main class="flex-1 overflow-hidden">
            <div id="image-wrapper" ref="contentRef" class="h-full overflow-hidden overflow-y-auto">
              <div v-if="chatIsLoading" class="w-full h-full flex items-center justify-center">
                <n-spin :show="chatIsLoading" size="large" />
              </div>
              <div
                v-else
                ref="scrollRef"
                class="max-w-screen-2xl m-auto"
                :class="[isMobile ? 'p-2' : 'p-5 py-8 !px-12']"
              >
                <Message
                  v-for="(item, index) of dataSources"
                  :key="index"
                  :date-time="item.createTime"
                  :error="item.isError"
                  :inversion="item.role !== 'assistant'"
                  :loading="loading"
                  :text="item.message"
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
            <div
              class="w-full max-w-screen-2xl m-auto relative"
              :class="isMobile ? 'pb-2' : ' px-20 pb-10 '"
            >
              <div class="flex items-center justify-between">
                <n-input
                  ref="inputRef"
                  v-model:value="prompt"
                  type="textarea"
                  @keypress="handleEnter"
                  :autosize="{ minRows: 1, maxRows: isMobile ? 1 : 4 }"
                  class="!rounded-full px-2 py-1"
                  :placeholder="t('chat.placeholder')"
                  size="large"
                >
                  <template #prefix>
                    <n-popselect placement="top" :options="menuOptions" trigger="click">
                      <n-button text class="!mr-2" size="large">
                        <template #icon>
                          <SvgIcon icon="ion:attach" />
                        </template>
                      </n-button>
                    </n-popselect>
                  </template>
                  <template #suffix>
                    <n-button text :loading="loading" @click="handleSubmit">
                      <template #icon>
                        <SvgIcon icon="mdi:sparkles-outline" />
                      </template>
                    </n-button>
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

<style lang="less" scoped></style>
