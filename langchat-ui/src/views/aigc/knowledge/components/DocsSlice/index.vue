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
  import { h, onMounted, reactive, ref } from 'vue';
  import { BasicTable, TableAction } from '@/components/Table';
  import { BasicForm, useForm } from '@/components/Form';
  import { del, page as getPage } from '@/api/aigc/slice';
  import { columns, searchSchemas } from './columns';
  import { DeleteOutlined } from '@vicons/antd';
  import { useDialog, useMessage } from 'naive-ui';
  import { useRouter } from 'vue-router';
  import { list } from '@/api/aigc/docs';

  const router = useRouter();
  const message = useMessage();
  const dialog = useDialog();
  const actionRef = ref();
  const docsList = ref();

  const actionColumn = reactive({
    width: 70,
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
  onMounted(async () => {
    docsList.value = await list({});
  });

  const loadDataTable = async (res: any) => {
    const knowledgeId = router.currentRoute.value.params.id;
    return await getPage({ ...getFieldsValue(), ...res, knowledgeId });
  };

  function reloadTable() {
    actionRef.value.reload();
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
  function handleSelectDocs(val: string) {
    console.log(val);
  }
</script>

<template>
  <n-card>
    <BasicForm @register="register" @reset="handleReset" @submit="reloadTable">
      <template #docsSlot="{ model, field }">
        <n-select
          v-model:value="model[field]"
          :options="docsList"
          filterable
          clearable
          label-field="name"
          value-field="id"
          placeholder="请选择文档查询"
          @update:value="handleSelectDocs"
        />
      </template>
    </BasicForm>

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
</template>

<style lang="less" scoped></style>
