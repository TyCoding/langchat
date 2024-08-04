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
  import { add, getById, tree as getDeptList, update } from '@/api/upms/dept';
  import { useMessage } from 'naive-ui';
  import { formSchemas } from './columns';
  import { basicModal, useModal } from '@/components/Modal';
  import { BasicForm, useForm } from '@/components/Form';
  import { isNullOrWhitespace } from '@/utils/is';

  const emit = defineEmits(['reload']);
  const message = useMessage();

  const [
    modalRegister,
    { openModal: openModal, closeModal: closeModal, setSubLoading: setSubLoading },
  ] = useModal({
    title: '新增/编辑',
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

  const deptList = ref();
  async function show(id: string, parentId?: string) {
    openModal();
    await nextTick();
    if (id) {
      setFieldsValue(await getById(id));
    } else {
      setFieldsValue({ parentId: parentId });
    }
    deptList.value = await getDeptList({});
  }

  async function handleSubmit(values: any) {
    if (values !== false) {
      if (isNullOrWhitespace(values.id)) {
        await add(values);
        closeModal();
        emit('reload');
        message.success('新增成功');
      } else {
        await update(values);
        closeModal();
        emit('reload');
        message.success('修改成功');
      }
    } else {
      setSubLoading(false);
      message.error('请完善表单');
    }
  }
  defineExpose({ show });
</script>

<template>
  <basicModal style="width: 30%" @register="modalRegister">
    <template #default>
      <BasicForm class="mt-5" @register="register" @submit="handleSubmit">
        <template #parentSlot="{ model, field }">
          <n-tree-select
            v-model:value="model[field]"
            :options="deptList"
            clearable
            filterable
            key-field="id"
            label-field="name"
            placeholder="请选择上级部门"
            value-field="id"
          />
        </template>
      </BasicForm>
    </template>
  </basicModal>
</template>

<style lang="less" scoped></style>
