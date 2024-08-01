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
  const props = defineProps<{
    option: any;
  }>();
</script>

<template>
  <n-collapse-item v-if="option.xAxis !== undefined" title="x轴组件">
    <template #header-extra>
      <n-switch v-model:value="option!.xAxis.show" :default-value="true" size="small" @click.stop />
    </template>
    <div class="pl-[22px] flex gap-2 flex-col">
      <div class="flex flex-row justify-center items-center">
        <div class="w-10 text-gray-400">名称</div>
        <n-input v-model:value="option!.xAxis.name" size="small" />
      </div>
      <div class="flex flex-row justify-center items-center">
        <div class="w-10 text-gray-400">位置</div>
        <n-input v-model:value="option!.title.left" size="small" />
      </div>
    </div>
  </n-collapse-item>

  <n-collapse-item v-if="option.yAxis !== undefined" title="y轴组件">
    <template #header-extra>
      <n-switch v-model:value="option!.yAxis.show" :default-value="true" size="small" @click.stop />
    </template>
    <div class="pl-[22px] flex gap-2 flex-col">
      <div class="flex flex-row justify-center items-center">
        <div class="w-10 text-gray-400">名称</div>
        <n-input v-model:value="option!.yAxis.name" size="small" />
      </div>
      <div class="flex flex-row justify-center items-center">
        <div class="w-10 text-gray-400">位置</div>
        <n-input v-model:value="option!.title.left" size="small" />
      </div>
    </div>
  </n-collapse-item>
</template>

<style lang="less" scoped></style>
