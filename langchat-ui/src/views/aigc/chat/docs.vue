<script setup lang="ts">
  import { onMounted, ref } from 'vue';
  import SvgIcon from '@/components/SvgIcon/index.vue';
  import Chat from './components/Chat.vue';
  import { list as getKnowledgeList } from '@/api/aigc/knowledge';
  import { list as getPromptList } from '@/api/aigc/prompt';
  import { modelList } from '@/api/models';
  import { getMessages, clean } from '@/api/aigc/chat';
  import { useChatStore } from './components/store/useChatStore';
  import { useDialog, useMessage } from 'naive-ui';
  import DataTable from './components/DataTable.vue';

  const dialog = useDialog();
  const ms = useMessage();
  const checked = ref();
  const showSelect = ref();
  const activeTab = ref('knowledge');
  const list = ref();
  const knowledgeList = ref();
  const promptList = ref();
  const value = ref('');
  const loading = ref(true);
  const chatLoading = ref(false);
  const chatStore = useChatStore();

  onMounted(async () => {
    loading.value = true;
    const data = await getKnowledgeList({});
    let arr: any[] = [];
    data.forEach((item: any) => {
      item.docs = item.docs.map((opt) => ({
        label: opt.name,
        value: opt.id,
      }));
      arr.push(item);
    });
    knowledgeList.value = arr;
    promptList.value = await getPromptList({});
    list.value = knowledgeList.value;
    loading.value = false;
  });

  async function onCheck(item: any) {
    checked.value = item;
    chatLoading.value = true;
    chatStore.messages = [];
    chatStore.conversationId = item.id;
    chatStore.knowledge = null;
    chatStore.prompt = null;
    chatStore.messages = await getMessages(item.id);

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

  // 清除
  function handleClear() {
    if (loading.value || chatStore.conversationId == null) {
      return;
    }
    dialog.warning({
      title: '清除聊天',
      content: '确认清除聊天',
      positiveText: '是',
      negativeText: '否',
      onPositiveClick: async () => {
        await clean(chatStore.conversationId);
        ms.success('聊天记录清除成功');
      },
    });
  }

  function onShowSelect(item: any) {
    if (showSelect.value == item) {
      showSelect.value = undefined;
    } else {
      showSelect.value = item;
    }
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
            <template v-for="item in list" :key="item.id">
              <n-popselect
                v-model:value="value"
                :options="item.docs"
                placement="right"
                :show="item == showSelect"
              >
                <n-alert
                  @click="onCheck(item)"
                  :type="checked == item ? 'success' : ''"
                  class="rounded-lg cursor-pointer !gap-6"
                >
                  <template #header>
                    <div class="flex items-center justify-between">
                      <div>{{ item.name }}</div>
                      <n-button
                        @click="onShowSelect(item)"
                        v-if="item.isExcel"
                        type="success"
                        dashed
                        size="tiny"
                      >
                        Select
                      </n-button>
                    </div>
                  </template>
                  <template #icon>
                    <n-icon>
                      <SvgIcon class="text-4xl" icon="solar:document-bold-duotone" />
                    </n-icon>
                  </template>
                  <n-ellipsis expand-trigger="click">
                    {{ item.des == undefined ? item.prompt : item.des }}
                  </n-ellipsis>
                </n-alert>
              </n-popselect>
            </template>
          </div>
        </div>
      </n-spin>
    </n-layout-sider>

    <div class="flex flex-col gap-1 items-center w-full mt-0">
      <n-split direction="vertical" :default-size="0" :resize-trigger-size="0">
        <template #1>
          <div class="w-full p-2 mb-4 h-full">
            <span
              class="inline-flex items-center mb-2 gap-x-2 rounded-full bg-green-600/20 px-2.5 py-1 text-sm font-semibold leading-5 text-green-600"
            >
              <span class="inline-block h-1.5 w-1.5 rounded-full bg-green-600"></span>
              Approved
            </span>
            <DataTable />
          </div>
        </template>
        <template #2>
          <div class="p-8 pt-6 w-full h-full mb-2">
            <div class="mb-2 flex flex-wrap justify-between items-center">
              <div class="font-bold flex justify-center items-center flex-wrap gap-2">
                <SvgIcon class="text-lg" icon="ion:sparkles-outline" />
                <span>AI对话</span>
              </div>
              <n-space align="center">
                <n-select
                  size="small"
                  v-model:value="chatStore.model"
                  :options="modelList"
                  :consistent-menu-width="false"
                  class="!w-32"
                />

                <n-button @click="handleClear" size="small" type="warning" secondary>
                  <template #icon>
                    <SvgIcon class="text-[14px]" icon="fluent:delete-12-regular" />
                  </template>
                  清空聊天
                </n-button>
              </n-space>
            </div>
            <div class="w-full h-full rounded-md p-2 flex items-center justify-center">
              <n-spin :show="chatLoading">
                <Chat v-if="checked !== undefined && checked.id !== undefined" />
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
</style>
