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
  import { computed } from 'vue';
  import type { EdgeProps } from '@vue-flow/core';
  import { CloseCircle } from '@vicons/ionicons5';
  import {
    SmoothStepEdge,
    EdgeLabelRenderer,
    getSmoothStepPath,
    useVueFlow,
    useEdge,
  } from '@vue-flow/core';
  import { renderPropsIcon } from '@/utils';

  const { edge } = useEdge();
  const props = defineProps<EdgeProps>();

  const { removeEdges } = useVueFlow();

  const path = computed(() => getSmoothStepPath(props));
</script>

<template>
  <SmoothStepEdge
    :source-x="sourceX"
    :source-y="sourceY"
    :target-x="targetX"
    :target-y="targetY"
    :source-position="sourcePosition"
    :target-position="targetPosition"
    class="custom-edge"
    :class="edge?.selected ? 'line-active' : ''"
  />
  <EdgeLabelRenderer>
    <div
      :style="{
        zIndex: 1,
        pointerEvents: 'all',
        position: 'absolute',
        transform: `translate(-50%, -50%) translate(${path[1]}px,${path[2] + 2}px)`,
      }"
    >
      <transition name="fade">
        <n-button
          @click="removeEdges(id)"
          text
          v-if="edge?.selected"
          class="hover:!text-[#de576d]"
          type="info"
          :render-icon="renderPropsIcon(CloseCircle, { size: '14' })"
        />
      </transition>
    </div>
  </EdgeLabelRenderer>
</template>

<style lang="less"></style>
