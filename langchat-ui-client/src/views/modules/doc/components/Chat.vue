<script lang="ts" setup>
  import { onMounted, ref, watch } from 'vue';
  import { chat } from '@/api/docs';
  import { v4 as uuid } from 'uuid';
  import { useRouter } from 'vue-router';
  import MarkdownIt from 'markdown-it';
  import hljs from 'highlight.js';
  import mila from 'markdown-it-link-attributes';
  import mdKatex from '@traptitech/markdown-it-katex';
  import Message from './Message.vue';
  import { SvgIcon } from '@/components/common';
  import { useDocStore } from '@/views/modules/doc/store';

  const emits = defineEmits(['focus-active']);
  const messageRef = ref();
  const router = useRouter();
  const message = ref('');
  const loading = ref(false);
  const docStore = useDocStore();

  function init() {
    messages.value = docStore.curMessage;
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
      id: string;
      inversion: boolean;
      error: boolean;
      message: string;
      time?: number;
      usedToken?: number;
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
        inversion: false,
        message: message.value,
      };
      docStore.addMessage(userChat);
      messages.value.push(userChat, {
        id: id,
        error: false,
        inversion: true,
        message: '',
        usedToken: 0,
        time: 0,
      });
      const items = messages.value.filter((i) => i.id == id);
      await chat(
        {
          id: docStore.file?.id,
          message: message.value,
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
              items[0].usedToken = usedToken;
              items[0].time = time;
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
  <div class="container relative h-full card-shadow rounded-xl mb-2">
    <Message ref="messageRef" :messages="messages" />

    <div class="bottom absolute bottom-2 pt-5 left-0 w-full h-[60px] z-10">
      <div class="px-8 flex justify-center items-center space-x-2 w-full">
        <n-input
          v-model:value="message"
          :autosize="{ minRows: 1, maxRows: 5 }"
          :disabled="loading"
          class="w-full ]text-xs rounded-md"
          type="textarea"
          @focus="handleFocus"
          @keypress="handleEnter"
        >
          <template #suffix>
            <n-button :loading="loading" size="small" text @click="handleSubmit">
              <template #icon>
                <n-icon color="#18a058">
                  <SvgIcon icon="mingcute:send-line" />
                </n-icon>
              </template>
            </n-button>
          </template>
        </n-input>
      </div>
    </div>
  </div>
</template>

<style scoped lang="less">
  ::v-deep(.markdown-body) {
    background-color: transparent !important;
    font-size: inherit;
  }
</style>
