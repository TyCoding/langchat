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
  import Account from './components/Account.vue';
  import Pass from './components/Pass.vue';
  import { SvgIcon } from '@/components/common';
  import { onMounted, ref } from 'vue';
  import { info } from '@/api/auth';

  const form = ref();
  const loading = ref(true);
  onMounted(async () => {
    form.value = await info();
    loading.value = false;
  });
</script>

<template>
  <n-spin :show="loading" class="w-full h-full">
    <div class="p-4 pl-9">
      <div class="pt-3 pb-3 text-lg">个人中心</div>
      <n-tabs v-if="form" animated type="line">
        <n-tab-pane name="1">
          <template #tab> <SvgIcon class="mr-1" icon="solar:user-id-broken" />个人信息 </template>
          <Account :form="form" />
        </n-tab-pane>
        <n-tab-pane name="2">
          <template #tab> <SvgIcon class="mr-1" icon="carbon:password" />修改密码 </template>
          <Pass />
        </n-tab-pane>
      </n-tabs>
      <div></div>
    </div>
  </n-spin>
</template>

<style lang="less" scoped></style>
