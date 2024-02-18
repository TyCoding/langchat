<script setup lang="ts">
  import { computed, ref } from 'vue';
  import { useMessage } from 'naive-ui';
  import TextComponent from './TextComponent.vue';
  import AvatarComponent from './Avatar.vue';
  import { SvgIcon } from '@/components/common';
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
    (ev: 'regenerate'): void;
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
        label: '删除',
        key: 'delete',
        icon: iconRender({ icon: 'ri:delete-bin-line' }),
      },
      {
        label: '复制',
        key: 'copyText',
        icon: iconRender({ icon: 'ri:file-copy-2-line' }),
      },
    ];

    if (!props.inversion) {
      common.unshift({
        label: asRawText.value ? '预览' : '显示原文',
        key: 'toggleRenderType',
        icon: iconRender({ icon: asRawText.value ? 'ic:outline-code-off' : 'ic:outline-code' }),
      });
    }

    return props.inversion ? common : common.slice().reverse();
  });

  function handleSelect(key: any) {
    console.log('点击', key);
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

  function handleRegenerate() {
    messageRef.value?.scrollIntoView();
    emit('regenerate');
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
      <div
        class="flex gap-1 mt-2"
        :class="[inversion ? 'flex-col items-end' : 'flex-col justify-start items-start']"
      >
        <TextComponent
          ref="textRef"
          :inversion="inversion"
          :error="error"
          :text="text"
          :loading="loading"
          :as-raw-text="asRawText"
        />
        <div class="flex flex-row justify-start items-start gap-1">
          <button
            v-if="!inversion"
            :disabled="loading"
            class="mb-2 transition text-neutral-300 hover:text-neutral-800"
            @click="handleRegenerate"
          >
            <SvgIcon icon="ri:restart-line" />
          </button>

          <n-popover
            v-for="item in options"
            :key="item"
            :trigger="isMobile ? 'click' : 'hover'"
            placement="bottom"
            class="custom-popover"
          >
            <template #trigger>
              <button
                @click="handleSelect(item.key)"
                class="transition text-neutral-300 hover:text-neutral-800"
              >
                <component :is="item.icon" />
              </button>
            </template>
            {{ item.label }}
          </n-popover>
        </div>
      </div>
    </div>
  </div>
</template>

<style scoped lang="less"></style>
