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
  import { onMounted } from 'vue';

  onMounted(async () => {
    // 默认组件位于整个页面的右下角，可以指定父级element的方式
    window.langchatChatWidget.config.element = document.getElementById('langchat-web-sdk')!;

    window.langchatChatWidget.config.apiKey = 'langchat-337a293acb60477ea5496f49f8892b14';
    window.langchatChatWidget.config.greetingMessage =
      "Hello there! Here's a super long greeting.\n\nJust to see how you'd handle multiple lines and linebreaks.";
    window.langchatChatWidget.config.url = 'http://127.0.0.1:8100/v1/chat/completions/channel/web';
    await window.langchatChatWidget.init();
  });
</script>

<template>
  <div class="h-full overflow-auto rounded w-full relative">
    <div id="langchat-web-sdk"></div>
  </div>
</template>

<style lang="less"></style>
