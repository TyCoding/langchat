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
  const props = defineProps({
    url: {
      type: String,
    },
  });

  // 在生产环境下使用微软的office在线预览方式，导入支持office和word导入，在线预览不支持开发环境
  const path =
    process.env.NODE_ENV === 'development'
      ? props.url
      : `http://view.officeapps.live.com/op/view.aspx?src=${props.url}`;
</script>

<template>
  <iframe :src="path" class="!z-0" frameborder="0" height="100%" width="100%"></iframe>
</template>

<style lang="less" scoped></style>
