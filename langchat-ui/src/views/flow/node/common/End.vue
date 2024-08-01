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

<script setup lang="ts">
  import { Handle, Position, useNode } from '@vue-flow/core';
  import { useFlowStore } from '@/views/flow/store';

  const { node } = useNode();
  const flowStore = useFlowStore();
</script>

<template>
  <div
    class="w-20 h-full bg-gray-200 rounded-md custom-node"
    :class="node.selected ? 'node-selected' : ''"
  >
    <div class="flex justify-left items-center h-full p-2 pr-2.5">
      <n-icon>
        <svg
          width="10"
          height="18"
          viewBox="1 0 4 13"
          fill="none"
          xmlns="http://www.w3.org/2000/svg"
          style="color: #d4d4d8; display: inline-block"
        >
          <path
            d="M1 0.5H5M1 5.5H5M1 10.5H5"
            stroke="currentColor"
            stroke-linecap="round"
            stroke-linejoin="round"
          />
        </svg>
      </n-icon>
      <span class="font-bold pl-1">End</span>
    </div>

    <Handle type="target" :position="Position.Left" :class="node.selected ? '!bg-[#2d8cf0]' : ''" />
  </div>
</template>

<style scoped lang="less"></style>
