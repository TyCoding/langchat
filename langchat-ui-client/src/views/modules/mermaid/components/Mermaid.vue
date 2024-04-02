<script setup lang="ts">
  import { SvgIcon } from '@/components/common';
  import { onMounted, ref, watch } from 'vue';
  import html2canvas from 'html2canvas';
  import { t } from '@/locales';
  import { VueMermaidRender } from 'vue-mermaid-render';

  const props = defineProps<{
    genText: string;
  }>();
  const width = ref(80);

  onMounted(() => {});

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
  async function onDownload() {
    const ele = document.getElementById('mermaid-view');
    const canvas = await html2canvas(ele as HTMLDivElement, {
      useCORS: true,
    });
    const imgUrl = canvas.toDataURL('image/png');
    const tempLink = document.createElement('a');
    tempLink.style.display = 'none';
    tempLink.href = imgUrl;
    tempLink.setAttribute('download', 'mermaid-shot.png');
    if (typeof tempLink.download === 'undefined') tempLink.setAttribute('target', '_blank');
    document.body.appendChild(tempLink);
    tempLink.click();
  }

  function download() {
    const mermaidDiv = document.getElementById('mermaid-view');
    const svg = mermaidDiv!.querySelector('svg');
    const url = svgToBlob(svg);
    const link = document.createElement('a');
    link.href = url;
    link.download = 'my-svg-file.svg';
    document.body.append(link);
    link.click();
    link.remove();
    URL.revokeObjectURL(url);
  }

  function svgToBlob(svg) {
    const data = new XMLSerializer().serializeToString(svg);
    const blob = new Blob([data], { type: 'image/svg+xml' });
    return URL.createObjectURL(blob);
  }
</script>

<template>
  <div class="bg w-full h-full" :class="genText == '' ? 'overflow-hidden' : ''">
    <div v-if="genText !== ''" class="absolute top-0 z-10 p-2 flex flex-wrap justify-center gap-2">
      <n-button @click="onZoomIn" text>
        <SvgIcon class="text-2xl" icon="basil:zoom-in-outline" />
      </n-button>
      <n-button @click="onZoomOut" text>
        <SvgIcon class="text-2xl" icon="basil:zoom-out-outline" />
      </n-button>
      <n-button @click="onZoomFill" text>
        <SvgIcon class="text-2xl" icon="fluent:full-screen-zoom-24-filled" />
      </n-button>
      <n-button text>
        <SvgIcon @click="onDownload" class="text-2xl" icon="material-symbols:download" />
      </n-button>
      <n-button>
        <SvgIcon @click="download" class="text-2xl" icon="material-symbols:download" />
      </n-button>
    </div>

    <div class="h-full w-full flex flex-col justify-center items-center gap-3" v-if="genText == ''">
      <SvgIcon class="text-6xl" icon="ri:mind-map" />
      <div class="text-2xl font-bold">{{ t('mindmap.title') }}</div>
      <div class="text-gray-400">{{ t('mindmap.titleDes') }}</div>
      <n-button type="success">{{ t('mindmap.begin') }}</n-button>
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
