<!--
  - Copyright (c) 2024 LangChat. TyCoding All Rights Reserved.
  -
  - Licensed under the GNU Affero General Public License, Version 3 (the "License");
  - you may not use this file except in compliance with the License.
  - You may obtain a copy of the License at
  -
  -     https://www.gnu.org/licenses/agpl-3.0.html
  -
  - Unless required by applicable law or agreed to in writing, software
  - distributed under the License is distributed on an "AS IS" BASIS,
  - WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
  - See the License for the specific language governing permissions and
  - limitations under the License.
  -->

<script lang="ts" setup>
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

  const dataSources = computed(() => {
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
    chatStore.metadata = null;

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
          appId: chatStore.appId,
          message,
          role: 'user',
          modelId: chatStore.modelId,
          modelName: chatStore.modelName,
          modelProvider: chatStore.modelProvider,
        },
        controller,
        async ({ event }) => {
          console.log('消息：', event);
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

            const { done, message, metadata } = JSON.parse(i.substring(5, i.length));
            console.log('xxx', metadata);
            if (done || message === null) {
              if (metadata != null && metadata != {}) {
                chatStore.metadata = metadata;
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
      controller = new AbortController();
      loading.value = false;
    }
  }

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
    <main class="flex-1 overflow-hidden">
      <div ref="contentRef" class="h-full overflow-hidden overflow-y-auto">
        <div
          ref="scrollRef"
          :class="[isMobile ? 'p-2' : 'p-5']"
          class="w-full max-w-screen-3xl m-auto pl-8 pr-8"
        >
          <Message
            v-for="(item, index) of dataSources"
            :key="index"
            :class="dataSources.length - 1 == index ? '!mb-2' : 'mb-6'"
            :date-time="item.createTime"
            :error="item.isError"
            :inversion="item.role !== 'assistant'"
            :loading="loading"
            :text="item.message"
            @delete="handleDelete(item)"
          />
          <div
            v-if="chatStore.metadata != null && chatStore.metadata.length != 0"
            class="w-fit ml-10"
          >
            <n-collapse class="bg-[#f4f6f8] rounded-lg px-3 py-0.5 min-collapse pr-4">
              <n-collapse-item>
                <template #header>
                  <div class="text-[12px] text-gray-500 p-2 pl-0 flex items-center gap-1">
                    <SvgIcon class="text-blue-500 text-[14px]" icon="mingcute:document-2-fill" />
                    <span>引用知识库文档：{{ chatStore.metadata.length }}条</span>
                  </div>
                </template>
                <div class="pb-2">
                  <div class="flex flex-col gap-2">
                    <div
                      v-for="item in chatStore.metadata"
                      :key="item"
                      class="!bg-gray-200 text-[12px] rounded hover:bg-gray-300 cursor-pointer p-2 px-3"
                    >
                      {{ item.docsName }}
                    </div>
                  </div>
                </div>
              </n-collapse-item>
            </n-collapse>
          </div>
        </div>
      </div>
    </main>

    <footer :class="footerClass">
      <div class="w-full max-w-screen-3xl m-auto pl-8 pr-8 pb-6 relative">
        <div class="flex items-center justify-between space-x-2">
          <n-input
            ref="inputRef"
            v-model:value="message"
            :autosize="{ minRows: 1, maxRows: isMobile ? 1 : 4 }"
            class="!rounded-full px-2 py-1 custom-input"
            placeholder="今天想聊些什么~"
            size="large"
            type="textarea"
            @keypress="handleEnter"
          >
            <template #suffix>
              <n-button
                v-if="!loading"
                class="!cursor-pointer"
                size="large"
                text
                @click="handleSubmit"
              >
                <template #icon>
                  <SvgIcon icon="mdi:sparkles-outline" />
                </template>
              </n-button>
              <div v-if="loading" class="!cursor-pointer" @click="handleStop">
                <SvgIcon
                  class="!text-3xl hover:text-gray-500 !cursor-pointer"
                  icon="ri:stop-circle-line"
                />
              </div>
            </template>
          </n-input>
        </div>
      </div>
    </footer>
  </div>
</template>

<style lang="less" scoped>
  ::v-deep(.custom-input) {
    .n-input-wrapper {
      padding-right: 6px !important;
    }
  }
  ::v-deep(.min-collapse) {
    .n-collapse-item__content-inner {
      padding-top: 0 !important;
    }
  }
</style>
