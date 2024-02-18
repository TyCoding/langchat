<script setup lang="ts">
  import { SvgIcon } from '@/components/common';
  import { Transformer } from 'markmap-lib';
  import { Markmap } from 'markmap-view';
  import { onMounted, ref, watch } from 'vue';
  import html2canvas from 'html2canvas';
  import { t } from '@/locales';

  const props = defineProps<{
    genText: string;
  }>();

  let instance: Markmap | null = null;
  onMounted(() => {
    const el = document.getElementById('mind-map') as any;
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
  async function onDownload() {
    const ele = document.getElementById('mind-map-view');
    const canvas = await html2canvas(ele as HTMLDivElement, {
      useCORS: true,
    });
    const imgUrl = canvas.toDataURL('image/png');
    const tempLink = document.createElement('a');
    tempLink.style.display = 'none';
    tempLink.href = imgUrl;
    tempLink.setAttribute('download', 'mind-map-shot.png');
    if (typeof tempLink.download === 'undefined') tempLink.setAttribute('target', '_blank');
    document.body.appendChild(tempLink);
    tempLink.click();
  }
</script>

<template>
  <div class="w-full h-full" :class="genText == '' ? 'overflow-hidden' : ''">
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
    </div>

    <div class="h-full w-full flex flex-col justify-center items-center gap-3" v-if="genText == ''">
      <SvgIcon class="text-6xl" icon="ri:mind-map" />
      <div class="text-2xl font-bold">{{ t('mindmap.title') }}</div>
      <div class="text-gray-400">{{ t('mindmap.titleDes') }}</div>
      <n-button type="success">{{ t('mindmap.begin') }}</n-button>
    </div>

    <div class="h-full w-full" id="mind-map-view">
      <svg class="h-full w-full" id="mind-map" />
    </div>
  </div>
</template>

<style scoped lang="less"></style>
