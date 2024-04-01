<script lang="ts" setup>
  import { ref } from 'vue';
  import { useMessage } from 'naive-ui';
  import { genTranslate } from '@/api/chat';
  import MarkdownIt from 'markdown-it';
  import hljs from 'highlight.js';
  import mila from 'markdown-it-link-attributes';
  import mdKatex from '@traptitech/markdown-it-katex';
  import { ChatR } from '@/api/models';

  const ms = useMessage();
  const options = ref([
    { value: '中文' },
    { value: '英文' },
    { value: '韩文' },
    { value: '日文' },
    { value: '自定义' },
  ]);
  const loading = ref(false);
  const language = ref('');
  const result = ref('');
  const form = ref<ChatR>({
    message: '',
    language: '英文',
  });

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

  async function onSubmit() {
    loading.value = true;
    await genTranslate(form.value, ({ event }) => {
      const list = event.target.responseText.split('\n\n');
      let text = '';
      list.forEach((i: any) => {
        if (!i.startsWith('data:{')) {
          return;
        }
        const { done, message } = JSON.parse(i.substring(5, i.length));
        if (done) {
          ms.success('翻译完成');
        } else {
          text += message;
        }
      });
      result.value = mdi.render(text);
    }).finally(() => (loading.value = false));
  }
</script>

<template>
  <div class="p-4">
    <div class="text-xl">文档翻译</div>
  </div>
  <div class="w-full flex flex-row gap-2 p-4 pt-0">
    <div class="w-full h-full">
      <div class="text-gray-600 mb-2 text-[15px] flex items-center justify-between">
        <n-button :loading="loading" secondary size="small" type="success" @click="onSubmit">
          开始翻译
        </n-button>
        <div
          :class="form.language == '自定义' ? 'w-3/12' : 'w-2/12'"
          class="flex flex-row items-center justify-end gap-2"
        >
          <n-select
            v-model:value="form.language"
            :options="options"
            filterable
            label-field="value"
            placeholder="请选择目标语言"
            size="small"
          />
          <n-input
            v-if="form.language == '自定义'"
            v-model:value="language"
            placeholder="请输入目标语言"
            size="small"
          />
        </div>
      </div>
      <n-input
        v-model:value="form.message"
        :disabled="loading"
        placeholder="原始文档内容"
        style="height: calc(100vh - 150px)"
        type="textarea"
      />
    </div>
    <div class="w-full h-full">
      <div class="mb-2 text-lg text-gray-400 flex items-center justify-between">
        <div>Preview</div>
        <n-button secondary size="small" type="success">复制</n-button>
      </div>
      <div class="w-full rounded-md p-2 h-full markdown-body" v-html="result"></div>
    </div>
  </div>
</template>

<style lang="less">
  @import url(./style.less);
</style>
