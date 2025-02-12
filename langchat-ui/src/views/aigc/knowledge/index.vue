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
  import { onMounted, ref } from 'vue';
  import { BasicForm, useForm } from '@/components/Form/index';
  import { del, page as getPage } from '@/api/aigc/knowledge';
  import { searchSchemas } from './columns';
  import Edit from './edit.vue';
  import { useDialog, useMessage } from 'naive-ui';
  import { useRouter } from 'vue-router';
  import SvgIcon from '@/components/SvgIcon/index.vue';

  const router = useRouter();
  const message = useMessage();
  const dialog = useDialog();
  const editRef = ref();
  const loading = ref(true);
  const pagination = ref({
    total: 0,
    page: 1,
    limit: 10,
  });
  const list = ref<any>([]);
  const actionOptions = [
    {
      label: '修改',
      value: 'edit',
    },
    {
      label: '删除',
      value: 'delete',
    },
  ];

  const [register, { getFieldsValue }] = useForm({
    gridProps: { cols: '1 s:1 m:2 l:3 xl:4 2xl:4' },
    labelWidth: 100,
    schemas: searchSchemas,
    showAdvancedButton: false,
  });

  onMounted(async () => {
    await fetch();
  });
  async function fetch() {
    const data = await getPage({ ...getFieldsValue(), ...pagination.value });
    pagination.value.total = data.total;
    list.value = data.rows;
    loading.value = false;
  }

  async function handleInfo(record: any) {
    await router.push('/aigc/knowledge/' + record.id);
  }

  function handleAdd() {
    editRef.value.show();
  }

  function onSelectAction(key, item) {
    if (key === 'edit') {
      handleEdit(item);
    }
    if (key === 'delete') {
      handleDelete(item);
    }
  }

  function handleEdit(record: any) {
    editRef.value.show(record.id);
  }

  function handleDelete(record: any) {
    dialog.info({
      title: '提示',
      content: `您想删除 ${record.name}`,
      positiveText: '确定',
      negativeText: '取消',
      onPositiveClick: async () => {
        await del(String(record.id));
        message.success('删除成功');
        await fetch();
      },
      onNegativeClick: () => {},
    });
  }
</script>

<template>
  <section class="overflow-y-auto h-full px-3 py-4">
    <BasicForm @register="register" @reset="fetch" @submit="fetch" />

    <n-spin :show="loading">
      <div class="grid grid-cols-1 gap-4 md:grid-cols-2 xl:grid-cols-4 mb-8">
        <div
          class="bg-gray-100 py-3 pt-4 transition-all duration-300 px-2 transform border cursor-pointer rounded-xl group"
        >
          <div class="font-bold text-xs mb-1.5 px-6 text-gray-500">创建知识库</div>
          <div
            class="w-full transition-all hover:bg-white rounded-lg py-1.5 px-6 flex items-center gap-1 font-medium hover:text-blue-500"
            @click="handleAdd"
          >
            <SvgIcon icon="line-md:file-plus" />
            <span class="text-sm">创建空白知识库</span>
          </div>
        </div>

        <n-empty v-if="list == null || list.length == 0" class="mt-10" />

        <div
          v-for="item in list"
          :key="item.id"
          class="bg-white px-4 py-3 pt-4 relative transition-all duration-300 transform border cursor-pointer rounded-xl hover:border-transparent group hover:shadow-lg"
          @click="handleInfo(item)"
        >
          <div class="flex flex-col sm:-mx-4 sm:flex-row">
            <div class="sm:mx-4">
              <div class="relative bg-blue-100 p-4 rounded-lg">
                <SvgIcon class="text-3xl" icon="ep:document" />
              </div>
            </div>

            <div class="pr-4 w-full">
              <div class="flex items-center justify-between">
                <h1 class="text-lg font-semibold text-gray-700 capitalize"> {{ item.name }} </h1>
              </div>

              <p class="mt-2 text-gray-500 capitalize text-xs">
                {{ item.des }}
              </p>
            </div>
          </div>

          <div class="flex mt-6 px-2 text-gray-400 justify-between items-center">
            <div class="flex items-center gap-1">
              <SvgIcon class="" icon="mdi:tag-outline" />
              <span class="text-xs">文档数:{{ item.docsNum }}</span>
              <n-divider class="!m-0.5" vertical />
              <span class="text-xs">{{ (Number(item.totalSize) / 1000000).toFixed(2) }} MB</span>
              <n-divider class="!m-0.5" vertical />
              <SvgIcon icon="material-symbols:database-outline" />
              <span v-if="item.embedStore != null" class="!text-xs">
                {{ item.embedStore.name }}
              </span>
            </div>
            <div class="flex items-center">
              <n-popselect
                :options="actionOptions"
                @update:value="(key) => onSelectAction(key, item)"
              >
                <n-button text>
                  <SvgIcon class="text-xl" icon="heroicons-outline:dots-horizontal" />
                </n-button>
              </n-popselect>
            </div>
          </div>
        </div>
      </div>

      <div class="flex justify-end my-2 mt-8">
        <n-pagination
          v-model:page="pagination.page"
          :item-count="pagination.total"
          show-quick-jumper
          show-size-picker
          size="medium"
        >
          <template #prefix="{}"> 共 {{ pagination.total }} 条 </template>
        </n-pagination>
      </div>
    </n-spin>

    <Edit ref="editRef" @reload="fetch" />
  </section>
</template>

<style lang="less" scoped></style>
