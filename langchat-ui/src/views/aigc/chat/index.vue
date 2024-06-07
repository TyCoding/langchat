<script setup lang="ts">
  import { onMounted, ref } from 'vue';
  import Chat from '@/views/aigc/chat/components/Chat.vue';
  import { getMessages } from '@/api/aigc/chat';
  import { useChatStore } from '@/views/aigc/chat/components/store/useChatStore';
  import { useUserStore } from '@/store/modules/user';
  import Header from '@/views/aigc/chat/components/Header.vue';

  const loading = ref(true);
  const chatStore = useChatStore();
  const userStore = useUserStore();

  onMounted(async () => {
    loading.value = true;
    chatStore.conversationId = userStore.info.id;
    chatStore.messages = await getMessages(userStore.info.id);
    loading.value = false;
  });
</script>

<template>
  <n-card class="p-4 pt-1 w-full h-full">
    <Header title="AI自由聊天" />
    <div style="height: calc(100vh - 180px)" class="flex flex-col w-full overflow-hidden">
      <main ref="contentRef" class="flex-1 overflow-hidden overflow-y-auto">
        <Chat />
      </main>
    </div>
  </n-card>
</template>

<style scoped lang="less">
  ::v-deep(.n-tabs.n-tabs--top .n-tab-pane) {
    padding: 0 !important;
  }
</style>
