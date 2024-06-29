<script setup lang="ts">
  import { useFlowStore } from '@/views/flow/store';

  const flowStore = useFlowStore();
</script>

<template>
  <div class="mb-2">
    <div class="pin-title">
      <n-input class="hover-input text-lg" />
    </div>
    <n-input
      type="textarea"
      size="small"
      class="hover-input text-xs text-gray-300"
      placeholder="Node description..."
    />
  </div>
  <n-form ref="formRef" class="custom-form">
    <n-space vertical :size="16">
      <n-form-item path="age">
        <template #label>
          <n-popover placement="bottom" class="custom-popover">
            给AI传入的文本内容
            <template #trigger>
              <div class="tips-line">输入（Prompt）</div>
            </template>
          </n-popover>
        </template>
        <n-input disabled />
      </n-form-item>
    </n-space>
  </n-form>
</template>

<style scoped lang="less"></style>
