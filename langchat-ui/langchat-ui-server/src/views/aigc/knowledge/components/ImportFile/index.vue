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
  import { CloudUploadOutline, CreateOutline } from '@vicons/ionicons5';
  import CheckCard from '@/components/CheckCard';
  import { CheckSource } from '@/components/CheckCard/CheckCard.vue';
  import DocInput from './components/DocInput.vue';
  import DocImport from './components/DocImport.vue';

  interface Props {
    data?: any;
  }
  defineProps<Props>();

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
  ];
  const checked = ref<string>('doc-import');

  function handleCheck(item: CheckSource) {
    checked.value = item.key;
  }
</script>

<template>
  <div v-if="data" class="flex flex-col gap-3">
    <CheckCard
      :data-source="dataSource"
      :default-checked="checked"
      :justify="'start'"
      @on-checked="handleCheck"
    />

    <n-card>
      <DocInput v-if="checked == 'doc-input'" />
      <DocImport v-if="checked == 'doc-import'" />
    </n-card>
  </div>
</template>
<style lang="less" scoped></style>
