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
  import { h, onMounted, ref } from 'vue';
  import Edit from './edit.vue';
  import { del as delApi, list as getApiList } from '@/api/app/appApi';
  import { del as delWeb, list as getWebList } from '@/api/app/appWeb';
  import SvgIcon from '@/components/SvgIcon/index.vue';
  import router from '@/router';
  import { useDialog, useMessage } from 'naive-ui';

  const editRef = ref();
  const dialog = useDialog();
  const ms = useMessage();
  const loading = ref(true);
  const groups = ref<any[]>([]);

  onMounted(async () => {
    await fetchData();
  });

  async function fetchData() {
    loading.value = true;
    try {
      const apiArr = await getApiList({});
      const webArr = await getWebList({});
      groups.value = [
        { key: 'CHANNEL_API', value: apiArr as any[] },
        { key: 'CHANNEL_WEB', value: webArr as any[] },
        { key: 'CHANNEL_WEIXIN', value: [] as any[] },
      ];
    } finally {
      loading.value = false;
    }
  }

  async function onInfo(item: any) {
    console.log('点击了', item);
    if (item.channel === 'CHANNEL_API') {
      await router.push('/aigc/app/api/' + item.id);
    }
    if (item.channel === 'CHANNEL_WEB') {
      await router.push('/aigc/app/web/' + item.id);
    }
    if (item.channel === 'CHANNEL_WEIXIN') {
      await router.push('/aigc/app/weixin/' + item.id);
    }
  }

  function renderTitle(channel: string) {
    if (channel === 'CHANNEL_API') return 'HTTP API渠道';
    if (channel === 'CHANNEL_WEB') return 'WEB渠道';
    if (channel === 'CHANNEL_WEIXIN') return '微信渠道';
  }

  function renderIcon(channel: string) {
    return {
      render() {
        if (channel === 'CHANNEL_API') {
          return h(SvgIcon, {
            class: 'text-4xl text-blue-500',
            icon: 'hugeicons:api',
          });
        }
        if (channel === 'CHANNEL_WEB') {
          return h(SvgIcon, {
            class: 'text-4xl text-blue-500',
            icon: 'mdi:web-sync',
          });
        }
        if (channel === 'CHANNEL_WEIXIN') {
          return h(SvgIcon, {
            class: 'text-4xl text-green-400',
            icon: 'uiw:weixin',
          });
        }
      },
    };
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
</script>

<template>
  <div class="dot-bg overflow-y-auto">
    <div class="n-layout-page-header">
      <n-card :bordered="false" title="应用集成能力">
        简单配置应用，快速通过IFRAME嵌套、HTTP接口、微信、钉钉等多种渠道接入LangChat
      </n-card>
    </div>

    <n-spin :show="loading" class="mt-4 w-full mb-10 px-6">
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
                  :value="item.link"
                  class="flex-1 block h-8 px-4 text-sm text-gray-700 bg-white border border-gray-200 rounded-md focus:border-blue-400 focus:ring-blue-300 focus:ring-opacity-40 focus:outline-none focus:ring"
                  type="text"
                />

                <button
                  class="rounded-md hidden sm:block p-1.5 text-gray-700 bg-white border border-gray-200 focus:border-blue-400 focus:ring-blue-300 focus:ring-opacity-40 focus:outline-none focus:ring transition-colors duration-300 hover:text-blue-500 dark:hover:text-blue-500"
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
    </n-spin>

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
