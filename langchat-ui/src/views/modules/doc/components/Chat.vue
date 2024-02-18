<script setup lang="ts">
  import { ref } from 'vue';
  import { chat } from '@/api/file';
  import { v4 as uuid } from 'uuid';
  import { useRouter } from 'vue-router';
  import MarkdownIt from 'markdown-it';
  import hljs from 'highlight.js';
  import mila from 'markdown-it-link-attributes';
  import mdKatex from '@traptitech/markdown-it-katex';
  import Message from './Message.vue';
  import { SvgIcon } from '@/components/common';

  const emits = defineEmits(['focus-active']);

  const messageRef = ref();
  const router = useRouter();
  const content = ref('');
  const loading = ref(false);

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
      content: string;
      time?: number;
      usedToken?: number;
    }[]
  >([]);

  async function handleSubmit() {
    loading.value = true;
    messageRef.value.scrollToBottom();

    try {
      let id = uuid();
      messages.value.push(
        {
          id: uuid(),
          inversion: false,
          content: content.value,
        },
        {
          id: id,
          inversion: true,
          content: '',
          usedToken: 0,
          time: 0,
        }
      );
      const items = messages.value.filter((i) => i.id == id);
      await chat(
        {
          content: content.value,
        },
        ({ event }) => {
          const list = event.target.responseText.split('\n\n');
          let text = '';
          list.forEach((i) => {
            if (!i.startsWith('data:{')) {
              return;
            }
            const { usedToken, done, content, time } = JSON.parse(i.substring(5, i.length));
            if (done) {
              items[0].usedToken = usedToken;
              items[0].time = time;
            } else {
              text += content;
            }
          });
          items[0].content = mdi.render(text);
          messageRef.value.scrollToBottom();
        }
      ).catch(() => {});
      content.value = '';
      loading.value = false;
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
</script>

<template>
  <div class="container relative h-full card-shadow rounded-xl mb-2">
    <Message ref="messageRef" :messages="messages" />

    <div class="bottom absolute bottom-2 left-0 w-full h-[60px] z-10">
      <div class="pl-12 pr-12 flex justify-center items-center space-x-2 w-full">
        <n-input
          @focus="handleFocus"
          v-model:value="content"
          type="textarea"
          class="w-full ]text-xs rounded-md"
          :autosize="{ minRows: 1, maxRows: 5 }"
          :disabled="loading"
          @keypress="handleEnter"
        >
          <template #suffix>
            <n-button @click="handleSubmit" :loading="loading" size="small" text>
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

<style lang="less"></style>
