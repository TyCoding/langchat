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
  import { useChatStore } from '@/views/chat/store/useChatStore';
  import { onMounted, ref, toRaw } from 'vue';
  import SvgIcon from '@/components/SvgIcon/index.vue';
  import { list as getModels } from '@/api/aigc/model';
  import { LLMProviders } from '@/views/aigc/model/components/chat/data';
  import { ModelTypeEnum } from '@/api/models';

  const chatStore = useChatStore();
  const modelList = ref([]);

  onMounted(async () => {
    const providers = (await getModels({ type: ModelTypeEnum.CHAT })) as any;
    const data: any = [];
    if (chatStore.modelName === '') {
      if (providers != null && providers.length != 0) {
        chatStore.modelId = providers[0].id;
        chatStore.modelName = providers[0].model;
        chatStore.modelProvider = providers[0].provider;
      }
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
  <div class="flex items-center">
    <SvgIcon class="text-xl" icon="bitcoin-icons:magic-wand-outline" />
    <n-select
      v-model:value="chatStore.modelId"
      :consistent-menu-width="false"
      :label-field="'name'"
      :options="modelList"
      :value-field="'id'"
      class="min-w-[100px]"
      placeholder="请先完成模型配置"
      @update:value="onUpdate"
    />
  </div>
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
