<script lang="ts" setup>
  import { SvgIcon } from '@/components/common';
  import { useBasicLayout } from '@/hooks/useBasicLayout';
  import { useChatStore } from '@/views/modules/chat/store/useChatStore';
  import { onMounted, ref } from 'vue';
  import { useDialog, useMessage } from 'naive-ui';
  import { t } from '@/locales';
  import { clearMessage } from '@/api/conversation';
  import html2canvas from 'html2canvas';
  import { modelList } from '@/api/models/index.d.ts';

  const { isMobile } = useBasicLayout();
  const chatStore = useChatStore();
  const dialog = useDialog();
  const ms = useMessage();

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
    class="sticky px-6 z-30 border-b dark:border-neutral-800 bg-white/80 dark:bg-black/20 backdrop-blur"
  >
    <div class="relative flex items-center justify-between min-w-0 overflow-hidden h-14 ml-2 mr-2">
      <div class="flex items-center gap-2">
        <n-button v-if="isMobile" text @click="chatStore.setSiderCollapsed(false)">
          <SvgIcon class="text-2xl" icon="solar:list-bold-duotone" />
        </n-button>
        <n-select
          size="small"
          v-model:value="chatStore.model"
          :options="modelList"
          class="!w-[200px] tracking-widest"
        />
      </div>

      <div class="flex items-center space-x-2">
        <n-button type="warning" tertiary round @click="onClear">
          <SvgIcon class="text-lg" icon="fluent:delete-28-regular" />
          <span>{{ t('chat.clearChat') }}</span>
        </n-button>

        <n-button type="info" tertiary round @click="handleExport">
          <SvgIcon class="text-xl" icon="material-symbols:download" />
          <span>{{ t('chat.exportImage') }}</span>
        </n-button>
      </div>
    </div>
  </header>
</template>

<style scoped lang="less"></style>
