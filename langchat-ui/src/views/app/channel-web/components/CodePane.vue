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
  import hljs from 'highlight.js';
  import SvgIcon from '@/components/SvgIcon/index.vue';
  import { ref } from 'vue';
  import { copyToClip } from '@/utils/copy';
  import { useMessage } from 'naive-ui';

  const ms = useMessage();
  const showCode = ref(false);
  const showVueCode = ref(false);

  const code = `
<link href="/langchat-web-sdk/style.css" rel="stylesheet">
<script src="/langchat-web-sdk/index.js"><\/script>
<script>
window.onload = function() {
  new LangChatBot({
    apiUrl: 'https://api.example.com/chat',
    token: 'your-token',
    layout: 'widget',
    position: 'bottom-right',
    theme: {
      primary: '#3B82F6',
      secondary: '#1D4ED8'
    }
  });
}
<\/script>
`;

  const codeVue = `
import '@/assets/langchat-web-sdk/style.css';
import LangChatBot from '@/assets/langchat-web-sdk/index';

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
  `;

  async function onCopyCode(text: any) {
    await copyToClip(text);
    ms.success('复制成功');
  }
</script>

<template>
  <div class="flex items-center gap-2 mb-3">
    <n-button @click="showCode = !showCode">
      <template #icon>
        <SvgIcon icon="ph:file-js" />
      </template>
      Vanilla JS引入
    </n-button>
    <n-button @click="showVueCode = !showVueCode">
      <template #icon>
        <SvgIcon icon="cib:vue-js" />
      </template>
      Vue引入
    </n-button>

    <n-modal v-model:show="showCode">
      <n-card :bordered="false" aria-modal="true" style="width: 70vh" title="JavaScript项目中使用">
        <template #header-extra>
          <n-button text type="info" @click="onCopyCode(code)">
            <SvgIcon class="text-xl" icon="mingcute:copy-3-fill" />
          </n-button>
        </template>
        <div class="bg-gray-200 px-4 py-2 overflow-x-auto rounded-lg">
          <n-code :code="code" :hljs="hljs" language="JavaScript" />
        </div>
      </n-card>
    </n-modal>

    <n-modal v-model:show="showVueCode">
      <n-card :bordered="false" aria-modal="true" style="width: 70vh" title="Vue项目中使用">
        <template #header-extra>
          <n-button text type="info" @click="onCopyCode(code)">
            <SvgIcon class="text-xl" icon="mingcute:copy-3-fill" />
          </n-button>
        </template>
        <div class="bg-gray-200 px-4 py-2 overflow-x-auto rounded-lg">
          <n-code :code="codeVue" :hljs="hljs" language="JavaScript" />
        </div>
      </n-card>
    </n-modal>
  </div>
</template>

<style lang="less" scoped></style>
