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
  import Edit from './edit.vue';
  import { useDialog, useMessage } from 'naive-ui';
  import SvgIcon from '@/components/SvgIcon/index.vue';
  import { h, onMounted, ref, toRaw } from 'vue';
  import { del, list as getList } from '@/api/aigc/app';
  import router from '@/router';

  const ms = useMessage();
  const dialog = useDialog();
  const editRef = ref();
  const list = ref();

  onMounted(async () => {
    await fetchData();
  });

  async function fetchData() {
    list.value = await getList({});
  }

  function onSelectAction(key, item) {
    if (key === 'edit') {
      handleEdit(item);
    }
    if (key === 'delete') {
      dialog.info({
        title: '提示',
        content: `您确定删除 ${item.name} 应用？`,
        positiveText: '确定',
        negativeText: '取消',
        onPositiveClick: async () => {
          await del(item.id);
          ms.success('删除成功');
          await fetchData();
        },
        onNegativeClick: () => {},
      });
    }
  }

  async function onInfo(item) {
    await router.push('/app/info/' + item.id);
  }

  function handleAdd() {
    editRef.value.show();
  }
  function handleEdit(record: Recordable) {
    editRef.value.show(toRaw(record.id));
  }

  function getOptions(item: any) {
    return [
      { label: '编辑此应用', key: 'edit' },
      { type: 'divider' },
      { key: 'delete', label: '删除此应用' },
      { type: 'divider' },
      {
        key: 'header',
        type: 'render',
        render: () => {
          return h(
            'div',
            {
              class: 'flex flex-col gap-1 px-3.5 py-2',
            },
            [
              h('span', null, '信息'),
              h(
                'span',
                {
                  class: 'text-xs text-stone-500',
                },
                `创建时间：${item.createTime}`
              ),
              h(
                'span',
                {
                  class: 'text-xs mt-1 text-blue-500',
                },
                `LangChat Apps`
              ),
            ]
          );
        },
      },
    ];
  }
  const activeDropdownId = ref(null);
  const handleDropdownShow = (show: boolean, itemId) => {
    activeDropdownId.value = show ? itemId : null;
  };
</script>

<template>
  <section class="overflow-y-auto h-full px-3 py-4">
    <div class="grid grid-cols-1 gap-6 md:grid-cols-2 xl:grid-cols-4 mb-8">
      <div
        class="bg-[#eceef0] py-3 pt-4 transition-all duration-300 px-2 hover:border hover:border-blue-400 border border-transparent cursor-pointer rounded-xl group"
      >
        <div class="font-bold text-xs mb-1.5 px-6 text-gray-500">创建应用</div>
        <div
          class="w-full transition-all hover:bg-white rounded-lg py-1.5 px-6 flex items-center gap-1 font-medium hover:text-blue-500"
          @click="handleAdd"
        >
          <SvgIcon icon="line-md:file-plus" />
          <span class="text-sm">创建空白应用</span>
        </div>
      </div>

      <div
        v-for="item in list"
        :key="item.id"
        :class="[activeDropdownId === item.id ? '!border-blue-400' : '']"
        class="bg-white px-4 py-3 pt-4 transition-all hover:border hover:border-blue-400 border border-transparent duration-300 transform cursor-pointer rounded-xl group"
        @click="onInfo(item)"
      >
        <div class="flex flex-col sm:-mx-4 sm:flex-row">
          <div class="sm:mx-4">
            <div class="relative bg-orange-100 p-4 rounded-lg">
              <SvgIcon class="text-3xl" icon="prime:microchip-ai" />

              <div
                class="absolute bottom-[-6px] p-1 right-[-5px] shadow bg-white mx-auto rounded-lg"
              >
                <SvgIcon class="text-sm text-orange-500" icon="lucide:bot" />
              </div>
            </div>
          </div>

          <div class="pr-4">
            <h1 class="text-lg font-semibold text-gray-700 capitalize"> {{ item.name }} </h1>

            <p class="mt-2 text-gray-500 capitalize text-xs">
              {{ item.des }}
            </p>
          </div>
        </div>

        <div class="flex mt-4 -mx-2 px-2 text-gray-400 justify-between items-center">
          <div class="flex items-center gap-1">
            <SvgIcon class="" icon="mdi:tag-outline" />
            <span v-if="item.model != null" class="text-xs">{{ item.model.model }}</span>
          </div>
          <div class="flex items-center" @click.stop>
            <n-dropdown
              :options="getOptions(item)"
              class="justify-start min-w-[160px] transition-all"
              placement="bottom-end"
              size="small"
              trigger="click"
              @select="(key) => onSelectAction(key, item)"
              @update:show="(show) => handleDropdownShow(show, item.id)"
            >
              <div
                :class="[activeDropdownId === item.id ? 'bg-gray-200' : 'hover:bg-gray-200']"
                class="rounded p-1 transition-all"
              >
                <SvgIcon class="w-5 h-5" icon="ri:more-fill" />
              </div>
            </n-dropdown>
          </div>
        </div>
      </div>
    </div>

    <Edit ref="editRef" @reload="fetchData" />
  </section>
</template>

<style lang="less" scoped></style>
