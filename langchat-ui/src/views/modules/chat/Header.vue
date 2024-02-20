<script setup lang="ts">
  import { SvgIcon } from '@/components/common';
  import { useBasicLayout } from '@/hooks/useBasicLayout';
  import { useChatStore } from '@/views/modules/chat/store/useChatStore';
  import { ModelList } from '@/api/chat';
  import { computed } from 'vue';

  const { isMobile } = useBasicLayout();
  const chatStore = useChatStore();

  const chatModel = computed(() => {
    return ModelList.filter((i) => i.value == chatStore.model)[0].label;
  });
</script>

<template>
  <header
    class="sticky pl-2 pr-2 z-30 border-b dark:border-neutral-800 bg-white/80 dark:bg-black/20 backdrop-blur"
  >
    <div class="relative flex items-center justify-between min-w-0 overflow-hidden h-14 ml-2 mr-2">
      <div class="flex items-center">
        <n-button v-if="isMobile" @click="chatStore.setSiderCollapsed(false)" text>
          <SvgIcon class="text-2xl" icon="solar:list-bold-duotone" />
        </n-button>

        <n-popselect v-model:value="chatStore.model" :options="ModelList" trigger="click">
          <n-button icon-placement="right">
            <span class="font-bold">{{ chatModel }}</span>
            <template #icon>
              <SvgIcon icon="mingcute:down-fill" />
            </template>
          </n-button>
        </n-popselect>
      </div>
      <div class="flex items-center space-x-2">
        <n-button text><SvgIcon class="text-lg" icon="material-symbols:download" /></n-button>
      </div>
    </div>
  </header>
</template>

<style scoped lang="less"></style>
