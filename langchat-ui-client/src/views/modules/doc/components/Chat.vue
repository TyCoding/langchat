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
  import { ref } from 'vue';
  import { docsChat } from '@/api/chat';
  import { v4 as uuid } from 'uuid';
  import MarkdownIt from 'markdown-it';
  import hljs from 'highlight.js';
  import mila from 'markdown-it-link-attributes';
  import mdKatex from '@traptitech/markdown-it-katex';
  import Message from './Message.vue';
  import { SvgIcon } from '@/components/common';
  import { useDocStore } from '@/views/modules/doc/store';
  import { t } from '@/locales';
  import Header from '@/views/modules/chat/Header.vue';
  import { useBasicLayout } from '@/hooks/useBasicLayout';
  import { useChatStore } from '@/views/modules/chat/store/useChatStore';
  import ModelProvider from '@/views/modules/common/ModelProvider.vue';

  const { isMobile } = useBasicLayout();
  const emits = defineEmits(['focus-active']);
  const messageRef = ref();
  const message = ref('');
  const isGoogleSearch = ref(false);
  const loading = ref(false);
  const docStore = useDocStore();
  const chatStore = useChatStore();

  function init() {
    messages.value = docStore.messages as any;
  }

  function handleFocus() {
    emits('focus-active');
  }

  const mdi = new MarkdownIt({
    html: false,
    linkify: true,
    highlight(code, language) {
      const validLang = !!(language && hljs.getLanguage(language));
      if (validLang) {
        const lang = language ?? '';
        return highlightBlock(hljs.highlight(code, { language: lang }).value, lang);
      }
      return highlightBlock(hljs.highlightAuto(code).value, '');
    },
  });
  mdi.use(mila, { attrs: { target: '_blank', rel: 'noopener' } });
  mdi.use(mdKatex, { blockClass: 'katexmath-block rounded-md p-[10px]', errorColor: ' #cc0000' });
  function highlightBlock(str: string, lang?: string) {
    return `<pre class="code-block-wrapper"><div class="code-block-header"><span class="code-block-header__lang">${lang}</span><span class="code-block-header__copy">复制</span></div><code class="hljs code-block-body ${lang}">${str}</code></pre>`;
  }

  const messages = ref<
    {
      id: any;
      role: 'user' | 'assistant';
      error: boolean;
      message: string;
      createTime?: any;
      tokens?: number;
    }[]
  >([]);

  async function handleSubmit() {
    if (docStore.file.id === undefined) {
      window.$message?.error('请先选择文档');
      return;
    }

    loading.value = true;
    messageRef.value.scrollToBottom();
    try {
      let id = uuid();
      const userChat = {
        id: uuid(),
        error: false,
        role: 'user',
        message: message.value,
      };
      docStore.addMessage(userChat);
      messages.value.push(userChat, {
        id: id,
        error: false,
        role: 'assistant',
        message: '',
        tokens: 0,
        createTime: 0,
      });
      const items = messages.value.filter((i) => i.id == id);
      await docsChat(
        docStore.file?.id,
        {
          conversationId: docStore.file?.id,
          message: message.value,
          modelId: chatStore.modelId,
          modelName: chatStore.modelName,
          modelProvider: chatStore.modelProvider,
          isGoogleSearch: isGoogleSearch.value,
        },
        ({ event }) => {
          const list = event.target.responseText.split('\n\n');
          let text = '';
          list.forEach((i: any) => {
            if (!i.startsWith('data:{')) {
              return;
            }
            const { usedToken, done, message, time } = JSON.parse(i.substring(5, i.length));
            if (done) {
              items[0].tokens = usedToken;
              items[0].createTime = time;
              docStore.addMessage(items[0]);
            } else {
              text += message;
              items[0].message = mdi.render(text);
              messageRef.value.scrollToBottom();
            }
          });
        }
      )
        .catch((err: any) => {
          if (err.message !== undefined) {
            items[0].error = true;
            items[0].message = err.message;
          }
          loading.value = false;
        })
        .finally(() => {
          message.value = '';
          loading.value = false;
        });
    } finally {
      loading.value = false;
      messageRef.value.scrollToBottom();
    }
  }

  function handleEnter(event: KeyboardEvent) {
    if (event.key === 'Enter' && event.ctrlKey) {
    } else if (event.key === 'Enter') {
      event.preventDefault();
      handleSubmit();
    }
  }

  defineExpose({ init });
</script>

<template>
  <div class="container relative h-full card-shadow rounded-xl mb-2 flex flex-col">
    <header
      :class="isMobile ? 'px-1' : 'px-2'"
      class="sticky z-30 border-b dark:border-neutral-800 border-l-0 bg-white/80 dark:bg-black/20 backdrop-blur"
    >
      <div
        class="relative flex items-center justify-between min-w-0 overflow-hidden h-12 ml-2 mr-2 gap-2"
      >
        <ModelProvider />
      </div>
    </header>
    <Message ref="messageRef" :messages="messages" />

    <div
      v-if="docStore.file.id"
      :class="isMobile ? 'mb-2' : 'mb-6'"
      class="pt-2 left-0 w-full z-10"
    >
      <div class="px-8 flex justify-center items-center space-x-2 w-full">
        <n-input
          v-model:value="message"
          :autosize="{ minRows: 1, maxRows: 3 }"
          :disabled="loading"
          :placeholder="t('chat.placeholder')"
          class="!rounded-full px-2 py-1"
          type="textarea"
          @focus="handleFocus"
          @keypress="handleEnter"
        >
          <template #suffix>
            <n-button :loading="loading" text @click="handleSubmit">
              <template #icon>
                <SvgIcon icon="mdi:sparkles-outline" />
              </template>
            </n-button>
          </template>
        </n-input>
      </div>
    </div>
  </div>
</template>

<style lang="less" scoped>
  ::v-deep(.markdown-body) {
    background-color: transparent !important;
    font-size: inherit;
  }
</style>
