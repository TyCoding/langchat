<template>
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
            {{ kb.name }}
          </span>
        </n-space>
      </template>

      {{ kb.des }}
    </n-card>
  </div>
  <div class="mt-2 h-full" style="height: calc(100vh - 242px) !important">
    <n-grid :x-gap="10" class="h-full" cols="2 s:2 m:2 l:24 xl:24 2xl:24" responsive="screen">
      <n-gi class="bg-white pt-2" span="3">
        <n-menu v-model:value="menu" :options="menuOptions" @update:value="handleSelect" />
      </n-gi>
      <n-gi class="h-full overflow-y-auto" span="21">
        <!--        <DocList v-if="menu == 'doc-list'" />-->
        <!--        <ImportFile v-if="menu == 'import-file'" />-->
        <FileList v-if="menu == 'file-list'" />
      </n-gi>
    </n-grid>
  </div>
</template>
<script lang="ts" setup>
  // import DocList from '@/views/kb/modules/DocList/Layout.vue';
  import FileList from './FileList/index.vue';
  // import ImportFile from '@/views/kb/modules/ImportFile/Layout.vue';
  import { onMounted, ref } from 'vue';
  import type { MenuOption } from 'naive-ui';
  import { NIcon } from 'naive-ui';
  import { useRouter } from 'vue-router';
  import { renderIcon } from '@/utils';
  import {
    ArrowUndoOutline,
    CloudUploadOutline,
    CreateOutline,
    FileTrayFullOutline,
  } from '@vicons/ionicons5';
  import { getById } from '@/api/aigc/kb';

  const router = useRouter();

  const menu = ref();
  const menuOptions: MenuOption[] = [
    {
      label: '文档录入列表',
      key: 'doc-list',
      icon: renderIcon(CreateOutline),
    },
    {
      label: '文件上传列表',
      key: 'file-list',
      icon: renderIcon(FileTrayFullOutline),
    },
    {
      label: '导入数据文件',
      key: 'import-file',
      icon: renderIcon(CloudUploadOutline),
    },
  ];

  const kb = ref<any>({});
  onMounted(async () => {
    const id = router.currentRoute.value.params.id;
    kb.value = await getById(String(id));
    menu.value = 'doc-list';
  });

  function handleSelect(key: string, item: MenuOption) {
    menu.value = key;
  }

  function handleReturn() {
    router.back();
  }
</script>
<style lang="less" scoped></style>
