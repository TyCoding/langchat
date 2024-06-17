<script setup lang="ts">
  import { FileTrayOutline, DocumentTextOutline, ServerOutline } from '@vicons/ionicons5';
  import { useRouter } from 'vue-router';
  import { Flow } from '@/api/models/flow';
  import { update as updateFlow, getById } from '@/api/aigc/flow';
  import { onMounted } from 'vue';
  import { isNullOrWhitespace } from '@/utils/is';
  import { initializeTemplate } from '@/views/flow/initialize/index';
  import { RouteItem, useTabsViewStore } from '@/store/modules/tabsView';
  const router = useRouter();
  const tabsViewStore = useTabsViewStore();

  let flowId = '';
  let data: Flow = {};
  onMounted(async () => {
    flowId = String(router.currentRoute.value.params.id);
    data = await getById(flowId);
    if (!isNullOrWhitespace(data.flow)) {
      tabsViewStore.closeCurrentTab(router.currentRoute.value as RouteItem);
      await router.push({ name: 'flow_edit', params: { id: flowId } });
    }
  });

  async function handler(key: string) {
    const temp = initializeTemplate(key);
    data.des = temp?.des;
    data.script = temp?.script;
    data.flow = JSON.stringify(temp?.flow);
    await updateFlow(data);
    await router.push({ name: 'flow_edit', params: { id: flowId } });
  }

  const templates = [
    {
      key: 'blank',
      label: '空模版',
      icon: FileTrayOutline,
      value: '创建一个空的模版，仅包含最基础的节点，可以在此模版上自由设计。',
    },
    {
      key: 'knowledge',
      label: '从知识库中开始问答',
      icon: ServerOutline,
      value: '使用自定义知识库数据，从知识库中读取数据并标准化问答内容。',
    },
    {
      key: 'file',
      label: '从文档文件中开始文档',
      icon: DocumentTextOutline,
      value: '上传文档文件，从文档中读取数据，回答文档中有关的问题。',
    },
  ];
</script>

<template>
  <n-config-provider>
    <div class="flex dot-bg justify-center items-center w-full" style="height: calc(100vh - 130px)">
      <n-card style="width: 900px" class="rounded-md">
        <div class="p-6">
          <div class="text-2xl pb-3 font-bold">创建模版</div>
          <div>你可以通过下列模版配置初始化你的流程</div>
        </div>
        <div class="pl-8 pr-8 mb-10 flex flex-col gap-4">
          <n-button
            v-for="item in templates"
            :key="item.key"
            @click="handler(item.key)"
            class="w-full justify-start h-15 pt-2 pb-3 pl-8 rounded-l"
            dashed
          >
            <template #icon>
              <n-icon size="30">
                <component :is="item.icon" />
              </n-icon>
            </template>
            <template #default>
              <div class="flex flex-col justify-start gap-2 ml-4">
                <div class="text-lg text-left">{{ item.label }}</div>
                <div class="text">{{ item.value }}</div>
              </div>
            </template>
          </n-button>
        </div>
      </n-card>
    </div>
  </n-config-provider>
</template>

<style scoped lang="less">
  ::v-deep(.n-button) {
    &:hover {
      color: var(--n-text-color-hover) !important;
      .text {
        color: var(--n-text-color-hover) !important;
      }
    }
    height: auto !important;
    ::v-deep(.n-button__content) {
      display: block !important;
    }
    .text {
      color: var(--n-close-icon-color);
    }
  }
</style>
