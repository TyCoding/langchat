<script lang="ts" setup>
  import { computed, ref } from 'vue';
  import { SvgIcon } from '@/components/common';
  import { downloadPng, downloadSvg } from '@/utils/downloadFile';

  const props = defineProps({
    theme: {
      type: String,
      default: 'light', // 默认主题
    },
  });

  const themes = {
    light: {
      '--houdini-colorA': '#89b0d9',
      '--houdini-colorB': '#ffeecb',
      '--houdini-angle': '45deg',
      '--houdini-bg': 'hsla(0, 0%, 100%, 0.9)',
      '--houdini-color': 'rgba(0,2,0,0.67)',
    },
    dark: {
      '--houdini-colorA': '#5E6A89',
      '--houdini-colorB': '#0F1328',
      '--houdini-angle': '45deg',
      '--houdini-bg': 'rgba(0,0,0,.4)',
      '--houdini-color': 'rgba(255, 255, 255, 0.8)',
    },
    trans: {
      '--houdini-colorA': '#B0BDBF',
      '--houdini-colorB': '#CDCBFF',
      '--houdini-angle': '45deg',
      '--houdini-bg': 'hsla(0,0%,100%,.5)',
      '--houdini-color': 'rgba(0,2,0,0.67)',
    },
  };

  const houdiniStyles = computed(() => {
    return themes[props.theme] || themes.light;
  });

  const qr = ref('https://langchat.cn/');
  const showQr = ref(false);

  function onDownloadImage() {
    downloadPng('card', 'card');
  }
  function onDownloadSvg() {
    downloadSvg('card', 'card');
  }
</script>

<template>
  <div :style="houdiniStyles" class="translate">
    <n-modal v-model:show="showQr" class="!rounded-xl" preset="dialog" title="更换二维码链接">
      <div class="flex justify-center items-center p-2 flex-col gap-4 pb-0">
        <n-input v-model:value="qr" placeholder="请输入链接地址" />
        <n-button block type="primary">更新链接</n-button>
      </div>
    </n-modal>

    <div class="flex items-center justify-center gap-4 mb-4">
      <n-button class="!rounded-2xl" secondary type="primary" @click="onDownloadImage">
        <template #icon>
          <SvgIcon icon="lucide:image" />
        </template>
        导出图片
      </n-button>
      <n-button class="!rounded-2xl" secondary type="primary" @click="onDownloadSvg">
        <template #icon>
          <SvgIcon icon="ph:file-svg" />
        </template>
        导出SVG
      </n-button>
    </div>
    <div id="card" class="w-[440px] flex flex-col content-mode">
      <div
        class="theme relative px-[32px] pt-[28px] pb-[40px] w-full flex-1 flex flex-col justify-between theme rounded-[25px]"
      >
        <div style="text-align: left">
          <div class="flex justify-start mb-2">
            <div class="flex text-gray-500 text-[18px]">
              <div contenteditable="true" translate="no">
                <p>TyCoding</p>
              </div>
            </div>
          </div>
          <div class="leading-[1.5] opacity-40 mb-4 mt-2" style="font-size: calc(0.875rem)">
            <div>
              <div class="bg-[transparent]">
                <div contenteditable="true" translate="no">
                  <p>2024年8月27日</p>
                </div>
              </div>
            </div>
          </div>
          <div
            class="leading-[1.4] opacity-90 font-bold mb-2 mt-2"
            style="font-size: calc(1.25rem)"
          >
            <div class="bg-[transparent]">
              <div class="" contenteditable="true" translate="no">
                <p>👋 hi 你好</p>
              </div>
            </div>
          </div>
          <div class="content" style="font-size: calc(1.1rem)">
            <div class="bg-[transparent]" data-v-0fe2eeb7="">
              <div class="outline-0" contenteditable="true" translate="no">
                <p>
                  你可以在卡片任意文字部分实时编辑文字，立即生效。
                  <br />
                  - Ctrl+B 加粗文本
                  <br />
                  - 点击更换二维码图片
                </p>
              </div>
            </div>
          </div>
        </div>
        <div>
          <div class="flex justify-between mt-6">
            <div class="flex-1">
              <div class="opacity-40" style="font-size: calc(14px)">
                <div class="bg-[transparent]">
                  <div class="theme-color" contenteditable="false" translate="no">
                    <p>LangChat</p>
                  </div>
                </div>
              </div>
            </div>
            <div class="opacity-40 leading-[1.5] min-w-max" style="font-size: calc(0.875rem)">
              字数: 80
            </div>
          </div>
          <div>
            <div class="divider bg-[rgba(153,153,153,0.1)] h-px mt-5"></div>
            <div class="flex justify-between items-center pt-4">
              <div class="flex flex-col flex-1">
                <div
                  class="leading-[1.4] font-bold opacity-90 w-full"
                  style="font-size: calc(1.2rem)"
                >
                  <div class="bg-[transparent]">
                    <div class="theme-color" contenteditable="true">
                      <p>LangChat Card</p>
                    </div>
                  </div>
                </div>
                <div class="leading-[1.4] opacity-90 mt-2 w-full" style="font-size: calc(0.875rem)">
                  <div class="bg-[transparent]">
                    <div class="" contenteditable="true" translate="no">
                      <p>扫码加我👉🏻</p>
                    </div>
                  </div>
                </div>
              </div>
              <div class="flex justify-center items-center cursor-pointer">
                <n-qr-code
                  :color="houdiniStyles['--houdini-color']"
                  :size="58"
                  :value="qr"
                  background-color="transparent"
                  padding="0"
                  @click="showQr = true"
                />
              </div>
            </div>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<style lang="less" scoped>
  .content-mode {
    padding: 30px;
    min-height: auto;
    background-image: linear-gradient(
      var(--houdini-angle),
      var(--houdini-colorA),
      var(--houdini-colorB)
    );
    transition: padding 0.5s, --houdini-angle 1s, --houdini-colorA 1s, --houdini-colorB 1s;
  }
  .theme {
    background-color: var(--houdini-bg);
    box-shadow: 0 10px 40px hsla(0, 0%, 39%, 0.4);
    color: var(--houdini-color);
    transition: all 1s;
  }
  .theme-color {
    color: var(--houdini-color);
  }
</style>
