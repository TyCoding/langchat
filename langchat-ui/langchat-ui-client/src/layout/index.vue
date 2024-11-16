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
  import Sider from './Sider.vue';
  import { onMounted, ref, watch } from 'vue';
  import { useBasicLayout } from '@/hooks/useBasicLayout';
  import Login from '@/layout/login/Login.vue';
  import MobileSider from '@/layout/MobileSider.vue';
  import Profile from '@/layout/Profile.vue';

  const { isMobile } = useBasicLayout();
  const collapsed = ref(false);
  watch(isMobile, (val) => {
    collapsed.value = val;
  });

  onMounted(() => {
    collapsed.value = isMobile.value;
  });
</script>

<template>
  <div
    :class="isMobile ? 'items-start' : 'px-3 items-center'"
    class="w-full overflow-hidden flex justify-center h-full"
  >
    <Login />

    <div :class="isMobile ? '' : 'flex'" class="w-full">
      <div v-if="isMobile" class="flex items-center justify-between px-2 py-2">
        <div class="w-[94px]"></div>
        <div class="text-xl flex-1 font-bold flex items-center justify-center">LangChat</div>
        <div class="flex justify-end">
          <Profile />
        </div>
      </div>

      <div
        :class="isMobile ? '' : 'flex'"
        :style="isMobile ? 'height: calc(100vh - 100px)' : 'height: calc(100vh - 20px)'"
        class="w-full overflow-y-auto"
      >
        <Sider v-if="!isMobile" />

        <MobileSider v-if="isMobile" />

        <div class="overflow-y-auto w-full h-full">
          <RouterView v-slot="{ Component, route }">
            <keep-alive>
              <div
                :class="isMobile ? '' : 'ml-3'"
                class="p-0 border h-full rounded-lg overflow-y-auto bg-white dark:bg-transparent dark:border-[#ffffff17]"
              >
                <component :is="Component" :key="route.fullPath" />
              </div>
            </keep-alive>
          </RouterView>
        </div>
      </div>
    </div>
  </div>
</template>
