<script setup lang="ts">
  import Pptxgen from 'pptxgenjs';
  import { onMounted, ref } from 'vue';
  let pptx = new Pptxgen();
  const pptxRef = ref();

  onMounted(() => {});
</script>

<template>
  <div class="h-full w-full p-2">
    <div id="pptxRef" ref="pptxRef">
      <h1>H1</h1>
      <h2>H2</h2>
      <p>事实上司收拾收拾</p>
    </div>
    <!--    <iframe-->
    <!--      width="100%"-->
    <!--      height="100%"-->
    <!--      src="https://view.officeapps.live.com/op/embed.aspx?src=https://djgurnpwsdoqjscwqbsj.supabase.co/storage/v1/object/public/presentations/3d68fd2ac262bdbd.pptx"-->
    <!--    ></iframe>-->
  </div>
</template>

<style scoped lang="less"></style>
