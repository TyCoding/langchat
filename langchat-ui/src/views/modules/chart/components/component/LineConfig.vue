<script setup lang="ts">
  const props = defineProps<{
    option: any;
  }>();
</script>

<template>
  <n-collapse-item title="x轴组件" v-if="option.xAxis !== undefined">
    <template #header-extra>
      <n-switch size="small" @click.stop v-model:value="option!.xAxis.show" :default-value="true" />
    </template>
    <div class="pl-[22px] flex gap-2 flex-col">
      <div class="flex flex-row justify-center items-center">
        <div class="w-10 text-gray-400">名称</div>
        <n-input size="small" v-model:value="option!.xAxis.name" />
      </div>
      <div class="flex flex-row justify-center items-center">
        <div class="w-10 text-gray-400">位置</div>
        <n-input size="small" v-model:value="option!.title.left" />
      </div>
    </div>
  </n-collapse-item>

  <n-collapse-item title="y轴组件" v-if="option.yAxis !== undefined">
    <template #header-extra>
      <n-switch size="small" @click.stop v-model:value="option!.yAxis.show" :default-value="true" />
    </template>
    <div class="pl-[22px] flex gap-2 flex-col">
      <div class="flex flex-row justify-center items-center">
        <div class="w-10 text-gray-400">名称</div>
        <n-input size="small" v-model:value="option!.yAxis.name" />
      </div>
      <div class="flex flex-row justify-center items-center">
        <div class="w-10 text-gray-400">位置</div>
        <n-input size="small" v-model:value="option!.title.left" />
      </div>
    </div>
  </n-collapse-item>
</template>

<style scoped lang="less"></style>
