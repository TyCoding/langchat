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
  import { getSchemas, LLMProviders } from './columns';
  import { isNullOrWhitespace } from '@/utils/is';
  import { add, del, list as getModels, update } from '@/api/aigc/model';
  import { useDialog, useMessage } from 'naive-ui';
  import { onMounted } from 'vue';
  import { ModelTypeEnum } from '@/api/models';
  import { ref } from 'vue-demi';

  const message = useMessage();
  const dialog = useDialog();
  const ms = useMessage();
  const schemas = ref();
  const isLocalEmbedding = ref(false);

  const [register, { setFieldsValue, getFieldsValue }] = useForm({
    labelWidth: 120,
    gridProps: { cols: 1 },
    layout: 'horizontal',
    submitButtonText: '提交',
  });

  onMounted(async () => {
    const data = await getModels({ type: ModelTypeEnum.EMBEDDING });
    if (data != null && data.length >= 1) {
      setFieldsValue({ ...data[0] });
      schemas.value = getSchemas(data[0].provider);
    } else {
      isLocalEmbedding.value = true;
      schemas.value = getSchemas('');
    }
  });

  function onChange(val) {
    schemas.value = getSchemas(val);
  }

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

  function onUseLocal(val: boolean) {
    if (val) {
      dialog.info({
        title: '提示',
        content: `启用本地Embedding模型将会删除下面表单中的模型配置，请谨慎操作`,
        positiveText: '确定',
        negativeText: '取消',
        onPositiveClick: async () => {
          const data = getFieldsValue();
          if (data.id !== undefined) {
            await del(data.id);
          }
          message.success('本地模型已启用');
        },
        onNegativeClick: () => {},
      });
    } else {
      message.success('本地模型已禁用');
    }
  }
</script>

<template>
  <div class="flex gap-16 mb-10 h-full">
    <n-card class="w-1/4 h-full">
      <div class="pb-4 text-lg text-gray-800">注意事项</div>
      <n-alert
        class="w-full mb-4 mt-2 min-alert"
        title="默认提供本地化Embedding向量模型方案：BgeSmallEnV15QuantizedEmbeddingModel，此模型仅占用20M的内存。此模型使用384向量纬度"
        type="warning"
      />
      <n-alert
        class="w-full mb-4 mt-2 min-alert"
        title="如果不使用默认Embedding模型则需要配置供应商信息。Dimensions代表向量纬度，不同的模型配置不一样，此值和VectorStore的配置要完全对应，如果在application.yml已经配置了VectorStore的Dimensions参数，那么这里也应该保持相同，否则需要删除VectorStore表重新启动项目"
        type="warning"
      />
      <n-alert
        class="w-full mb-4 mt-2 min-alert"
        title="一旦切换Embedding模型，很可能由于向量纬度不同导致VectorStore中原有数据不可用，请谨慎操作"
        type="warning"
      />
    </n-card>
    <div class="flex-1 mr-[30vh]">
      <div class="mb-4">
        <n-tag class="mr-2 rounded-2xl px-5" type="success">
          是否启用本地Embedding向量模型：
        </n-tag>
        <n-switch v-model:value="isLocalEmbedding" @update:value="onUseLocal">
          <template #checked> 启用 </template>
          <template #unchecked> 不启用 </template>
        </n-switch>
      </div>

      <BasicForm :schemas="schemas" class="mt-5" @register="register" @submit="onSubmit">
        <template #providerSlot="{ model, field }">
          <n-select
            v-model:value="model[field]"
            :options="LLMProviders"
            label-field="name"
            value-field="model"
            @change:value="onChange"
          />
        </template>
      </BasicForm>
    </div>
  </div>
</template>

<style lang="less" scoped></style>
