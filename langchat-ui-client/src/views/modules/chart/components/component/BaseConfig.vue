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
  defineProps<{
    option: any;
  }>();
</script>

<template>
  <n-collapse-item name="1" title="标题组件">
    <template #header-extra>
      <n-switch v-model:value="option!.title.show" :default-value="true" size="small" @click.stop />
    </template>
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

  <n-collapse-item title="系列颜色">
    <div class="pl-[22px] text-xs flex gap-2 flex-row">
      <n-color-picker
        v-model:value="option!.color"
        :swatches="['#FFFFFF', '#18A058', '#2080F0', '#F0A020', 'rgba(208, 48, 80, 1)']"
        size="small"
      />
    </div>
  </n-collapse-item>
  <n-collapse-item title="图例组件">
    <template #header-extra>
      <n-switch
        v-model:value="option!.legend.show"
        :default-value="true"
        size="small"
        @click.stop
      />
    </template>
    <div class="pl-[22px] text-xs flex gap-2 flex-col">
      <div class="flex flex-row justify-center items-center">
        <div class="w-[62px] text-gray-400">位置布局</div>
        <n-select
          v-model:value="option!.legend.orient"
          :options="[
            { value: 'horizontal', label: 'horizontal' },
            { value: 'vertical', label: 'vertical' },
          ]"
          size="small"
        />
      </div>
      <n-grid :cols="2" :x-gap="6" :y-gap="8">
        <n-gi>
          <div class="flex flex-row justify-center items-center">
            <div class="w-[74px] text-gray-400">顶部距离</div>
            <n-input v-model:value="option!.legend.top" size="small" />
          </div>
        </n-gi>
        <n-gi>
          <div class="flex flex-row justify-center items-center">
            <div class="w-[74px] text-gray-400">底部距离</div>
            <n-input v-model:value="option!.legend.bottom" size="small" />
          </div>
        </n-gi>
        <n-gi>
          <div class="flex flex-row justify-center items-center">
            <div class="w-[74px] text-gray-400">左侧距离</div>
            <n-input v-model:value="option!.legend.left" size="small" />
          </div>
        </n-gi>
        <n-gi>
          <div class="flex flex-row justify-center items-center">
            <div class="w-[74px] text-gray-400">右侧距离</div>
            <n-input v-model:value="option!.legend.right" size="small" />
          </div>
        </n-gi>
      </n-grid>
    </div>
  </n-collapse-item>
  <n-collapse-item title="提示框组件">
    <template #header-extra>
      <n-switch
        v-model:value="option!.tooltip.show"
        :default-value="true"
        size="small"
        @click.stop
      />
    </template>
    <div class="pl-[22px] text-xs flex gap-2 flex-col">
      <div class="flex flex-row justify-center items-center">
        <div class="w-[62px] text-gray-400">触发类型</div>
        <n-select
          v-model:value="option!.legend.trigger"
          :options="[
            { value: 'item', label: '数据项图形触发' },
            { value: 'axis', label: '坐标轴触发' },
            { value: 'none', label: '不触发' },
          ]"
          size="small"
        />
      </div>
      <n-grid :cols="2" :x-gap="6" :y-gap="8">
        <n-gi>
          <div class="flex flex-row justify-center items-center">
            <div class="w-[74px] text-gray-400">顶部距离</div>
            <n-input v-model:value="option!.legend.top" size="small" />
          </div>
        </n-gi>
        <n-gi>
          <div class="flex flex-row justify-center items-center">
            <div class="w-[74px] text-gray-400">底部距离</div>
            <n-input v-model:value="option!.legend.bottom" size="small" />
          </div>
        </n-gi>
        <n-gi>
          <div class="flex flex-row justify-center items-center">
            <div class="w-[74px] text-gray-400">左侧距离</div>
            <n-input v-model:value="option!.legend.left" size="small" />
          </div>
        </n-gi>
        <n-gi>
          <div class="flex flex-row justify-center items-center">
            <div class="w-[74px] text-gray-400">右侧距离</div>
            <n-input v-model:value="option!.legend.right" size="small" />
          </div>
        </n-gi>
      </n-grid>
    </div>
  </n-collapse-item>
</template>

<style lang="less" scoped></style>
