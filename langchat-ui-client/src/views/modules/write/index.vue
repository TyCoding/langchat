<script lang="ts" setup>
  import { SvgIcon } from '@/components/common';
  import Write from './components/Write.vue';
  import Beautify from './components/Beautify.vue';
  import { ref } from 'vue';

  const message = ref('');
  function onOk(val: string) {
    message.value = val;
  }
</script>

<template>
  <n-layout class="h-full w-full" has-sider>
    <n-layout-sider
      :collapsed-width="0"
      :width="500"
      bordered
      collapse-mode="width"
      show-trigger="arrow-circle"
    >
      <div class="flex justify-center items-center m-4 rounded">
        <n-tabs type="segment">
          <n-tab-pane display-directive="show" name="chap1" tab="文案撰写">
            <Write @ok="onOk" />
          </n-tab-pane>
          <n-tab-pane display-directive="show" name="chap2" tab="内容美化">
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
            <n-button secondary size="small" type="success">
              <template #icon>
                <SvgIcon class="text-[14px]" icon="ion:sparkles-outline" />
              </template>
              复制内容
            </n-button>
          </div>
        </div>
        <n-input v-model:value="message" class="h-full" type="textarea" />
      </div>
    </div>
  </n-layout>
</template>

<style lang="less" scoped></style>
