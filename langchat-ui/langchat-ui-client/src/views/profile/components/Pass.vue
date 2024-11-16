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

<script lang="ts" setup></script>

<template>
  <div class="flex justify-start w-auto mt-4">
    <n-form
      class="w-[25%]"
      label-placement="left"
      label-width="auto"
      require-mark-placement="right-hanging"
    >
      <n-form-item label="原始密码" path="password">
        <n-input type="password" />
      </n-form-item>
      <n-form-item label="新密码" path="username">
        <n-input type="password" />
      </n-form-item>
      <n-form-item label="重复密码" path="password">
        <n-input type="password" />
      </n-form-item>
      <n-form-item>
        <n-space>
          <n-button secondary type="success">更新密码</n-button>
        </n-space>
      </n-form-item>
    </n-form>
  </div>
</template>

<style lang="less" scoped></style>
