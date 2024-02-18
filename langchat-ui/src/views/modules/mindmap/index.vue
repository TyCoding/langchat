<script setup lang="ts">
  import Sider from './components/Sider.vue';
  import MindMap from './components/MindMap.vue';
  import { ref } from 'vue';
  import { useMessage } from 'naive-ui';
  import { genMindMap } from '@/api/chat';
  import { isBlank } from '@/utils/is';
  import { t } from '@/locales';

  const message = useMessage();
  const loading = ref(false);
  const genText = ref('');
  async function onGenerate(text: string) {
    if (isBlank(text)) {
      message.warning(t('common.emptyTips'));
      return;
    }
    loading.value = true;
    const { content } = await genMindMap({
      content: text,
    });
    genText.value = content;

    loading.value = false;
  }

  function onCase(text: string) {
    genText.value = text;
  }
</script>

<template>
  <n-layout has-sider class="w-full h-full">
    <n-layout-sider
      :collapsed-width="1"
      collapse-mode="width"
      :width="400"
      show-trigger="arrow-circle"
      bordered
      :native-scrollbar="false"
    >
      <Sider :loading="loading" :genText="genText" @generate="onGenerate" @case="onCase" />
    </n-layout-sider>

    <MindMap :loading="loading" :genText="genText" />
  </n-layout>
</template>

<style scoped lang="less"></style>
