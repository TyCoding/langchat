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
      <QaImport v-if="checked == '2'" />
    </n-card>
  </div>
</template>
<script lang="ts" setup>
  import { ref } from 'vue';
  import CheckCard from '@/components/CheckCard';
  import { CheckSource } from '@/components/CheckCard/CheckCard.vue';
  import DocInput from './components/DocInput.vue';
  import QaImport from './components/QaImport.vue';
  import URLImport from './components/URLImport.vue';
  import DocImport from './components/DocImport.vue';

  const dataSource: CheckSource[] = [
    {
      key: 'doc-input',
      title: '手动录入文档',
      label: '支持手动粘贴文档内容到此系统解析',
    },
    {
      key: 'doc-import',
      title: '导入文档',
      label: '支持导入word、txt、pdf、markdown等文档文件',
    },
    {
      key: 'url-import',
      title: '线上导入',
      label: '支持解析线上文件URL地址，自动下载并解析文档内容',
    },
    {
      key: '4',
      title: 'CSV文件导入',
      label: '通过业界流行的技术栈来快速构建 Java 后端应用',
    },
  ];
  const checked = ref<string>('doc-input');

  function handleCheck(item: CheckSource) {
    checked.value = item.key;
  }
</script>
<style lang="less" scoped></style>
