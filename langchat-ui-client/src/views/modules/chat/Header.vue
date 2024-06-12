<script lang="ts" setup>
  import { SvgIcon } from '@/components/common';
  import { useBasicLayout } from '@/hooks/useBasicLayout';
  import { useChatStore } from '@/views/modules/chat/store/useChatStore';
  import { useDialog, useMessage } from 'naive-ui';
  import { t } from '@/locales';
  import { clearMessage } from '@/api/conversation';
  import html2canvas from 'html2canvas';
  // @ts-ignore
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
    class="sticky z-30 border-b dark:border-neutral-800 bg-white/80 dark:bg-black/20 backdrop-blur"
    :class="isMobile ? 'px-1' : 'px-6'"
  >
    <div
      class="relative flex items-center justify-between min-w-0 overflow-hidden h-14 ml-2 mr-2 gap-2"
    >
      <div class="flex items-center gap-2">
        <n-button v-if="isMobile" text @click="chatStore.setSiderCollapsed(false)">
          <SvgIcon class="text-2xl" icon="solar:list-bold-duotone" />
        </n-button>
        <n-select
          size="small"
          v-model:value="chatStore.model"
          :options="modelList"
          class="tracking-widest"
          :class="isMobile ? 'w-auto' : '!w-[200px]'"
        />
        <n-tag
          checkable
          v-model:checked="chatStore.isGoogleSearch"
          :bordered="false"
          type="primary"
          class="border"
        >
          <div class="text-sm flex items-center gap-1">
            <SvgIcon icon="devicon:google" />
            <div>Google Search</div>
          </div>
        </n-tag>
      </div>

      <div class="flex items-center space-x-2 overflow-x-auto">
        <n-button type="warning" tertiary size="small" @click="onClear">
          <SvgIcon class="text-lg" icon="fluent:delete-28-regular" />
          <span>{{ t('chat.clearChat') }}</span>
        </n-button>

        <n-button type="info" tertiary size="small" @click="handleExport">
          <SvgIcon class="text-xl" icon="material-symbols:download" />
          <span>{{ t('chat.exportImage') }}</span>
        </n-button>
      </div>
    </div>
  </header>
</template>

<style scoped lang="less"></style>
