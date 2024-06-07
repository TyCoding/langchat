<script setup lang="ts">
  import Message from './message/Message.vue';
  import { useProjectSetting } from '@/hooks/setting/useProjectSetting';
  import { computed, ref } from 'vue';
  import { v4 as uuidv4 } from 'uuid';
  import { useChatStore } from './store/useChatStore';
  import { useScroll } from './store/useScroll';
  import { useDialog, useMessage } from 'naive-ui';
  import SvgIcon from '@/components/SvgIcon/index.vue';
  import { chat } from '@/api/aigc/chat';

  const dialog = useDialog();
  const ms = useMessage();
  const chatStore = useChatStore();
  const { scrollRef, contentRef, scrollToBottom, scrollToBottomIfAtBottom } = useScroll();
  const { isMobile } = useProjectSetting();
  const loading = ref<boolean>(false);
  const message = ref('');
  const chatId = ref<string>('');
  const aiChatId = ref<string>('');
  let controller = new AbortController();

  const footerClass = computed(() => {
    let classes = ['p-4'];
    if (isMobile.value) {
      classes = ['sticky', 'left-0', 'bottom-0', 'right-0', 'p-2', 'pr-3', 'overflow-hidden'];
    }
    return classes;
  });
  const buttonDisabled = computed(() => {
    return loading.value;
  });

  // 初始化加载数据
  const dataSources = computed(() => {
    // 获取当前聊天窗口的数据
    scrollToBottom();
    return chatStore.messages;
  });

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
  async function handleSubmit() {
    const msg = message.value;
    if (loading.value) {
      return;
    }
    if (!msg || msg.trim() === '') {
      ms.error('请先输入消息内容');
      return;
    }
    controller = new AbortController();

    // user
    chatId.value = uuidv4();
    await chatStore.addMessage(msg, 'user', chatId.value);

    loading.value = true;
    message.value = '';

    // ai
    await scrollToBottom();
    aiChatId.value = uuidv4();
    await scrollToBottom();
    await chatStore.addMessage('', 'assistant', aiChatId.value);
    await scrollToBottomIfAtBottom();

    await onChat(msg);
  }

  async function onChat(message: string) {
    try {
      await chat(
        {
          chatId: chatId.value,
          conversationId: chatStore.conversationId,
          promptId: chatStore.prompt?.id,
          promptText: chatStore.prompt?.prompt,
          docsId: chatStore.docsId,
          knowledgeId: chatStore.knowledge?.id,
          message,
          role: 'user',
          isGoogleSearch: chatStore.isGoogleSearch,
          model: chatStore.model,
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
            if (done || message === null) {
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
          console.error('chat error', e);
          if (e.message !== undefined) {
            chatStore.updateMessage(aiChatId.value, e.message || 'chat error', true);
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

  function handleStop() {
    if (loading.value) {
      controller.abort();
      loading.value = false;
    }
  }

  // 删除
  function handleDelete(item: any) {
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
  }
</script>

<template>
  <div class="flex flex-col w-full h-full">
    <!-- 聊天记录窗口 -->
    <main class="flex-1 overflow-hidden">
      <div ref="contentRef" class="h-full overflow-hidden overflow-y-auto">
        <div
          ref="scrollRef"
          class="w-full max-w-screen-3xl m-auto pl-8 pr-8"
          :class="[isMobile ? 'p-2' : 'p-5']"
        >
          <Message
            v-for="(item, index) of dataSources"
            :key="index"
            :date-time="item.createTime"
            :text="item.message"
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

    <!-- 底部 -->
    <footer :class="footerClass">
      <div class="w-full max-w-screen-3xl m-auto pl-8 pr-8 pb-6 relative">
        <div class="flex items-center justify-between space-x-2">
          <NInput
            ref="inputRef"
            v-model:value="message"
            type="textarea"
            :autosize="{ minRows: 3, maxRows: isMobile ? 4 : 8 }"
            @keypress="handleEnter"
            class="custom-input"
          >
            <template #suffix>
              <div class="flex items-center gap-2">
                <NButton
                  type="default"
                  size="small"
                  :disabled="buttonDisabled"
                  @click="handleSubmit"
                >
                  <SvgIcon icon="ph:file-plus-duotone" />
                </NButton>
                <NButton
                  type="primary"
                  size="small"
                  :disabled="buttonDisabled"
                  @click="handleSubmit"
                >
                  <SvgIcon icon="ri:send-plane-fill" />
                </NButton>
              </div>
            </template>
          </NInput>
        </div>
      </div>
    </footer>
  </div>
</template>

<style scoped lang="less">
  ::v-deep(.custom-input) {
    .n-input-wrapper {
      padding-right: 10px;
    }
    .n-input__suffix {
      align-items: end;
      padding-bottom: 6px;
    }
  }
</style>
