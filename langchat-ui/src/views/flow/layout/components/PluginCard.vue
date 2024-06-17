<script setup lang="ts">
  import { Pin, renderNodeIcon } from '@/views/flow/store/get';
  import Draggable from 'vuedraggable';

  interface Props {
    list: Array<any>;
  }
  const props = defineProps<Props>();

  function onDragStart(e: any, v: Pin) {
    if (e.dataTransfer) {
      e.dataTransfer.setData('isNode', false);
    }
  }
</script>

<template>
  <n-collapse class="panel" :default-expanded-names="[0, 1, 2, 3, 4, 5, 6, 7]">
    <n-collapse-item
      v-for="(item, index) in props.list"
      :key="index"
      :title="item.key"
      :name="index"
    >
      <Draggable
        animation="300"
        :list="item.value"
        :group="{ name: 'plugins', pull: 'clone', put: false }"
        itemKey="label"
        :sort="false"
        :clone="(e) => e"
        class="flex flex-col gap-2 w-full"
      >
        <template #item="{ element }">
          <n-card
            @dragstart="onDragStart($event, element)"
            :draggable="true"
            hoverable
            size="small"
            class="rounded-md cursor-pointer"
            header-class="pb-1"
            content-class="pb-2"
          >
            <template #header>
              <n-icon>
                <component :is="renderNodeIcon(element.type)" />
              </n-icon>
              {{ element.type }}
            </template>
            <template #header-extra>
              <n-button text type="primary">
                <SvgIcon class="text-lg" icon="ic:baseline-plus" />
              </n-button>
            </template>
            <span class="text-gray-400 text-xs">{{ element.des }}</span>
          </n-card>
        </template>
      </Draggable>
    </n-collapse-item>
  </n-collapse>
</template>

<style scoped lang="less"></style>
