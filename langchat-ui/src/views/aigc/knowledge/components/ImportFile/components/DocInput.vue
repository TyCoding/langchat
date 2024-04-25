<script lang="ts" setup>
  import { ref } from 'vue';
  import { useMessage } from 'naive-ui';
  import { isNullOrWhitespace } from '@/utils/is';
  import { embeddingText } from '@/api/aigc/embedding';
  import { useRouter } from 'vue-router';

  const router = useRouter();
  const message = useMessage();
  const form = ref({
    name: '',
    content: '',
  });
  const rules = ref({
    name: {
      required: true,
      message: '请输入文件名',
      trigger: ['input', 'blur'],
    },
    content: {
      required: true,
      message: '请输入文档内容',
      trigger: ['input', 'blur'],
    },
  });

  async function handleSubmit() {
    if (isNullOrWhitespace(form.value.content)) {
      message.warning('请输入文档内容');
      return;
    }
    const knowledgeId = router.currentRoute.value.params.id;
    await embeddingText({
      ...form.value,
      knowledgeId,
    });
    message.success('文档录入成功，正在解析中...');
    form.value = {
      name: '',
      content: '',
    };
  }
</script>

<template>
  <div class="flex flex-col gap-4">
    <div>
      <n-button type="success" @click="handleSubmit">提交到知识库学习</n-button>
    </div>

    <n-form :rules="rules" :model="form" label-placement="left" label-width="auto">
      <n-form-item label="文件名称" path="name">
        <n-input v-model:value="form.name" />
      </n-form-item>
      <n-form-item label="文档内容" path="content">
        <n-input
          v-model:value="form.content"
          placeholder="请输入需要录入的文档内容"
          rows="20"
          type="textarea"
        />
      </n-form-item>
    </n-form>
  </div>
</template>

<style lang="less" scoped></style>
