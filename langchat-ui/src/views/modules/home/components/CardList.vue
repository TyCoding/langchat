<script setup lang="ts">
  import { SvgIcon } from '@/components/common';
  import { NEmpty } from 'naive-ui';
  import { Bot } from '@/api/models';
  import { useRouter } from 'vue-router';
  import { t } from '@/locales';
  import { useChatStore } from '@/views/modules/chat/store/useChatStore';
  import { add as addConversation } from '@/api/conversation';
  import { Conversation } from '@/typings/chat';

  interface Props {
    list: Array<Bot>;
  }
  const props = defineProps<Props>();
  const chatStore = useChatStore();
  const router = useRouter();

  async function onUse(id: string) {
    const data = (await addConversation({ promptId: id })) as Conversation;
    chatStore.curConversation = data;
    await router.push({
      name: 'Chat',
      query: { conversationId: data.id, promptId: data.id },
    });
    chatStore.active = '';
  }
</script>

<template>
  <n-grid :x-gap="12" :y-gap="12" cols="1 400:2 1200:3 1300:4" class="mt-4">
    <n-grid-item v-for="item in props.list" :key="item">
      <n-card hoverable content-style="padding:0px">
        <div>
          <n-thing class="inline-block bg-white dark:bg-[#34373f] p-4 rounded-[2px] cursor-pointer">
            <template #avatar>
              <n-avatar :size="80">
                <SvgIcon v-if="item.icon !== null" class="text-2xl" :icon="item.icon" />
                <span class="text-2xl" v-else>{{ String(item.name).substring(0, 1) }}</span>
              </n-avatar>
            </template>
            <template #header>
              <n-ellipsis class="text-[18px]" style="max-width: 200px">
                {{ item.name }}
              </n-ellipsis>
            </template>
            <template #description>
              <n-ellipsis class="text-[14px] text-gray-400 h-[68px]" :line-clamp="3">
                {{ item.prompt }}
                <template #tooltip>
                  <div style="width: 400px">
                    {{ item.prompt }}
                  </div>
                </template>
              </n-ellipsis>
            </template>
            <template #footer>
              <div class="flex justify-between items-center">
                <div class="flex gap-1">
                  <n-tag v-for="tag in item.tags" :key="tag" size="small">{{ tag }}</n-tag>
                </div>
                <n-button @click="onUse(item.id)" size="small" type="success" secondary round>
                  {{ t('home.use') }}
                </n-button>
              </div>
            </template>
          </n-thing>
        </div>
      </n-card>
    </n-grid-item>
  </n-grid>

  <n-empty v-if="props.list.length == 0" :description="t('home.empty')" class="mt-4" />
</template>

<style scoped lang="less"></style>
