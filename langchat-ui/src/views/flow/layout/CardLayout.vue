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
  import { collapses } from '@/views/flow/store/get';
  import { useFlowStore } from '@/views/flow/store';
  import NodeCard from './components/NodeCard.vue';
  import PluginCard from './components/PluginCard.vue';

  const flowStore = useFlowStore();
</script>

<template>
  <n-drawer
    v-model:show="flowStore.showCard"
    :show-mask="false"
    :mask-closable="false"
    placement="left"
    width="280px"
    to="#graph-layout"
    class="rounded-lg left-5 bottom-6"
  >
    <n-drawer-content
      closable
      :header-style="{ padding: '13px' }"
      :body-content-style="{ padding: '0px' }"
    >
      <template #header>
        <div class="text-[13px]">Node Card & Plugins</div>
      </template>

      <div class="p-3">
        <n-tabs type="segment" size="small">
          <n-tab-pane name="Node" tab="Node">
            <NodeCard :list="collapses(true)" />
          </n-tab-pane>
          <n-tab-pane name="Plugins" tab="Plugins">
            <PluginCard :list="collapses(false)" />
          </n-tab-pane>
        </n-tabs>
      </div>
    </n-drawer-content>
  </n-drawer>
</template>

<style scoped lang="less">
  .panel {
    margin-top: 8px;
    font-size: 10px !important;
    ::v-deep(.n-collapse-item) {
      border: 0 !important;
      margin: 0 !important;
      .n-collapse-item__content-outer {
        padding-top: 10px !important;
      }
    }

    ::v-deep(.n-collapse-item__header-main) {
      .n-collapse-item-arrow {
        font-size: 14px !important;
      }
      font-weight: 500 !important;
      font-size: 12px !important;
    }
  }
</style>
