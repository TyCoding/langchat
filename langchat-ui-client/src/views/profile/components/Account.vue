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
  defineProps<{
    form: any;
  }>();
</script>

<template>
  <div class="flex justify-start w-auto">
    <n-form
      class="w-[25%]"
      label-placement="left"
      label-width="auto"
      require-mark-placement="right-hanging"
    >
      <div class="pl-16 pb-4 pt-3">
        <n-avatar class="!w-[60px] !h-[60px]" round />
      </div>
      <n-form-item label="头像" path="username">
        <n-input v-model:value="form.avatar" />
      </n-form-item>
      <n-form-item label="用户名" path="username">
        <n-input v-model:value="form.username" />
      </n-form-item>
      <n-form-item label="密码" path="password">
        <n-input />
      </n-form-item>
      <n-form-item label="邮箱" path="password">
        <n-input v-model:value="form.email" />
      </n-form-item>
      <n-form-item>
        <n-space>
          <n-button secondary type="success">更新账户</n-button>
          <n-button secondary type="warning">退出登录</n-button>
        </n-space>
      </n-form-item>
    </n-form>
  </div>
</template>

<style lang="less" scoped></style>
