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
  import SvgIcon from '@/components/SvgIcon/index.vue';
  import { computed, ref } from 'vue';
  import { copyToClip } from '@/utils/copy';
  import { useMessage } from 'naive-ui';
  import linkOptionSvg from '@/assets/link-option.svg';
  import iframeOptionSvg from '@/assets/iframe-option.svg';
  import widgetOptionSvg from '@/assets/widget-option.svg';

  const ms = useMessage();
  const show = ref(false);
  const active = ref('page');

  const options = [
    { key: 'page', img: linkOptionSvg },
    { key: 'iframe', img: iframeOptionSvg },
    { key: 'widget', img: widgetOptionSvg },
  ];

  async function onCopy(text: any) {
    await copyToClip(text);
    ms.success('复制成功');
  }

  const title = computed(() => {
    if (active.value === 'page') return '将下面链接复制到浏览器打开';
    if (active.value === 'iframe') return '复制下面 iframe 加入到你的网站中';
    if (active.value === 'widget') return '将下面代码加入到你的网站中';
  });

  const code = computed(() => {
    if (active.value === 'page') {
      return `https://backend.langchat.cn/share/langchat-7e9c7d3bed0f4f02ba16cfe5e6fc1265`;
    }
    if (active.value === 'iframe') {
      return `<iframe
  src="https://backend.langchat.cn/share/langchat-7e9c7d3bed0f4f02ba16cfe5e6fc1265"
  style="width: 100%; height: 100%;"
  frameborder="0"
  allow="*"
/>`;
    }
    if (active.value === 'widget') {
      return `<link href="https://backend.langchat.cn/langchat-web-sdk/style.css" rel="stylesheet">
<script src="https://backend.langchat.cn/langchat-web-sdk/index.js"><\/script>
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
<\/script>`;
    }
  });
</script>

<template>
  <div class="flex items-center gap-2 mb-3">
    <n-button @click="show = !show">
      <template #icon>
        <SvgIcon icon="streamline:code-monitor-1" />
      </template>
      嵌入
    </n-button>

    <n-modal v-model:show="show">
      <n-card :bordered="false" aria-modal="true" style="width: 40%">
        <template #header>
          <div class="flex gap-1 items-center">
            <SvgIcon class="w-6 h-6" icon="icon-park:point" />
            <span>选择接入方式</span>
          </div>
        </template>
        <template #header-extra>
          <n-button text type="info" @click="show = false">
            <SvgIcon class="text-xl" icon="mingcute:close-line" />
          </n-button>
        </template>
        <div class="flex flex-col gap-3">
          <div class="font-semibold">选中一种方式将聊天应用嵌入到你的网站中</div>
          <div class="flex items-center gap-2">
            <div
              v-for="item in options"
              :key="item.key"
              :class="active === item.key ? 'outline outline-blue-400 rounded-lg' : ''"
              @click="active = item.key"
            >
              <img :src="item.img" alt="" class="w-full h-full object-cover" />
            </div>
          </div>
          <div class="bg-gray-50 overflow-x-auto rounded-lg">
            <div class="border-b border-gray-100">
              <div class="flex items-center px-4 py-3 justify-between">
                <div class="text-gray-700 font-semibold">{{ title }}</div>
                <n-button text @click="onCopy">
                  <SvgIcon class="text-xl" icon="mingcute:copy-line" />
                </n-button>
              </div>
            </div>

            <div class="px-4 py-3 bg-gray-100 overflow-x-auto">
              <n-code :code="code" />
            </div>
          </div>
        </div>
      </n-card>
    </n-modal>
  </div>
</template>

<style lang="less" scoped></style>
