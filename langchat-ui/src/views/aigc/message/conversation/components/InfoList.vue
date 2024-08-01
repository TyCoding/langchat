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
  import { getMessages } from '@/api/aigc/conversation';
  import Message from '@/views/aigc/chat/components/message/Message.vue';

  const messageRef = ref();
  const contentRef = ref();
  const loading = ref(true);
  const showModel = ref(false);
  const info = ref<any>({});
  const messages = ref<any>([]);

  async function show(row: any) {
    showModel.value = true;
    await nextTick();

    info.value = row;
    messages.value = await getMessages(row.id);
    loading.value = false;
  }

  async function handleDelete(row) {
    console.log('del', row);
  }

  defineExpose({ show });
</script>

<template>
  <n-drawer v-model:show="showModel" :width="1000" placement="right">
    <n-drawer-content :title="info.title">
      <div ref="contentRef">
        <n-scrollbar ref="messageRef">
          <Message
            v-for="(item, index) of messages"
            :key="index"
            :date-time="item.createTime"
            :error="false"
            :inversion="item.role !== 'assistant'"
            :loading="loading"
            :text="item.message"
            @delete="handleDelete(item)"
          />
        </n-scrollbar>
      </div>
      <n-empty v-if="messages.length == 0" class="mt-5" description="此会话还没有聊天信息" />

      <template #footer>
        <n-button @click="showModel = false"> 关闭</n-button>
      </template>
    </n-drawer-content>
  </n-drawer>
</template>

<style lang="less" scoped></style>
