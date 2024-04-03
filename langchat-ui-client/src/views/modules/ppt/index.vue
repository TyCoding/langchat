<script setup lang="ts">
  import { onMounted, ref } from 'vue';
  import { SvgIcon } from '@/components/common';
  import { usePptStore } from '@/views/modules/ppt/store';
  import ChartSelect from '@/views/modules/chart/components/ChartSelect.vue';
  import ChartData from '@/views/modules/chart/components/ChartData.vue';
  import ChartConfig from '@/views/modules/chart/components/ChartConfig.vue';

  const pptStore = usePptStore();
  const steps = [
    { key: '选择PPT模版', component: ChartSelect },
    { key: '输入Prompt', component: ChartData },
    { key: '生成PPT', component: ChartConfig },
  ];

  onMounted(() => {});
</script>

<template>
  <div class="w-full pt-3">
    <div class="flex justify-between items-center pl-10 pr-10">
      <n-button text type="primary" size="large">
        <template #icon>
          <SvgIcon icon="mingcute:ppt-fill" />
        </template>
        AI生成PPT
      </n-button>
      <n-button text type="primary"> 使用说明 </n-button>
    </div>
    <n-divider class="!mt-2 !mb-6" />
    <div class="w-full flex justify-center items-center gap-4 flex-col pb-16">
      <div class="!w-[90%]">
        <n-tabs
          v-model:value="pptStore.step"
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
                :class="pptStore.step < index ? 'text-gray-400' : ''"
                class="flex justify-center items-center w-full pr-2"
              >
                <div
                  class="w-full flex justify-center items-center"
                  :class="pptStore.step === index ? 'text-[#70c0e8]' : ''"
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

  <div class="h-full w-full p-2">
    <div id="pptxRef" ref="pptxRef">
      <h1>H1</h1>
      <h2>H2</h2>
      <p>事实上司收拾收拾</p>
    </div>
    <iframe
      width="100%"
      height="100%"
      src="https://view.officeapps.live.com/op/embed.aspx?src=https://djgurnpwsdoqjscwqbsj.supabase.co/storage/v1/object/public/presentations/3d68fd2ac262bdbd.pptx"
    ></iframe>
  </div>
</template>

<style scoped lang="less">
  ::v-deep(.n-tabs .n-tabs-tab .n-tabs-tab__label) {
    width: 100% !important;
  }
</style>
