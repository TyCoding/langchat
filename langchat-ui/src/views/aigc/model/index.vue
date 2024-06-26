<script lang="ts" setup>
  import { computed, h, nextTick, reactive, ref } from 'vue';
  import { BasicTable, TableAction } from '@/components/Table';
  import { DeleteOutlined, EditOutlined, PlusOutlined } from '@vicons/antd';
  import { LLMProviders, ProviderEnum } from './data';
  import Edit from './edit.vue';
  import { del, list } from '@/api/aigc/model';
  import { useDialog, useMessage } from 'naive-ui';
  import { getColumns } from '@/views/aigc/model/coumns';

  const message = useMessage();
  const dialog = useDialog();
  const actionRef = ref();
  const editRef = ref();
  const provider = ref('');
  const actionColumn = reactive({
    width: 100,
    title: '操作',
    key: 'action',
    fixed: 'right',
    align: 'center',
    render(record: any) {
      return h(TableAction as any, {
        style: 'text',
        actions: [
          {
            type: 'info',
            icon: EditOutlined,
            onClick: handleEdit.bind(null, record),
          },
          {
            type: 'error',
            icon: DeleteOutlined,
            onClick: handleDel.bind(null, record),
          },
        ],
      });
    },
  });

  const columns = computed(() => {
    nextTick();
    return getColumns(provider.value);
  });
  const loadDataTable = async (params: any) => {
    if (provider.value === '') {
      provider.value = LLMProviders[0].model;
    }

    return await list({ ...params, provider: provider.value });
  };
  async function handleAdd() {
    if (ProviderEnum.EMBEDDING === provider.value) {
      const arr = await loadDataTable({});
      if (arr.length > 0) {
        message.error('只允许创建一条Embedding配置，请先删除之前的配置');
        return;
      }
    }
    editRef.value.show({ provider: provider.value });
  }

  function handleEdit(record: any) {
    editRef.value.show(record);
  }

  function reloadTable() {
    actionRef.value.reload();
  }

  function handleDel(record: any) {
    dialog.warning({
      title: '警告',
      content: `你确定删除 [${record.name}] 模型吗？删除之后不可再用该模型对话`,
      positiveText: '确定',
      negativeText: '不确定',
      onPositiveClick: async () => {
        await del(record.id);
        message.success('模型删除成功');
      },
    });
  }
</script>

<template>
  <div>
    <div class="n-layout-page-header">
      <n-card :bordered="false" title="模型配置">
        支持动态配置LLM大模型参数，支持每个模型使用不同的Key，编辑后的模型配置将立即生效。
      </n-card>
    </div>

    <n-card :bordered="false" class="mt-4">
      <div class="flex gap-5">
        <div class="w-52 flex flex-col gap-4 py-1">
          <div class="font-bold text-base">LLM Provider</div>
          <n-menu
            v-model:value="provider"
            :key-field="'model'"
            :label-field="'name'"
            :options="LLMProviders"
            class="model-menu"
            @update:value="reloadTable"
          />
        </div>

        <div class="w-full">
          <BasicTable
            ref="actionRef"
            :actionColumn="actionColumn"
            :columns="columns"
            :pagination="false"
            :request="loadDataTable"
            :row-key="(row:any) => row.model"
            :single-line="false"
            default-expand-all
          >
            <template #tableTitle>
              <n-button type="primary" @click="handleAdd">
                <template #icon>
                  <n-icon>
                    <PlusOutlined />
                  </n-icon>
                </template>
                新增模型
              </n-button>
            </template>
          </BasicTable>
        </div>
      </div>
    </n-card>

    <Edit :provider="provider" ref="editRef" @reload="reloadTable" />
  </div>
</template>

<style lang="less" scoped></style>
