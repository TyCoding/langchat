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
  import { useChatStore } from '@/views/chat/store/useChatStore';
  import { useDialog, useMessage } from 'naive-ui';
  import { clean } from '@/api/aigc/chat';
  import ModelSelect from '@/views/channel/ModelSelect.vue';

  defineProps<{
    title: string;
  }>();
  const emits = defineEmits(['reload']);
  const dialog = useDialog();
  const ms = useMessage();
  const chatStore = useChatStore();

  function handleClear() {
    if (chatStore.conversationId == null) {
      return;
    }
    dialog.warning({
      title: '清除聊天',
      content: '确认清除聊天',
      positiveText: '是',
      negativeText: '否',
      onPositiveClick: async () => {
        await clean(chatStore.conversationId);
        emits('reload');
        ms.success('聊天记录清除成功');
      },
    });
  }
</script>

<template>
  <div class="mb-3 flex flex-wrap justify-between items-center">
    <div class="font-bold flex justify-center items-center flex-wrap gap-2">
      <SvgIcon class="text-lg" icon="ion:sparkles-outline" />
      <span>{{ title }}</span>
    </div>
    <n-space align="center">
      <ModelSelect :id="chatStore.modelId" class="w-auto" style="min-width: 140px" />

      <n-button secondary size="small" type="warning" @click="handleClear">
        <template #icon>
          <SvgIcon class="text-[14px]" icon="fluent:delete-12-regular" />
        </template>
        清空聊天
      </n-button>
    </n-space>
  </div>
</template>

<style lang="less" scoped></style>
