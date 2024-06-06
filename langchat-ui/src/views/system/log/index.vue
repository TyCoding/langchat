<template>
  <div class="n-layout-page-header">
    <n-card :bordered="false" title="日志数据管理"> 所有用户的操作日志信息。 </n-card>
  </div>

  <n-card :bordered="false" class="mt-4">
    <BasicTable
      :single-line="false"
      :size="'small'"
      :columns="columns"
      :request="loadDataTable"
      :row-key="(row:any) => row.id"
      ref="actionRef"
      :actionColumn="actionColumn"
    />
  </n-card>
</template>

<script lang="ts" setup>
  import { h, reactive, ref } from 'vue';
  import { BasicTable, TableAction } from '@/components/Table';
  import { page as getPage, del } from '@/api/upms/log';
  import { columns } from './columns';
  import { DeleteOutlined } from '@vicons/antd';
  import { useDialog, useMessage } from 'naive-ui';
  import { Log } from '@/api/models/auth';
  const message = useMessage();
  const dialog = useDialog();

  const actionRef = ref();
  const editRef = ref();

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
            type: 'error',
            label: '删除',
            size: 'tiny',
            icon: DeleteOutlined,
            onClick: handleDelete.bind(null, record),
          },
        ],
      });
    },
  });

  const loadDataTable = async (res: any) => {
    return await getPage({ ...res });
  };

  function reloadTable() {
    actionRef.value.reload();
  }

  function handleDelete(record: Log) {
    dialog.info({
      title: '提示',
      content: `您确定删除 ${record.operation} 日志？`,
      positiveText: '确定',
      negativeText: '取消',
      onPositiveClick: async () => {
        await del(record.id);
        message.success('删除成功');
        reloadTable();
      },
      onNegativeClick: () => {},
    });
  }
</script>

<style lang="less" scoped></style>
