<script lang="ts" setup>
  import DocList from './DocsList/index.vue';
  import FileList from './DocsSlice/index.vue';
  import ImportFile from './ImportFile/index.vue';
  import { onMounted, ref } from 'vue';
  import type { MenuOption } from 'naive-ui';
  import { NIcon } from 'naive-ui';
  import { useRouter } from 'vue-router';
  import { renderIcon } from '@/utils';
  import {
    ArrowUndoOutline,
    CloudUploadOutline,
    DocumentTextOutline,
    AlbumsOutline,
  } from '@vicons/ionicons5';
  import { getById } from '@/api/aigc/knowledge';

  const router = useRouter();

  const menu = ref();
  const menuOptions: MenuOption[] = [
    {
      label: '数据导入',
      key: 'import-file',
      icon: renderIcon(CloudUploadOutline),
    },
    {
      label: '文档管理',
      key: 'doc-list',
      icon: renderIcon(DocumentTextOutline),
    },
  ];

  const knowledge = ref<any>({});
  onMounted(async () => {
    const id = router.currentRoute.value.params.id;
    knowledge.value = await getById(String(id));
    menu.value = menuOptions[0].key;
    if (knowledge.value.isExcel) {
      menuOptions.push({
        label: '切片管理',
        key: 'slice-list',
        icon: renderIcon(AlbumsOutline),
      });
    }
  });

  function handleSelect(key: string, item: MenuOption) {
    menu.value = key;
  }

  function handleReturn() {
    router.back();
  }
</script>

<template>
  <div>
    <div class="n-layout-page-header">
      <n-card :bordered="false" size="medium">
        <template #header>
          <n-space class="flex items-center">
            <n-button dashed type="success" @click="handleReturn">
              知识库列表
              <template #icon>
                <n-icon>
                  <ArrowUndoOutline />
                </n-icon>
              </template>
            </n-button>
            <span>
              {{ knowledge.name }}
            </span>
          </n-space>
        </template>

        {{ knowledge.des }}
      </n-card>
    </div>
    <div class="mt-2 h-full mx-4" style="height: calc(100vh - 242px) !important">
      <n-grid :x-gap="10" class="h-full" cols="2 s:2 m:2 l:24 xl:24 2xl:24" responsive="screen">
        <n-gi class="bg-white pt-2" span="3">
          <n-menu v-model:value="menu" :options="menuOptions" @update:value="handleSelect" />
        </n-gi>
        <n-gi class="h-full overflow-y-auto" span="21">
          <DocList v-if="menu == 'doc-list'" />
          <FileList v-if="menu == 'slice-list'" />
          <ImportFile :data="knowledge" v-if="menu == 'import-file'" />
        </n-gi>
      </n-grid>
    </div>
  </div>
</template>

<style lang="less" scoped></style>
