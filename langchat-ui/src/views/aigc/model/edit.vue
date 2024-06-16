<template>
  <n-drawer v-model:show="isShow" placement="right" width="40%">
    <n-drawer-content :title="title" closable>
      <BasicForm class="mt-5" @register="register" @submit="onSubmit" />
    </n-drawer-content>
  </n-drawer>
</template>
<script lang="ts" setup>
  import { computed, nextTick, ref } from 'vue';
  import { BasicForm, useForm } from '@/components/Form';
  import { formSchemas } from './data';
  import { isNullOrWhitespace } from '@/utils/is';
  import { add, update } from '@/api/aigc/model';
  import { useMessage } from 'naive-ui';

  const emit = defineEmits(['reload']);
  const isShow = ref(false);
  const info = ref();
  const message = useMessage();
  const title = computed(() => {
    return info.value == undefined || info.value.provider == undefined
      ? 'Add Model'
      : info.value.provider;
  });
  const form = {
    responseLimit: 2000,
    temperature: 0.8,
    topP: 1,
  };

  async function show(record?: any) {
    isShow.value = true;
    await nextTick();
    info.value = record;
    setFieldsValue({ ...form, ...record });
  }

  const [register, { setFieldsValue }] = useForm({
    labelWidth: 120,
    gridProps: { cols: 1 },
    layout: 'horizontal',
    submitButtonText: '提交',
    schemas: formSchemas,
  });

  async function onSubmit(values: any) {
    if (values !== false) {
      isShow.value = false;
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

<style lang="less" scoped></style>
