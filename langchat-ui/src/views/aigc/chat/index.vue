<!--
  - Copyright (c) 2024 LangChat. TyCoding All Rights Reserved.
  -
  - Licensed under the GNU Affero General Public License, Version 3 (the "License");
  - you may not use this file except in compliance with the License.
  - You may obtain a copy of the License at
  -
  -     https://www.gnu.org/licenses/agpl-3.0.html
  -
  - Unless required by applicable law or agreed to in writing, software
  - distributed under the License is distributed on an "AS IS" BASIS,
  - WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
  - See the License for the specific language governing permissions and
  - limitations under the License.
  -->

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
