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

<script lang="ts" setup>
  import type { CSSProperties } from 'vue';
  import { computed, watch } from 'vue';
  import { SvgIcon } from '@/components/common';
  import { NButton, NLayoutSider } from 'naive-ui';
  import { useChatStore } from '../store/useChatStore';
  import { useBasicLayout } from '../store/useBasicLayout';
  import List from './List.vue';
  import { add as addConversation } from '@/api/conversation';
  import { t } from '@/locales';

  const chatStore = useChatStore();
  const { isMobile } = useBasicLayout();

  const loading = computed(() => {
    return chatStore.sideIsLoading;
  });

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

  async function onAddConversation() {
    chatStore.sideIsLoading = true;
    await addConversation({
      title: 'New Chat' + Number(chatStore.conversations.length + 1),
    });
    await chatStore.loadData();
  }
</script>

<template>
  <NLayoutSider
    :collapsed="collapsed"
    :collapsed-width="0"
    :show-trigger="isMobile ? false : 'arrow-circle'"
    :style="getMobileClass"
    :width="260"
    bordered
    collapse-mode="transform"
    position="absolute"
    @update-collapsed="handleUpdateCollapsed"
  >
    <div :style="mobileSafeArea" class="flex flex-col h-full">
      <div v-if="loading" class="w-full h-full flex items-center justify-center">
        <n-spin :show="loading" />
      </div>
      <main v-else class="flex flex-col flex-1 min-h-0">
        <div class="p-4 pt-3 flex justify-between items-center gap-2">
          <n-button block secondary type="success" @click="onAddConversation">
            <SvgIcon icon="ic:round-plus" />
            <span>{{ t('chat.newChatButton') }}</span>
          </n-button>
        </div>
        <div class="flex-1 min-h-0 pb-6 overflow-hidden flex flex-col gap-2">
          <List />
          <div class="pb-2 px-4">
            <n-select
              v-model:value="chatStore.appId"
              :label-field="'name'"
              :options="chatStore.apps"
              :value-field="'id'"
              placeholder="请选择应用"
            />
          </div>
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
