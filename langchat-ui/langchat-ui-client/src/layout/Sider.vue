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
  import { SvgIcon } from '@/components/common';
  import { routesConst } from '@/router';
  import { useRouter } from 'vue-router';
  import Profile from '@/layout/Profile.vue';

  const router = useRouter();
</script>

<template>
  <div
    class="w-[190px] h-full bg-[#fafafa] dark:bg-transparent flex border dark:border-[#ffffff17] flex-col rounded-lg"
  >
    <n-scrollbar class="flex-1 w-full">
      <div class="flex flex-col gap-3 border-neutral-800 dark:border-[#ffffff17] pt-3 px-3 w-full">
        <div class="text-lg gap-2 text-center flex justify-center items-center">
          <img height="30" src="@/assets/login/logo.png" width="30" />
          <span class="font-bold">LangChat</span>
        </div>
        <n-divider class="!my-0 !py-0" />
        <n-button
          v-for="item in routesConst"
          :key="item.name"
          :class="
            router.currentRoute.value.name == item.name
              ? '!text-[#0052CC] w-full !bg-[#eff0f0] dark:!bg-[#ffffff1a] dark:border-[#ffffff17] !rounded-[8px]'
              : 'w-full'
          "
          text
          @click="router.push({ name: item.name })"
        >
          <div class="w-full px-4 py-3 flex items-center cursor-pointer gap-3.5">
            <SvgIcon :icon="item.meta?.icon" class="text-lg" />
            <div class="mt-1 mb-1 font-medium text-[14px]"> {{ item.meta?.label }} </div>
          </div>
        </n-button>
      </div>
    </n-scrollbar>

    <div class="p-2 flex flex-col justify-center items-center gap-2 pb-3 bottom-0">
      <n-divider class="!my-0 !py-0" />

      <Profile />
    </div>
  </div>
</template>

<style lang="less" scoped>
  ::v-deep(.n-button .n-button__content) {
    width: 100% !important;
  }
</style>
