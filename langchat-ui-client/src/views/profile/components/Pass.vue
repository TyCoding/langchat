<script setup lang="ts"></script>

<template>
  <div class="flex justify-start w-auto mt-4">
    <n-form
      label-width="auto"
      label-placement="left"
      require-mark-placement="right-hanging"
      class="w-[25%]"
    >
      <n-form-item label="原始密码" path="password">
        <n-input type="password" />
      </n-form-item>
      <n-form-item label="新密码" path="username">
        <n-input type="password" />
      </n-form-item>
      <n-form-item label="重复密码" path="password">
        <n-input type="password" />
      </n-form-item>
      <n-form-item>
        <n-space>
          <n-button type="success" secondary>更新密码</n-button>
        </n-space>
      </n-form-item>
    </n-form>
  </div>
</template>

<style scoped lang="less"></style>
