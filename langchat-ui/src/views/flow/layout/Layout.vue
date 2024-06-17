<script setup lang="ts">
  import { onMounted, ref, computed } from 'vue';
  import GraphLayout from '@/views/flow/layout/GraphLayout.vue';
  import PinLayout from '@/views/flow/layout/PinLayout.vue';
  import { useProjectSettingStore } from '@/store/modules/projectSetting';
  import VueSplitter from '@rmp135/vue-splitter';
  import { useVueFlow } from '@vue-flow/core';
  import { getById } from '@/api/aigc/flow';
  import { isNullOrWhitespace } from '@/utils/is';
  import { useRouter } from 'vue-router';
  import { useFlowStore } from '@/views/flow/store';

  // 预先初始化一个Flow instance，后续组件使用useVueFlow()函数将共享这个实例
  useVueFlow();

  const loading = ref(true);
  const flowStore = useFlowStore();
  const router = useRouter();
  const projectStore = useProjectSettingStore();

  onMounted(async () => {
    const data = await getById(String(router.currentRoute.value.params.id));
    flowStore.data = data;
    if (isNullOrWhitespace(data.flow)) {
      await router.push({ name: 'flow_initialize', params: { id: data.id } });
    } else {
      projectStore.setMenuCollapse();
    }
    loading.value = false;
  });

  const percent = ref(80);
  const limitedPercent = computed({
    get() {
      return percent.value;
    },
    set(val) {
      percent.value = Math.max(50, Math.min(98, val));
    },
  });
</script>

<template>
  <div class="w-full" style="height: calc(100vh - 80px)">
    <n-spin :show="loading" description="正在加载Flow流程">
      <vue-splitter initial-percent="80" v-model:percent="limitedPercent" v-if="flowStore.data">
        <template #left-pane>
          <GraphLayout class="h-full" />
        </template>
        <template #right-pane>
          <PinLayout class="h-full" />
        </template>
      </vue-splitter>
    </n-spin>
  </div>
</template>

<style lang="less">
  @import '@/styles/flow';
</style>
