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
  import { BasicForm, useForm } from '@/components/Form';
  import { del, page as getPage, reEmbed } from '@/api/aigc/docs';
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
            onClick: handleReEmbed.bind(null, record),
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

  function handleReEmbed(record: Recordable) {
    dialog.info({
      title: '提示',
      content: `您确定重新向量化该文档？此操作将会删除Vector向量库中旧数据`,
      positiveText: '确定',
      negativeText: '取消',
      onPositiveClick: async () => {
        await reEmbed(record.id);
        message.success('文档向量化解析成功');
        reloadTable();
      },
      onNegativeClick: () => {},
    });
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
