<script setup lang="ts">
  import { User } from '@/api/models';

  const props = defineProps<{
    form: User;
  }>();
</script>

<template>
  <div class="flex justify-start w-auto">
    <n-form
      label-width="auto"
      label-placement="left"
      require-mark-placement="right-hanging"
      class="w-[25%]"
    >
      <div class="pl-16 pb-4 pt-3">
        <n-avatar round class="!w-[60px] !h-[60px]" />
      </div>
      <n-form-item label="头像" path="username">
        <n-input v-model:value="form.avatar" />
      </n-form-item>
      <n-form-item label="用户名" path="username">
        <n-input v-model:value="form.username" />
      </n-form-item>
      <n-form-item label="密码" path="password">
        <n-input />
      </n-form-item>
      <n-form-item label="邮箱" path="password">
        <n-input v-model:value="form.email" />
      </n-form-item>
      <n-form-item>
        <n-space>
          <n-button type="success" secondary>更新账户</n-button>
          <n-button type="warning" secondary>退出登录</n-button>
        </n-space>
      </n-form-item>
    </n-form>
  </div>
</template>

<style scoped lang="less"></style>
