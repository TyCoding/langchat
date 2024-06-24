<script setup lang="ts">
  const props = defineProps({
    url: {
      type: String,
    },
  });

  // 在生产环境下使用微软的office在线预览方式，导入支持office和word导入，在线预览不支持开发环境
  const path =
    process.env.NODE_ENV === 'development'
      ? props.url
      : `http://view.officeapps.live.com/op/view.aspx?src=${props.url}`;
</script>

<template>
  <iframe :src="path" width="100%" height="100%" frameborder="0" class="!z-0"></iframe>
</template>

<style scoped lang="less"></style>
