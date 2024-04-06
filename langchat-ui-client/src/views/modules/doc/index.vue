<script setup lang="ts">
  import { ref } from 'vue';
  import Chat from './components/Chat.vue';
  import FileList from './components/FileList.vue';
  import { useMessage } from 'naive-ui';
  import { Oss } from '@/api/models';
  import { t } from '@/locales';

  const message = useMessage();
  const file = ref<Oss>({});

  function onSelect(item: Oss) {
    if (file.value.url == item.url) {
      return;
    }
    file.value = item;
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
      <FileList :file="file" @select="onSelect" />
    </n-layout-sider>
    <div class="w-full h-full flex flex-row pt-2 gap-2">
      <div class="w-8/12 h-full overflow-y-auto">
        <div
          v-if="file.fileName"
          class="text-gray-700 text-[17px] pl-2 pb-2 pt-0 font-bold h-min-8 mt-0.5"
        >
          {{ file.fileName }}.{{ file.type }}
        </div>
        <n-empty
          v-if="file.url === undefined"
          class="h-full w-full justify-center"
          :description="t('doc.previewEmpty')"
        />
        <template v-else>
          <file-viewer :url="file.url" />
          <!--          <iframe v-if="file.type === 'pdf'" width="100%" height="100%" :src="file.url"></iframe>-->
          <!--          <iframe-->
          <!--            v-else-->
          <!--            width="100%"-->
          <!--            height="100%"-->
          <!--            :src="'https://view.officeapps.live.com/op/embed.aspx?src=' + file.url"-->
          <!--          ></iframe>-->
        </template>
      </div>
      <div class="w-4/12 border-l dark:border-l-[#1e1e20]">
        <Chat />
      </div>
    </div>
  </n-layout>
</template>

<style scoped lang="less"></style>
