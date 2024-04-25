<script lang="ts" setup>
  import { ref } from 'vue';
  import {
    CreateOutline,
    CloudUploadOutline,
    CloudDownloadOutline,
    PodiumOutline,
  } from '@vicons/ionicons5';
  import CheckCard from '@/components/CheckCard';
  import { CheckSource } from '@/components/CheckCard/CheckCard.vue';
  import DocInput from './components/DocInput.vue';
  import ExcelImport from './components/ExcelImport.vue';
  import URLImport from './components/URLImport.vue';
  import DocImport from './components/DocImport.vue';

  const dataSource: CheckSource[] = [
    {
      key: 'doc-input',
      icon: CreateOutline,
      title: '手动录入文档',
      label: '手动输入文档文本内容，文档内容过多时请上传文件',
    },
    {
      key: 'doc-import',
      icon: CloudUploadOutline,
      title: '导入文档',
      label: '支持导入word、txt、pdf、markdown等文本文件',
    },
    {
      key: 'excel-import',
      icon: PodiumOutline,
      title: 'Excel文件导入',
      label: '处理excel结构化数据，支持对Excel行列数据读取',
    },
    {
      key: 'url-import',
      icon: CloudDownloadOutline,
      title: '线上导入',
      label: '解析线上文件URL地址，自动下载并解析文档内容',
    },
  ];
  const checked = ref<string>('doc-input');

  function handleCheck(item: CheckSource) {
    checked.value = item.key;
  }
</script>

<template>
  <div>
    <CheckCard
      :data-source="dataSource"
      :default-checked="checked"
      :justify="'start'"
      @on-checked="handleCheck"
    />
    <n-card class="mt-3">
      <DocInput v-if="checked == 'doc-input'" />
      <URLImport v-if="checked == 'url-import'" />
      <DocImport v-if="checked == 'doc-import'" />
      <ExcelImport v-if="checked == 'excel-import'" />
    </n-card>
  </div>
</template>
<style lang="less" scoped></style>
