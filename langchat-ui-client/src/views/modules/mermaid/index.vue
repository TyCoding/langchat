<script setup lang="ts">
  import Sider from './components/Sider.vue';
  import Mermaid from './components/Mermaid.vue';
  import { ref } from 'vue';
  import { useMessage } from 'naive-ui';
  import { genMermaid } from '@/api/chat';
  import { isBlank } from '@/utils/is';
  import { t } from '@/locales';

  const ms = useMessage();
  const loading = ref(false);
  const genText = ref('');
  async function onGenerate(text: string) {
    if (isBlank(text)) {
      ms.warning(t('common.emptyTips'));
      return;
    }
    genText.value = '';
    loading.value = true;
    await genMermaid(
      {
        message: text,
      },
      ({ event }) => {
        const list = event.target.responseText.split('\n\n');
        list.forEach((i: any) => {
          if (!i.startsWith('data:{')) {
            return;
          }
          const { usedToken, done, message, time } = JSON.parse(i.substring(5, i.length));
          if (done || message == null) {
            loading.value = false;
          } else {
            genText.value += message;
          }
        });
      }
    ).finally(() => {
      loading.value = false;
    });
  }

  function onCase(text: string) {
    genText.value = text;
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
      <Sider :genText="genText" :loading="loading" @case="onCase" @generate="onGenerate" />
    </n-layout-sider>

    <Mermaid :genText="genText" :loading="loading" />
  </n-layout>
</template>

<style scoped lang="less"></style>
