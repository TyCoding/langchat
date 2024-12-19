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
  import Sider from './components/Sider.vue';
  import MindMap from './components/MindMap.vue';
  import { ref } from 'vue';
  import { useMessage } from 'naive-ui';
  import { genMindMap } from '@/api/aigc/chat';
  import { isNullOrWhitespace } from '@/utils/is';
  import { useChatStore } from '@/views/chat/store/useChatStore';

  const ms = useMessage();
  const loading = ref(false);
  const chatStore = useChatStore();
  const genText = ref('');

  async function onGenerate(text: string) {
    if (isNullOrWhitespace(text)) {
      ms.warning('内容为空');
      return;
    }
    loading.value = true;
    try {
      const { message } = await genMindMap({
        message: text,
        modelId: chatStore.modelId,
        modelName: chatStore.modelName,
        modelProvider: chatStore.modelProvider,
      });
      genText.value = message;
    } finally {
      loading.value = false;
    }
  }

  function onRender(text: string) {
    genText.value = text;
  }
</script>

<template>
  <div class="w-full flex h-full">
    <Sider :genText="genText" :loading="loading" @generate="onGenerate" @render="onRender" />

    <MindMap :genText="genText" :loading="loading" />
  </div>
</template>

<style lang="less" scoped></style>
