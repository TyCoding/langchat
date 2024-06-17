<script setup lang="ts">
  import { useFlowStore } from '@/views/flow/store';
  import { onMounted, ref } from 'vue';
  import { Flow } from '@/api/models/flow';

  const flowStore = useFlowStore();
  const form = ref<Flow>({});

  onMounted(() => {
    if (!flowStore.data) {
      return;
    }
    const { name, des } = flowStore.data;
    form.value.name = name;
    form.value.des = des;
  });
</script>

<template>
  <div class="mb-2">
    <div class="pin-title">
      <n-input class="hover-input text-lg" v-model:value="form.name" />
    </div>
    <n-input
      type="textarea"
      size="small"
      class="hover-input text-xs text-gray-300"
      placeholder="Node description..."
      v-model:value="form.des"
    />
  </div>
</template>

<style scoped lang="less"></style>
