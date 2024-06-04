<script setup lang="ts">
  import { onMounted, ref } from 'vue';
  import SvgIcon from '@/components/SvgIcon/index.vue';
  import Chat from './components/Chat.vue';
  import { modelList } from '@/api/models';
  import { clean, getMessages } from '@/api/aigc/chat';
  import { useChatStore } from './components/store/useChatStore';
  import { useDialog, useMessage } from 'naive-ui';
  import { useUserStore } from '@/store/modules/user';

  const dialog = useDialog();
  const ms = useMessage();
  const loading = ref(true);
  const chatLoading = ref(false);
  const chatStore = useChatStore();
  const userStore = useUserStore();

  onMounted(async () => {
    loading.value = true;
    chatStore.conversationId = userStore.info.id;
    chatStore.messages = await getMessages(userStore.info.id);
    console.log('xxx', chatStore.conversationId);
    loading.value = false;
  });

  // 清除
  function handleClear() {
    if (loading.value || chatStore.conversationId == null) {
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
  <n-card class="p-4 pt-1 w-full h-full">
    <div class="mb-2 flex flex-wrap justify-between items-center">
      <div class="font-bold flex justify-center items-center flex-wrap gap-2">
        <SvgIcon class="text-lg" icon="ion:sparkles-outline" />
        <span>AI自由聊天助手</span>
      </div>
      <n-space align="center">
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
    <div style="height: calc(100vh - 180px)" class="flex flex-col w-full overflow-hidden">
      <main ref="contentRef" class="flex-1 overflow-hidden overflow-y-auto">
        <n-spin :show="chatLoading" class="min-h-full flex-1 py-4" description="Loading...">
          <Chat />
        </n-spin>
      </main>
    </div>
  </n-card>
</template>

<style scoped lang="less">
  ::v-deep(.n-tabs.n-tabs--top .n-tab-pane) {
    padding: 0 !important;
  }
</style>
