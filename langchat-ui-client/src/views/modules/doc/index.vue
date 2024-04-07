<script setup lang="ts">
  import { ref } from 'vue';
  import Chat from './components/Chat.vue';
  import FileList from './components/FileList.vue';
  import { useMessage } from 'naive-ui';
  import { Oss } from '@/api/models';
  import { t } from '@/locales';
  import FileView from './components/FileView.vue';

  const message = useMessage();
  const file = ref<Oss>({});

  function onSelect(item: Oss) {
    if (file.value.url == item.url) {
      return;
    }
    file.value = item;
  }

  function onClear() {
    file.value.url = '';
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
      <FileList :file="file" @clear="onClear" @select="onSelect" />
    </n-layout-sider>
    <div class="w-full h-full">
      <n-split direction="horizontal" class="h-full" :default-size="0.6">
        <template #1>
          <div class="w-full h-full">
            <div
              v-if="file.fileName"
              class="text-gray-700 text-[17px] border-b px-4 font-bold h-12 flex justify-between items-center dark:text-white"
            >
              <div>{{ file.fileName }}.{{ file.type }}</div>
              <div>OpenAI</div>
            </div>
            <n-empty
              v-if="file.url === undefined"
              class="h-full w-full justify-center"
              :description="t('doc.previewEmpty')"
            />
            <template v-else>
              <FileView :url="file.url" />
            </template>
          </div>
        </template>
        <template #2>
          <div class="w-full h-full border-l dark:border-l-[#1e1e20]">
            <Chat :file="file" />
          </div>
        </template>
      </n-split>
    </div>
  </n-layout>
</template>

<style scoped lang="less"></style>
