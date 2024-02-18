<script setup lang="ts">
  import { SvgIcon } from '@/components/common';
  import Write from './components/Write.vue';
  import Beautify from './components/Beautify.vue';
  import { ref } from 'vue';

  const content = ref('');
  function onOk(val: string) {
    content.value = val;
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
          <n-tab-pane name="chap1" tab="文案撰写" display-directive="show">
            <Write @ok="onOk" />
          </n-tab-pane>
          <n-tab-pane name="chap2" tab="内容美化" display-directive="show">
            <Beautify @ok="onOk" />
          </n-tab-pane>
        </n-tabs>
      </div>
    </n-layout-sider>

    <div class="flex justify-center items-center w-full mt-4">
      <div class="p-8 w-full h-full mb-14">
        <div class="mb-2 flex flex-wrap justify-between items-center">
          <div class="font-bold flex items-center flex-wrap gap-2">
            <SvgIcon class="text-lg" icon="ion:sparkles-outline" />
            <span>输出内容预览</span>
          </div>
          <div>
            <n-button size="small" type="success" secondary>
              <template #icon>
                <SvgIcon class="text-[14px]" icon="ion:sparkles-outline" />
              </template>
              复制内容
            </n-button>
          </div>
        </div>
        <n-input v-model:value="content" type="textarea" class="h-full" />
      </div>
    </div>
  </n-layout>
</template>

<style scoped lang="less"></style>
