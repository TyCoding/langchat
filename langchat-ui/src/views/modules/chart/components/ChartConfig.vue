<script setup lang="ts">
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
  const option = ref();

  watch(
    () => chartStore.data,
    (val: Object) => {
      console.log(val);
      option.value = val;
    }
  );

  function onUpdate(val: string) {
    option.value = JSON.parse(val);
  }
</script>

<template>
  <div class="w-full h-full p-1 flex flex-row gap-2">
    <div class="w-9/12 flex justify-center items-center" style="height: calc(100vh - 200px)">
      <div class="h-[85%] w-[80%] rounded-md p-6 pt-4">
        <VChart :option="option" autoresize />
      </div>
    </div>
    <n-scrollbar class="!w-3/12" style="height: calc(100vh - 200px)">
      <div class="flex flex-col gap-2 p-2" v-if="option">
        <n-collapse>
          <BaseConfig :option="option" />
          <LineConfig v-if="chartStore.key.startsWith('line-')" :option="option" />
          <PieConfig v-if="chartStore.key.startsWith('pie-')" :option="option" />
          <CodeConfig :option="option" @update="onUpdate" />
        </n-collapse>
      </div>
    </n-scrollbar>
  </div>
</template>

<style scoped lang="less">
  ::v-deep(.n-collapse .n-collapse-item:not(:first-child)) {
    border: none !important;
    margin: 6px 0 0 0;
  }
</style>
