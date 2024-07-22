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
    if (chatStore.modelName === '') {
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

<style lang="less" scoped></style>
