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
  import { onMounted, ref } from 'vue';
  import { ConnectionLineType, useVueFlow, VueFlow } from '@vue-flow/core';
  import { MiniMap } from '@vue-flow/minimap';
  import { Background } from '@vue-flow/background';
  import '@vue-flow/core/dist/style.css';
  import '@vue-flow/core/dist/theme-default.css';
  import '@vue-flow/minimap/dist/style.css';
  import CustomEdge from '@/views/flow/custom/CustomEdge.vue';
  import CustomZoom from '@/views/flow/custom/CustomZoom.vue';
  import { useFlowStore } from '@/views/flow/store';
  import { nodeTypes } from '@/views/flow/store/get';
  import CardLayout from '@/views/flow/layout/CardLayout.vue';

  const flowStore = useFlowStore();
  const loading = ref(false);
  const nodeMenuRef = ref();
  const list = ref();

  const { addNodes, onConnect, addEdges, project, vueFlowRef, onNodeContextMenu } = useVueFlow({
    connectionLineOptions: {
      type: ConnectionLineType.SmoothStep,
      class: 'animated',
    },
  });

  onMounted(async () => {
    loading.value = true;
    const data = flowStore.data;
    if (data == undefined) {
      return;
    }
    if (data.flow == undefined) {
      loading.value = true;
      return;
    }
    list.value = JSON.parse(data.flow);
    loading.value = false;

    // 将面板设置为显示状态
    flowStore.setShowCard();
  });

  onConnect((params) => {
    addEdges({ ...params, type: 'custom', animated: true });
  });

  onNodeContextMenu((e) => {
    e.node.selected = true;
    console.log(e);
    nodeMenuRef.value.show(e.node, e.event);
  });

  function onDragOver(event: any) {
    event.preventDefault();
    if (event.dataTransfer) {
      event.dataTransfer.dropEffect = 'move';
    }
  }

  function onDrop(event: any) {
    if (event.clientX < 340) {
      // 拖拽区域没有超过面板宽度时 忽略拖拽
      return;
    }
    const isNode = event.dataTransfer?.getData('isNode');
    if (isNode !== 'true') {
      return;
    }
    const { type, data } = JSON.parse(event.dataTransfer?.getData('data'));

    const { left, top } = vueFlowRef.value!.getBoundingClientRect();

    const position = project({
      x: event.clientX - left,
      y: event.clientY - top,
    });

    const newNode = {
      id: new Date().getTime().toString(),
      type: type,
      position,
      label: type,
      data: data ?? {},
    };
    console.log('添加节点', newNode);

    addNodes([newNode]);
  }
</script>

<template>
  <div @drop="onDrop" class="h-full w-full relative" id="graph-layout">
    <CardLayout />

    <CustomZoom />
    <VueFlow @dragover="onDragOver" v-model="list" :node-types="nodeTypes">
      <Background />
      <MiniMap />

      <template #edge-custom="props">
        <CustomEdge v-bind="props" />
      </template>
    </VueFlow>
  </div>
</template>

<style scoped></style>
