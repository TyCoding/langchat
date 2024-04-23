<template>
  <n-space vertical>
    <n-button type="success" @click="handleSubmit">提交到知识库学习</n-button>
    <n-upload :custom-request="handleImport" directory-dnd>
      <n-upload-dragger>
        <div style="margin-bottom: 12px">
          <n-icon :depth="3" size="48">
            <DownloadOutline />
          </n-icon>
        </div>
        <n-text style="font-size: 16px"> 点击或者拖动文件到该区域来上传</n-text>
        <n-p depth="3" style="margin: 8px 0 0 0">
          请不要上传敏感数据，比如你的银行卡号和密码，信用卡号有效期和安全码
        </n-p>
      </n-upload-dragger>
    </n-upload>
  </n-space>
</template>
<script lang="ts" setup>
  import { DownloadOutline } from '@vicons/ionicons5';
  import { useRouter } from 'vue-router';
  import { UploadCustomRequestOptions, useMessage } from 'naive-ui';
  import { upload } from '@/api/aigc/kb-file';
  import { ref } from 'vue';

  const router = useRouter();
  const message = useMessage();
  const fileList = ref<any[]>([]);

  async function handleSubmit() {
    if (fileList.value.length == 0) {
      message.success('已经提交到知识库');
      return;
    }
    console.log(fileList);
    for (const i of fileList.value) {
      // await add(i);
    }
  }

  const handleImport = ({
    file,
    data,
    headers,
    withCredentials,
    action,
    onFinish,
    onError,
    onProgress,
  }: UploadCustomRequestOptions) => {
    const kbId = router.currentRoute.value.params.id;
    upload(
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
        console.log(res);
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
<style lang="less" scoped></style>
