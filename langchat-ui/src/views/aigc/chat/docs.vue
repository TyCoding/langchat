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

<script setup lang="ts">
  import { computed, h, nextTick, onMounted, ref } from 'vue';
  import SvgIcon from '@/components/SvgIcon/index.vue';
  import Chat from '@/views/aigc/chat/components/Chat.vue';
  import { list as getKnowledgeList } from '@/api/aigc/knowledge';
  import { list as getPromptList } from '@/api/aigc/prompt';
  import { getMessages } from '@/api/aigc/chat';
  import { useChatStore } from '@/views/aigc/chat/components/store/useChatStore';
  import DataTable from './components/ExcelData.vue';
  import Header from '@/views/aigc/chat/components/Header.vue';

  const checked = ref();
  const showExcel = ref<any>(false);
  const split = ref<any>(0);
  const activeTab = ref('knowledge');
  const dataTableRef = ref();
  const list = ref();
  const knowledgeList = ref();
  const promptList = ref();
  const loading = ref(true);
  const chatLoading = ref(false);
  const chatStore = useChatStore();

  onMounted(async () => {
    loading.value = true;
    knowledgeList.value = await getKnowledgeList({});
    promptList.value = await getPromptList({});
    list.value = knowledgeList.value;
    loading.value = false;
  });

  async function onSelect(item: any) {
    checked.value = item;
    chatLoading.value = true;
    chatStore.docsId = null;
    chatStore.selectExcelId = undefined;
    chatStore.messages = [];
    chatStore.knowledge = null;
    chatStore.prompt = null;
    chatStore.messages = await getMessages(item.id);

    if (item.isExcel !== true) {
      chatStore.conversationId = item.id;
    }
    if (activeTab.value === 'knowledge') {
      chatStore.knowledge = item;
    }
    if (activeTab.value === 'prompt') {
      chatStore.prompt = item;
    }

    chatLoading.value = false;
  }
  async function onUpdate(val: string) {
    activeTab.value = val;
    if (val === 'knowledge') {
      list.value = knowledgeList.value;
    }
    if (val === 'prompt') {
      list.value = promptList.value;
    }
  }

  const isShowChat = computed(() => {
    if (checked.value !== undefined && checked.value.id !== undefined) {
      if (checked.value.isExcel && chatStore.selectExcelId == undefined) {
        return false;
      }
      return true;
    }
    return false;
  });

  async function onCheckMenu(key: string, item: any) {
    showExcel.value = true;
    split.value = 0.3;
    await nextTick();
    dataTableRef.value.init(key);
    chatStore.conversationId = null;
    chatStore.docsId = key;
  }
</script>

<template>
  <n-layout has-sider class="h-full w-full">
    <n-layout-sider :width="350" bordered :native-scrollbar="false">
      <div class="px-2 py-2 pb-0">
        <n-tabs type="segment" animated @update:value="onUpdate">
          <n-tab-pane name="knowledge" tab="知识库" />
          <n-tab-pane name="prompt" tab="提示词库" />
        </n-tabs>
      </div>

      <n-spin :show="loading">
        <n-empty v-if="list == null || list.length == 0" class="mt-10" />
        <div class="flex justify-center items-center p-4 pt-0 rounded overflow-y-auto">
          <div class="mt-2 space-y-3 w-full">
            <n-collapse>
              <n-collapse-item
                :disabled="!item.isExcel"
                v-for="item in list"
                :key="item.id"
                :name="item.id"
              >
                <template #header>
                  <n-alert
                    @click="onSelect(item)"
                    :type="checked == item ? 'success' : ''"
                    class="rounded-lg cursor-pointer"
                  >
                    <template #header>
                      <div class="flex items-center justify-between">
                        <div>{{ item.name }}</div>
                      </div>
                    </template>
                    <template #icon>
                      <n-icon>
                        <SvgIcon v-if="item.isExcel" class="text-4xl" icon="tabler:file-excel" />
                        <SvgIcon v-else class="text-4xl" icon="fe:file-word" />
                      </n-icon>
                    </template>
                    <n-ellipsis expand-trigger="click" class="w-[260px]">
                      {{ item.des == undefined ? item.prompt : item.des }}
                    </n-ellipsis>
                  </n-alert>
                </template>
                <div v-if="item.isExcel" class="flex justify-start items-center">
                  <n-menu
                    :options="item.docs"
                    :key-field="'id'"
                    :value-field="'id'"
                    :label-field="'name'"
                    class="w-full"
                    @update:value="onCheckMenu"
                    v-model:value="chatStore.selectExcelId"
                    :render-icon="
                      () =>
                        h(
                          SvgIcon,
                          {
                            icon: 'tabler:file-excel',
                          },
                          {}
                        )
                    "
                  />
                </div>
              </n-collapse-item>
            </n-collapse>
          </div>
        </div>
      </n-spin>
    </n-layout-sider>

    <div class="flex flex-col gap-1 items-center w-full mt-0">
      <n-split direction="vertical" v-model:size="split" :resize-trigger-size="showExcel ? 1 : 0">
        <template #1>
          <div v-if="showExcel" class="w-full p-2 mb-4 h-full">
            <DataTable ref="dataTableRef" />
          </div>
        </template>

        <template #2>
          <div class="p-7 pt-6 w-full h-full mb-2">
            <Header title="AI智能助手" />
            <div class="w-full h-full rounded-md p-2 flex items-center justify-center">
              <n-spin :show="chatLoading">
                <Chat v-if="isShowChat" />

                <div v-else class="w-full h-full flex items-center justify-center">
                  <n-empty description="请先选中左侧的知识库或者提示词列表开始聊天！">
                    <template #extra>
                      <n-button size="small" type="success"> 立即开始 </n-button>
                    </template>
                  </n-empty>
                </div>
              </n-spin>
            </div>
          </div>
        </template>
      </n-split>
    </div>
  </n-layout>
</template>

<style scoped lang="less">
  ::v-deep(.n-tabs.n-tabs--top .n-tab-pane) {
    padding: 0 !important;
  }
  ::v-deep(.n-alert-body) {
    padding-left: 50px !important;
  }
  ::v-deep(.n-alert__icon) {
    padding-right: 10px !important;
  }
  ::v-deep(.n-collapse-item-arrow) {
    display: none !important;
  }
  ::v-deep(.n-collapse .n-collapse-item) {
    border-top: none !important;
    margin: 0 !important;
    .n-collapse-item__header {
      padding-top: 10px;
    }
    .n-collapse-item__content-wrapper .n-collapse-item__content-inner {
      padding-top: 0 !important;
    }
    .n-menu-item-content {
      padding-left: 12px !important;
      .n-menu-item-content__icon {
        margin-right: 3px !important;
      }
    }
    .n-menu .n-menu-item-content::before {
      left: 0 !important;
      right: 0 !important;
    }
    .n-divider.n-divider--vertical {
      height: 2em;
    }
  }
</style>
