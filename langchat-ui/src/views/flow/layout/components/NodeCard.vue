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
  import { Pin, renderNodeIcon } from '@/views/flow/store/get';
  import Draggable from 'vuedraggable';

  interface Props {
    list: Array<any>;
  }
  const props = defineProps<Props>();

  function onDragStart(e: any, v: Pin) {
    if (e.dataTransfer) {
      e.dataTransfer.setData('isNode', true);
      e.dataTransfer.setData('data', JSON.stringify(v));
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
            header-class="pb-0"
            content-class="pb-2"
          >
            <template #header>
              <div class="flex justify-start items-center">
                <n-icon>
                  <component :is="renderNodeIcon(element.type)" />
                </n-icon>
                <span class="text-[14px] ml-1">{{ element.type }}</span>
              </div>
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
