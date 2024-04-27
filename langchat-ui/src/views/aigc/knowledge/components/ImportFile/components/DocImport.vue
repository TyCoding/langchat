<script lang="ts" setup>
  import { DownloadOutline } from '@vicons/ionicons5';
  import { useRouter } from 'vue-router';
  import { UploadCustomRequestOptions, useMessage } from 'naive-ui';
  import { embeddingDocs } from '@/api/aigc/embedding';
  import { ref } from 'vue';

  const router = useRouter();
  const message = useMessage();
  const fileList = ref<any[]>([]);

  const handleImport = ({ file, onFinish, onError, onProgress }: UploadCustomRequestOptions) => {
    const kbId = router.currentRoute.value.params.id;
    embeddingDocs(
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
  <n-space vertical>
    <n-upload
      :custom-request="handleImport"
      directory-dnd
      accept=".doc,.docx,application/msword,application/vnd.openxmlformats-officedocument.wordprocessingml.document,.pdf"
    >
      <n-upload-dragger>
        <div style="margin-bottom: 12px">
          <n-icon :depth="3" size="48">
            <DownloadOutline />
          </n-icon>
        </div>
        <n-text style="font-size: 16px"> 点击或者拖动文件到该区域来上传</n-text>
        <n-p depth="3" style="margin: 8px 0 0 0">
          请上传文档文本类型的文件，文本类型文件将被单独处理和向量化，支持的文件格式有：.txt、 .md、
          .docx、 .doc、.pdf
        </n-p>
      </n-upload-dragger>
    </n-upload>
  </n-space>
</template>

<style lang="less" scoped></style>
