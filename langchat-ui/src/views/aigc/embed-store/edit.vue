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
  import { computed, nextTick } from 'vue';
  import { add, getById, update } from '@/api/aigc/embed-store';
  import { useMessage } from 'naive-ui';
  import { getProviderLabel, getSchemas } from './columns';
  import { basicModal, useModal } from '@/components/Modal';
  import { BasicForm, useForm } from '@/components/Form';
  import { isNullOrWhitespace } from '@/utils/is';

  const props = defineProps<{
    provider: string;
  }>();
  const emit = defineEmits(['reload']);
  const message = useMessage();
  const [
    modalRegister,
    { openModal: openModal, closeModal: closeModal, setSubLoading: setSubLoading },
  ] = useModal({
    title: getProviderLabel(props.provider) + ' 新增/编辑',
    closable: true,
    maskClosable: false,
    showCloseBtn: false,
    showSubBtn: false,
  });

  const [register, { setFieldsValue }] = useForm({
    gridProps: { cols: 2 },
    labelWidth: 120,
    layout: 'horizontal',
    submitButtonText: '提交',
  });

  const schemas = computed(() => {
    nextTick();
    return getSchemas(props.provider);
  });

  async function show(id: string) {
    openModal();
    await nextTick();
    if (id) {
      setFieldsValue(await getById(id));
    } else {
      setFieldsValue({ isPerms: true, provider: props.provider });
    }
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
  <basicModal style="width: 45%" @register="modalRegister">
    <template #default>
      <n-alert
        class="w-full mb-4 mt-2 min-alert"
        title="注意：请慎重修改模型的向量纬度参数（Dimension），此参数需要和向量库匹配（错误修改可能将影响已有的向量数据）"
        type="info"
      />
      <BasicForm :schemas="schemas" class="mt-5" @register="register" @submit="handleSubmit" />
    </template>
  </basicModal>
</template>

<style lang="less" scoped></style>
