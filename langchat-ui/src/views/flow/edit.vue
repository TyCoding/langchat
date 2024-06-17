<template>
  <basicModal @register="modalRegister" style="width: 45%">
    <BasicForm @register="register" @submit="handleSubmit" class="mt-5" />
  </basicModal>
</template>
<script lang="ts" setup>
  import { nextTick } from 'vue';
  import { update, getById } from '@/api/aigc/flow';
  import { useMessage } from 'naive-ui';
  import { formSchemas } from './columns';
  import { BasicForm, useForm } from '@/components/Form';
  import { basicModal, useModal } from '@/components/Modal';

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

  async function show(id: string) {
    openModal();
    await nextTick();
    if (id) {
      setFieldsValue(await getById(id));
    }
  }

  async function handleSubmit(values: any) {
    if (values !== false) {
      closeModal();
      await update(values);
      emit('reload');
      message.success('修改成功');
    } else {
      message.error('请完善表单');
    }
  }
  defineExpose({ show });
</script>

<style scoped lang="less"></style>
