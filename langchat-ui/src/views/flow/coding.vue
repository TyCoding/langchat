<script setup lang="ts">
  import { nextTick, ref } from 'vue';
  import { oneDark } from '@codemirror/theme-one-dark';
  import { javascript } from '@codemirror/lang-javascript';
  import { Codemirror } from 'vue-codemirror';
  import { CodeWorking } from '@vicons/ionicons5';
  import { renderIcon } from '@/utils';
  import { getById } from '@/api/aigc/flow';

  const emit = defineEmits(['reload']);
  const code = ref('');
  const extensions = [oneDark, javascript()];

  const showModal = ref(false);

  async function show(id: string) {
    showModal.value = true;
    await nextTick();
    if (id) {
      const res = await getById(id);
      code.value = res.script;
    }
  }

  defineExpose({ show });
</script>

<template>
  <n-modal
    v-model:show="showModal"
    style="width: 30%"
    :icon="renderIcon(CodeWorking)"
    preset="dialog"
    title="编辑"
  >
    <codemirror
      v-model="code"
      placeholder="Code goes here..."
      :style="{ height: '400px' }"
      :autofocus="true"
      :indent-with-tab="true"
      :tab-size="2"
      :extensions="extensions"
    />
  </n-modal>
</template>

<style scoped lang="less">
  ::v-deep(.cm-focused) {
    outline: none !important;
  }
</style>
