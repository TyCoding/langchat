<script setup lang="ts">
  import { ref } from 'vue';
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
  <div class="w-full pt-3">
    <div class="flex justify-between items-center pl-10 pr-10">
      <n-button text type="primary" size="large">
        <template #icon>
          <SvgIcon icon="solar:chart-broken" />
        </template>
        可视化图表分析
      </n-button>
      <n-button text type="primary"> 使用说明 </n-button>
    </div>
    <n-divider class="!mt-2 !mb-6" />
    <div class="w-full flex justify-center items-center gap-4 flex-col pb-16">
      <div class="!w-[90%]">
        <n-tabs
          v-model:value="chartStore.step"
          type="segment"
          animated
          justify-content="space-evenly"
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
                  class="w-full flex justify-center items-center"
                  :class="chartStore.step === index ? 'text-[#70c0e8]' : ''"
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

<style scoped lang="less">
  ::v-deep(.n-tabs .n-tabs-tab .n-tabs-tab__label) {
    width: 100% !important;
  }
</style>
