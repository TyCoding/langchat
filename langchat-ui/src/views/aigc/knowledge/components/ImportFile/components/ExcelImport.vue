<script setup lang="ts">
  import { DownloadOutline } from '@vicons/ionicons5';
  import { UploadCustomRequestOptions, useMessage } from 'naive-ui';
  import { embeddingExcel } from '@/api/aigc/embedding';
  import { useRouter } from 'vue-router';
  import { ref } from 'vue';

  const router = useRouter();
  const message = useMessage();
  const fileList = ref<any[]>([]);

  const handleImport = ({ file, onFinish, onError, onProgress }: UploadCustomRequestOptions) => {
    const kbId = router.currentRoute.value.params.id;
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
        message.success('上传成功');
        onFinish();
      })
      .catch((err) => {
        message.error('上传失败');
        onError();
      });
  };
</script>

<template>
  <div>
    <n-upload
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
  </div>
</template>

<style scoped lang="less"></style>
