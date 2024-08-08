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
  import SvgIcon from '@/components/SvgIcon/index.vue';
  import KnowledgeList from './KnowledgeList.vue';
  import { ref } from 'vue';
  import { useAppStore } from '@/views/app/store';

  const appStore = useAppStore();
  const knowledgeRef = ref();

  function onShowKbPane() {
    knowledgeRef.value.show();
  }

  function onRemove(item) {
    appStore.removeKnowledge(item);
  }
</script>

<template>
  <div class="p-2 py-4 flex flex-col gap-3">
    <n-collapse :default-expanded-names="['1']">
      <n-collapse-item name="1" title="知识库">
        <template #header-extra>
          <n-button text @click.stop="onShowKbPane">
            <SvgIcon class="text-lg" icon="ic:round-plus" />
          </n-button>
        </template>
        <div v-if="appStore.knowledges">
          <n-list clickable hoverable>
            <n-list-item
              v-for="item in appStore.knowledges"
              :key="item.id"
              class="w-full bg-white overflow-hidden !rounded-lg hover:bg-gray-100"
            >
              <div class="flex items-center justify-between">
                <div class="flex gap-1 items-center">
                  <SvgIcon class="text-3xl" icon="flat-color-icons:document" />
                  <div>{{ item.name }}</div>
                </div>
                <n-button text @click="onRemove(item)">
                  <SvgIcon icon="gg:remove" />
                </n-button>
              </div>
            </n-list-item>
          </n-list>
          <div v-if="appStore.knowledges.length == 0" class="text-gray-400 text-md">
            将文档、URL、三方数据源上传为文本知识库后，用户发送消息时，Bot
            能够引用文本知识中的内容回答用户问题。
          </div>
        </div>
      </n-collapse-item>
      <n-collapse-item name="2" title="插件">
        <n-empty description="正在开发中..." />
      </n-collapse-item>
    </n-collapse>

    <KnowledgeList ref="knowledgeRef" />
  </div>
</template>

<style lang="less" scoped>
  ::v-deep(.n-collapse-item__header-main) {
    font-weight: 600 !important;
    color: #060709cc;
  }
  ::v-deep(.n-list) {
    background: transparent !important;
  }
</style>
