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

<template>
  <div>
    <div class="n-layout-page-header">
      <n-card :bordered="false" title="令牌信息管理"> 系统中所有已经生成的令牌数据。 </n-card>
    </div>

    <n-card :bordered="false" class="mt-4">
      <BasicTable
        ref="actionRef"
        :actionColumn="actionColumn"
        :columns="columns"
        :request="loadDataTable"
        :row-key="(row:any) => row.id"
        :single-line="false"
        :size="'small'"
      />
    </n-card>

    <Edit ref="editRef" @reload="reloadTable" />
  </div>
</template>

<script lang="ts" setup>
  import { h, reactive, ref } from 'vue';
  import { BasicTable, TableAction } from '@/components/Table';
  import { delToken, getTokens } from '@/api/auth';
  import { columns } from './columns';
  import { DeleteOutlined, EyeOutlined } from '@vicons/antd';
  import Edit from './edit.vue';
  import { useDialog, useMessage } from 'naive-ui';

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
            icon: EyeOutlined,
            onClick: handleEdit.bind(null, record),
          },
          {
            type: 'error',
            label: '下线',
            icon: DeleteOutlined,
            onClick: handleDelete.bind(null, record),
          },
        ],
      });
    },
  });

  const loadDataTable = async (res: any) => {
    return await getTokens({ ...res });
  };

  function reloadTable() {
    actionRef.value.reload();
  }

  function handleEdit(record: any) {
    editRef.value.show(record);
  }

  function handleDelete(record: any) {
    dialog.info({
      title: '提示',
      content: `您确定强制下线 ${record.username} 账户？`,
      positiveText: '确定',
      negativeText: '取消',
      onPositiveClick: async () => {
        await delToken(record.token);
        message.success('下线成功');
        reloadTable();
      },
      onNegativeClick: () => {},
    });
  }
</script>

<style lang="less" scoped></style>
