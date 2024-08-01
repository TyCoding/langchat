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
  import { onMounted, onUnmounted, onUpdated, ref } from 'vue';
  import { SvgIcon } from '@/components/common';
  import { copyToClip } from '@/utils/copy';
  import { useMessage } from 'naive-ui';
  import { t } from '@/locales';

  interface Props {
    messages: {
      role: 'user' | 'assistant';
      error: boolean;
      message: string;
      createTime?: number;
      tokens?: number;
    }[];
  }
  const message = useMessage();
  const { messages } = defineProps<Props>();

  onMounted(() => {
    addCopyEvents();
  });
  onUpdated(() => {
    addCopyEvents();
  });
  onUnmounted(() => {
    removeCopyEvents();
  });

  const textRef = ref<HTMLElement>();
  function addCopyEvents() {
    if (textRef.value) {
      const copyBtn = textRef.value.querySelectorAll('.code-block-header__copy');
      copyBtn.forEach((btn) => {
        btn.addEventListener('click', () => {
          const code = btn.parentElement?.nextElementSibling?.textContent;
          if (code) {
            copyToClip(code).then(() => {
              btn.textContent = t('common.copySuccess');
              setTimeout(() => {
                btn.textContent = t('common.copy');
              }, 1000);
            });
          }
        });
      });
    }
  }

  function removeCopyEvents() {
    if (textRef.value) {
      const copyBtn = textRef.value.querySelectorAll('.code-block-header__copy');
      copyBtn.forEach((btn) => {
        btn.removeEventListener('click', () => {});
      });
    }
  }

  const scrollToBottom = () => {
    const middleElement = textRef.value;
    if (middleElement) {
      middleElement.scrollTop = middleElement.scrollHeight;
    }
  };

  function onCopy(content: string) {
    const parser = new DOMParser();
    const doc = parser.parseFromString(content, 'text/html');
    const text = doc.body.textContent || '';
    copyToClip(text).then(() => {
      message.success(t('common.copySuccess'));
    });
  }

  defineExpose({ scrollToBottom });
</script>

<template>
  <div ref="textRef" class="middle w-full flex-1 py-3 z-0 overflow-y-auto">
    <div v-if="messages.length == 0" class="flex-1 flex h-full justify-center">
      <div class="w-1/2 flex flex-col justify-center text-xs items-center gap-2">
        <n-icon color="#e4e4e7" size="70">
          <SvgIcon icon="et:chat" />
        </n-icon>
        <n-button secondary size="small" type="success">Chat starting</n-button>
      </div>
    </div>
    <div v-else class="flex-1 overflow-y-auto mb-1">
      <div class="h-full w-full flex flex-col space-y-3 relative px-5 pt-0">
        <template v-for="item in messages" :key="item">
          <div
            v-if="item.role === 'user'"
            class="flex justify-end p-2 pl-3 pr-3 rounded select-text self-end bg-[#d2f9d1]"
          >
            {{ item.message }}
          </div>
          <div
            v-if="item.role === 'assistant'"
            class="flex justify-start items-center rounded-md self-start min-w-[40px] min-h-[33px] bg-[#f4f6f8]"
          >
            <div v-if="item.message == ''" class="flex justify-center items-center w-[55px]">
              <span class="dot-loading"></span>
            </div>
            <div v-else class="p-2 pl-3 pr-3">
              <div v-if="item.error" class="text-red-400" v-text="item.message"></div>
              <div v-else class="markdown-body pb-2" v-html="item.message"></div>
              <div
                v-if="item.createTime !== 0"
                class="border-t border-gray-200 pt-2 text-xs text-gray-400 flex flex-row justify-between items-center min-w-[200px]"
              >
                <div class="flex items-center">
                  <n-button text type="success">
                    <SvgIcon icon="mdi:success" />
                  </n-button>
                  <span>{{ Number(Number(item.createTime) / 1000).toFixed(1) }}s</span>
                  <n-divider class="ml-1 mr-1" vertical />
                  <span>{{ item.tokens }} Token</span>
                </div>
                <div class="flex items-center">
                  <n-popover class="custom-popover">
                    <template #trigger>
                      <n-button text @click="onCopy(item.message)">
                        <SvgIcon icon="ph:copy-duotone" />
                      </n-button>
                    </template>
                    Copy
                  </n-popover>
                </div>
              </div>
            </div>
          </div>
        </template>
      </div>
    </div>
  </div>
</template>

<style lang="less" scoped>
  @keyframes fHrN_o2s8Mo6LbmdjIwZ {
    0% {
      background-color: #000;
    }

    50% {
      background-color: rgba(0, 0, 0, 0.1);
    }

    to {
      background-color: #000;
    }
  }
  .dot-loading {
    animation: fHrN_o2s8Mo6LbmdjIwZ 0.8s infinite alternate;
    animation-delay: -0.2s;
    animation-timing-function: ease;
    margin: 0 8px;
    overflow: visible !important;
    position: relative;
  }

  .dot-loading,
  .dot-loading:after,
  .dot-loading:before {
    background-color: rgba(0, 0, 0, 0.1);
    border-radius: 4px;
    color: rgba(0, 0, 0, 0.1);
    height: 4px;
    width: 4px;
  }

  .dot-loading:after,
  .dot-loading:before {
    animation: fHrN_o2s8Mo6LbmdjIwZ 0.8s infinite alternate;
    animation-timing-function: ease;
    content: '';
    display: inline-block;
    position: absolute;
    top: 0;
  }

  .dot-loading:before {
    animation-delay: -0.4s;
    left: -8px;
  }

  .dot-loading:after {
    animation-delay: 0s;
    left: 8px;
  }
</style>
