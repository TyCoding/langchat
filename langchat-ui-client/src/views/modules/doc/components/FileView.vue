<script setup lang="ts">
  import '@flyfish-group/file-viewer3/dist/style.css';
  defineProps({
    url: {
      type: String,
    },
  });
</script>

<template>
  <file-viewer :url="url" />
</template>

<style scoped lang="less">
  ::v-deep(.name) {
    display: none !important;
  }
  ::v-deep(.docx) {
    color: #343639;
    background: #f2f2f2 !important;
  }
  ::v-deep(.docx span) {
    font-size: medium !important;
  }
  ::v-deep(.docx-wrapper) {
    padding: 0 !important;
    .docx {
      width: auto !important;
      padding: 5pt 15pt !important;
      //background: #f2f2f2 !important;
    }
  }
  ::v-deep(.code-area) {
    width: auto !important;
    background: none !important;
    color: #101014 !important;
    padding: 10px 17px;
    font-size: medium;
    font-family: normal !important;
    padding-bottom: 30px;
  }
  ::v-deep(.code-area:is(.dark *)) {
    background: #101014 !important;
    color: white !important;
  }
</style>
