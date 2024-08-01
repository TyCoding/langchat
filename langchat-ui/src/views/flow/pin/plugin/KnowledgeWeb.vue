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

<script setup lang="ts"></script>

<template>
  <n-form ref="formRef" class="custom-form">
    <n-space vertical :size="16">
      <n-form-item path="age">
        <template #label>
          <n-popover placement="bottom" class="custom-popover">
            文档文件的URL地址，请确保该地址可下载文件
            <template #trigger>
              <div class="tips-line">HTTP URL</div>
            </template>
          </n-popover>
        </template>
        <n-input size="small" />
      </n-form-item>
    </n-space>
  </n-form>
</template>

<style scoped lang="less"></style>
