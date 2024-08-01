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
  import { SvgIcon } from '@/components/common';
  import { UploadCustomRequestOptions, useMessage } from 'naive-ui';
  import { del, list, task as getTask, update, upload } from '@/api/docs';
  import { onMounted, ref } from 'vue';
  import { t } from '@/locales';
  import { useDocStore } from '@/views/modules/doc/store';

  const emit = defineEmits(['select', 'clear']);
  const ms = useMessage();
  const fileList = ref<any[]>([]);
  const loading = ref(true);
  const isEdit = ref(0);
  const docStore = useDocStore();

  onMounted(async () => {
    await fetchData();
  });

  async function fetchData() {
    loading.value = true;
    try {
      fileList.value = await list();
    } finally {
      loading.value = false;
    }
  }

  function onSelect(item: any) {
    emit('select', item);
  }

  const onUpload = ({ file, onFinish, onError, onProgress }: UploadCustomRequestOptions) => {
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
        ms.success(t('common.importSuccess'));
        onFinish();
        fetchData();
        // startTask();
      })
      .catch(() => {
        ms.error(t('common.wrong'));
        onError();
      });
  };

  async function onDelete(item: any) {
    await del(item.id);
    ms.success(t('common.deleteSuccess'));
    await fetchData();
    emit('clear');
  }

  async function onUpdate(item: any) {
    await update(item);
    ms.success(t('common.editSuccess'));
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
        :accept="'application/pdf,.doc,.docx,application/msword,application/vnd.openxmlformats-officedocument.wordprocessingml.document'"
        :custom-request="onUpload"
        :show-file-list="false"
        :trigger-style="{ width: '100%' }"
      >
        <n-button block dashed type="success">{{ t('doc.upload') }}</n-button>
      </n-upload>

      <n-tag v-if="taskCount" class="rounded-md mb-1" size="small" type="warning">
        <template #icon>
          <SvgIcon class="text-lg font-bold" icon="line-md:uploading-loop" />
        </template>
        {{ t('doc.taskRun') }}: {{ taskCount }}
      </n-tag>
      <n-tag v-else class="rounded-md mb-1" size="small" type="success">
        <template #icon>
          <SvgIcon class="font-bold" icon="icon-park-outline:success" />
        </template>
        {{ t('doc.taskOk') }}
      </n-tag>
      <div
        v-for="item in fileList"
        :key="item"
        :class="docStore.file.id == item.id ? 'card-active' : ''"
        class="flex p-3 pb-2 flex-row items-start justify-start rounded-md gap-2 cursor-pointer card-hover h-full"
        @click="onSelect(item)"
      >
        <n-icon color="#d03050" size="30">
          <SvgIcon v-if="item.ext?.startsWith('doc')" icon="ant-design:file-word-twotone" />
          <SvgIcon v-else-if="item.ext?.startsWith('xls')" icon="ant-design:file-excel-twotone" />
          <SvgIcon v-else-if="item.ext?.startsWith('pdf')" icon="ant-design:file-pdf-filled" />
          <SvgIcon v-else icon="ant-design:file-text-twotone" />
        </n-icon>
        <div class="flex flex-col justify-between items-start gap-2 h-full w-full !mt-[-5px]">
          <n-input
            v-if="isEdit == item.id"
            v-model:value="item.originalFilename"
            size="tiny"
            @click.stop
          >
            <template #suffix>
              <n-button text @click="onUpdate(item)"><SvgIcon icon="mynaui:save" /></n-button>
            </template>
          </n-input>
          <div v-else class="text-[15px]">
            <n-ellipsis style="max-width: 180px">
              {{ item.originalFilename }}
            </n-ellipsis>
          </div>

          <div class="flex flex-row justify-between items-center w-full">
            <div class="text-gray-400 text-xs">{{ item.createTime }}</div>
            <div v-if="docStore.file.id == item.id" class="flex justify-center items-center gap-1">
              <n-button text @click.stop="isEdit = item.id">
                <SvgIcon class="text-gray-600" icon="tabler:edit" />
              </n-button>
              <n-popconfirm @positive-click.stop="onDelete(item)">
                <template #trigger>
                  <n-button text>
                    <SvgIcon class="text-gray-600" icon="fluent:delete-28-regular" />
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

<style lang="less" scoped>
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
