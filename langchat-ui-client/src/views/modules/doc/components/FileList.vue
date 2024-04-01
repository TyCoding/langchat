<script setup lang="ts">
  import { SvgIcon } from '@/components/common';
  import { UploadCustomRequestOptions, useMessage, type UploadFileInfo } from 'naive-ui';
  import { list, upload, del, update, task as getTask } from '@/api/file';
  import { onMounted, ref } from 'vue';
  import { Oss } from '@/api/models';
  import { t } from '@/locales';

  const props = defineProps<{
    file: any;
  }>();
  const emit = defineEmits(['select']);
  const message = useMessage();
  const fileList = ref<Oss[]>([]);
  const loading = ref(true);
  const isEdit = ref(0);

  onMounted(async () => {
    await fetchData();
  });

  async function fetchData() {
    loading.value = true;
    fileList.value = await list();
    loading.value = false;
  }

  function onSelect(item: Oss) {
    emit('select', item);
  }

  const onUpload = ({
    file,
    data,
    headers,
    withCredentials,
    action,
    onFinish,
    onError,
    onProgress,
  }: UploadCustomRequestOptions) => {
    upload(
      {
        file: file.file,
      },
      (progressEvent) => {
        onProgress({
          percent: Math.round((progressEvent.loaded * 100) / Number(progressEvent.total)),
        });
      }
    )
      .then((res: any) => {
        console.log(res);
        fileList.value.push(res);
        message.success(t('common.importSuccess'));
        onFinish();
        startTask();
      })
      .catch(() => {
        message.error(t('common.wrong'));
        onError();
      });
  };
  function onBeforeUpload(data: { file: UploadFileInfo }) {
    if (!data.file.file?.type.includes('pdf')) {
      message.error(t('common.onlyPdf'));
      return false;
    }
    return true;
  }

  async function onDelete(item: Oss) {
    await del(item.id);
    message.success(t('common.deleteSuccess'));
    await fetchData();
  }

  async function onUpdate(item: Oss) {
    await update(item);
    message.success(t('common.editSuccess'));
    isEdit.value = 0;
    await fetchData();
  }

  const taskCount = ref(0);
  let timerId: string | number | NodeJS.Timeout | undefined;
  let continuePolling = false;
  function startTask() {
    continuePolling = true;
    timerId = setInterval(() => {
      if (!continuePolling) {
        return;
      }
      getTask()
        .then((res: any) => {
          res == 0 ? stopTimer() : (taskCount.value = res);
        })
        .catch(() => {
          stopTimer();
        });
    }, 1000);
  }
  function stopTimer() {
    continuePolling = false;
    clearInterval(timerId);
    taskCount.value = 0;
  }
</script>

<template>
  <n-spin :show="loading" class="h-full w-full">
    <div class="p-4 flex flex-col gap-2">
      <n-upload
        :show-file-list="false"
        :custom-request="onUpload"
        @before-upload="onBeforeUpload"
        :trigger-style="{ width: '100%' }"
      >
        <n-button dashed type="success" block>{{ t('doc.upload') }}</n-button>
      </n-upload>

      <n-tag v-if="taskCount" type="warning" size="small" class="rounded-md mb-1">
        <template #icon>
          <SvgIcon class="text-lg font-bold" icon="line-md:uploading-loop" />
        </template>
        {{ t('doc.taskRun') }}: {{ taskCount }}
      </n-tag>
      <n-tag v-else type="success" size="small" class="rounded-md mb-1">
        <template #icon>
          <SvgIcon class="font-bold" icon="icon-park-outline:success" />
        </template>
        {{ t('doc.taskOk') }}
      </n-tag>
      <div
        v-for="item in fileList"
        @click="onSelect(item)"
        :key="item"
        class="flex p-3 pb-2 flex-row items-start justify-start rounded-md gap-2 cursor-pointer card-hover h-full"
        :class="file.id == item.id ? 'card-active' : ''"
      >
        <n-icon size="30" color="#d03050">
          <SvgIcon icon="ant-design:file-pdf-filled" />
        </n-icon>
        <div class="flex flex-col justify-between items-start gap-2 h-full w-full !mt-[-5px]">
          <n-input v-if="isEdit == item.id" size="tiny" @click.stop v-model:value="item.fileName">
            <template #suffix>
              <n-button @click="onUpdate(item)" text><SvgIcon icon="mynaui:save" /></n-button>
            </template>
          </n-input>
          <div v-else class="text-[15px]">
            <n-ellipsis style="max-width: 180px"> {{ item.fileName }}.{{ item.type }} </n-ellipsis>
          </div>

          <div class="flex flex-row justify-between items-center w-full">
            <div class="text-gray-400 text-xs">{{ item.createTime }}</div>
            <div class="flex justify-center items-center gap-1" v-if="file.id == item.id">
              <n-button @click.stop="isEdit = item.id" text>
                <SvgIcon icon="tabler:edit" class="text-gray-600" />
              </n-button>
              <n-popconfirm @positive-click.stop="onDelete(item)">
                <template #trigger>
                  <n-button text>
                    <SvgIcon icon="fluent:delete-28-regular" class="text-gray-600" />
                  </n-button>
                </template>
                {{ t('doc.delConfirm') }}
              </n-popconfirm>
            </div>
          </div>
        </div>
      </div>
    </div>
  </n-spin>
</template>

<style scoped lang="less">
  .card-hover {
    border: 1px solid rgb(224, 224, 230);
    transition: border-color 0.2s ease-in-out;
  }
  .card-hover:hover {
    border-color: #18a058;
  }
  .card-active {
    border-color: #18a058;
    color: #18a058 !important;
  }
</style>
