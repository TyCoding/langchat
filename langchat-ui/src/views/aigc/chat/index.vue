<script setup lang="ts">
  import { onMounted, ref } from 'vue';
  import SvgIcon from '@/components/SvgIcon/index.vue';
  import Chat from './components/Chat.vue';
  import { list as getKnowledgeList } from '@/api/aigc/knowledge';
  import { list as getPromptList } from '@/api/aigc/prompt';

  const checked = ref();
  const list = ref();
  const knowledgeList = ref();
  const promptList = ref();
  const value = ref();
  const loading = ref(true);

  onMounted(async () => {
    loading.value = true;
    knowledgeList.value = await getKnowledgeList({});
    promptList.value = await getPromptList({});
    list.value = knowledgeList.value;
    loading.value = false;
  });
  const options = [
    {
      label: 'Drive My Car',
      value: 'Drive My Car',
    },
    {
      label: 'Norwegian Wood',
      value: 'Norwegian Wood',
    },
  ];

  function onCheck(item: any) {
    checked.value = item;
  }
  async function onUpdate(val: string) {
    if (val === 'knowledge') {
      list.value = knowledgeList.value;
    }
    if (val === 'prompt') {
      list.value = promptList.value;
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
        <div class="flex justify-center items-center p-4 pt-0 rounded overflow-y-auto">
          <ul class="mt-2 space-y-3 w-full">
            <li v-for="(item, idx) in list" :key="idx" @click="onCheck(item)">
              <n-popselect
                v-model:value="value"
                :options="options"
                placement="right"
                :show="item.isStruct !== undefined && item.isStruct && item == checked"
              >
                <label :for="item.name" class="block relative">
                  <input
                    :id="item.name"
                    type="radio"
                    :checked="item == checked"
                    name="payment"
                    class="sr-only peer"
                  />
                  <div
                    class="w-full flex gap-x-3 items-start p-4 pb-2.5 cursor-pointer rounded-lg border bg-white shadow-sm ring-indigo-600 peer-checked:bg-indigo-50/75 peer-checked:ring-1 duration-200"
                  >
                    <div class="flex-none">
                      <SvgIcon class="text-3xl" icon="twemoji:open-book" />
                    </div>
                    <div>
                      <div class="leading-none text-gray-800 font-medium text-[15px] pr-3">
                        {{ item.name }}
                      </div>
                      <p class="text-xs text-gray-600 mt-[9px]">
                        <n-ellipsis :line-clamp="2">
                          {{ item.des == undefined ? item.prompt : item.des }}
                        </n-ellipsis>
                      </p>
                    </div>
                  </div>
                  <div
                    class="absolute top-4 right-4 flex-none flex items-center justify-center w-4 h-4 rounded-full border peer-checked:bg-indigo-600 text-white peer-checked:text-white duration-200"
                  >
                    <svg class="w-2.5 h-2.5" viewBox="0 0 12 10">
                      <polyline
                        fill="none"
                        stroke-width="2px"
                        stroke="currentColor"
                        stroke-dasharray="16px"
                        points="1.5 6 4.5 9 10.5 1"
                      />
                    </svg>
                  </div>
                </label>
              </n-popselect>
            </li>
          </ul>
        </div>
      </n-spin>
    </n-layout-sider>

    <div class="flex justify-center items-center w-full mt-0">
      <div class="p-8 pt-6 w-full h-full mb-2">
        <div class="mb-2 flex flex-wrap justify-between items-center">
          <div class="font-bold flex justify-center items-center flex-wrap gap-2">
            <SvgIcon class="text-lg" icon="ion:sparkles-outline" />
            <span>AI对话</span>
          </div>
          <div>
            <n-button size="small" type="success" secondary>
              <template #icon>
                <SvgIcon class="text-[14px]" icon="fluent:delete-12-regular" />
              </template>
              清空聊天
            </n-button>
          </div>
        </div>
        <div class="w-full h-full rounded-md p-2 flex items-center justify-center">
          <Chat :id="checked.id" v-if="checked !== undefined && checked.id !== undefined" />
          <n-empty v-else description="请先选中左侧的知识库或者提示词列表仅">
            <template #extra>
              <n-button size="small" type="success"> 立即开始 </n-button>
            </template>
          </n-empty>
        </div>
      </div>
    </div>
  </n-layout>
</template>

<style scoped lang="less">
  ::v-deep(.n-tabs.n-tabs--top .n-tab-pane) {
    padding: 0 !important;
  }
</style>
