<template>
  <div class="n-layout-page-header">
    <n-card :bordered="false" title="令牌信息管理"> 系统中所有已经生成的令牌数据。 </n-card>
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

  <Edit ref="editRef" @reload="reloadTable" />
</template>

<script lang="ts" setup>
  import { h, reactive, ref } from 'vue';
  import { BasicTable, TableAction } from '@/components/Table';
  import { page as getPage, del } from '@/api/auth';
  import { columns } from './columns';
  import { DeleteOutlined, EyeOutlined } from '@vicons/antd';
  import Edit from './edit.vue';
  import { useDialog, useMessage } from 'naive-ui';
  import { Token } from '@/api/models/auth';
  const message = useMessage();
  const dialog = useDialog();

  const actionRef = ref();
  const editRef = ref();

  const actionColumn = reactive({
    width: 160,
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
            label: '详情',
            size: 'tiny',
            icon: EyeOutlined,
            onClick: handleEdit.bind(null, record),
          },
          {
            type: 'error',
            label: '下线',
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

  function handleEdit(record: Token) {
    editRef.value.show(record);
  }

  function handleDelete(record: Token) {
    dialog.info({
      title: '提示',
      content: `您确定强制下线 ${record.principal.username} 账户？`,
      positiveText: '确定',
      negativeText: '取消',
      onPositiveClick: async () => {
        await del(record.value);
        message.success('下线成功');
        reloadTable();
      },
      onNegativeClick: () => {},
    });
  }
</script>

<style lang="less" scoped></style>
