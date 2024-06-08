<script lang="ts" setup>
  import { onMounted, ref } from 'vue';
  import { BasicForm, useForm } from '@/components/Form/index';
  import { del, page as getPage } from '@/api/aigc/knowledge';
  import { searchSchemas } from './columns';
  import {
    CloudOutlined,
    DeleteOutlined,
    EditOutlined,
    FolderOutlined,
    PlusOutlined,
  } from '@vicons/antd';
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

  function handleInfo(record: any) {
    router.push({ name: 'knowledge_info', params: { id: record.id } });
  }

  function handleAdd() {
    editRef.value.show();
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
  <n-card :bordered="false">
    <BasicForm @register="register" @reset="fetch" @submit="fetch" />

    <div>
      <n-button type="primary" @click="handleAdd">
        <template #icon>
          <n-icon>
            <PlusOutlined />
          </n-icon>
        </template>
        新增知识库
      </n-button>
    </div>

    <n-spin :show="loading">
      <n-empty v-if="list == null || list.length == 0" class="mt-10" />

      <ul class="mt-6 grid gap-8 sm:grid-cols-3 lg:grid-cols-4">
        <li v-for="(item, idx) in list" :key="idx" class="rounded-lg shadow-lg shadow-neutral-200">
          <div class="flex items-center justify-between p-4">
            <div class="flex items-center gap-2">
              <n-avatar v-if="item.cover" :src="item.cover" :size="48" round>
                <template #fallback>
                  <SvgIcon class="text-4xl" icon="twemoji:open-book" />
                </template>
              </n-avatar>
              <SvgIcon v-else class="text-4xl" icon="twemoji:open-book" />
              <h2
                @click="handleInfo(item)"
                class="text-gray-800 font-semibold cursor-pointer hover:text-blue-500"
              >
                {{ item.name }}
              </h2>
            </div>
            <button
              @click="handleInfo(item)"
              class="text-blue-500 text-sm border rounded-lg px-3 py-2 duration-150 hover:bg-gray-200 flex items-center gap-2"
            >
              <n-icon size="18">
                <FolderOutlined />
              </n-icon>
              <span>文档数：{{ item.docsNum }}</span>
            </button>
          </div>
          <div class="p-4 pt-2">
            <p class="text-gray-600 text-sm">
              <n-ellipsis class="w-full max-w-full">
                {{ item.des }}
              </n-ellipsis>
            </p>
          </div>
          <div class="py-3 px-4 border-t flex justify-between items-center">
            <div class="flex gap-1 items-center">
              <n-icon size="20"> <CloudOutlined /> </n-icon>
              <span class="text-sm"> {{ (Number(item.totalSize) / 1000000).toFixed(2) }} MB </span>
            </div>
            <div class="flex gap-3">
              <n-button @click="handleEdit(item)" text type="primary">
                <template #icon>
                  <n-icon>
                    <EditOutlined />
                  </n-icon>
                </template>
                修改
              </n-button>
              <n-button @click="handleDelete(item)" text type="error">
                <template #icon>
                  <n-icon>
                    <DeleteOutlined />
                  </n-icon>
                </template>
                删除
              </n-button>
            </div>
          </div>
        </li>
      </ul>

      <div class="flex justify-end my-2 mt-8">
        <n-pagination
          v-model:page="pagination.page"
          :item-count="pagination.total"
          size="medium"
          show-quick-jumper
          show-size-picker
        >
          <template #prefix="{}"> 共 {{ pagination.total }} 条 </template>
        </n-pagination>
      </div>
    </n-spin>

    <Edit ref="editRef" @reload="fetch" />
  </n-card>
</template>

<style lang="less" scoped></style>
