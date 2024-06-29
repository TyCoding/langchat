<script setup lang="ts">
  import { NodeLayout } from '@/views/flow/node';
  import { useNode } from '@vue-flow/core';

  const { node } = useNode();
</script>

<template>
  <NodeLayout :node="node">
    <template #content>
      <div class="">
        <n-input size="small" placeholder="input http url..." />
      </div>
    </template>
  </NodeLayout>
</template>

<style scoped lang="less"></style>
