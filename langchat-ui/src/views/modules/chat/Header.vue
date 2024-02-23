<script setup lang="ts">
  import { SvgIcon } from '@/components/common';
  import { useBasicLayout } from '@/hooks/useBasicLayout';
  import { useChatStore } from '@/views/modules/chat/store/useChatStore';
  import { findModelLabel, ModelOptions } from '@/api/chat';
  import { computed } from 'vue';
  import { useDialog, useMessage } from 'naive-ui';
  import { t } from '@/locales';
  import { clearMessage } from '@/api/conversation';
  import html2canvas from 'html2canvas';

  const { isMobile } = useBasicLayout();
  const chatStore = useChatStore();
  const dialog = useDialog();
  const ms = useMessage();

  const chatModel = computed(() => {
    return findModelLabel(chatStore.model);
  });

  function onClear() {
    dialog.warning({
      title: t('chat.clearChat'),
      content: t('chat.clearChatConfirm'),
      positiveText: t('common.yes'),
      negativeText: t('common.no'),
      onPositiveClick: async () => {
        await clearMessage(chatStore.curConversation?.id);
      },
    });
  }

  function handleExport() {
    const d = dialog.warning({
      title: t('chat.exportImage'),
      content: t('chat.exportImageConfirm'),
      positiveText: t('common.yes'),
      negativeText: t('common.no'),
      onPositiveClick: async () => {
        try {
          d.loading = true;
          const ele = document.getElementById('image-wrapper');
          const canvas = await html2canvas(ele as HTMLDivElement, {
            useCORS: true,
            height: ele?.scrollHeight,
            windowHeight: ele?.scrollHeight,
          });
          const imgUrl = canvas.toDataURL('image/png');
          const tempLink = document.createElement('a');
          tempLink.style.display = 'none';
          tempLink.href = imgUrl;
          tempLink.setAttribute('download', 'chat-shot.png');
          if (typeof tempLink.download === 'undefined') tempLink.setAttribute('target', '_blank');

          document.body.appendChild(tempLink);
          tempLink.click();
          document.body.removeChild(tempLink);
          window.URL.revokeObjectURL(imgUrl);
          d.loading = false;
          ms.success(t('chat.exportSuccess'));
          Promise.resolve();
        } catch (error: any) {
          ms.error(t('chat.exportFailed'));
        } finally {
          d.loading = false;
        }
      },
    });
  }
</script>

<template>
  <header
    class="sticky pl-4 pr-6 z-30 border-b dark:border-neutral-800 bg-white/80 dark:bg-black/20 backdrop-blur"
  >
    <div class="relative flex items-center justify-between min-w-0 overflow-hidden h-14 ml-2 mr-2">
      <div class="flex items-center">
        <n-button v-if="isMobile" @click="chatStore.setSiderCollapsed(false)" text>
          <SvgIcon class="text-2xl" icon="solar:list-bold-duotone" />
        </n-button>

        <n-cascader
          v-model:value="chatStore.model"
          class="bold-cascader"
          expand-trigger="hover"
          :options="ModelOptions"
          check-strategy="child"
          :show-path="true"
        />
      </div>

      <div class="flex items-center space-x-2">
        <n-popover trigger="hover">
          <template #trigger>
            <n-button @click="onClear" text>
              <SvgIcon class="text-lg" icon="fluent:delete-28-regular" />
            </n-button>
          </template>
          <span>{{ t('chat.clearChat') }}</span>
        </n-popover>

        <n-popover trigger="hover">
          <template #trigger>
            <n-button @click="handleExport" text>
              <SvgIcon class="text-xl" icon="material-symbols:download" />
            </n-button>
          </template>
          <span>{{ t('chat.exportImage') }}</span>
        </n-popover>
      </div>
    </div>
  </header>
</template>

<style scoped lang="less"></style>
