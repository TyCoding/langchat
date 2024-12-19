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
  import SvgIcon from '@/components/SvgIcon/index.vue';
  import ChatProvider from './components/chat/index.vue';
  import EmbedProvider from './components/embedding/index.vue';
  import ImageProvider from './components/image/index.vue';

  const active = ref('1');
</script>

<template>
  <div class="h-full overflow-y-auto">
    <n-card :bordered="false" class="mt-2">
      <n-tabs v-model:value="active" class="flex items-center">
        <n-tab name="1">
          <SvgIcon class="text-lg" icon="lets-icons:chat" />
          <span class="pl-2 font-bold">Chat模型供应商</span>
        </n-tab>
        <n-tab name="2">
          <SvgIcon class="text-lg" icon="ph:database" />
          <span class="pl-2 font-bold">Embedding向量模型</span>
        </n-tab>
        <n-tab name="3">
          <SvgIcon class="text-lg" icon="ph:image" />
          <span class="pl-2 font-bold">Image文生图模型</span>
        </n-tab>
      </n-tabs>
      <n-divider />

      <ChatProvider v-if="active === '1'" />
      <EmbedProvider v-if="active === '2'" />
      <ImageProvider v-if="active === '3'" />
    </n-card>
  </div>
</template>

<style lang="less" scoped>
  ::v-deep(.n-menu .n-menu-item) {
    height: 40px !important;
  }
</style>
