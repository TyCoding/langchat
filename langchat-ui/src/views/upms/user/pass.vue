<script lang="ts" setup>
  import { nextTick } from 'vue';
  import { getById, resetPass } from '@/api/upms/user';
  import { useMessage } from 'naive-ui';
  import { basicModal, useModal } from '@/components/Modal';
  import { BasicForm, useForm } from '@/components/Form';
  import { passFormSchemas } from '@/views/upms/user/columns';

  const emit = defineEmits(['reload']);
  const message = useMessage();

  const [
    modalRegister,
    { openModal: openModal, closeModal: closeModal, setSubLoading: setSubLoading },
  ] = useModal({
    title: '重置密码',
    closable: true,
    maskClosable: false,
    showCloseBtn: false,
    showSubBtn: false,
  });

  const [register, { setFieldsValue }] = useForm({
    gridProps: { cols: 2 },
    labelWidth: 120,
    layout: 'horizontal',
    submitButtonText: '确认重置',
    schemas: passFormSchemas,
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
      if (values.password !== values.repass) {
        message.error('两次输入密码不一致');
        return;
      }
      await resetPass(values);
      closeModal();
      emit('reload');
      message.success('密码重置成功');
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
      <BasicForm class="mt-5" @register="register" @submit="handleSubmit" />
    </template>
  </basicModal>
</template>

<style lang="less" scoped></style>
