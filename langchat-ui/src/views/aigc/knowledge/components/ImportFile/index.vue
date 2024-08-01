<!--
  - Copyright (c) 2024 LangChat. TyCoding All Rights Reserved.
  -
  - Licensed under the GNU Affero General Public License, Version 3 (the "License");
  - you may not use this file except in compliance with the License.
  - You may obtain a copy of the License at
  -
  -     https://www.gnu.org/licenses/agpl-3.0.html
  -
  - Unless required by applicable law or agreed to in writing, software
  - distributed under the License is distributed on an "AS IS" BASIS,
  - WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
  - See the License for the specific language governing permissions and
  - limitations under the License.
  -->

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

  interface Props {
    data?: any;
  }
  const props = defineProps<Props>();

  const dataSource: CheckSource[] = [
    {
      key: 'doc-import',
      icon: CloudUploadOutline,
      title: '导入文档',
      label: '支持导入word、txt、pdf、markdown等文本文件',
    },
    {
      key: 'doc-input',
      icon: CreateOutline,
      title: '手动录入文档',
      label: '手动输入文档文本内容，文档内容过多时请上传文件',
    },
    {
      key: 'url-import',
      icon: CloudDownloadOutline,
      title: '线上导入',
      label: '解析线上文件URL地址，自动下载并解析文档内容',
    },
  ];
  const checked = ref<string>('doc-import');

  function handleCheck(item: CheckSource) {
    checked.value = item.key;
  }
</script>

<template>
  <div v-if="data" class="flex flex-col gap-3">
    <template v-if="!data.isExcel">
      <CheckCard
        :data-source="dataSource"
        :default-checked="checked"
        :justify="'start'"
        @on-checked="handleCheck"
      />
    </template>

    <n-card>
      <template v-if="data.isExcel">
        <n-tag type="warning" class="mb-2" :bordered="false">
          仅支持上传Excel文件，后期将会考虑支持更多的结构化数据
        </n-tag>
        <ExcelImport />
      </template>
      <template v-else>
        <DocInput v-if="checked == 'doc-input'" />
        <URLImport v-if="checked == 'url-import'" />
        <DocImport v-if="checked == 'doc-import'" />
      </template>
    </n-card>
  </div>
</template>
<style lang="less" scoped></style>
