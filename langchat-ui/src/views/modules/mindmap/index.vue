<script lang="ts" setup>
  import Sider from './components/Sider.vue';
  import MindMap from './components/MindMap.vue';
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

    <MindMap :genText="genText" :loading="loading" />
  </n-layout>
</template>

<style lang="less" scoped></style>
