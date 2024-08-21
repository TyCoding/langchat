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
  import { onMounted, ref, toRaw } from 'vue';
  import { del, list as getList } from '@/api/app/app';
  import router from '@/router';

  const ms = useMessage();
  const dialog = useDialog();
  const editRef = ref();
  const list = ref();
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
    await router.push('/aigc/app/info/' + item.id);
  }

  function handleAdd() {
    editRef.value.show();
  }
  function handleEdit(record: Recordable) {
    editRef.value.show(toRaw(record.id));
  }
</script>

<template>
  <section class="overflow-y-auto h-full">
    <div class="n-layout-page-header">
      <n-card :bordered="false" title="应用配置能力">
        LangChat支持动态增加各种AIGC应用，通过自定义Prompt和关联知识库来实现个性机器人
      </n-card>
    </div>

    <n-card :bordered="false" class="my-4 pb-4">
      <div class="mb-4">
        <n-button dashed type="primary" @click="handleAdd">新增应用</n-button>
      </div>

      <Edit ref="editRef" @reload="fetchData" />

      <div class="grid grid-cols-1 gap-6 md:grid-cols-2 xl:grid-cols-4">
        <div
          v-for="item in list"
          :key="item.id"
          class="bg-white px-4 shadow-md py-3 pt-4 transition-colors duration-300 transform border cursor-pointer rounded-xl hover:border-transparent group hover:shadow-lg"
          @click="onInfo(item)"
        >
          <div class="flex flex-col sm:-mx-4 sm:flex-row">
            <img
              :src="item.cover == null ? '/src/assets/icons/app.png' : item.cover"
              class="flex-shrink-0 object-cover w-16 h-16 rounded sm:mx-4"
            />

            <div class="pr-4">
              <h1 class="text-lg font-semibold text-gray-700 capitalize"> {{ item.name }} </h1>

              <p class="mt-2 text-gray-500 capitalize text-xs">
                {{ item.des }}
              </p>
            </div>
          </div>

          <div class="flex mt-4 -mx-2 px-4 text-gray-400 justify-between items-center">
            <div class="flex items-center">
              <div v-if="item.model != null" class="flex items-center">
                <SvgIcon class="text-xl" icon="bitcoin-icons:magic-wand-outline" />
                <span>{{ item.model.model }}</span>
              </div>
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
    </n-card>
  </section>
</template>

<style lang="less" scoped></style>
