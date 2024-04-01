<script setup lang="ts">
  import Sider from './Sider.vue';
  import { onMounted, ref, watch } from 'vue';
  import { useBasicLayout } from '@/hooks/useBasicLayout';

  const { isMobile } = useBasicLayout();
  const collapsed = ref(false);
  watch(isMobile, (val) => {
    collapsed.value = val;
  });

  onMounted(() => {
    collapsed.value = isMobile.value;
  });
</script>

<template>
  <n-layout has-sider class="h-full">
    <n-layout-sider
      bordered
      collapse-mode="width"
      :collapsed-width="0"
      :width="77"
      :collapsed="collapsed"
      show-trigger="bar"
      @collapse="collapsed = true"
      @expand="collapsed = false"
    >
      <Sider />
    </n-layout-sider>
    <n-layout-content>
      <RouterView v-slot="{ Component, route }">
        <keep-alive>
          <component :is="Component" :key="route.fullPath" />
        </keep-alive>
      </RouterView>
    </n-layout-content>
  </n-layout>
</template>
