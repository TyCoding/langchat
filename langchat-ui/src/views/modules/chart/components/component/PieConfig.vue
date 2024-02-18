<script setup lang="ts">
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
            <n-input size="small" v-model:value="option!.title.top" />
          </div>
        </n-gi>
        <n-gi>
          <div class="flex flex-row justify-center items-center">
            <div class="w-[74px] text-gray-400">底部距离</div>
            <n-input size="small" v-model:value="option!.title.bottom" />
          </div>
        </n-gi>
        <n-gi>
          <div class="flex flex-row justify-center items-center">
            <div class="w-[74px] text-gray-400">左侧距离</div>
            <n-input size="small" v-model:value="option!.title.left" />
          </div>
        </n-gi>
        <n-gi>
          <div class="flex flex-row justify-center items-center">
            <div class="w-[74px] text-gray-400">右侧距离</div>
            <n-input size="small" v-model:value="option!.title.right" />
          </div>
        </n-gi>
      </n-grid>
    </div>
  </n-collapse-item>
</template>

<style scoped lang="less"></style>
