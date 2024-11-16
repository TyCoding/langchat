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
  import { ref } from 'vue';
  import Chat from './components/Chat.vue';
  import FileList from './components/FileList.vue';
  import { t } from '@/locales';
  import FileView from './components/FileView.vue';
  import { useDocStore } from '@/views/modules/doc/store';
  import { useBasicLayout } from '@/hooks/useBasicLayout';

  const { isMobile } = useBasicLayout();
  const chatRef = ref();
  const docStore = useDocStore();

  async function onSelect(item: any) {
    await docStore.onSelect(item);
    chatRef.value.init();
  }
</script>

<template>
  <div :class="isMobile ? 'flex flex-col' : ''" class="h-full w-full flex">
    <div :class="isMobile ? '' : 'w-[30%] border-r'">
      <FileList @select="onSelect" />
    </div>

    <div v-if="!isMobile" class="w-full h-full">
      <n-split
        :default-size="isMobile ? 0 : 0.6"
        :resize-trigger-size="0.5"
        class="h-full"
        direction="horizontal"
      >
        <template v-if="!isMobile" #1>
          <div class="w-full h-full">
            <div
              v-if="docStore.file.fileName"
              class="text-gray-700 text-[17px] border-b px-2 font-bold h-12 flex justify-between items-center dark:text-white"
            >
              <div>{{ docStore.file.fileName }}.{{ docStore.file.type }}</div>
            </div>
            <n-empty
              v-if="docStore.file.url === undefined"
              :description="t('doc.previewEmpty')"
              class="h-full w-full justify-center"
            />
            <template v-else>
              <FileView :url="docStore.file.url" />
            </template>
          </div>
        </template>
        <template #2>
          <div class="w-full h-full border-l dark:border-l-[#1e1e20]">
            <Chat ref="chatRef" />
          </div>
        </template>
      </n-split>
    </div>

    <div v-else>
      <div class="w-full h-full border-t border-b py-4 mb-2">
        <div
          v-if="docStore.file.fileName"
          class="text-gray-700 text-[17px] border-b px-2 font-bold h-12 flex justify-between items-center dark:text-white"
        >
          <div>{{ docStore.file.fileName }}.{{ docStore.file.type }}</div>
        </div>
        <n-empty
          v-if="docStore.file.url === undefined"
          :description="t('doc.previewEmpty')"
          class="h-full w-full justify-center"
        />
        <template v-else>
          <FileView :url="docStore.file.url" />
        </template>
      </div>

      <div class="w-full h-full border-l dark:border-l-[#1e1e20]">
        <Chat ref="chatRef" />
      </div>
    </div>
  </div>
</template>

<style lang="less" scoped></style>
