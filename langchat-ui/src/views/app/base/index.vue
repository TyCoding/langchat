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
  import PromptPage from '@/views/app/base/prompt/index.vue';
  import SettingsPage from '@/views/app/base/settings/index.vue';
  import Chat from '@/views/chat/Chat.vue';
  import router from '@/router';
  import { onMounted, ref } from 'vue';
  import { useMessage } from 'naive-ui';
  import { useAppStore } from '../store';
  import { useChatStore } from '@/views/chat/store/useChatStore';
  import { getAppInfo } from '@/api/aigc/chat';
  import { formatToDateTime } from '@/utils/dateUtil';

  const appStore = useAppStore();
  const chatStore = useChatStore();
  const form = ref<any>({});
  const loading = ref(false);
  const ms = useMessage();

  onMounted(async () => {
    await fetchData();
  });

  async function fetchData() {
    loading.value = true;
    const id = router.currentRoute.value.params.id;
    const data = await getAppInfo({
      appId: id,
      conversationId: null,
    });
    form.value = data;
    appStore.info = data;
    appStore.knowledgeIds = data.knowledgeIds == null ? [] : data.knowledgeIds;
    appStore.modelId = data.modelId == null ? null : data.modelId;
    appStore.knowledges = data.knowledges == null ? [] : data.knowledges;
    chatStore.modelId = data.modelId == null ? null : data.modelId;
    chatStore.appId = data.id;
    // 对于应用调试页面，不存储聊天记录
    // chatStore.conversationId = data.id;
    // chatStore.messages = await getMessages(chatStore.conversationId!);
    loading.value = false;
  }

  async function onSave() {
    loading.value = true;
    form.value.saveTime = formatToDateTime(new Date());
    await appStore.updateInfo();
    ms.success('应用配置保存成功');
    loading.value = false;
  }
</script>

<template>
  <n-split
    :default-size="0.3"
    :max="0.9"
    :min="0.2"
    :resize-trigger-size="1"
    class="h-full"
    direction="horizontal"
  >
    <template #1>
      <div class="p-2 h-full m-2 bg-white rounded-lg">
        <PromptPage @update="onSave" />
      </div>
    </template>
    <template #2>
      <n-split
        :default-size="0.4"
        :max="0.8"
        :min="0.2"
        :resize-trigger-size="1"
        direction="horizontal"
        style="height: 100%"
      >
        <template #1>
          <div class="p-2 h-full m-2 bg-white rounded-lg">
            <SettingsPage @update="onSave" />
          </div>
        </template>
        <template #2>
          <div class="pb-10 h-full w-full bg-white rounded-xl m-2">
            <Chat />
          </div>
        </template>
      </n-split>
    </template>
  </n-split>
</template>

<style lang="less" scoped></style>
