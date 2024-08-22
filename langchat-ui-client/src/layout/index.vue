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
  <div class="h-screen w-full grid-mask">
    <n-layout class="h-full" has-sider>
      <n-layout-sider
        :collapsed="collapsed"
        :collapsed-width="0"
        :width="200"
        @collapse="collapsed = true"
        @expand="collapsed = false"
      >
        <div class="m-4 mr-2" style="height: calc(100vh - 33px)">
          <Sider />
        </div>
      </n-layout-sider>
      <n-layout-content>
        <RouterView v-slot="{ Component, route }">
          <keep-alive>
            <div
              class="h-full m-4 ml-2 border rounded-lg bg-white dark:bg-transparent dark:border-[#ffffff17]"
              style="height: calc(100vh - 33px)"
            >
              <component :is="Component" :key="route.fullPath" />
            </div>
          </keep-alive>
        </RouterView>
      </n-layout-content>
    </n-layout>
  </div>
</template>
