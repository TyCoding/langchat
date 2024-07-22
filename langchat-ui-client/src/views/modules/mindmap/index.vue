<script lang="ts" setup>
  import Sider from './components/Sider.vue';
  import MindMap from './components/MindMap.vue';
  import { ref } from 'vue';
  import { useMessage } from 'naive-ui';
  import { genMindMap } from '@/api/chat';
  import { isBlank } from '@/utils/is';
  import { t } from '@/locales';
  import ModelProvider from '@/views/modules/common/ModelProvider.vue';

  const model = ref('gpt-4o');
  const ms = useMessage();
  const loading = ref(false);
  const genText = ref('');

  async function onGenerate(text: string) {
    if (isBlank(text)) {
      ms.warning(t('common.emptyTips'));
      return;
    }
    loading.value = true;
    try {
      const { message } = await genMindMap({
        message: text,
        model: model.value,
      });
      genText.value = message;
    } finally {
      loading.value = false;
    }
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
      <div class="px-4 pt-2 flex items-center justify-between">
        <div>{{ t('mindmap.des') }}</div>

        <ModelProvider />
      </div>
      <Sider :genText="genText" :loading="loading" @case="onCase" @generate="onGenerate" />
    </n-layout-sider>

    <MindMap :genText="genText" :loading="loading" />
  </n-layout>
</template>

<style lang="less" scoped></style>
