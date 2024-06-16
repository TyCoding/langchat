<script lang="ts" setup>
  import { h, reactive, ref, toRaw } from 'vue';
  import { BasicTable, TableAction } from '@/components/Table';
  import { DeleteOutlined, EditOutlined, PlusOutlined } from '@vicons/antd';
  import { columns, LLMProviders } from './data';
  import Edit from './edit.vue';
  import { list } from '@/api/aigc/model';

  const actionRef = ref();
  const editRef = ref();
  const expands = ref([]);
  const actionColumn = reactive({
    width: 100,
    title: '操作',
    key: 'action',
    fixed: 'right',
    align: 'center',
    render(record: any) {
      const providers = LLMProviders.map((i) => i.model);
      if (providers.includes(toRaw(record).model)) {
        return h(TableAction as any, {
          style: 'text',
          actions: [
            {
              type: 'success',
              icon: PlusOutlined,
              onClick: handleAdd.bind(null, { provider: record.model }),
            },
          ],
        });
      }
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

  const loadDataTable = async (params: any) => {
    const models = await list({ ...params });
    const data: any[] = [];
    LLMProviders.forEach((i) => {
      const children = models.filter((m) => m.provider == i.model);
      console.log(children);
      data.push({
        model: i.model,
        name: i.name,
        type: 'expand',
        expandable: true,
        children: children,
      });
    });
    return data;
  };
  function handleAdd(record: any) {
    editRef.value.show(record);
  }

  function handleEdit(record: any) {
    editRef.value.show(record);
  }

  function reloadTable() {
    actionRef.value.reload();
  }

  function handleDel(record: any) {}
</script>

<template>
  <div>
    <div class="n-layout-page-header">
      <n-card :bordered="false" title="模型配置"> 支持对常见的模型配置。 </n-card>
    </div>

    <n-card :bordered="false" class="mt-4">
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
    </n-card>

    <Edit ref="editRef" @reload="reloadTable" />
  </div>
</template>

<style lang="less" scoped></style>
