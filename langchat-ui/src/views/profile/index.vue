<script setup lang="ts">
  import Account from './components/Account.vue';
  import Pass from './components/Pass.vue';
  import { SvgIcon } from '@/components/common';
  import { onMounted, ref } from 'vue';
  import { info } from '@/api/auth';

  const form = ref();
  const loading = ref(true);
  onMounted(async () => {
    form.value = await info();
    loading.value = false;
  });
</script>

<template>
  <n-spin :show="loading" class="w-full h-full">
    <div class="p-4 pl-9">
      <div class="pt-3 pb-3 text-lg">个人中心</div>
      <n-tabs type="line" animated v-if="form">
        <n-tab-pane name="1">
          <template #tab> <SvgIcon icon="solar:user-id-broken" class="mr-1" />个人信息 </template>
          <Account :form="form" />
        </n-tab-pane>
        <n-tab-pane name="2">
          <template #tab> <SvgIcon icon="carbon:password" class="mr-1" />修改密码 </template>
          <Pass />
        </n-tab-pane>
        <n-tab-pane name="3" tab="消耗" />
      </n-tabs>
      <div></div>
    </div>
  </n-spin>
</template>

<style scoped lang="less"></style>
