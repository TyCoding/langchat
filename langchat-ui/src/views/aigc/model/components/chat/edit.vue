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
  import { computed, nextTick, ref } from 'vue';
  import { BasicForm, useForm } from '@/components/Form';
  import { getSchemas } from './schemas';
  import { isNullOrWhitespace } from '@/utils/is';
  import { add, update } from '@/api/aigc/model';
  import { useMessage } from 'naive-ui';
  import { getTitle } from './data';

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
      : getTitle(info.value.provider);
  });
  const form: any = {
    responseLimit: 2000,
    temperature: 0.2,
    topP: 0.8,
  };

  const schemas = computed(() => {
    nextTick();
    return getSchemas(props.provider);
  });

  async function show(record?: any) {
    isShow.value = true;
    await nextTick();
    info.value = record;
    console.log(record);
    setFieldsValue({ ...form, ...record });
  }

  const [register, { setFieldsValue }] = useForm({
    labelWidth: 120,
    gridProps: { cols: 1 },
    layout: 'horizontal',
    submitButtonText: '提交',
  });

  async function onSubmit(values: any) {
    if (values !== false) {
      isShow.value = false;
      const data = { ...values };
      if (isNullOrWhitespace(data.id)) {
        await add(data);
        emit('reload');
        message.success('新增成功');
      } else {
        await update(data);
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
