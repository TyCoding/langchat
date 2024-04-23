<template>
  <div>
    <n-space vertical>
      <n-button type="success" @click="handleSubmit">提交到知识库学习</n-button>
      <n-divider dashed> <n-tag type="success">文档描述信息</n-tag> </n-divider>
      <n-input v-model:value="des" placeholder="请输入文档描述信息" />
      <n-divider dashed> <n-tag type="success">文档录入内容</n-tag> </n-divider>
      <n-input
        v-model:value="content"
        placeholder="请输入需要录入的文档内容"
        rows="20"
        type="textarea"
      />
    </n-space>
  </div>
</template>
<script lang="ts" setup>
  import { ref } from 'vue';
  import { useMessage } from 'naive-ui';
  import { isNullOrWhitespace } from '@/utils/is';
  import { add } from '@/api/aigc/kb-doc';
  import { useRouter } from 'vue-router';

  const router = useRouter();

  const message = useMessage();

  const des = ref<string>('');
  const content = ref<string>('');

  async function handleSubmit() {
    if (isNullOrWhitespace(content.value)) {
      message.warning('请输入文档内容');
      return;
    }
    const kbId = router.currentRoute.value.params.id;
    await add({
      des: des.value,
      content: content.value,
      kbId: kbId,
    });
    message.success('文档录入成功，正在解析中...');
    des.value = '';
    content.value = '';
  }
</script>
<style lang="less" scoped></style>
