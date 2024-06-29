<script setup lang="ts">
  import { Handle, Position, useVueFlow } from '@vue-flow/core';
  import Draggable from 'vuedraggable';
  import { AddOutline, CopyOutline } from '@vicons/ionicons5';
  import { ref } from 'vue';
  import { useFlowStore } from '@/views/flow/store';
  import { renderIcon, renderPropsIcon } from '@/utils';
  import { type GraphNode } from '@vue-flow/core';
  import { Pin, renderNodeDes, renderNodeIcon } from '@/views/flow/store/get';

  const plugins = ref([]);
  interface Props {
    node: GraphNode;
  }
  const props = defineProps<Props>();
  const { node } = props;
  plugins.value = node.data.plugins ?? [];

  const { removeNodes } = useVueFlow();
  const flowStore = useFlowStore();

  function onClickAdd() {
    node.selected = true;
    flowStore.setShowCard();
  }

  function onAddEle(e: any) {
    const item: any = plugins.value[e.newIndex];
    if (item.isNode) {
      // 对于节点，不需要作为plugin添加
      plugins.value.splice(e.newIndex, 1);
    } else {
      // 校验不准许出现相同plugin
      const filter = plugins.value.filter((i) => i.type == item.type);
      if (filter.length > 1) {
        window['$message'].warning('Plugin is existed');
        plugins.value.splice(e.newIndex, 1);
        return;
      }

      // @ts-ignore
      plugins.value[e.newIndex] = {
        type: item.type,
        label: item.label,
      };
    }
    node.data.plugins = plugins.value;
    console.log('添加', plugins.value);
  }

  function onValid(e, e2): boolean {
    return true;
  }

  function onPluginClick(plugin: Pin) {
    node.selected = true;
    flowStore.initPlugin(plugin);
  }

  const menuOptions = [
    {
      label: 'Delete',
      key: 'delete',
    },
  ];
  function onClickMenu(key: string) {
    if (key === 'delete') {
      console.log('删除', flowStore.nodeId);
      removeNodes(flowStore.nodeId);
    }
  }
</script>

<template>
  <n-el
    tag="div"
    class="w-[340px] rounded-md shadow custom-node p-2.5"
    style="background: var(--card-color)"
    :class="node.selected ? 'node-selected' : ''"
  >
    <div class="flex justify-between items-center h-full">
      <div class="flex items-center">
        <n-icon class="mr-1">
          <component :is="renderNodeIcon(node.type)" />
        </n-icon>
        <n-input
          v-model:value="node.label"
          size="small"
          class="hover-input font-bold text-lg w-[80%]"
          placeholder=""
        />
      </div>
      <n-dropdown trigger="click" :options="menuOptions" @select="onClickMenu">
        <n-button text type="primary" class="z-20">
          <n-icon class="ml-1 text-[17px]">
            <svg
              width="10"
              height="18"
              viewBox="1 0 4 13"
              fill="none"
              xmlns="http://www.w3.org/2000/svg"
            >
              <path
                d="M1 0.5H5M1 5.5H5M1 10.5H5"
                stroke="currentColor"
                stroke-linecap="round"
                stroke-linejoin="round"
              />
            </svg>
          </n-icon>
        </n-button>
      </n-dropdown>
    </div>
    <div class="text-xs text-gray-400 outline-2 pt-1 pb-1">{{ renderNodeDes(node.type) }}</div>

    <div class="my-2">
      <slot name="content"></slot>
    </div>

    <div class="">
      <Draggable
        animation="300"
        itemKey="label"
        :list="plugins"
        class="nodrag"
        group="plugins"
        @add="onAddEle"
      >
        <template #item="{ element }">
          <n-button
            :class="
              flowStore.pin !== null && element.type == flowStore.pin.type ? 'hover-button' : ''
            "
            size="small"
            icon-placement="left"
            block
            class="mb-1 justify-start nodrag text-[12px]"
            :render-icon="renderNodeIcon(element.type)"
            @click.stop="onPluginClick(element)"
          >
            {{ element.label ?? element.type }}
          </n-button>
        </template>
      </Draggable>

      <n-button
        v-if="!plugins.length"
        size="small"
        block
        dashed
        :render-icon="renderPropsIcon(CopyOutline, { size: '14px' })"
        class="text-xs text-gray-400"
      >
        Drag Drop This
      </n-button>

      <n-button
        class="nodrag mt-2 mb-2 justify-start"
        size="small"
        block
        dashed
        @click="onClickAdd"
        :render-icon="renderIcon(AddOutline)"
      >
        Add Card
      </n-button>
    </div>

    <Handle
      id="2"
      type="target"
      :position="Position.Left"
      class="!top-[23px]"
      :class="node.selected ? '!bg-[#2d8cf0]' : ''"
    />

    <Handle
      id="1"
      type="source"
      :position="Position.Right"
      :is-valid-connection="onValid"
      class="!top-auto !bottom-[21px] bg-[#36ad6a]"
      :class="node.selected ? '!bg-[#2d8cf0]' : ''"
    />
  </n-el>
</template>

<style scoped lang="less"></style>
