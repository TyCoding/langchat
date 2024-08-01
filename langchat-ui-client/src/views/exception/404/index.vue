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

  const router = useRouter();

  function goHome() {
    router.push('/');
  }
</script>

<template>
  <div class="flex h-full">
    <div class="px-4 m-auto space-y-4 text-center max-[400px]">
      <h1 class="text-4xl text-slate-800 dark:text-neutral-200"> Sorry, page not found! </h1>
      <p class="text-base text-slate-500 dark:text-neutral-400">
        Sorry, we couldn’t find the page you’re looking for. Perhaps you’ve mistyped the URL? Be
        sure to check your spelling.
      </p>
      <div class="flex items-center justify-center text-center">
        <div class="w-[300px]">
          <img alt="404" src="../../../icons/404.svg" />
        </div>
      </div>
      <n-button type="primary" @click="goHome"> Go to Home </n-button>
    </div>
  </div>
</template>
