<script lang="ts" setup>
  import { nextTick, ref } from 'vue';
  import { add, getById, update } from '@/api/aigc/prompt';
  import { useMessage } from 'naive-ui';
  import { formSchemas } from './columns';
  import { BasicForm, useForm } from '@/components/Form';
  import { basicModal, useModal } from '@/components/Modal';
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

  const modelTypes = ref();
  const chatModels = ref();
  async function show(id: string) {
    openModal();
    await nextTick();
    if (id) {
      setFieldsValue(await getById(id));
    }
  }

  function handleSelectModel(value: any, option: any) {}

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
  <basicModal style="width: 45%" @register="modalRegister">
    <BasicForm class="mt-5" @register="register" @submit="handleSubmit">
      <template #modelSlot="{ model, field }">
        <n-select
          v-model:value="model[field]"
          :options="modelTypes"
          filterable
          placeholder="请选择模型名称"
          @update:value="handleSelectModel"
        />
      </template>
      <template #chatModelSlot="{ model, field }">
        <n-select
          v-model:value="model[field]"
          :options="chatModels"
          filterable
          placeholder="请选择对话模型"
        />
      </template>
    </BasicForm>
  </basicModal>
</template>

<style lang="less" scoped></style>
