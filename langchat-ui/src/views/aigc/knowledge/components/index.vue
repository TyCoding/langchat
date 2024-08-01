<!--
  - Copyright (c) 2024 LangChat. TyCoding All Rights Reserved.
  -
  - Licensed under the GNU Affero General Public License, Version 3 (the "License");
  - you may not use this file except in compliance with the License.
  - You may obtain a copy of the License at
  -
  -     https://www.gnu.org/licenses/agpl-3.0.html
  -
  - Unless required by applicable law or agreed to in writing, software
  - distributed under the License is distributed on an "AS IS" BASIS,
  - WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
  - See the License for the specific language governing permissions and
  - limitations under the License.
  -->

<script lang="ts" setup>
  import DocList from './DocsList/index.vue';
  import DocsSlice from './DocsSlice/index.vue';
  import DocsSliceSearch from './DocsSliceSearch/index.vue';
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
    SearchOutline,
  } from '@vicons/ionicons5';
  import { getById } from '@/api/aigc/knowledge';

  const router = useRouter();

  const menu = ref();
  const menuOptions = ref([
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
  ]);

  const knowledge = ref<any>({});
  onMounted(async () => {
    const id = router.currentRoute.value.params.id;
    knowledge.value = await getById(String(id));
    menu.value = menuOptions.value[0].key;
    console.log(!knowledge.value.isExcel);
    if (!knowledge.value.isExcel) {
      menuOptions.value.push(
        {
          label: '切片管理',
          key: 'slice-list',
          icon: renderIcon(AlbumsOutline),
        },
        {
          label: '向量搜索',
          key: 'slice-search',
          icon: renderIcon(SearchOutline),
        }
      );
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
          <DocsSlice v-if="menu == 'slice-list'" />
          <DocsSliceSearch v-if="menu == 'slice-search'" />
          <ImportFile :data="knowledge" v-if="menu == 'import-file'" />
        </n-gi>
      </n-grid>
    </div>
  </div>
</template>

<style lang="less" scoped></style>
