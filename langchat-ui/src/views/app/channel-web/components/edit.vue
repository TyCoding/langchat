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
  import { ref } from 'vue';
  import SvgIcon from '@/components/SvgIcon/index.vue';
  import CodePane from './CodePane.vue';
  import SettingPane from './SettingPane.vue';
  import WindowPane from './WindowPane.vue';
  import ApiTable from '@/views/app/ApiTable.vue';
  import { CHANNEL } from '@/views/app/columns';

  const emit = defineEmits(['reload']);
  const url = ref('http://backend.langchat.cn/v1/chat/completions');
</script>

<template>
  <div class="bg-white p-4 rounded">
    <div class="bg-[#eff4ff66] border mb-3 px-3 py-4 rounded-lg" size="small">
      <div class="flex items-center justify-between">
        <div class="flex items-center gap-2">
          <div
            class="flex bg-[#d1dfff] border border-[#b2ccff] rounded-lg justify-center items-center p-2.5"
          >
            <SvgIcon class="text-lg" icon="mdi:web-sync" />
          </div>

          <div class="flex flex-col justify-between items-start">
            <div class="!font-medium">客服机器人（Web接入渠道）</div>
            <div class="text-gray-400 text-xs">快速接入任意第三方应平台</div>
          </div>
        </div>
      </div>

      <div class="flex gap-1 flex-col justify-start my-5">
        <div class="text-xs text-gray-600">公开访问 URL</div>
        <n-input v-model:value="url">
          <template #suffix>
            <n-button text>
              <SvgIcon icon="mingcute:copy-3-fill" />
            </n-button>
          </template>
        </n-input>
      </div>

      <div class="flex gap-2">
        <SettingPane />

        <WindowPane />

        <CodePane />
      </div>
    </div>

    <ApiTable :channel="CHANNEL.WEB" />
  </div>
</template>

<style lang="less" scoped></style>
