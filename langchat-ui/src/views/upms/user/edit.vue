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
  import { list as getDeptList } from '@/api/upms/dept';
  import { list as getRoleList } from '@/api/upms/role';
  import { add, update, getById } from '@/api/upms/user';
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

  const [register, { setFieldsValue, clearValidate, setProps }] = useForm({
    gridProps: { cols: 2 },
    labelWidth: 120,
    layout: 'horizontal',
    submitButtonText: '提交',
    schemas: formSchemas,
  });

  const deptList = ref();
  const roleList = ref();
  async function show(id: string) {
    openModal();
    await nextTick();
    if (id) {
      setFieldsValue(await getById(id));
    } else {
      setFieldsValue({ status: true, sex: '男' });
    }
    // 隐藏密码输入框
    const filterSchemas = formSchemas.filter((i) => {
      if (i.field == 'password') {
        i.isHidden = !isNullOrWhitespace(id);
      }
      clearValidate();
      return true;
    });
    await setProps({ schemas: filterSchemas });
    deptList.value = await getDeptList({});
    roleList.value = await getRoleList({});
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
  <basicModal @register="modalRegister" style="width: 45%">
    <template #default>
      <BasicForm @register="register" @submit="handleSubmit" class="mt-5">
        <template #deptSlot="{ model, field }">
          <n-select
            v-model:value="model[field]"
            filterable
            key-field="id"
            label-field="name"
            value-field="id"
            placeholder="请选择部门"
            :options="deptList"
          />
        </template>
        <template #roleSlot="{ model, field }">
          <n-tree-select
            v-model:value="model[field]"
            filterable
            multiple
            clearable
            key-field="id"
            label-field="name"
            value-field="id"
            children-field="children"
            placeholder="请选择角色"
            :options="roleList"
          />
        </template>
      </BasicForm>
    </template>
  </basicModal>
</template>

<style scoped lang="less"></style>
