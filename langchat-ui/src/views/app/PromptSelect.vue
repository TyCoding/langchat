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
  import { onMounted, ref, toRaw } from 'vue';
  import { list } from '@/api/aigc/prompt';

  const props = defineProps<{
    id: any;
  }>();
  const emit = defineEmits(['update']);
  const options = ref([]);
  const knowledgeId = ref('');

  onMounted(async () => {
    options.value = await list({});
    knowledgeId.value = props.id;
  });

  function onUpdate(val: any, opt) {
    const obj = toRaw(opt);
    emit('update', {
      id: obj.id,
    });
  }
</script>

<template>
  <n-select
    v-model:value="knowledgeId"
    :consistent-menu-width="false"
    :label-field="'name'"
    :options="options"
    :value-field="'id'"
    placeholder="请选择关联提示词"
    @update:value="onUpdate"
  />
</template>

<style lang="less" scoped></style>
