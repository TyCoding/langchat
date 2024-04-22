<script setup lang="ts">
  import { nextTick, ref } from 'vue';
  import { getMessages } from '@/api/modules/conversation';

  const messageRef = ref();
  const contentRef = ref();
  const loading = ref(true);
  const showModel = ref(false);
  const info = ref({});
  const messages = ref([]);

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
            :text="item.content"
            :inversion="item.role !== 'assistant'"
            :error="false"
            :loading="loading"
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

<style scoped lang="less"></style>
