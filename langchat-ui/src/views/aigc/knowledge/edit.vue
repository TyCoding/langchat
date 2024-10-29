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
  import { nextTick, ref } from 'vue';
  import { add, getById, update } from '@/api/aigc/knowledge';
  import { list as getModelStores } from '@/api/aigc/embed-store';
  import { list as getEmbedModels } from '@/api/aigc/model';
  import { useMessage } from 'naive-ui';
  import { formSchemas } from './columns';
  import { BasicForm, useForm } from '@/components/Form';
  import { basicModal, useModal } from '@/components/Modal';
  import { isNullOrWhitespace } from '@/utils/is';
  import { ModelTypeEnum } from '@/api/models';

  const emit = defineEmits(['reload']);
  const message = useMessage();
  const embedStoreList = ref([]);
  const embedModelList = ref([]);

  const [modalRegister, { openModal, closeModal }] = useModal({
    title: '新增/编辑知识库',
    closable: true,
    maskClosable: false,
    showCloseBtn: false,
    showSubBtn: false,
  });
  const [register, { setFieldsValue }] = useForm({
    gridProps: { cols: 1 },
    labelWidth: 120,
    layout: 'horizontal',
    submitButtonText: '提交',
    schemas: formSchemas,
  });

  async function show(id: string) {
    openModal();
    const stores = await getModelStores({});
    if (stores != null) {
      embedStoreList.value = stores.map((item: any) => {
        return {
          label: item.name,
          value: item.id,
        };
      });
    }
    const models = await getEmbedModels({ type: ModelTypeEnum.EMBEDDING });
    if (models != null) {
      embedModelList.value = models.map((item: any) => {
        return {
          label: item.name,
          value: item.id,
        };
      });
    }

    await nextTick();
    if (id) {
      setFieldsValue(await getById(id));
    }
  }
  async function handleSubmit(values: any) {
    if (values !== false) {
      closeModal();
      if (isNullOrWhitespace(values.id)) {
        await add(values);
        emit('reload');
        message.success('新增成功');
      } else {
        await update(values);
        emit('reload');
        message.success('修改成功');
      }
    } else {
      message.error('请完善表单');
    }
  }
  defineExpose({ show });
</script>

<template>
  <basicModal style="width: 35%" @register="modalRegister">
    <BasicForm class="mt-5" @register="register" @submit="handleSubmit">
      <template #embedStoreSlot="{ model, field }">
        <n-select
          v-model:value="model[field]"
          :options="embedStoreList"
          placeholder="请选择关联向量数据库"
        />
      </template>
      <template #embedModelSlot="{ model, field }">
        <n-select
          v-model:value="model[field]"
          :options="embedModelList"
          placeholder="请选择关联向量化模型"
        />
      </template>
    </BasicForm>
  </basicModal>
</template>

<style lang="less" scoped></style>
