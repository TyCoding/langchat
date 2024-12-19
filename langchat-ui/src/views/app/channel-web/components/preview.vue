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
  import { onMounted, onUnmounted } from 'vue';
  import '@langchat/web-sdk/style.css';
  import LangChatBot from '@langchat/web-sdk';

  let langChatBotService: any;
  onMounted(async () => {
    langChatBotService = new LangChatBot({
      apiUrl: 'http://localhost:8100/v1/chat/completions',
      token: 'your-api-token',
      layout: 'widget', // or 'fullpage'
      position: 'bottom-right', // or 'bottom-left'
      theme: {
        primary: '#3B82F6',
        secondary: '#1D4ED8',
      },
    });
  });

  onUnmounted(() => {
    langChatBotService.destroy();
  });
</script>

<template>
  <div></div>
</template>

<style lang="less"></style>
