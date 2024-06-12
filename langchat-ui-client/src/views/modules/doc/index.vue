<script setup lang="ts">
  import { ref } from 'vue';
  import Chat from './components/Chat.vue';
  import FileList from './components/FileList.vue';
  import { t } from '@/locales';
  import FileView from './components/FileView.vue';
  import { useDocStore } from '@/views/modules/doc/store';
  import { Oss } from '@/api/models';

  const chatRef = ref();
  const docStore = useDocStore();

  function onSelect(item: Oss) {
    docStore.onSelect(item);
    chatRef.value.init();
  }
</script>

<template>
  <n-layout has-sider class="h-full w-full">
    <n-layout-sider
      :collapsed-width="0"
      collapse-mode="width"
      :width="280"
      show-trigger="arrow-circle"
      bordered
    >
      <FileList @select="onSelect" />
    </n-layout-sider>
    <div class="w-full h-full">
      <n-split direction="horizontal" class="h-full" :default-size="0.6">
        <template #1>
          <div class="w-full h-full">
            <div
              v-if="docStore.file.fileName"
              class="text-gray-700 text-[17px] border-b px-2 font-bold h-12 flex justify-between items-center dark:text-white"
            >
              <div>{{ docStore.file.fileName }}.{{ docStore.file.type }}</div>
            </div>
            <n-empty
              v-if="docStore.file.url === undefined"
              class="h-full w-full justify-center"
              :description="t('doc.previewEmpty')"
            />
            <template v-else>
              <FileView :url="docStore.file.url" />
            </template>
          </div>
        </template>
        <template #2>
          <div class="w-full h-full border-l dark:border-l-[#1e1e20]">
            <Chat ref="chatRef" />
          </div>
        </template>
      </n-split>
    </div>
  </n-layout>
</template>

<style scoped lang="less"></style>
