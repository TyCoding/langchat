<script setup lang="ts">
  import { NodeLayout } from '@/views/flow/node';
  import { useNode } from '@vue-flow/core';
  import { Codemirror } from 'vue-codemirror';
  import { ref } from 'vue';
  import { oneDark } from '@codemirror/theme-one-dark';
  import { javascript } from '@codemirror/lang-javascript';

  const { node } = useNode();
  const code = ref('');
  const extensions = [oneDark, javascript()];
</script>

<template>
  <NodeLayout :node="node">
    <template #content>
      <div class="rounded overflow-hidden">
        <codemirror
          v-model="code"
          placeholder="Code goes here..."
          :style="{ height: '100px' }"
          :autofocus="true"
          :indent-with-tab="true"
          :tab-size="2"
          :extensions="extensions"
        />
      </div>
    </template>
  </NodeLayout>
</template>

<style scoped lang="less">
  ::v-deep(.cm-focused) {
    outline: none !important;
  }
</style>
