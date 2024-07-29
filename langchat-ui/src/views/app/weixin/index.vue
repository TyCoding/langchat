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
</script>

<template>
  <div class="w-full h-full px-4 my-3 pb-8 flex flex-col">
    <div class="flex w-full items-center justify-between mb-4">
      <n-button dashed size="small" type="primary" @click="router.back()">
        <SvgIcon class="text-lg" icon="carbon:return" />
        返回列表
      </n-button>
      <n-tabs class="flex items-center">
        <n-tab name="1">
          <SvgIcon class="text-lg" icon="uil:setting" />
          <span class="pl-2">应用配置</span>
        </n-tab>
        <n-tab name="2">
          <SvgIcon class="text-lg" icon="grommet-icons:console" />
          <span class="pl-2">应用接入</span>
        </n-tab>
      </n-tabs>
      <div></div>
    </div>

    <n-card class="flex-1">11</n-card>
  </div>
</template>

<style lang="less" scoped></style>
