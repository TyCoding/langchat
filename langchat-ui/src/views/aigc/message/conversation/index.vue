<script lang="ts" setup>
  import { h, reactive, ref } from 'vue';
  import { BasicTable, TableAction } from '@/components/Table';
  import { BasicForm, useForm } from '@/components/Form/index';
  import { del, page as getPage } from '@/api/aigc/conversation';
  import { columns, searchSchemas } from './columns';
  import { DeleteOutlined, EyeOutlined } from '@vicons/antd';
  import InfoList from './components/InfoList.vue';
  import { useDialog, useMessage } from 'naive-ui';

  const message = useMessage();
  const dialog = useDialog();

  const actionRef = ref();
  const infoRef = ref();

  const actionColumn = reactive({
    width: 80,
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
            icon: EyeOutlined,
            onClick: handleShowInfo.bind(null, record),
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

  const [register, { getFieldsValue, setFieldsValue }] = useForm({
    gridProps: { cols: '1 s:1 m:2 l:3 xl:4 2xl:4' },
    labelWidth: 80,
    schemas: searchSchemas,
    showAdvancedButton: false,
  });

  const loadDataTable = async (res: any) => {
    return await getPage({ ...getFieldsValue(), ...res });
  };

  function reloadTable() {
    actionRef.value.reload();
  }

  function handleShowInfo(row: any) {
    infoRef.value.show(row);
  }

  function handleDelete(record: Recordable) {
    dialog.info({
      title: '提示',
      content: `您想删除 ${record.title}`,
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
  <div class="mt-2">
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

    <InfoList ref="infoRef" />
  </div>
</template>

<style lang="less" scoped></style>
