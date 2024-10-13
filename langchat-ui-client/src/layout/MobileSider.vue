<script lang="ts" setup>
  import { SvgIcon } from '@/components/common';
  import { routesConst } from '@/router';
  import { useRouter } from 'vue-router';

  const router = useRouter();
</script>

<template>
  <div
    class="px-1 py-2 mb-0 bg-[#fafafa] fixed bottom-0 z-10 overflow-x-auto w-full flex items-center justify-center"
  >
    <n-button
      v-for="item in routesConst"
      :key="item.name"
      :class="
        router.currentRoute.value.name == item.name
          ? '!text-[#0052CC] w-full !bg-[#eff0f0] dark:!bg-[#ffffff1a] dark:border-[#ffffff17] !rounded-[8px]'
          : 'w-full'
      "
      text
      @click="router.push({ name: item.name })"
    >
      <div class="w-full py-1 flex items-center cursor-pointer flex-col gap-1.5 px-3">
        <SvgIcon :icon="item.meta?.icon" class="text-lg" />
        <div class="font-medium text-[11px]"> {{ item.meta?.label }} </div>
      </div>
    </n-button>
  </div>
</template>

<style lang="less" scoped></style>
