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
  import { BasicTable, TableAction } from '@/components/Table';
  import { DeleteOutlined, EditOutlined, PlusOutlined } from '@vicons/antd';
  import Edit from './edit.vue';
  import { computed, h, nextTick, reactive, ref } from 'vue';
  import { getColumns } from './columns';
  import { LLMProviders } from './data';
  import { del, list as getModels } from '@/api/aigc/model';
  import { useDialog, useMessage } from 'naive-ui';
  import { ModelTypeEnum } from '@/api/models';

  const provider = ref('');
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
            onClick: handleDel.bind(null, record),
          },
        ],
      });
    },
  });

  const columns = computed(() => {
    nextTick();
    return getColumns(provider.value);
  });
  const loadDataTable = async (params: any) => {
    if (provider.value === '') {
      provider.value = LLMProviders[0].model;
    }
    return await getModels({ ...params, provider: provider.value, type: ModelTypeEnum.CHAT });
  };
  async function handleAdd() {
    editRef.value.show({ provider: provider.value });
  }

  function handleEdit(record: any) {
    editRef.value.show(record);
  }

  function reloadTable() {
    actionRef.value.reload();
  }

  function handleDel(record: any) {
    dialog.warning({
      title: '警告',
      content: `你确定删除 [${record.name}] 模型吗？删除之后不可再用该模型对话`,
      positiveText: '确定',
      negativeText: '不确定',
      onPositiveClick: async () => {
        await del(record.id);
        reloadTable();
        message.success('模型删除成功');
      },
    });
  }
</script>

<template>
  <div class="flex gap-5">
    <div class="w-52 flex flex-col gap-4 py-1">
      <div class="font-bold text-base">聊天模型列表</div>
      <n-menu
        v-model:value="provider"
        :key-field="'model'"
        :label-field="'name'"
        :options="LLMProviders"
        class="model-menu"
        @update:value="reloadTable"
      />
    </div>

    <div class="w-full">
      <n-alert
        class="w-full mb-4 mt-2 min-alert"
        title="如果使用第三方代理，需要填写BaseUrl，并增加`/v1`后缀，官方Key不需要设置BaseUrl"
        type="info"
      />
      <BasicTable
        ref="actionRef"
        :actionColumn="actionColumn"
        :columns="columns"
        :pagination="false"
        :request="loadDataTable"
        :row-key="(row:any) => row.model"
        :single-line="false"
        default-expand-all
      >
        <template #tableTitle>
          <n-button type="primary" @click="handleAdd">
            <template #icon>
              <n-icon>
                <PlusOutlined />
              </n-icon>
            </template>
            新增模型
          </n-button>
        </template>
      </BasicTable>

      <Edit ref="editRef" :provider="provider" @reload="reloadTable" />
    </div>
  </div>
</template>

<style lang="less" scoped></style>
