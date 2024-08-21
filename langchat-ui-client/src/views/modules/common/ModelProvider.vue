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
  import { onMounted, toRaw } from 'vue';
  import { getChatModels } from '@/api/chat';
  import { LLMProviders } from '@/views/modules/common/data';
  import { ref } from 'vue-demi';
  import { useChatStore } from '@/views/modules/chat/store/useChatStore';

  const chatStore = useChatStore();
  const modelList = ref([]);

  onMounted(async () => {
    const providers = await getChatModels();
    const data: any = [];
    if (chatStore.modelId === '' || chatStore.modelId === null) {
      chatStore.modelId = providers[0].id;
      chatStore.modelName = providers[0].model;
      chatStore.modelProvider = providers[0].provider;
    }
    LLMProviders.forEach((i) => {
      const children = providers.filter((m) => m.provider == i.model);
      if (children.length === 0) {
        return;
      }
      data.push({
        type: 'group',
        name: i.name,
        id: i.id,
        children: children,
      });
    });
    modelList.value = data;
  });

  function onUpdate(val, opt) {
    const obj = toRaw(opt);
    chatStore.modelId = obj.id;
    chatStore.modelName = obj.model;
    chatStore.modelProvider = obj.provider;
  }
</script>

<template>
  <n-select
    v-model:value="chatStore.modelId"
    :consistent-menu-width="false"
    :label-field="'name'"
    :options="modelList"
    :value-field="'id'"
    class="!w-32"
    size="small"
    @update:value="onUpdate"
  />
</template>

<style lang="less" scoped>
  ::v-deep(
      .n-base-selection .n-base-selection__border,
      .n-base-selection .n-base-selection__state-border
    ) {
    border: none !important;
  }
  ::v-deep(.n-base-selection-label) {
    font-weight: 600 !important;
    gap: 6px !important;
    display: flex !important;
  }
  ::v-deep(.n-base-selection-input) {
    margin-right: 4px;
  }
</style>
