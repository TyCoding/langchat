<script setup lang="ts">
  import { NodeLayout } from '@/views/flow/node';
  import { useNode } from '@vue-flow/core';
  import { onMounted, ref, toRaw } from 'vue';

  const { node } = useNode();
  const options = [
    {
      label: 'gpt-4',
      value: 'gpt-4',
    },
    {
      label: 'gpt-3.5',
      value: 'gpt-3.5',
    },
  ];
  const form = ref<{
    model: string;
    temperate: number;
  }>({
    model: '',
    temperate: 0,
  });

  onMounted(() => {
    const { model, temperate } = toRaw(node.data);
    form.value.model = model;
    form.value.temperate = temperate;
  });
</script>

<template>
  <NodeLayout :node="node">
    <template #content>
      <div class="w-full h-full flex flex-col gap-2 mt-1 mb-2">
        <div class="bg-[#f7f7f7] p-2 flex flex-row justify-between items-center rounded-md">
          <div class="flex gap-1 w-full items-center">
            <span class="text-xs text-gray-400">模型</span>
            <n-select
              :options="options"
              v-model:value="form.model"
              size="tiny"
              class="w-[78%] min-select"
            />
          </div>
          <div class="flex gap-1 w-full items-center justify-end">
            <span class="text-xs text-gray-400">温度</span>
            <n-input-number
              size="tiny"
              v-model:value="form.temperate"
              :max="1"
              :min="0"
              :step="0.1"
              class="w-[78%]"
            />
          </div>
        </div>
        <div class="bg-[#f7f7f7] p-2 rounded-md">
          <n-collapse class="min-collapse">
            <n-collapse-item name="1">
              <template #header>
                <div class="text-xs flex gap-0.5">
                  <span>Prompt</span>
                  <n-popover trigger="hover">
                    <template #trigger>
                      <SvgIcon icon="tabler:exclamation-circle" class="text-gray-400" />
                    </template>
                    Prompt
                  </n-popover>
                </div>
              </template>
              <template #header-extra>
                <n-button text size="tiny">
                  <n-icon class="mr-1">
                    <SvgIcon icon="iconamoon:screen-full" />
                  </n-icon>
                  Full Screen
                </n-button>
              </template>
              <n-input type="textarea" class="text-xs" />
            </n-collapse-item>
          </n-collapse>
        </div>
      </div>
    </template>
  </NodeLayout>
</template>

<style scoped lang="less"></style>
