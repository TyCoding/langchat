<script setup lang="ts">
  import Sider from './components/Sider.vue';
  import Mermaid from './components/Mermaid.vue';
  import { ref } from 'vue';
  import { useMessage } from 'naive-ui';
  import { genMindMap } from '@/api/chat';
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
    loading.value = true;
    const { message } = await genMindMap({
      message: text,
    });
    genText.value = message;

    loading.value = false;
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

<style scoped lang="less">
  ::v-deep(.bg) {
    height: 100%;
    background-image: radial-gradient(circle at center, rgba(0, 0, 0, 0.13) 1.2px, transparent 0);
    background-size: 15px 15px;
    background-repeat: round;
  }
</style>
