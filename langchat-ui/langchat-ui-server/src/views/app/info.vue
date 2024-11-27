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
  import AppBase from './base/index.vue';
  import ApiChannel from './channel-api/index.vue';
  import WebChannel from './channel-web/index.vue';
  import SvgIcon from '@/components/SvgIcon/index.vue';
  import router from '@/router';
  import { onMounted, ref } from 'vue';
  import { useAppStore } from './store';
  import { getAppInfo } from '@/api/aigc/chat';

  const appStore = useAppStore();
  const form = ref<any>({});
  const loading = ref(false);
  const activeMenus = [
    { key: 'setting', icon: 'uil:setting', label: '应用配置' },
    { key: 'api', icon: 'hugeicons:api', label: 'API接入渠道' },
    { key: 'web', icon: 'hugeicons:api', label: 'Web接入渠道' },
  ];

  onMounted(async () => {
    await fetchData();
  });

  async function fetchData() {
    loading.value = true;
    const id = router.currentRoute.value.params.id;
    const data = await getAppInfo({
      appId: id,
      conversationId: null,
    });
    form.value = data;
    loading.value = false;
  }
</script>

<template>
  <div v-if="form.name !== undefined" class="rounded bg-[#f9f9f9] w-full h-full pb-10">
    <div class="p-4 flex justify-between items-center bg-white rounded">
      <div class="flex gap-5 items-center min-w-20">
        <n-button text type="primary" @click="router.push('/app/list')">
          <SvgIcon class="text-xl" icon="icon-park-outline:back" />
        </n-button>
        <div class="flex gap-2 items-center pr-4">
          <div class="mr-3">
            <div class="relative bg-orange-100 p-4 rounded-lg">
              <SvgIcon class="text-3xl" icon="prime:microchip-ai" />

              <div
                class="absolute bottom-[-6px] p-1 right-[-5px] shadow bg-white mx-auto rounded-lg"
              >
                <SvgIcon class="text-sm text-orange-500" icon="lucide:bot" />
              </div>
            </div>
          </div>

          <div class="flex flex-col justify-between gap-2">
            <div class="font-bold text-lg">{{ form.name }}</div>
            <div v-if="!loading" class="text-gray-400 text-xs">自动保存：{{ form.saveTime }}</div>
            <div v-else class="flex items-center gap-1 text-gray-400 text-xs">
              <SvgIcon icon="eos-icons:bubble-loading" />保存中...
            </div>
          </div>
        </div>
      </div>

      <div class="flex items-center gap-2">
        <n-button
          v-for="item in activeMenus"
          :key="item.key"
          :type="appStore.activeMenu === item.key ? 'primary' : 'default'"
          class="!px-5 !rounded-2xl"
          secondary
          strong
          @click="appStore.setActiveMenu(item.key)"
        >
          <template #icon>
            <SvgIcon :icon="item.icon" />
          </template>
          {{ item.label }}
        </n-button>
      </div>
    </div>

    <AppBase v-if="appStore.activeMenu === 'setting'" />

    <ApiChannel v-if="appStore.activeMenu === 'api'" />

    <WebChannel v-if="appStore.activeMenu === 'web'" />
  </div>
</template>

<style lang="less" scoped></style>
