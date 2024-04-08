<script setup lang="ts">
  import { SvgIcon } from '@/components/common';
  import { onMounted, ref, watch } from 'vue';
  import { t } from '@/locales';
  import { VueMermaidRender } from 'vue-mermaid-render';
  import { downloadPdf, downloadPng, downloadSvg } from '@/utils/downloadFile';

  const props = defineProps<{
    genText: string;
  }>();
  const width = ref(80);

  watch(
    () => props.genText,
    (val) => {}
  );

  function onZoomIn() {
    width.value += 2;
    console.log(width.value);
  }
  function onZoomOut() {
    width.value -= 2;
  }
  function onZoomFill() {
    width.value = 80;
  }

  function downPng() {
    downloadPng('mermaid-view', 'mermaid-shot');
  }
  function downSvg() {
    downloadSvg('mermaid-view', 'mermaid-shot');
  }
  function downPdf() {
    downloadPdf('mermaid-view', 'mermaid-shot');
  }
</script>

<template>
  <div class="dot-bg w-full h-full" :class="genText == '' ? 'overflow-hidden' : ''">
    <div class="absolute top-0 z-10 p-2 flex flex-wrap justify-center gap-2">
      <n-button @click="onZoomIn" text>
        <SvgIcon class="text-2xl" icon="basil:zoom-in-outline" />
      </n-button>
      <n-button @click="onZoomOut" text>
        <SvgIcon class="text-2xl" icon="basil:zoom-out-outline" />
      </n-button>
      <n-button @click="onZoomFill" text>
        <SvgIcon class="text-2xl" icon="fluent:full-screen-zoom-24-filled" />
      </n-button>
      <n-button round size="small" @click="downPng">
        <template #icon>
          <SvgIcon class="text-lg" icon="material-symbols:download" />
        </template>
        PNG
      </n-button>
      <n-button round size="small" @click="downSvg">
        <template #icon>
          <SvgIcon class="text-lg" icon="material-symbols:download" />
        </template>
        SVG
      </n-button>
      <n-button round size="small" @click="downPdf">
        <template #icon>
          <SvgIcon class="text-lg" icon="material-symbols:download" />
        </template>
        PDF
      </n-button>
    </div>

    <div class="h-full w-full flex flex-col justify-center items-center gap-3" v-if="genText == ''">
      <SvgIcon class="text-6xl" icon="flowbite:chart-mixed-outline" />
      <div class="text-2xl font-bold">{{ t('mermaid.title') }}</div>
      <div class="text-gray-400">{{ t('mermaid.titleDes') }}</div>
      <n-button type="success">{{ t('mermaid.begin') }}</n-button>
    </div>

    <div class="h-full w-full flex justify-center items-center">
      <div :style="'width: ' + width + 'vh'" id="mermaid-view">
        <VueMermaidRender :content="props.genText" :config="{}" />
      </div>
    </div>
  </div>
</template>

<style lang="less">
  .mermaid {
    svg {
      max-width: 100% !important;
    }
  }
</style>
