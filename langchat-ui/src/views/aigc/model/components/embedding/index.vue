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
  import { BasicForm, useForm } from '@/components/Form';
  import { schemas } from './columns';
  import { isNullOrWhitespace } from '@/utils/is';
  import { add, list as getModels, update } from '@/api/aigc/model';
  import { useMessage } from 'naive-ui';
  import { onMounted } from 'vue';
  import { ModelTypeEnum } from '@/api/models';

  const ms = useMessage();
  const [register, { setFieldsValue }] = useForm({
    labelWidth: 120,
    gridProps: { cols: 1 },
    layout: 'horizontal',
    submitButtonText: '提交',
  });

  onMounted(async () => {
    const data = await getModels({ type: ModelTypeEnum.EMBEDDING });
    if (data != null && data.length >= 0) {
      setFieldsValue({ ...data[0] });
    }
  });

  async function onSubmit(values: any) {
    if (values !== false) {
      const data = { ...values };
      if (isNullOrWhitespace(data.id)) {
        await add(data);
        ms.success('新增成功');
      } else {
        await update(data);
        ms.success('修改成功');
      }
    } else {
      ms.error('请完善表单');
    }
  }
</script>

<template>
  <div class="flex justify-center items-center mb-10">
    <div class="w-2/4">
      <n-alert
        class="w-full mb-4 mt-2 min-alert"
        title="如果需要使用知识库的能力，则必须配置Embedding向量模型。Dimensions代表向量纬度，不同的模型配置不一样，此值和VectorStore的配置要完全对应，如果在application.yml已经配置了VectorStore的Dimensions参数，那么这里也应该保持相同，否则需要删除VectorStore表重新启动项目"
        type="info"
      />
      <BasicForm :schemas="schemas" class="mt-5" @register="register" @submit="onSubmit" />
    </div>
  </div>
</template>

<style lang="less" scoped></style>
