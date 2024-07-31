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
  import Edit from './edit.vue';
  import { del as delApi, list as getApiList } from '@/api/app/appApi';
  import { del as delWeb, list as getWebList } from '@/api/app/appWeb';
  import SvgIcon from '@/components/SvgIcon/index.vue';
  import { useDialog, useMessage } from 'naive-ui';
  import { copyToClip } from '@/utils/copy';
  import { ChannelEnum, getKey, onInfo, renderIcon, renderTitle } from '@/views/app/columns';

  const editRef = ref();
  const dialog = useDialog();
  const ms = useMessage();
  const groups = ref<any[]>([]);

  onMounted(async () => {
    await fetchData();
  });

  async function fetchData() {
    const apiArr = await getApiList({});
    const webArr = await getWebList({});
    groups.value = [
      { key: ChannelEnum.CHANNEL_API, value: apiArr as any[] },
      { key: ChannelEnum.CHANNEL_WEB, value: webArr as any[] },
      { key: ChannelEnum.CHANNEL_WEIXIN, value: [] as any[] },
      { key: ChannelEnum.CHANNEL_DING, value: [] as any[] },
    ];
  }

  function onDelete(channel: string, id: string) {
    dialog.info({
      title: '提示',
      content: '你确定要删除此条配置吗？',
      positiveText: '确定',
      negativeText: '取消',
      onPositiveClick: async () => {
        if (channel === 'CHANNEL_API') {
          await delApi(id);
        }
        if (channel === 'CHANNEL_WEB') {
          await delWeb(id);
        }
        if (channel === 'CHANNEL_WEIXIN') {
        }
        await fetchData();
        ms.success('删除成功');
      },
    });
  }

  async function onCopy(key: string) {
    await copyToClip(key);
    ms.success('Api Key复制成功');
  }
</script>

<template>
  <div class="dot-bg overflow-y-auto">
    <div class="n-layout-page-header">
      <n-card :bordered="false" title="应用集成能力">
        简单配置应用，快速通过IFRAME嵌套、HTTP接口、微信、钉钉等多种渠道接入LangChat
      </n-card>
    </div>

    <div class="mt-2 w-full mb-10 px-6 !h-auto">
      <div class="flex items-center gap-2 justify-start">
        <n-button dashed type="primary" @click="editRef.show()"> 新增应用 </n-button>
        <n-button tertiary type="primary" @click="fetchData">
          <SvgIcon class="text-xl" icon="material-symbols:refresh" />
        </n-button>
      </div>

      <div v-for="items in groups" :key="items">
        <n-alert :bordered="false" :title="renderTitle(items.key)" class="mt-4 mb-2" type="info">
          <template #icon>
            <component :is="renderIcon(items.key)" />
          </template>
        </n-alert>
        <n-empty v-if="items.value.length === 0" class="mt-6" description="暂时没有配置" />
        <div class="grid gap-2 sm:grid-cols-3 lg:grid-cols-5">
          <n-card
            v-for="item in items.value"
            :key="item.id"
            class="rounded-lg cursor-pointer p-1 app-card relative"
            hoverable
          >
            <div class="absolute right-2 top-3">
              <SvgIcon
                class="text-xl text-red-500 hover:text-red-600"
                icon="ant-design:delete-outlined"
                @click="onDelete(items.key, item.id)"
              />
            </div>
            <div class="flex flex-col h-full">
              <div class="flex-1">
                <div class="flex items-center justify-center">
                  <component :is="renderIcon(items.key)" />
                </div>

                <div class="mt-2 text-center">
                  <h3
                    id="modal-title"
                    class="text-lg font-medium leading-6 text-gray-800 capitalize hover:text-blue-500"
                    @click="onInfo(item)"
                  >
                    {{ item.name }}
                  </h3>
                  <p class="mt-2 text-sm text-gray-500">
                    {{ item.des }}
                  </p>
                </div>
              </div>

              <div class="flex items-center justify-between w-full mt-3 gap-x-2">
                <input
                  v-if="items.key === 'CHANNEL_API'"
                  :value="getKey(item.apiKey)"
                  class="flex-1 block h-8 px-4 text-sm text-gray-700 bg-white border border-gray-200 rounded-md focus:border-blue-400 focus:ring-blue-300 focus:ring-opacity-40 focus:outline-none focus:ring"
                  type="text"
                />
                <button
                  class="rounded-md hidden sm:block p-1.5 text-gray-700 bg-white border border-gray-200 focus:border-blue-400 focus:ring-blue-300 focus:ring-opacity-40 focus:outline-none focus:ring transition-colors duration-300 hover:text-blue-500 dark:hover:text-blue-500"
                  @click="onCopy(item.apiKey)"
                >
                  <SvgIcon class="text-lg" icon="uil:copy" />
                </button>

                <button
                  class="rounded-md hidden sm:block p-1.5 text-gray-700 bg-blue-100 focus:ring-opacity-40 focus:outline-none focus:ring transition-colors duration-300 hover:text-blue-500 dark:hover:text-blue-500"
                  @click="onInfo(item)"
                >
                  <SvgIcon class="text-lg text-blue-500" icon="uil:setting" />
                </button>
              </div>
            </div>
          </n-card>
        </div>
      </div>
    </div>

    <Edit ref="editRef" @reload="fetchData" />
  </div>
</template>

<style lang="less">
  .app-card {
    .n-card__content {
      padding: 14px 10px !important;
    }
  }
</style>
