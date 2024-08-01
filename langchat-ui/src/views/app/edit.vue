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
  import { BasicForm, useForm } from '@/components/Form';
  import { isNullOrWhitespace } from '@/utils/is';
  import { add as addApi, update as updateApi } from '@/api/app/appApi';
  import { add as addWeb, update as updateWeb } from '@/api/app/appWeb';
  import { useMessage } from 'naive-ui';
  import { formSchemas } from './columns';

  const emit = defineEmits(['reload']);
  const isShow = ref(false);
  const info = ref();
  const message = useMessage();

  async function show(record?: any) {
    isShow.value = true;
    await nextTick();
    info.value = record;
    setFieldsValue({ ...record });
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
        if (values.channel === 'CHANNEL_API') {
          await addApi(data);
        }
        if (values.channel === 'CHANNEL_WEB') {
          await addWeb(data);
        }
        if (values.channel === 'CHANNEL_WEIXIN') {
          // await addWeb(data);
        }
        emit('reload');
        message.success('新增成功');
      } else {
        if (values.channel === 'CHANNEL_API') {
          await updateApi(data);
        }
        if (values.channel === 'CHANNEL_WEB') {
          await updateWeb(data);
        }
        if (values.channel === 'CHANNEL_WEIXIN') {
          // await updateWeb(data);
        }
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
    <n-drawer-content closable title="编辑应用">
      <BasicForm :schemas="formSchemas" class="mt-5" @register="register" @submit="onSubmit" />
    </n-drawer-content>
  </n-drawer>
</template>

<style lang="less" scoped></style>
