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
  import { nextTick, ref } from 'vue';
  import SvgIcon from '@/components/SvgIcon/index.vue';
  import { basicModal, useModal } from '@/components/Modal';
  import { list as getList } from '@/api/aigc/knowledge';
  import { useAppStore } from '@/views/app/store';
  import { useMessage } from 'naive-ui';

  const ms = useMessage();
  const knowledges = ref();
  const appStore = useAppStore();

  const [modalRegister, { openModal: openModal }] = useModal({
    title: '关联知识库',
    closable: true,
    maskClosable: false,
    showCloseBtn: false,
    showSubBtn: false,
  });

  async function show() {
    console.log(appStore.knowledgeIds);
    knowledges.value = await getList({});
    await nextTick();
    openModal();
  }

  function onAdd(item) {
    appStore.addKnowledge(item);
    ms.success('关联成功');
  }
  function onRemove(item) {
    appStore.removeKnowledge(item);
    ms.success('移除成功');
  }

  defineExpose({ show });
</script>

<template>
  <basicModal style="width: 35%" @register="modalRegister">
    <n-scrollbar>
      <n-list clickable hoverable>
        <n-list-item v-for="item in knowledges" :key="item.id">
          <div class="flex items-center justify-between">
            <div class="flex gap-1 items-center">
              <SvgIcon class="text-3xl" icon="flat-color-icons:document" />
              <div>{{ item.name }}</div>
            </div>
            <n-button
              v-if="!appStore.knowledgeIds.includes(item.id)"
              class="rounded-2xl py-0 px-6"
              secondary
              size="small"
              type="info"
              @click="onAdd(item)"
            >
              关联
            </n-button>
            <n-button
              v-else
              class="rounded-2xl py-0 px-6"
              secondary
              size="small"
              type="error"
              @click="onRemove(item)"
            >
              移除
            </n-button>
          </div>
        </n-list-item>
      </n-list>
    </n-scrollbar>
  </basicModal>
</template>

<style lang="less" scoped></style>
