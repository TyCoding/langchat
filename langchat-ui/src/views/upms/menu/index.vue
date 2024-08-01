<!--
  - Copyright (c) 2024 LangChat. TyCoding All Rights Reserved.
  -
  - Licensed under the GNU Affero General Public License, Version 3 (the "License");
  - you may not use this file except in compliance with the License.
  - You may obtain a copy of the License at
  -
  -     https://www.gnu.org/licenses/agpl-3.0.html
  -
  - Unless required by applicable law or agreed to in writing, software
  - distributed under the License is distributed on an "AS IS" BASIS,
  - WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
  - See the License for the specific language governing permissions and
  - limitations under the License.
  -->

<script lang="ts" setup>
  import { h, reactive, ref } from 'vue';
  import { BasicTable, TableAction } from '@/components/Table';
  import { BasicForm, useForm } from '@/components/Form/index';
  import { del, tree as getPage } from '@/api/upms/menu';
  import { columns, searchSchemas } from './columns';
  import { DeleteOutlined, EditOutlined, PlusOutlined } from '@vicons/antd';
  import Edit from './edit.vue';
  import { useDialog, useMessage } from 'naive-ui';

  const message = useMessage();
  const dialog = useDialog();

  const actionRef = ref();
  const editRef = ref();

  const actionColumn = reactive({
    width: 120,
    title: '操作',
    key: 'action',
    fixed: 'right',
    align: 'center',
    render(record: any) {
      return h(TableAction as any, {
        style: 'text',
        actions: [
          {
            type: 'success',
            icon: PlusOutlined,
            onClick: handlePlus.bind(null, record),
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

  function handleAdd() {
    editRef.value.show();
  }

  function handleEdit(record: any) {
    editRef.value.show(record.id);
  }

  function handlePlus(record: any) {
    editRef.value.show(null, record.id);
  }

  function handleDelete(record: any) {
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

  function handleReset(values: any) {
    reloadTable();
  }
</script>

<template>
  <div class="h-full">
    <n-card :bordered="false">
      <BasicForm @register="register" @reset="handleReset" @submit="reloadTable" />

      <BasicTable
        ref="actionRef"
        :actionColumn="actionColumn"
        :columns="columns"
        :pagination="false"
        :request="loadDataTable"
        :row-key="(row:any) => row.id"
        :single-line="false"
        :size="'small'"
      >
        <template #tableTitle>
          <n-button type="primary" @click="handleAdd">
            <template #icon>
              <n-icon>
                <PlusOutlined />
              </n-icon>
            </template>
            新建菜单
          </n-button>
        </template>
      </BasicTable>
    </n-card>

    <Edit ref="editRef" @reload="reloadTable" />
  </div>
</template>

<style lang="less" scoped></style>
