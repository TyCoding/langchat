<script setup lang="ts">
  import { SvgIcon } from '@/components/common';
  import { Transformer } from 'markmap-lib';
  import { Markmap } from 'markmap-view';
  import { onMounted, watch } from 'vue';
  import { t } from '@/locales';
  import { downloadPdf, downloadPng, downloadSvg } from '@/utils/downloadFile';

  const props = defineProps<{
    genText: string;
  }>();

  let instance: Markmap | null = null;
  onMounted(() => {
    const el = document.getElementById('mindmap') as any;
    instance = Markmap.create(el);
  });

  watch(
    () => props.genText,
    (val) => {
      const transformer = new Transformer();
      const { root } = transformer.transform(val);
      instance?.setData(root);
      instance?.fit();
    }
  );

  function onZoomIn() {
    instance?.rescale(2);
  }
  function onZoomOut() {
    instance?.rescale(0.5);
  }
  function onZoomFill() {
    instance?.fit();
  }

  function downPng() {
    downloadPng('mindmap-view', 'mindmap-shot');
  }
  function downSvg() {
    downloadSvg('mindmap-view', 'mindmap-shot');
  }
  function downPdf() {
    downloadPdf('mindmap-view', 'mindmap-shot');
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
      <SvgIcon class="text-6xl" icon="ri:mind-map" />
      <div class="text-2xl font-bold">{{ t('mindmap.title') }}</div>
      <div class="text-gray-400">{{ t('mindmap.titleDes') }}</div>
    </div>

    <div class="h-full w-full" id="mindmap-view">
      <svg class="h-full w-full" id="mindmap" />
    </div>
  </div>
</template>

<style scoped lang="less"></style>
