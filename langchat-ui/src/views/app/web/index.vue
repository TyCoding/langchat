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
  import router from '@/router';
  import { ref } from 'vue-demi';
  import Preview from './components/preview.vue';
  import Edit from './components/edit.vue';

  const active = ref('1');
</script>

<template>
  <div class="w-full h-full px-4 my-3 pb-8 flex flex-col">
    <div class="flex w-full items-center justify-between mb-4">
      <n-button dashed size="small" type="primary" @click="router.back()">
        <SvgIcon class="text-lg" icon="carbon:return" />
        返回列表
      </n-button>
      <n-tabs v-model:value="active" class="flex items-center">
        <n-tab name="1">
          <SvgIcon class="text-lg" icon="uil:setting" />
          <span class="pl-2">应用配置</span>
        </n-tab>
      </n-tabs>
      <div></div>
    </div>

    <div class="flex-1 overflow-y-auto">
      <div class="flex gap-4 h-full w-full">
        <Preview class="w-3/5" />
        <Edit class="w-full" />
      </div>
    </div>
  </div>
</template>

<style lang="less" scoped></style>
