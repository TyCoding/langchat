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
  import { ref } from 'vue';
  import DALL from './component/DALL.vue';
  import { downloadByUrl } from '@/utils/downloadFile';
  import { t } from '@/locales';

  const image = ref<string>('');
  function onOk(data: any) {
    if (data.url !== undefined) {
      image.value = data.url;
    }
  }

  function onDownload() {
    downloadByUrl({ url: image.value });
  }
</script>

<template>
  <n-layout class="h-full w-full" has-sider>
    <n-layout-sider
      :collapsed-width="0"
      :width="500"
      bordered
      collapse-mode="width"
      show-trigger="arrow-circle"
    >
      <div class="flex justify-center items-center m-4 rounded">
        <n-tabs type="segment">
          <n-tab-pane display-directive="show" name="chap1" tab="OpenAI DALL·E">
            <DALL @ok="onOk" />
          </n-tab-pane>
          <n-tab-pane display-directive="show" name="chap2" tab="Mj & More...">
            <n-empty description="更多文生图模型后续即将支持..." />
          </n-tab-pane>
        </n-tabs>
      </div>
    </n-layout-sider>

    <div class="flex justify-center items-center w-full mt-4 dot-bg">
      <div class="p-8 w-full h-full mb-14">
        <div class="mb-2 flex flex-wrap justify-between items-center">
          <div class="font-bold flex justify-center items-center flex-wrap gap-2">
            <SvgIcon class="text-lg" icon="ion:sparkles-outline" />
            <span>图片预览</span>
          </div>
          <div>
            <n-button secondary size="small" type="success" @click="onDownload">
              <template #icon>
                <SvgIcon class="text-[14px]" icon="ion:sparkles-outline" />
              </template>
              下载图片
            </n-button>
          </div>
        </div>
        <div class="w-full h-full rounded-md p-2 flex items-center justify-center">
          <div
            v-if="image"
            class="flex justify-center items-center"
            style="height: calc(100vh - 20%)"
          >
            <img :src="image" class="max-w-full max-h-full" />
          </div>
          <div v-else class="h-full w-full flex flex-col justify-center items-center gap-3">
            <SvgIcon class="text-6xl" icon="ri:mind-map" />
            <div class="text-2xl font-bold">{{ t('image.title') }}</div>
            <div class="text-gray-400">{{ t('image.titleDes') }}</div>
          </div>
        </div>
      </div>
    </div>
  </n-layout>
</template>

<style lang="less" scoped></style>
