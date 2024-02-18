<script setup lang="ts">
  import type { CSSProperties } from 'vue';
  import { computed, watch } from 'vue';
  import { SvgIcon } from '@/components/common';
  import { NButton, NLayoutSider, useMessage } from 'naive-ui';
  import List from './List.vue';
  import { useChatStore } from '../store/useChatStore';
  import { useBasicLayout } from '../store/useBasicLayout';
  const chatStore = useChatStore();
  const { isMobile } = useBasicLayout();
  const ms = useMessage();

  const loading = computed(() => {
    return chatStore.sideIsLoading;
  });

  const appList = computed(() => {
    return chatStore.apps;
  });

  function handleSelect(key: string | number, option: any) {
    console.log(option);
    chatStore.addConversation({ title: 'New Chat', appId: option.id });
    if (isMobile.value) chatStore.setSiderCollapsed(true);
  }

  const collapsed = computed(() => chatStore.siderCollapsed);
  function handleUpdateCollapsed() {
    chatStore.setSiderCollapsed(!collapsed.value);
  }

  const getMobileClass = computed<CSSProperties>(() => {
    if (isMobile.value) {
      return {
        position: 'fixed',
        zIndex: 50,
      };
    }
    return {};
  });

  const mobileSafeArea = computed(() => {
    if (isMobile.value) {
      return {
        paddingBottom: 'env(safe-area-inset-bottom)',
      };
    }
    return {};
  });

  watch(
    isMobile,
    (val) => {
      chatStore.setSiderCollapsed(val);
    },
    {
      immediate: true,
      flush: 'post',
    }
  );
</script>

<template>
  <NLayoutSider
    :collapsed="collapsed"
    :collapsed-width="0"
    :width="260"
    :show-trigger="isMobile ? false : 'arrow-circle'"
    collapse-mode="transform"
    position="absolute"
    bordered
    :style="getMobileClass"
    @update-collapsed="handleUpdateCollapsed"
  >
    <div class="flex flex-col h-full" :style="mobileSafeArea">
      <div v-if="loading" class="w-full h-full flex items-center justify-center">
        <n-spin :show="loading" />
      </div>
      <main v-else class="flex flex-col flex-1 min-h-0">
        <div class="p-4 pt-3 flex justify-between items-center gap-2">
          <n-input size="small" placeholder="搜索">
            <template #prefix> <SvgIcon icon="carbon:search" /> </template>
          </n-input>
          <n-button size="small" type="success" secondary>
            <SvgIcon icon="ic:round-plus" />
          </n-button>
        </div>
        <div class="flex-1 min-h-0 pb-4 overflow-hidden">
          <List />
        </div>
      </main>
    </div>
  </NLayoutSider>
  <template v-if="isMobile">
    <div
      v-show="!collapsed"
      class="fixed inset-0 z-40 w-full h-full bg-black/40"
      @click="handleUpdateCollapsed"
    ></div>
  </template>
</template>
