<script lang="ts" setup>
  import { h, reactive, ref } from 'vue';
  import { BasicTable, TableAction } from '@/components/Table';
  import { BasicForm, useForm } from '@/components/Form';
  import { del, page as getPage } from '@/api/aigc/docs';
  import { columns, searchSchemas } from './columns';
  import { AlbumsOutline } from '@vicons/ionicons5';
  import { DeleteOutlined, EditOutlined } from '@vicons/antd';
  import Edit from './edit.vue';
  import { useDialog, useMessage } from 'naive-ui';
  import { useRouter } from 'vue-router';

  const router = useRouter();
  const message = useMessage();
  const dialog = useDialog();
  const actionRef = ref();
  const editRef = ref();

  const actionColumn = reactive({
    width: 150,
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
            icon: AlbumsOutline,
            onClick: handleEdit.bind(null, record),
          },
          {
            type: 'info',
            icon: EditOutlined,
            onClick: handleEdit.bind(null, record),
          },
          {
            type: 'error',
            icon: DeleteOutlined,
            onClick: handleDelete.bind(null, record),
          },
        ],
      });
    },
  });

  const [register, { getFieldsValue }] = useForm({
    gridProps: { cols: '1 s:1 m:2 l:3 xl:4 2xl:4' },
    labelWidth: 80,
    showAdvancedButton: false,
    schemas: searchSchemas,
  });

  const loadDataTable = async (res: any) => {
    const knowledgeId = router.currentRoute.value.params.id;
    return await getPage({ ...getFieldsValue(), ...res, knowledgeId });
  };

  function reloadTable() {
    actionRef.value.reload();
  }

  function handleEdit(record: Recordable) {
    editRef.value.show('', record.id);
  }

  function handleDelete(record: Recordable) {
    dialog.info({
      title: '提示',
      content: `您想删除 ${record.name}`,
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

  function handleReset(values: Recordable) {
    reloadTable();
  }
</script>

<template>
  <n-card>
    <BasicForm @register="register" @reset="handleReset" @submit="reloadTable" />

    <BasicTable
      ref="actionRef"
      :actionColumn="actionColumn"
      :columns="columns"
      :request="loadDataTable"
      :row-key="(row:any) => row.id"
      :single-line="false"
      :size="'small'"
    />

    <Edit ref="editRef" @reload="reloadTable" />
  </n-card>
</template>

<style lang="less" scoped></style>
