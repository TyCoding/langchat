<script lang="ts" setup>
  import { ref } from 'vue';
  import { embeddingSearch } from '@/api/aigc/embedding';
  import router from '@/router';

  const content = ref('');
  const loading = ref(false);
  const list = ref<any>([]);

  async function onSearch() {
    if (content.value === '') {
      list.value = [];
      return;
    }
    loading.value = true;
    list.value = await embeddingSearch({
      content: content.value,
      knowledgeId: router.currentRoute.value.params.id,
    }).finally(() => {
      loading.value = false;
    });
  }
</script>

<template>
  <n-card class="h-full w-full">
    <div class="flex h-full gap-4">
      <div class="w-1/3 flex flex-col gap-3">
        <n-button @click="onSearch" :loading="loading" secondary type="success">向量搜索</n-button>
        <n-input v-model:value="content" type="textarea" rows="10" />
      </div>
      <div class="w-full">
        <n-spin :show="loading">
          <div class="grid grid-cols-3 gap-4">
            <n-card
              hoverable
              v-for="item in list"
              :key="item.index"
              embedded
              :bordered="false"
              class="rounded-lg"
            >
              <template #header>
                <n-skeleton v-if="loading" text width="60%" />
                <template v-else>
                  <n-ellipsis> 📖 {{ item.docsName }} </n-ellipsis>
                </template>
              </template>
              <n-scrollbar style="max-height: 200px">
                {{ item.text }}
              </n-scrollbar>
            </n-card>
          </div>
          <n-empty v-if="list.length === 0" class="my-4" />
        </n-spin>
      </div>
    </div>
  </n-card>
</template>

<style lang="less" scoped></style>
