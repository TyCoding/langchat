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
  import ChartSelect from './components/ChartSelect.vue';
  import ChartData from './components/ChartData.vue';
  import ChartConfig from './components/ChartConfig.vue';
  import ChartExport from './components/ChartExport.vue';
  import { useChartStore } from '@/views/modules/chart/store';

  const chartStore = useChartStore();
  const steps = [
    { key: '选择图表', component: ChartSelect },
    { key: '导入数据', component: ChartData },
    { key: '配置图表', component: ChartConfig },
    { key: '导出图表', component: ChartExport },
  ];
</script>

<template>
  <div class="w-full pt-3 dot-bg">
    <div class="flex justify-between items-center pl-10 pr-10">
      <n-button size="large" text type="primary">
        <template #icon>
          <SvgIcon icon="solar:chart-broken" />
        </template>
        <div class="flex gap-1 items-center">
          <span>可视化图表分析</span>
          <n-tag size="small" type="warning">本功能正在开发中...</n-tag>
        </div>
      </n-button>
    </div>
    <n-divider class="!mt-2 !mb-6" />
    <div class="w-full flex justify-center items-center gap-4 flex-col pb-16">
      <div class="!w-[90%]">
        <n-tabs
          v-model:value="chartStore.step"
          animated
          justify-content="space-evenly"
          type="segment"
        >
          <n-tab-pane
            v-for="(item, index) in steps"
            :key="index"
            :name="index"
            display-directive="show"
          >
            <template #tab>
              <div
                :class="chartStore.step < index ? 'text-gray-400' : ''"
                class="flex justify-center items-center w-full pr-2"
              >
                <div
                  :class="chartStore.step === index ? 'text-[#70c0e8]' : ''"
                  class="w-full flex justify-center items-center"
                >
                  <div class="w-1/2 justify-center flex">{{ item.key }}</div>
                </div>
              </div>
              <SvgIcon icon="mingcute:right-fill" />
            </template>
            <component :is="item.component" />
          </n-tab-pane>
        </n-tabs>
      </div>
    </div>
  </div>
</template>

<style lang="less" scoped>
  ::v-deep(.n-tabs .n-tabs-tab .n-tabs-tab__label) {
    width: 100% !important;
  }
</style>
