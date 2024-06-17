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
