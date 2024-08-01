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

<script setup lang="ts">
  import { DownloadOutline } from '@vicons/ionicons5';
  import { UploadCustomRequestOptions, useMessage } from 'naive-ui';
  import { embeddingExcel } from '@/api/aigc/embedding';
  import { useRouter } from 'vue-router';
  import { ref } from 'vue';

  const router = useRouter();
  const message = useMessage();
  const fileList = ref<any[]>([]);
  const loading = ref(false);

  const handleImport = ({ file, onFinish, onError, onProgress }: UploadCustomRequestOptions) => {
    message.info('数据解析中...');
    const kbId = router.currentRoute.value.params.id;
    loading.value = true;
    embeddingExcel(
      String(kbId),
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
        fileList.value.push(res);
        message.success('数据解析成功...');
        onFinish();
      })
      .catch((err) => {
        console.error(err);
        message.error('数据解析失败...');
        onError();
      })
      .finally(() => {
        loading.value = false;
      });
  };
</script>

<template>
  <n-spin :show="loading">
    <n-upload
      :disabled="loading"
      :custom-request="handleImport"
      directory-dnd
      accept="application/vnd.openxmlformats-officedocument.spreadsheetml.sheet, application/vnd.ms-exce"
    >
      <n-upload-dragger>
        <div style="margin-bottom: 12px">
          <n-icon size="48" :depth="3">
            <DownloadOutline />
          </n-icon>
        </div>
        <n-text style="font-size: 16px"> 点击或者拖动文件到该区域来上传 </n-text>
        <n-p depth="3" style="margin: 8px 0 0 0">
          仅支持上传Excel文件，系统需要对Excel这种结构化文档数据单独处理和训练
        </n-p>
      </n-upload-dragger>
    </n-upload>
  </n-spin>
</template>

<style scoped lang="less"></style>
