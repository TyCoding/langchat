<!--
  - Copyright (c) 2024 LangChat. TyCoding All Rights Reserved.
  -
  - Licensed under the GNU Affero General Public License, Version 3 (the "License");
  - you may not use this file except in compliance with the License.
  - You may obtain a copy of the License at
  -
  -     https://www.gnu.org/licenses/agpl-3.0.html
  -
  - Unless required by applicable law or agreed to in writing, software
  - distributed under the License is distributed on an "AS IS" BASIS,
  - WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
  - See the License for the specific language governing permissions and
  - limitations under the License.
  -->

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
    <div class="flex items-center gap-3">
      <n-button type="success" disabled @click="handleSubmit">解析线上数据</n-button>
      <n-tag type="warning">后续将会支持更多解析方式...</n-tag>
    </div>

    <n-form :rules="rules" :model="form" label-placement="left" label-width="auto">
      <n-form-item label="文件URL地址" path="name">
        <n-input v-model:value="form.name" />
      </n-form-item>
    </n-form>
  </div>
</template>

<style lang="less" scoped></style>
