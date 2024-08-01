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
  import { computed, ref } from 'vue';
  import { useMessage } from 'naive-ui';
  import TextComponent from './TextComponent.vue';
  import AvatarComponent from './Avatar.vue';
  import SvgIcon from '@/components/SvgIcon/index.vue';
  import { useBasicLayout } from '../store/useBasicLayout';
  import { useIconRender } from '../store/useIconRender';
  import { copyToClip } from '@/utils/copy';

  interface Props {
    dateTime?: string;
    text?: string;
    inversion?: boolean;
    error?: boolean;
    loading?: boolean;
  }

  interface Emit {
    (ev: 'delete'): void;
  }

  const props = defineProps<Props>();

  const emit = defineEmits<Emit>();

  const { isMobile } = useBasicLayout();

  const { iconRender } = useIconRender();

  const message = useMessage();

  const textRef = ref<HTMLElement>();

  const asRawText = ref(props.inversion);

  const messageRef = ref<HTMLElement>();

  const options = computed(() => {
    const common = [
      {
        label: '复制',
        key: 'copyText',
        icon: iconRender({ icon: 'ri:file-copy-2-line' }),
      },
      {
        label: '删除',
        key: 'delete',
        icon: iconRender({ icon: 'ri:delete-bin-line' }),
      },
    ];

    if (!props.inversion) {
      common.unshift({
        label: asRawText.value ? '预览' : '显示原文',
        key: 'toggleRenderType',
        icon: iconRender({ icon: asRawText.value ? 'ic:outline-code-off' : 'ic:outline-code' }),
      });
    }

    return common;
  });

  function handleSelect(key: 'copyText' | 'delete' | 'toggleRenderType') {
    switch (key) {
      case 'copyText':
        handleCopy();
        return;
      case 'toggleRenderType':
        asRawText.value = !asRawText.value;
        return;
      case 'delete':
        emit('delete');
    }
  }

  async function handleCopy() {
    try {
      await copyToClip(props.text || '');
      message.success('复制成功');
    } catch {
      message.error('复制失败');
    }
  }
</script>

<template>
  <div
    ref="messageRef"
    class="flex w-full mb-6 overflow-hidden"
    :class="[{ 'flex-row-reverse': inversion }]"
  >
    <div
      class="flex items-center justify-center flex-shrink-0 h-8 overflow-hidden rounded-full basis-8"
      :class="[inversion ? 'ml-2' : 'mr-2']"
    >
      <AvatarComponent :image="inversion" />
    </div>
    <div class="overflow-hidden text-sm" :class="[inversion ? 'items-end' : 'items-start']">
      <p class="text-xs text-[#b4bbc4]" :class="[inversion ? 'text-right' : 'text-left']">
        {{ dateTime }}
      </p>
      <div class="flex items-end gap-1 mt-2" :class="[inversion ? 'flex-row-reverse' : 'flex-row']">
        <TextComponent
          ref="textRef"
          :inversion="inversion"
          :error="error"
          :text="text"
          :loading="loading"
          :as-raw-text="asRawText"
        />
        <div class="flex flex-col">
          <NDropdown
            :trigger="isMobile ? 'click' : 'hover'"
            :placement="!inversion ? 'right' : 'left'"
            :options="options"
            @select="handleSelect"
          >
            <button class="transition text-neutral-300 hover:text-neutral-800">
              <SvgIcon icon="ri:more-2-fill" />
            </button>
          </NDropdown>
        </div>
      </div>
    </div>
  </div>
</template>

<style scoped lang="less"></style>
