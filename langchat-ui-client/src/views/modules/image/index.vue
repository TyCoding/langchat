<script setup lang="ts">
  import { SvgIcon } from '@/components/common';
  import { ref } from 'vue';
  import DALL from './component/DALL.vue';
  import { downloadByUrl } from '@/utils/downloadFile';
  import { t } from '@/locales';

  const image = ref<string>('');
  function onOk(data: any) {
    if (data.url !== undefined) {
      image.value = data.url;
    }
  }

  function onDownload() {
    downloadByUrl({ url: image.value });
  }
</script>

<template>
  <n-layout has-sider class="h-full w-full">
    <n-layout-sider
      :collapsed-width="0"
      collapse-mode="width"
      :width="500"
      show-trigger="arrow-circle"
      bordered
    >
      <div class="flex justify-center items-center m-4 rounded">
        <n-tabs type="segment">
          <n-tab-pane name="chap1" tab="OpenAI DALL·E" display-directive="show">
            <DALL @ok="onOk" />
          </n-tab-pane>
          <n-tab-pane name="chap2" tab="Mj & More..." display-directive="show">
            <n-empty description="更多文生图模型后续即将支持..." />
          </n-tab-pane>
        </n-tabs>
      </div>
    </n-layout-sider>

    <div class="flex justify-center items-center w-full mt-4 dot-bg">
      <div class="p-8 w-full h-full mb-14">
        <div class="mb-2 flex flex-wrap justify-between items-center">
          <div class="font-bold flex justify-center items-center flex-wrap gap-2">
            <SvgIcon class="text-lg" icon="ion:sparkles-outline" />
            <span>图片预览</span>
          </div>
          <div>
            <n-button @click="onDownload" size="small" type="success" secondary>
              <template #icon>
                <SvgIcon class="text-[14px]" icon="ion:sparkles-outline" />
              </template>
              下载图片
            </n-button>
          </div>
        </div>
        <div class="w-full h-full rounded-md p-2 flex items-center justify-center">
          <div
            v-if="image"
            class="flex justify-center items-center"
            style="height: calc(100vh - 20%)"
          >
            <img :src="image" class="max-w-full max-h-full" />
          </div>
          <div class="h-full w-full flex flex-col justify-center items-center gap-3" v-else>
            <SvgIcon class="text-6xl" icon="ri:mind-map" />
            <div class="text-2xl font-bold">{{ t('mindmap.title') }}</div>
            <div class="text-gray-400">{{ t('mindmap.titleDes') }}</div>
          </div>
        </div>
      </div>
    </div>
  </n-layout>
</template>

<style scoped lang="less"></style>
