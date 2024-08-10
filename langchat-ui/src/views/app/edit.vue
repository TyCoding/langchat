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
  import { add, getById, update } from '@/api/app/app';
  import { UploadCustomRequestOptions, useMessage } from 'naive-ui';
  import { formSchemas } from './columns';
  import { BasicForm, useForm } from '@/components/Form';
  import { basicModal, useModal } from '@/components/Modal';
  import { isNullOrWhitespace } from '@/utils/is';
  import { uploadApi } from '@/api/aigc/oss';

  const emit = defineEmits(['reload']);
  const message = useMessage();
  const fileList = ref<any>([]);

  const [modalRegister, { openModal: openModal, closeModal: closeModal }] = useModal({
    title: '新增/编辑',
    closable: true,
    maskClosable: false,
    showCloseBtn: false,
    showSubBtn: false,
  });
  const [register, { setFieldsValue, getFieldsValue }] = useForm({
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
      const data = await getById(id);
      setFieldsValue(data);
      if (!isNullOrWhitespace(data.cover)) {
        fileList.value = [
          {
            id: '1',
            status: 'finished',
            url: data.cover,
          },
        ];
      }
    }
  }

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

  const handleImport = ({ file, onFinish, onError, onProgress }: UploadCustomRequestOptions) => {
    uploadApi(
      {
        file: file.file,
      },
      (progressEvent) => {
        onProgress({
          percent: Math.round((progressEvent.loaded * 100) / Number(progressEvent.total)),
        });
      }
    )
      .then((res) => {
        console.log(res);
        setFieldsValue({ ...getFieldsValue, cover: res.url });
        message.success('上传成功，文档解析中...');
        onFinish();
      })
      .catch((err) => {
        console.error(err);
        message.error('上传失败');
        onError();
      });
  };

  defineExpose({ show });
</script>

<template>
  <basicModal style="width: 45%" @register="modalRegister">
    <BasicForm class="mt-5" @register="register" @submit="handleSubmit">
      <template #coverSlot>
        <n-upload
          :custom-request="handleImport"
          accept=".jpg,.jpeg,.png,.gif,.bmp,.webp"
          directory-dnd
          list-type="image-card"
        />
      </template>
    </BasicForm>
  </basicModal>
</template>

<style lang="less" scoped></style>
