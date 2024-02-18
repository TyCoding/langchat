<script setup lang="ts">
  import { h, onMounted, ref } from 'vue';
  import { SvgIcon } from '@/components/common';
  import { getBotPage, del } from '@/api/prompt';
  import { NButton, NPopconfirm, NSpace } from 'naive-ui';
  import { t } from '@/locales';
  import Edit from './Edit.vue';

  const columns = [
    {
      title: t('prompt.name'),
      key: 'name',
      align: 'center',
    },
    {
      title: t('prompt.prompt'),
      key: 'prompt',
      align: 'center',
    },
    {
      title: t('common.createTime'),
      key: 'createTime',
      align: 'center',
    },
    {
      title: t('common.action'),
      key: 'actions',
      align: 'center',
      render(row: any) {
        return h(NSpace, { justify: 'center' }, () => [
          h(
            NButton,
            {
              tertiary: true,
              size: 'small',
              type: 'warning',
              onClick: () => {
                editRef.value.onShow(row.id);
              },
            },
            () => t('common.edit')
          ),
          h(
            NPopconfirm,
            {
              onPositiveClick: async () => {
                loading.value = true;
                await del(row.id);
                await onFetch();
                loading.value = false;
              },
            },
            {
              trigger: () =>
                h(
                  NButton,
                  {
                    tertiary: true,
                    size: 'small',
                    type: 'error',
                  },
                  () => t('common.delete')
                ),
              default: () => t('common.deleteWarn'),
            }
          ),
        ]);
      },
    },
  ];
  const searchForm = ref<{
    name: string;
  }>({
    name: '',
  });
  const loading = ref(true);
  const data = ref([]);
  const pagination = ref({ pageCount: 0, page: 1, pageSizes: [10] });
  const editRef = ref();

  onMounted(async () => {
    await onFetch();
  });

  async function onFetch() {
    loading.value = true;
    const { page, pageSizes } = pagination.value;
    const { rows, total } = await getBotPage({ page, limit: pageSizes[0], ...searchForm.value });
    data.value = rows;
    pagination.value.pageCount = total;
    loading.value = false;
  }

  function onClear() {
    searchForm.value = { name: '' };
  }
</script>

<template>
  <n-spin :show="loading" class="w-full h-full">
    <div class="p-4 pl-9">
      <div class="pt-3 pb-3 text-lg">{{ t('prompt.title') }}</div>
    </div>
    <div class="p-8 pt-0">
      <n-card class="mb-4">
        <div class="flex justify-between items-center">
          <n-input v-model:value="searchForm.name" style="width: 300px" />
          <div class="flex justify-center items-center gap-2">
            <n-button @click="editRef.onShow()" type="info" secondary>
              <template #icon>
                <SvgIcon icon="material-symbols:add" />
              </template>
              {{ t('common.add') }}
            </n-button>

            <n-button @click="onFetch" type="primary" secondary>
              <template #icon>
                <SvgIcon icon="material-symbols:search" />
              </template>
              {{ t('common.search') }}
            </n-button>

            <n-button @click="onClear" secondary>
              <template #icon>
                <SvgIcon icon="iconamoon:trash" />
              </template>
              {{ t('common.clear') }}
            </n-button>

            <n-button @click="onFetch" quaternary>
              <template #icon>
                <SvgIcon icon="material-symbols:refresh" />
              </template>
            </n-button>
          </div>
        </div>
      </n-card>
      <n-data-table
        :columns="columns"
        :data="data"
        :pagination="pagination"
        :single-line="false"
        :striped="true"
      />

      <Edit @fetch="onFetch" ref="editRef" />
    </div>
  </n-spin>
</template>

<style scoped lang="less"></style>
