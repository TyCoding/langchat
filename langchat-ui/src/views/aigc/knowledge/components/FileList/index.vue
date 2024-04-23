<template>
  <n-card>
    <BasicForm
      ref="actionRef"
      :actionColumn="actionColumn"
      :columns="[...columns, ...embedColumn]"
      :request="loadDataTable"
      :row-key="(row:any) => row.id"
      @register="register"
      @reset="handleReset"
      @submit="reloadTable"
    />
  </n-card>
</template>

<script lang="ts" setup>
  import { h, reactive, ref } from 'vue';
  import { BasicColumn, TableAction } from '@/components/Table';
  import { BasicForm, useForm } from '@/components/Form/index';
  import { del, page as getPage } from '@/api/aigc/kb-file';
  import { columns, searchSchemas } from './columns';
  import { DeleteOutlined, EditOutlined } from '@vicons/antd';
  import { NSwitch, useDialog, useMessage } from 'naive-ui';
  import { useRouter } from 'vue-router';
  import { KbFile } from '@/api/models/flow';

  const router = useRouter();
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

  const [register, { getFieldsValue, setFieldsValue }] = useForm({
    gridProps: { cols: '1 s:1 m:2 l:3 xl:4 2xl:4' },
    labelWidth: 80,
    schemas: searchSchemas,
  });

  const loadDataTable = async (res: any) => {
    const kbId = router.currentRoute.value.params.id;
    return await getPage({ ...getFieldsValue(), ...res, kbId });
  };

  function reloadTable() {
    actionRef.value.reload();
  }

  const embedColumn: BasicColumn<KbFile>[] = [
    {
      title: '是否Embed',
      key: 'isEmbed',
      align: 'center',
      width: 100,
      render(row) {
        return h(NSwitch, {
          size: 'small',
          'v-model:value': row.isEmbed,
          'onUpdate:value': (val) => embedHandler(val, row),
        });
      },
    },
  ];
  function embedHandler(val: boolean, row: KbFile) {
    console.log('点击了', val, row);
  }

  function handleAdd() {
    editRef.value.show();
  }

  function handleEdit(record: Recordable) {
    editRef.value.show(record.id);
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

<style lang="less" scoped></style>
