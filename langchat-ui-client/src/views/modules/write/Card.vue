<script lang="ts" setup>
  import LightCard from './cards/LightCard.vue';
  import { ref } from 'vue';

  const active = ref('light');
  const styles = [
    { name: '米白', key: 'light', icon: new URL('@/assets/icon/Grey.svg', import.meta.url).href },
    { name: '暗黑', key: 'dark', icon: new URL('@/assets/icon/Dark.svg', import.meta.url).href },
    { name: '透明', key: 'trans', icon: new URL('@/assets/icon/White.svg', import.meta.url).href },
  ];

  function onSelectStyle(style: string) {
    active.value = style;
  }
</script>

<template>
  <div
    class="p-4 h-full w-full overflow-y-auto flex justify-start items-center py-10 flex-col gap-3"
  >
    <LightCard :theme="active" />
    <div class="text-gray-400">你可以直接编辑图片文本内容哦~</div>
    <div class="w-full flex flex-col items-start px-6 gap-1">
      <div class="font-bold text-gray-700">卡片风格：</div>
      <div class="flex justify-center gap-3">
        <div
          v-for="item in styles"
          :key="item.key"
          class="flex flex-col justify-center items-center gap-1"
        >
          <div
            :class="[
              'border-2',
              'rounded-lg',
              'overflow-hidden',
              'cursor-pointer',
              'hover:border-[#0052CC]',
              active === item.key ? 'border-[#0052CC]' : '',
            ]"
            @click="onSelectStyle(item.key)"
          >
            <img :src="item.icon" width="80" />
          </div>
          <span>{{ item.name }}</span>
        </div>
      </div>
    </div>
  </div>
</template>

<style lang="less" scoped></style>
