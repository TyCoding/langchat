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
  import { useRouter } from 'vue-router';
  import Icon500 from '@/icons/500.vue';

  const router = useRouter();

  function goHome() {
    router.push('/');
  }
</script>

<template>
  <div class="flex h-full dark:bg-neutral-800">
    <div class="px-4 m-auto space-y-4 text-center max-[400px]">
      <header class="space-y-2">
        <h2 class="text-2xl font-bold text-center text-slate-800 dark:text-neutral-200"> 500 </h2>
        <p class="text-base text-center text-slate-500 dark:text-slate-500"> Server error </p>
        <div class="flex items-center justify-center text-center">
          <Icon500 class="w-[300px]" />
        </div>
      </header>
      <n-button type="primary" @click="goHome"> Go to Home </n-button>
    </div>
  </div>
</template>
