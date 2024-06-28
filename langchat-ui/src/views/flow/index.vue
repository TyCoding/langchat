<script lang="ts" setup>
  import { h, onMounted, onUnmounted, reactive, ref } from 'vue';
  import { BasicTable, TableAction } from '@/components/Table';
  import { BasicForm, useForm } from '@/components/Form/index';
  import { add, del, page as getPage } from '@/api/aigc/flow';
  import { columns, searchSchemas } from './columns';
  import { DeleteOutlined, EditOutlined, PlusOutlined } from '@vicons/antd';
  import { CreateOutline } from '@vicons/ionicons5';
  import Edit from '@/views/flow/edit.vue';
  import Coding from '@/views/flow/coding.vue';
  import { useDialog, useMessage, useNotification } from 'naive-ui';
  import { useRouter } from 'vue-router';
  import { isNullOrWhitespace } from '@/utils/is';
  import { renderIcon } from '@/utils';

  const router = useRouter();
  const message = useMessage();
  const notification = useNotification();
  const dialog = useDialog();
  const actionRef = ref();
  const editRef = ref();
  const codingRef = ref();

  onMounted(() => {
    notification.create({
      type: 'warning',
      title: 'About Workflows Design',
      content: `此模块主要为了流程化编排AI应用，提供一种高定制化流程的应用机器人。由于此模块的工作量会非常庞大，请耐心等待！`,
    });
  });
  onUnmounted(() => {
    notification.destroyAll();
  });

  const actionColumn = reactive({
    width: 170,
    title: '操作',
    key: 'action',
    fixed: 'right',
    align: 'center',
    render(record: any) {
      return h(TableAction as any, {
        style: 'text',
        actions: [
          {
            size: 'tiny',
            type: 'info',
            label: '脚本',
            onClick: handleCoding.bind(null, record),
          },
          {
            size: 'tiny',
            type: 'success',
            label: '编排',
            onClick: handleFlow.bind(null, record),
          },
          {
            size: 'tiny',
            type: 'warning',
            icon: EditOutlined,
            onClick: handleEdit.bind(null, record),
          },
          {
            size: 'tiny',
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

  function handleFlow(row: any) {
    if (isNullOrWhitespace(row.flow)) {
      router.push({ name: 'flow_initialize', params: { id: row.id } });
    } else {
      router.push({ name: 'flow_edit', params: { id: row.id } });
    }
  }

  async function handleAdd() {
    const data = await add({ name: 'New Flow Design' });
    await actionRef.value.reload();
    dialog.success({
      title: '创建成功',
      maskClosable: false,
      content: () =>
        h('div', {}, [
          'New Flow Design 已经创建完成，点击去编辑！',
          h('br'),
          h('span', {}, 'FlowId: '),
          h(
            'span',
            {
              style: { color: '#18a058' },
            },
            data.id
          ),
        ]),
      positiveText: '去编辑',
      positiveButtonProps: {
        renderIcon: renderIcon(CreateOutline),
      },
      onPositiveClick: () => {
        handleFlow(data);
      },
    });
  }

  function handleEdit(record: Recordable) {
    editRef.value.show(record.id);
  }

  function handleCoding(record: Recordable) {
    codingRef.value.show(record.id);
  }

  function handleDelete(record: Recordable) {
    dialog.info({
      title: '提示',
      content: `您想删除[${record.name}]流程？此操作将删除所有编排数据`,
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
  <n-card :bordered="false">
    <BasicForm @register="register" @submit="reloadTable" @reset="handleReset" />

    <BasicTable
      :single-line="false"
      :size="'small'"
      :columns="columns"
      :request="loadDataTable"
      :row-key="(row:any) => row.id"
      ref="actionRef"
      :actionColumn="actionColumn"
    >
      <template #tableTitle>
        <n-button type="primary" size="small" @click="handleAdd">
          <template #icon>
            <n-icon>
              <PlusOutlined />
            </n-icon>
          </template>
          新建
        </n-button>
      </template>
    </BasicTable>

    <Edit ref="editRef" @reload="reloadTable" />
    <Coding ref="codingRef" @reload="reloadTable" />
  </n-card>
</template>

<style lang="less" scoped></style>
