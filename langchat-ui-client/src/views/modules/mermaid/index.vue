<script setup lang="ts">
  import Sider from './components/Sider.vue';
  import Mermaid from './components/Mermaid.vue';
  import { ref } from 'vue';
  import { useMessage } from 'naive-ui';
  import { genMermaid } from '@/api/chat';
  import { isBlank } from '@/utils/is';
  import { t } from '@/locales';
  // @ts-ignore
  import { modelList } from '@/api/models/index.d.ts';

  const ms = useMessage();
  const model = ref('gpt-4o');
  const loading = ref(false);
  const mermaidText = ref('');
  async function onGenerate(val: string) {
    if (isBlank(val)) {
      ms.warning(t('common.emptyTips'));
      return;
    }
    let text = '';
    loading.value = true;
    try {
      await genMermaid(
        {
          message: val,
          model: model.value,
        },
        ({ event }) => {
          const list = event.target.responseText.split('\n\n');
          list.forEach((i: any) => {
            if (!i.startsWith('data:{')) {
              return;
            }

            const { done, message } = JSON.parse(i.substring(5, i.length));
            if (done || message == null) {
              loading.value = false;
              mermaidText.value = text;
              return;
            }
            text += message;
            console.log('现在的消息', text);
          });
        }
      );
    } finally {
      loading.value = false;
    }
  }

  function onCase(text: string) {
    mermaidText.value = text;
  }
</script>

<template>
  <n-layout class="w-full h-full" has-sider>
    <n-layout-sider
      :collapsed-width="1"
      :native-scrollbar="false"
      :width="400"
      bordered
      collapse-mode="width"
      show-trigger="arrow-circle"
    >
      <div class="px-4 pt-2 flex items-center justify-between">
        <div>{{ t('mermaid.des') }}</div>
        <n-select size="small" v-model:value="model" :options="modelList" class="!w-[140px]" />
      </div>
      <Sider :mermaidText="mermaidText" :loading="loading" @case="onCase" @generate="onGenerate" />
    </n-layout-sider>

    <Mermaid :text="mermaidText" :loading="loading" />
  </n-layout>
</template>

<style scoped lang="less"></style>
