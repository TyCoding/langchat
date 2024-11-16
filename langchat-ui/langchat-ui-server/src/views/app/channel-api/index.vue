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
  import Docs from './components/docs.vue';
  import Edit from './components/edit.vue';
</script>

<template>
  <div class="w-full my-3 pb-8 flex flex-col" style="height: calc(100vh - 200px)">
    <div class="flex-1 overflow-y-auto">
      <div class="flex gap-4 h-full w-full">
        <Edit class="w-4/5" />
        <Docs class="w-full" />
      </div>
    </div>
  </div>
</template>

<style lang="less" scoped></style>
