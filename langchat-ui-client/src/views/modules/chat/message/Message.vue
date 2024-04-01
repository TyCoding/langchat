<script lang="ts" setup>
  import { computed, ref } from 'vue';
  import { useMessage } from 'naive-ui';
  import TextComponent from './TextComponent.vue';
  import AvatarComponent from './Avatar.vue';
  import { useBasicLayout } from '../store/useBasicLayout';
  import { useIconRender } from '../store/useIconRender';
  import { copyToClip } from '@/utils/copy';
  import { t } from '@/locales';

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
        label: t('chat.deleteMessage'),
        key: 'delete',
        icon: iconRender({ icon: 'ri:delete-bin-line' }),
      },
      {
        label: t('chat.copy'),
        key: 'copyText',
        icon: iconRender({ icon: 'ri:file-copy-2-line' }),
      },
    ];

    if (!props.inversion) {
      common.unshift({
        label: asRawText.value ? t('chat.preview') : t('chat.showRawText'),
        key: 'toggleRenderType',
        icon: iconRender({ icon: asRawText.value ? 'ic:outline-code-off' : 'ic:outline-code' }),
      });
    }

    return props.inversion ? common : common.slice().reverse();
  });

  function handleSelect(key: any) {
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
      message.success(t('chat.copied'));
    } catch (e: any) {
      console.error(e);
    }
  }
</script>

<template>
  <div
    ref="messageRef"
    :class="[{ 'flex-row-reverse': inversion }]"
    class="flex w-full mb-6 overflow-hidden"
  >
    <div
      :class="[inversion ? 'ml-2' : 'mr-2']"
      class="flex items-center justify-center flex-shrink-0 h-8 overflow-hidden rounded-full basis-8"
    >
      <AvatarComponent :image="inversion" />
    </div>
    <div :class="[inversion ? 'items-end' : 'items-start']" class="overflow-hidden text-sm">
      <p :class="[inversion ? 'text-right' : 'text-left']" class="text-xs text-[#b4bbc4]">
        {{ dateTime }}
      </p>
      <div
        :class="[inversion ? 'flex-col items-end' : 'flex-col justify-start items-start']"
        class="flex gap-1 mt-2"
      >
        <TextComponent
          ref="textRef"
          :as-raw-text="asRawText"
          :error="error"
          :inversion="inversion"
          :loading="loading"
          :text="text"
        />
        <div class="flex flex-row justify-start items-start gap-1">
          <n-popover
            v-for="item in options"
            :key="item"
            :trigger="isMobile ? 'click' : 'hover'"
            class="custom-popover"
            placement="bottom"
          >
            <template #trigger>
              <button
                class="transition text-neutral-300 hover:text-neutral-800"
                @click="handleSelect(item.key)"
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
