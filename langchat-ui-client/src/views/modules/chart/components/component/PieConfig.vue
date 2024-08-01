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
  <n-collapse-item title="饼图配置">
    <div class="pl-[22px] text-xs flex gap-2 flex-col">
      <div class="flex flex-row justify-center items-center">
        <div class="w-[62px] text-gray-400">标题名称</div>
        <n-input v-model:value="option!.title.text" size="small" />
      </div>
      <n-grid :cols="2" :x-gap="6" :y-gap="8">
        <n-gi>
          <div class="flex flex-row justify-center items-center">
            <div class="w-[74px] text-gray-400">顶部距离</div>
            <n-input v-model:value="option!.title.top" size="small" />
          </div>
        </n-gi>
        <n-gi>
          <div class="flex flex-row justify-center items-center">
            <div class="w-[74px] text-gray-400">底部距离</div>
            <n-input v-model:value="option!.title.bottom" size="small" />
          </div>
        </n-gi>
        <n-gi>
          <div class="flex flex-row justify-center items-center">
            <div class="w-[74px] text-gray-400">左侧距离</div>
            <n-input v-model:value="option!.title.left" size="small" />
          </div>
        </n-gi>
        <n-gi>
          <div class="flex flex-row justify-center items-center">
            <div class="w-[74px] text-gray-400">右侧距离</div>
            <n-input v-model:value="option!.title.right" size="small" />
          </div>
        </n-gi>
      </n-grid>
    </div>
  </n-collapse-item>
</template>

<style lang="less" scoped></style>
