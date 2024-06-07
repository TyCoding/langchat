<script setup lang="ts">
  import { modelList } from '@/api/models';
  import SvgIcon from '@/components/SvgIcon/index.vue';
  import { useChatStore } from '@/views/aigc/chat/components/store/useChatStore';
  import { useDialog, useMessage } from 'naive-ui';
  import { clean } from '@/api/aigc/chat';

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
      <n-select
        size="small"
        v-model:value="chatStore.model"
        :options="modelList"
        :consistent-menu-width="false"
        class="!w-32"
      />

      <n-button @click="handleClear" size="small" type="warning" secondary>
        <template #icon>
          <SvgIcon class="text-[14px]" icon="fluent:delete-12-regular" />
        </template>
        清空聊天
      </n-button>
    </n-space>
  </div>
</template>

<style scoped lang="less"></style>
