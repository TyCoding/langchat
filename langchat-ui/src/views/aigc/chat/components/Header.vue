<script lang="ts" setup>
  import SvgIcon from '@/components/SvgIcon/index.vue';
  import { useChatStore } from '@/views/aigc/chat/components/store/useChatStore';
  import { useDialog, useMessage } from 'naive-ui';
  import { clean } from '@/api/aigc/chat';
  import ModelProvider from '@/views/aigc/common/ModelProvider.vue';

  defineProps<{
    title: string;
  }>();
  const dialog = useDialog();
  const ms = useMessage();
  const chatStore = useChatStore();

  function handleClear() {
    if (chatStore.conversationId == null) {
      return;
    }
    dialog.warning({
      title: '清除聊天',
      content: '确认清除聊天',
      positiveText: '是',
      negativeText: '否',
      onPositiveClick: async () => {
        await clean(chatStore.conversationId);
        ms.success('聊天记录清除成功');
      },
    });
  }
</script>

<template>
  <div class="mb-3 flex flex-wrap justify-between items-center">
    <div class="font-bold flex justify-center items-center flex-wrap gap-2">
      <SvgIcon class="text-lg" icon="ion:sparkles-outline" />
      <span>{{ title }}</span>
    </div>
    <n-space align="center">
      <n-tag
        v-model:checked="chatStore.isGoogleSearch"
        :bordered="false"
        checkable
        class="border"
        type="primary"
      >
        <div class="text-sm flex items-center gap-1">
          <SvgIcon icon="devicon:google" />
          <div>Google Search</div>
        </div>
      </n-tag>
      <ModelProvider />

      <n-button secondary size="small" type="warning" @click="handleClear">
        <template #icon>
          <SvgIcon class="text-[14px]" icon="fluent:delete-12-regular" />
        </template>
        清空聊天
      </n-button>
    </n-space>
  </div>
</template>

<style lang="less" scoped></style>
