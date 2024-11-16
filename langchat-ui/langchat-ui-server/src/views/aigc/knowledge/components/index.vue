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
  import SvgIcon from '@/components/SvgIcon/index.vue';
  import DocsSliceSearch from './DocsSliceSearch/index.vue';
  import ImportFile from './ImportFile/index.vue';
  import { onMounted, ref } from 'vue';
  import { NIcon } from 'naive-ui';
  import { useRouter } from 'vue-router';
  import { renderIcon } from '@/utils';
  import {
    AlbumsOutline,
    ArrowUndoOutline,
    CloudUploadOutline,
    DocumentTextOutline,
    SearchOutline,
  } from '@vicons/ionicons5';
  import { getById } from '@/api/aigc/knowledge';

  const router = useRouter();
  const active = ref('import-file');
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
    active.value = menuOptions.value[0].key;

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
  });

  function handleSelect(key: string) {
    active.value = key;
  }

  function handleReturn() {
    router.back();
  }
</script>

<template>
  <div class="mt-2" style="height: calc(100vh - 130px) !important">
    <n-grid :x-gap="13" class="h-full" cols="2 s:2 m:2 l:24 xl:24 2xl:24" responsive="screen">
      <n-gi class="bg-white p-4 rounded-md" span="5">
        <n-button block class="mb-4" dashed size="small" type="primary" @click="handleReturn">
          知识库列表
          <template #icon>
            <n-icon>
              <ArrowUndoOutline />
            </n-icon>
          </template>
        </n-button>

        <div class="flex items-center gap-2">
          <div class="relative bg-blue-100 p-2 rounded">
            <SvgIcon class="text-lg" icon="ep:document" />
          </div>
          <span class="font-semibold text-[16px]">{{ knowledge.name }}</span>
        </div>
        <div class="text-[13px] text-gray-400 mt-3">{{ knowledge.des }}</div>
        <n-divider class="my-3" />
        <div class="my-3 flex flex-col gap-2">
          <div class="text-xs">知识库ID</div>
          <n-input v-model:value="knowledge.id" />
        </div>
        <div class="my-3 flex flex-col gap-2">
          <div class="text-xs">关联向量数据库</div>
          <div v-if="knowledge.embedStore == null" class="py-2 text-gray-400"
            >没有配置关联向量数据库</div
          >
          <n-input v-else v-model:value="knowledge.embedStore.name" />
        </div>
        <div class="my-3 flex flex-col gap-2">
          <div class="text-xs">关联向量化模型</div>
          <div v-if="knowledge.embedModel == null" class="py-2 text-gray-400"
            >没有配置关联向量化模型</div
          >
          <n-input v-else v-model:value="knowledge.embedModel.name" />
        </div>
      </n-gi>
      <n-gi class="h-full bg-white p-4 overflow-y-auto rounded-md" span="19">
        <n-tabs v-model:value="active" class="flex items-center mb-6" @update:value="handleSelect">
          <n-tab v-for="item in menuOptions" :key="item.key" :name="item.key">
            <component :is="item.icon" />
            <span class="pl-2 font-bold">{{ item.label }}</span>
          </n-tab>
        </n-tabs>

        <n-tabs />
        <DocList v-if="active == 'doc-list'" />
        <DocsSlice v-if="active == 'slice-list'" />
        <DocsSliceSearch v-if="active == 'slice-search'" />
        <ImportFile v-if="active == 'import-file'" :data="knowledge" />
      </n-gi>
    </n-grid>
  </div>
</template>

<style lang="less" scoped></style>
