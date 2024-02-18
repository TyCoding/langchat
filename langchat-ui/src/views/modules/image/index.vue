<script setup lang="ts">
  import { SvgIcon } from '@/components/common';
  import { ref } from 'vue';
  import DALL from './component/DALL.vue';
  import { Oss } from '@/api/models';
  import { downloadByUrl } from '@/utils/downloadFile';

  const image = ref<string>('');
  function onOk(data: Oss) {
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
          <n-tab-pane name="chap2" tab="More" display-directive="show" />
        </n-tabs>
      </div>
    </n-layout-sider>

    <div class="flex justify-center items-center w-full mt-4">
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
          <n-empty v-else description="在左侧输入图片描述，开始生成图片吧！">
            <template #extra>
              <n-button size="small" type="success"> 立即开始 </n-button>
            </template>
          </n-empty>
        </div>
      </div>
    </div>
  </n-layout>
</template>

<style scoped lang="less"></style>
