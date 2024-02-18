<script setup lang="ts">
  import { Codemirror } from 'vue-codemirror';
  import { oneDark } from '@codemirror/theme-one-dark';
  import { json } from '@codemirror/lang-json';
  import { js } from 'js-beautify';
  import { ref, watch } from 'vue';

  const emit = defineEmits(['update']);
  const props = defineProps<{
    option: any;
  }>();
  const extensions = [oneDark, json()];
  const code = ref(js(JSON.stringify(props.option)));

  watch(props.option, (val) => {
    code.value = js(JSON.stringify(val));
  });
  function onChange(val: string) {
    emit('update', val);
  }
</script>

<template>
  <n-collapse-item title="编辑源码">
    <Codemirror
      v-model="code"
      @update:model-value="onChange"
      placeholder="Code goes here..."
      :style="{ height: '400px' }"
      :autofocus="true"
      :indent-with-tab="true"
      :tab-size="2"
      :extensions="extensions"
    />
  </n-collapse-item>
</template>

<style scoped lang="less">
  ::v-deep(.cm-focused) {
    outline: none !important;
  }
</style>
