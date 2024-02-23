<script setup lang="ts">
  import { computed, nextTick } from 'vue';
  import { NInput, NPopconfirm, NScrollbar } from 'naive-ui';
  import { SvgIcon } from '@/components/common';
  import { useChatStore } from '../store/useChatStore';
  import { debounce } from '@/utils/debounce';
  import { Conversation } from '@/typings/chat';
  import { t } from '@/locales';

  const chatStore = useChatStore();
  const dataSources = computed(() => {
    return chatStore.conversations;
  });

  async function handleSelect(item: Conversation) {
    if (isActive(item.id)) return;
    await chatStore.selectConversation(item);
  }

  function handleEdit({ id }: Conversation, isEdit: boolean, event?: MouseEvent) {
    chatStore.setEdit(id);
    event?.stopPropagation();
  }

  async function handleDelete(item: Conversation, event?: MouseEvent | TouchEvent) {
    await chatStore.delConversation(item.id);
    event?.stopPropagation();
  }

  const handleDeleteDebounce = debounce(handleDelete, 600);

  async function handleUpdate(item: Conversation, isEdit: boolean, event: KeyboardEvent) {
    await chatStore.updateConversation(item);
    event?.stopPropagation();
  }

  function isActive(id: string) {
    return chatStore.active === id;
  }
  function isEdit(id: string) {
    return chatStore.isEdit === id;
  }
</script>

<template>
  <NScrollbar class="px-4">
    <div class="flex flex-col gap-2 text-sm">
      <template v-if="!dataSources.length">
        <div
          class="flex flex-col items-center mt-4 text-center text-neutral-300 dark:border-neutral-800"
        >
          <SvgIcon icon="ri:inbox-line" class="mb-2 text-3xl" />
          <span>{{ t('chat.emptyConversation') }}</span>
        </div>
      </template>
      <template v-else>
        <div v-for="(item, index) of dataSources" :key="index">
          <a
            class="relative flex items-center gap-3 px-3 py-3 break-all border rounded-md cursor-pointer hover:bg-neutral-100 group dark:border-neutral-800 dark:hover:bg-[#24272e]"
            :class="
              isActive(item.id)
                ? 'border-[#4b9e5f] bg-neutral-100 text-[#4b9e5f] dark:bg-[#24272e] dark:border-[#4b9e5f] pr-14'
                : ''
            "
            @click="handleSelect(item)"
          >
            <span>
              <SvgIcon icon="ri:message-3-line" />
            </span>
            <div class="relative flex-1 overflow-hidden break-all text-ellipsis whitespace-nowrap">
              <NInput v-if="isEdit(item.id)" v-model:value="item.title" size="tiny" />
              <span v-else>{{ item.title }}</span>
            </div>
            <div v-if="isActive(item.id)" class="absolute z-10 flex visible right-1">
              <template v-if="isEdit(item.id)">
                <button class="p-1" @click="handleUpdate(item, false, $event)">
                  <SvgIcon icon="ri:save-line" />
                </button>
              </template>
              <template v-else>
                <button class="p-1">
                  <SvgIcon icon="ri:edit-line" @click="handleEdit(item, true, $event)" />
                </button>
                <NPopconfirm
                  placement="bottom"
                  @positive-click="handleDeleteDebounce(item, $event)"
                >
                  <template #trigger>
                    <button class="p-1">
                      <SvgIcon icon="ri:delete-bin-line" />
                    </button>
                  </template>
                  {{ t('chat.deleteConversationConfirm') }}
                </NPopconfirm>
              </template>
            </div>
          </a>
        </div>
      </template>
    </div>
  </NScrollbar>
</template>
