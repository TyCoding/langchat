<script lang="ts" setup>
  import { useChatStore } from '@/views/aigc/chat/components/store/useChatStore';
  import { onMounted } from 'vue';
  import { getChatModels } from '@/api/aigc/model';
  import { LLMProviders } from '@/views/aigc/model/data';
  import { ref } from 'vue-demi';

  const chatStore = useChatStore();
  const modelList = ref([]);

  onMounted(async () => {
    const providers = await getChatModels();
    const data: any = [];
    if (chatStore.model === '') {
      chatStore.model = providers[0].id;
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
</script>

<template>
  <n-select
    v-model:value="chatStore.model"
    :consistent-menu-width="false"
    :label-field="'name'"
    :options="modelList"
    :value-field="'id'"
    class="!w-32"
    size="small"
  />
</template>

<style lang="less" scoped></style>
