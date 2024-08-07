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
  import PromptPage from './components/prompt/index.vue';
  import SettingsPage from './components/settings/index.vue';
  import SvgIcon from '@/components/SvgIcon/index.vue';
  import Chat from '@/views/chat/Chat.vue';
  import router from '@/router';
  import { onMounted, ref } from 'vue';
  import { getById } from '@/api/app/app';
  import { useMessage } from 'naive-ui';
  import { useAppStore } from './store';
  import ModelSelect from '@/views/channel/ModelSelect.vue';

  const appStore = useAppStore();
  const form = ref<any>({});
  const loading = ref(false);
  const ms = useMessage();

  onMounted(async () => {
    await fetchData();
  });

  async function fetchData() {
    loading.value = true;
    const id = router.currentRoute.value.params.id;
    const data = await getById(id as string);
    form.value = data;
    appStore.info = data;
    appStore.knowledgeIds = data.knowledgeIds == null ? [] : data.knowledgeIds;
    appStore.modelId = data.modelId == null ? null : data.modelId;
    appStore.knowledges = data.knowledges == null ? [] : data.knowledges;
    loading.value = false;
  }

  async function onSave(val) {
    appStore.modelId = val.id;
    await appStore.updateInfo();
    ms.success('应用配置保存成功');
  }
</script>

<template>
  <div v-if="form.name !== undefined" class="rounded bg-[#f9f9f9] w-full h-full pb-10">
    <div class="p-4 flex justify-between items-center bg-white rounded">
      <div class="flex gap-5 items-center min-w-20">
        <n-button text type="primary" @click="router.back()">
          <SvgIcon class="text-xl" icon="icon-park-outline:back" />
        </n-button>
        <div class="flex gap-2 items-center pr-4">
          <n-avatar />
          <div class="flex flex-col justify-between gap-2">
            <div class="font-bold text-lg">{{ form.name }}</div>
            <div v-if="!loading" class="text-gray-400 text-xs">自动保存：{{ form.saveTime }}</div>
            <div v-else class="flex items-center gap-1 text-gray-400 text-xs">
              <SvgIcon icon="eos-icons:bubble-loading" />保存中...
            </div>
          </div>
        </div>
      </div>
      <div class="flex gap-2 items-center">
        <ModelSelect :id="appStore.modelId" @update="onSave" />
        <n-button class="px-6 rounded-lg" type="info" @click="onSave">保存应用</n-button>
      </div>
    </div>
    <n-split
      :default-size="0.3"
      :max="0.9"
      :min="0.2"
      :resize-trigger-size="2"
      class="h-full"
      direction="horizontal"
    >
      <template #1>
        <div class="p-2 h-full">
          <PromptPage />
        </div>
      </template>
      <template #2>
        <n-split
          :default-size="0.4"
          :max="0.8"
          :min="0.2"
          :resize-trigger-size="2"
          direction="horizontal"
          style="height: 100%"
        >
          <template #1>
            <SettingsPage />
          </template>
          <template #2>
            <div class="pb-10 h-full w-full bg-white border-t">
              <Chat />
            </div>
          </template>
        </n-split>
      </template>
    </n-split>
  </div>
</template>

<style lang="less" scoped></style>
