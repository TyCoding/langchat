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
  import SvgIcon from '@/components/SvgIcon/index.vue';
  import { ref } from 'vue';
  import DALL from './component/DALL.vue';
  import ZhiPu from './component/ZhiPu.vue';
  import { downloadByUrl } from '@/utils/downloadFile';

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
  <div class="flex w-full h-full">
    <div class="w-[45%] bg-white">
      <div class="flex justify-center items-center p-4 rounded">
        <n-tabs type="segment">
          <n-tab-pane display-directive="show" name="chap1" tab="DALL·E">
            <DALL @ok="onOk" />
          </n-tab-pane>
          <n-tab-pane display-directive="show" name="chap2" tab="智谱清言">
            <ZhiPu @ok="onOk" />
          </n-tab-pane>
        </n-tabs>
      </div>
    </div>

    <div class="flex justify-center items-center w-full">
      <div class="p-4 w-full h-full dot-bg flex flex-col">
        <div class="flex flex-wrap justify-between items-center">
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

        <div class="rounded-md flex items-center flex-1 justify-center">
          <div v-if="image" class="flex justify-center items-center">
            <img :src="image" class="max-w-full max-h-full" />
          </div>
          <div v-else class="h-full w-full flex flex-col justify-center items-center gap-3">
            <SvgIcon class="text-6xl" icon="ri:mind-map" />
            <div class="text-2xl font-bold">文生图</div>
            <div class="text-gray-400">在左侧输入想要生成图片的自然语言描述</div>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<style lang="less" scoped></style>
