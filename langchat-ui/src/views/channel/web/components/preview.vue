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

  onMounted(() => {
    window.langchatChatWidget.config.widgetTitle = 'LangChat';
    window.langchatChatWidget.config.apiKey = 'langchat-20c32a09b95c44c4a16301da70860d57';
    window.langchatChatWidget.config.greetingMessage =
      "Hello there! Here's a super long greeting.\n\nJust to see how you'd handle multiple lines and linebreaks.";
    window.langchatChatWidget.config.url = 'http://127.0.0.1:8100/v1/chat/completions/channel/web';
    window.langchatChatWidget.config.openOnLoad = true;
    window.langchatChatWidget.config.responseIsAStream = true;
    window.langchatChatWidget.config.closeOnOutsideClick = false;
    window.langchatChatWidget.init();
  });

  onUnmounted(() => {
    window.langchatChatWidget.close();
  });
</script>

<template>
  <div class="h-full overflow-auto rounded w-full relative">
    <div style="position: absolute; bottom: 8%; left: 8%">
      <div data-langchat-chat-widget-button style="cursor: pointer; margin-top: 10px">
        <svg height="40" viewBox="0 0 20 20" width="40" xmlns="http://www.w3.org/2000/svg">
          <path
            d="M14.479 8c.179 0 .356.034.521.099V4.5A1.5 1.5 0 0 0 13.5 3h-3v-.5A.48.48 0 0 0 10 2c-.276 0-.5.23-.5.5V3h-3A1.5 1.5 0 0 0 5 4.5v4A1.5 1.5 0 0 0 6.5 10h6.294l.338-1.039l.004-.012A1.42 1.42 0 0 1 14.48 8M7 6.5a1 1 0 1 1 2 0a1 1 0 0 1-2 0m4 0a1 1 0 1 1 2 0a1 1 0 0 1-2 0M10 18c1.743 0 3.042-.226 4.005-.612a1.3 1.3 0 0 1 .117-.44a1.42 1.42 0 0 1-.964-.897l-.004-.012l-.351-1.08l-.035-.09a1.2 1.2 0 0 0-.257-.383l-.064-.06a1.2 1.2 0 0 0-.406-.232l-1.08-.35l-.011-.005a1.423 1.423 0 0 1-.545-2.336H5.309a1.81 1.81 0 0 0-1.809 1.81v.689h.005c.034.78.248 1.757 1.123 2.555C5.584 17.43 7.234 18 10 18m5.226-7.647l-.348-1.07a.423.423 0 0 0-.799 0l-.348 1.07a2.2 2.2 0 0 1-1.377 1.397l-1.071.348a.423.423 0 0 0 0 .798l1.071.348a2.2 2.2 0 0 1 1.399 1.403l.348 1.07a.423.423 0 0 0 .798 0l.349-1.07a2.2 2.2 0 0 1 1.398-1.397l1.072-.348a.424.424 0 0 0 0-.798l-.022-.005l-1.072-.349a2.2 2.2 0 0 1-1.398-1.397m3.791 6.612l.766.248l.015.004a.303.303 0 0 1 .147.46a.3.3 0 0 1-.147.11l-.765.248a1.58 1.58 0 0 0-1 .999l-.248.764a.302.302 0 0 1-.57 0l-.249-.764a1.58 1.58 0 0 0-.999-1.002l-.765-.249a.303.303 0 0 1-.146-.46a.3.3 0 0 1 .146-.11l.765-.248a1.58 1.58 0 0 0 .984-.998l.249-.765a.302.302 0 0 1 .57 0l.249.765a1.58 1.58 0 0 0 .999.998"
            fill="currentColor"
          />
        </svg>
      </div>
    </div>
  </div>
</template>

<style lang="less" scoped></style>
