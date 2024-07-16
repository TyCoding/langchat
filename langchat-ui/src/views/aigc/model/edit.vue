<script lang="ts" setup>
  import { computed, nextTick, ref } from 'vue';
  import { BasicForm, useForm } from '@/components/Form';
  import { getSchemas } from './schemas';
  import { isNullOrWhitespace } from '@/utils/is';
  import { add, update } from '@/api/aigc/model';
  import { useMessage } from 'naive-ui';

  const props = defineProps<{
    provider: string;
  }>();
  const emit = defineEmits(['reload']);
  const isShow = ref(false);
  const info = ref();
  const message = useMessage();
  const title = computed(() => {
    return info.value == undefined || info.value.provider == undefined
      ? 'Add Model'
      : info.value.provider;
  });
  const form: any = {
    responseLimit: 2000,
    temperature: 0.8,
    topP: 1,
  };

  const schemas = computed(() => {
    nextTick();
    return getSchemas(props.provider);
  });

  async function show(record?: any) {
    isShow.value = true;
    await nextTick();
    info.value = record;
    setFieldsValue({ ...form, ...record });
  }

  console.log(props.provider);
  const [register, { setFieldsValue }] = useForm({
    labelWidth: 120,
    gridProps: { cols: 1 },
    layout: 'horizontal',
    submitButtonText: '提交',
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

<template>
  <n-drawer v-model:show="isShow" placement="right" width="40%">
    <n-drawer-content :title="title" closable>
      <BasicForm :schemas="schemas" class="mt-5" @register="register" @submit="onSubmit" />
    </n-drawer-content>
  </n-drawer>
</template>

<style lang="less" scoped></style>
