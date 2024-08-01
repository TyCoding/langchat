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
  import VueSplitter from '@rmp135/vue-splitter';
  import { computed, onMounted, ref } from 'vue';
  import { ArrowUndoOutline, CloudDoneOutline } from '@vicons/ionicons5';
  import { useRouter } from 'vue-router';
  import { useFlowStore } from '@/views/flow/store';
  import { renderIcon } from '@/utils';
  import CustomExec from '@/views/flow/custom/CustomExec.vue';
  import { useVueFlow } from '@vue-flow/core';
  import { getDatas } from '@/views/flow/store/get';
  import { update as updateFlow } from '@/api/aigc/flow';

  const { onNodeClick, onPaneClick, toObject, getConnectedEdges } = useVueFlow();
  const flowStore = useFlowStore();
  const router = useRouter();

  onNodeClick((e) => {
    console.log('点击node');
    flowStore.initNode(e.node);

    const edges = getConnectedEdges(e.node.id);
    if (e.node.selected) {
      edges.forEach((e) => {
        e.selected = true;
        e.zIndex = 1;
      });
    } else {
      edges.forEach((e) => {
        e.selected = false;
        e.zIndex = 0;
      });
    }
  });
  onPaneClick((e) => {
    console.log('点击graph');
    flowStore.cleanNode();
  });

  async function handleSubmit() {
    console.log('save', getDatas(toObject()));
    await updateFlow({
      id: String(router.currentRoute.value.params.id),
      flow: JSON.stringify(getDatas(toObject())),
    });
    window['$message'].success('保存成功');
  }

  const percent = ref(70);
  const limitedPercent = computed({
    get() {
      return percent.value;
    },
    set(val) {
      percent.value = Math.max(10, Math.min(80, val));
    },
  });
  function handlePaneClick(per: number) {
    limitedPercent.value = per;
  }
</script>

<template>
  <vue-splitter
    initial-percent="70"
    is-horizontal
    v-model:percent="percent"
    style="background: white"
  >
    <template #left-pane>
      <div class="h-full rounded-xl overflow-y-hidden card-shadow" @click="handlePaneClick(70)">
        <div class="border-b border-1 flex justify-between items-center p-2">
          <n-button
            type="success"
            size="small"
            secondary
            :render-icon="renderIcon(CloudDoneOutline)"
            @click="handleSubmit"
          >
            保存流程
          </n-button>
          <n-button
            @click="router.back()"
            type="error"
            size="small"
            secondary
            :render-icon="renderIcon(ArrowUndoOutline)"
          >
            返回
          </n-button>
        </div>
        <div class="p-4 overflow-y-auto h-full pb-20">
          <component :is="flowStore.pinComponent" />
        </div>
      </div>
    </template>
    <template #right-pane>
      <CustomExec @click="handlePaneClick(30)" />
    </template>
  </vue-splitter>
</template>

<style lang="less"></style>
