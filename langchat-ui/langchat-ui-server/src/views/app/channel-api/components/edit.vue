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
  import SvgIcon from '@/components/SvgIcon/index.vue';
  import { createApi, del, list as getApiList } from '@/api/app/appApi';
  import { useDialog, useMessage } from 'naive-ui';
  import { useRouter } from 'vue-router';
  import { copyToClip } from '@/utils/copy';
  import { BasicTable, TableAction } from '@/components/Table';
  import { CopyOutlined, DeleteOutlined } from '@vicons/antd';
  import { hideKey } from '@/api/models';

  const emit = defineEmits(['reload']);
  const ms = useMessage();
  const dialog = useDialog();
  const router = useRouter();
  const actionRef = ref();

  async function onSubmit() {
    await createApi(router.currentRoute.value.params.id);
    ms.success('新增成功');
    reloadTable();
  }

  async function onCopy(record: any) {
    await copyToClip(record.apiKey);
    ms.success('秘钥复制成功');
  }

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
            label: '复制',
            size: 'tiny',
            icon: CopyOutlined,
            onClick: onCopy.bind(null, record),
          },
          {
            type: 'error',
            label: '删除',
            size: 'tiny',
            icon: DeleteOutlined,
            onClick: handleDelete.bind(null, record),
          },
        ],
      });
    },
  });

  function handleDelete(item) {
    dialog.warning({
      title: '提示！',
      content: '你确定重置Key吗？删除后原Key将立即失效是，请谨慎操作',
      positiveText: '是',
      negativeText: '否',
      onPositiveClick: async () => {
        await del(item.id);
        ms.success('删除成功');
      },
    });
  }

  const loadDataTable = async (res: any) => {
    const data = await getApiList({ appId: router.currentRoute.value.params.id });
    data.forEach((item: any) => {
      item.apiKey = hideKey(item.apiKey);
    });
    return data;
  };

  const columns = [
    {
      title: '秘钥',
      key: 'apiKey',
      align: 'center',
    },
    {
      title: '创建时间',
      key: 'createTime',
      align: 'center',
      width: 180,
    },
  ];

  function reloadTable() {
    actionRef.value.reload();
  }
</script>

<template>
  <div class="bg-white p-4 rounded">
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
        <n-button size="small" type="primary" @click="onSubmit">
          <template #icon>
            <SvgIcon icon="ic:round-plus" />
          </template>
          创建秘钥
        </n-button>
      </template>
    </BasicTable>
  </div>
</template>

<style lang="less" scoped></style>
