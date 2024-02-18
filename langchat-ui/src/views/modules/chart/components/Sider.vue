<script setup lang="ts">
  import { ref } from 'vue';
  import { SvgIcon } from '@/components/common';

  const emit = defineEmits(['generate']);
  const loading = ref(false);
  const text = ref('');
  const genText = ref('');

  function onExample() {
    const str = `# a
# b
# c
# d
# e`;
    text.value = '这是一些列子';
    genText.value = str;
    emit('generate', str);
  }

  function onGenerate() {
    loading.value = true;
    // 生成内容

    emit('generate', genText.value);
    loading.value = false;
  }
</script>

<template>
  <div class="p-4">
    <div class="flex flex-wrap justify-between items-center mb-2">
      <div>内容描述</div>
      <n-button @click="onExample" type="success" text>示例</n-button>
    </div>
    <n-input v-model:value="text" type="textarea" :rows="6" />
    <div class="mt-2 mb-2">
      <n-button :loading="loading" @click="onGenerate" type="success" secondary block>
        <template #icon>
          <SvgIcon class="text-lg" icon="ion:sparkles-outline" />
        </template>
        生成内容
      </n-button>
    </div>

    <div class="mt-6"> </div>
    <div class="flex flex-row gap-2 font-bold">
      <div>图标标题</div>
    </div>
  </div>
</template>

<style scoped lang="less"></style>
