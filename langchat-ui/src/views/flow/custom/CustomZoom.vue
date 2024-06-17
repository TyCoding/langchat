<script setup lang="ts">
  import { FullscreenOutlined, ZoomInOutlined, ZoomOutOutlined } from '@vicons/antd';
  import { HammerOutline, OptionsOutline } from '@vicons/ionicons5';
  import { ref } from 'vue';
  import { useVueFlow } from '@vue-flow/core';
  import { publish as flowPublish, update as flowUpdate } from '@/api/aigc/flow';
  import { renderPropsIcon, renderIcon } from '@/utils';
  import { useFlowStore } from '@/views/flow/store';
  import { getDatas } from '@/views/flow/store/get';
  import { useRouter } from 'vue-router';

  const flowStore = useFlowStore();
  const router = useRouter();
  const { zoomIn, zoomOut, fitView, onViewportChange, toObject } = useVueFlow();
  const loading = ref(false);
  const zoom = ref(50);

  onViewportChange((e) => {
    zoom.value = e.zoom < 0.5 ? 0 : e.zoom > 2 ? 100 : Math.floor(((e.zoom - 0.5) / 1.5) * 100);
  });

  async function handleSubmit() {
    loading.value = true;
    const data = {
      id: String(router.currentRoute.value.params.id),
      flow: JSON.stringify(getDatas(toObject())),
    };
    await flowUpdate(data);

    try {
      await flowPublish(data);
      window['$message'].success('发布成功');
    } finally {
      loading.value = false;
    }
  }
</script>

<template>
  <div class="mt-3 ml-2 absolute z-10">
    <n-button
      v-if="!flowStore.showCard"
      @click="flowStore.setShowCard()"
      circle
      :render-icon="renderIcon(OptionsOutline)"
    />
  </div>

  <div class="custom-zoom">
    <div class="h-10 flex justify-center items-center w-auto pl-3 pr-3 z-10 rounded-lg">
      <n-popover trigger="hover" placement="bottom" class="custom-popover">
        <template #trigger>
          <n-button
            @click="flowStore.setShowCard()"
            text
            :render-icon="renderIcon(OptionsOutline)"
            :class="flowStore.showCard ? 'text-blue-400' : ''"
          />
        </template>
        <span>显示Node Card面板</span>
      </n-popover>

      <n-divider class="bg-gray-400" vertical />
      <n-popover trigger="hover" placement="bottom" class="custom-popover">
        <template #trigger>
          <n-button @click="fitView" text :render-icon="renderIcon(FullscreenOutlined)" />
        </template>
        <span>适应屏幕</span>
      </n-popover>

      <n-popover trigger="hover" placement="bottom" class="custom-popover">
        <template #trigger>
          <n-button
            @click="zoomIn({ duration: 0.2 })"
            text
            :render-icon="renderPropsIcon(ZoomInOutlined, { size: 14 })"
          />
        </template>
        <span>放大画布</span>
      </n-popover>
      <span class="text-xs text-gray-700 text-center" style="min-width: 28px"> {{ zoom }}% </span>
      <n-popover trigger="hover" placement="bottom" class="custom-popover">
        <template #trigger>
          <n-button
            @click="zoomOut({ duration: 0.2 })"
            text
            :render-icon="renderPropsIcon(ZoomOutOutlined, { size: 14 })"
          />
        </template>
        <span>缩小画布</span>
      </n-popover>

      <n-divider class="bg-gray-400" vertical />
      <n-button
        @click="handleSubmit"
        :loading="loading"
        type="primary"
        size="small"
        secondary
        :render-icon="renderIcon(HammerOutline)"
      >
        发布流程
      </n-button>
    </div>
  </div>
</template>

<style scoped lang="less">
  .custom-zoom {
    top: 8px;
    position: absolute;
    z-index: 1000;
    right: 50%;
    transform: translate(50%);
    height: 40px;
    width: auto;
    padding-right: 4px;
    padding-left: 4px;
    align-content: center;
    display: flex;
    justify-content: center;
    align-items: center;
    border-radius: 8px;
    border: 1px solid rgba(0, 0, 0, 0.05);
    background: rgba(228, 229, 231, 0.7);
  }
</style>
