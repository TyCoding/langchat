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
  import { use } from 'echarts/core';
  import { CanvasRenderer } from 'echarts/renderers';
  import { PieChart, LineChart } from 'echarts/charts';
  import {
    TitleComponent,
    TooltipComponent,
    LegendComponent,
    GridComponent,
    ToolboxComponent,
  } from 'echarts/components';
  import VChart from 'vue-echarts';
  import { ref, watch } from 'vue';
  import BaseConfig from './component/BaseConfig.vue';
  import PieConfig from './component/PieConfig.vue';
  import LineConfig from './component/LineConfig.vue';
  import CodeConfig from '@/views/modules/chart/components/component/CodeConfig.vue';
  import { useChartStore } from '@/views/modules/chart/store';

  use([
    CanvasRenderer,
    PieChart,
    LineChart,
    TitleComponent,
    GridComponent,
    TooltipComponent,
    LegendComponent,
    ToolboxComponent,
  ]);
  const chartStore = useChartStore();
  const option = ref({
    title: {
      text: 'Title',
      left: 'center',
    },
    legend: {
      orient: 'vertical',
      left: 'left',
      data: ['Direct', 'Email', 'Ad Networks', 'Video Ads', 'Search Engines'],
    },
    tooltip: {
      show: true,
    },
    series: [
      {
        name: 'Traffic Sources',
        type: 'pie',
        radius: '55%',
        center: ['50%', '60%'],
        data: [
          { value: 335, name: 'Direct' },
          { value: 310, name: 'Email' },
          { value: 234, name: 'Ad Networks' },
          { value: 135, name: 'Video Ads' },
          { value: 1548, name: 'Search Engines' },
        ],
        emphasis: {
          itemStyle: {
            shadowBlur: 10,
            shadowOffsetX: 0,
            shadowColor: 'rgba(0, 0, 0, 0.5)',
          },
        },
      },
    ],
  });

  watch(
    () => chartStore.data,
    (val: Object) => {
      console.log(val);
      // option.value = val;
    }
  );

  function onUpdate(val: string) {
    option.value = JSON.parse(val);
  }
</script>

<template>
  <div class="w-full h-full p-1 flex flex-row gap-2">
    <div
      class="w-9/12 flex justify-center items-center border rounded-xl"
      style="height: calc(100vh - 200px)"
    >
      <div class="h-[85%] w-[80%] rounded-md p-6 pt-4">
        <VChart :option="option" autoresize />
      </div>
    </div>
    <n-scrollbar class="!w-3/12" style="height: calc(100vh - 200px)">
      <div v-if="option" class="flex flex-col gap-2 p-2">
        <n-collapse expanded-names="1">
          <BaseConfig :option="option" />
          <LineConfig :option="option" />
          <PieConfig :option="option" />
          <CodeConfig :option="option" @update="onUpdate" />
        </n-collapse>
      </div>
    </n-scrollbar>
  </div>
</template>

<style lang="less" scoped>
  ::v-deep(.n-collapse .n-collapse-item:not(:first-child)) {
    border: none !important;
    margin: 6px 0 0 0;
  }
</style>
