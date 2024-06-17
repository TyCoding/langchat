<script setup lang="ts"></script>

<template>
  <n-form ref="formRef" class="custom-form">
    <n-space vertical :size="16">
      <n-form-item path="age">
        <template #label>
          <n-popover placement="bottom" class="custom-popover">
            文档文件的URL地址，请确保该地址可下载文件
            <template #trigger>
              <div class="tips-line">HTTP URL</div>
            </template>
          </n-popover>
        </template>
        <n-input size="small" />
      </n-form-item>
    </n-space>
  </n-form>
</template>

<style scoped lang="less"></style>
