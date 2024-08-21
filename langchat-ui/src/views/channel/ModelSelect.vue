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
  import { list as getModels } from '@/api/aigc/model';
  import { LLMProviders } from '@/views/aigc/model/components/chat/data';
  import { ModelTypeEnum } from '@/api/models';
  import { useChatStore } from '@/views/chat/store/useChatStore';

  const chatStore = useChatStore();
  const props = defineProps<{
    id: any;
  }>();
  const emit = defineEmits(['update', 'load']);
  const options = ref([]);
  const modelId = ref('');

  onMounted(async () => {
    const providers = await getModels({ type: ModelTypeEnum.CHAT });
    if (chatStore.modelId === '' || chatStore.modelId === null) {
      if (providers != null && providers.length != 0) {
        const item = providers[0];
        chatStore.modelId = item.id;
        chatStore.modelName = item.model;
        chatStore.modelProvider = item.provider;

        if (props.id == null) {
          modelId.value = item.id;
          emit('load', item);
        } else {
          modelId.value = props.id;
        }
      }
    }
    const data: any = [];
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
    options.value = data;
    modelId.value = chatStore.modelId;
  });

  function onUpdate(val: any, opt) {
    const obj = toRaw(opt);
    emit('update', {
      id: obj.id,
      modelName: obj.model,
      modelProvider: obj.provider,
    });
    chatStore.modelId = obj.id;
    chatStore.modelName = obj.model;
    chatStore.modelProvider = obj.provider;
  }
</script>

<template>
  <n-select
    v-model:value="modelId"
    :consistent-menu-width="false"
    :label-field="'name'"
    :options="options"
    :value-field="'id'"
    placeholder="请选择关联模型"
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
