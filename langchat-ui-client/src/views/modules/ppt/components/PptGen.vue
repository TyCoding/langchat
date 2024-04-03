<script setup lang="ts">
  import Pptxgen from 'pptxgenjs';
  import { onMounted, ref } from 'vue';
  import Docxtemplater from 'docxtemplater';
  import PizZipUtils from 'pizzip/utils';
  import PizZip from 'pizzip';
  import { downloadBlob } from '@/utils/downloadFile';
  import { SvgIcon } from '@/components/common';
  import ChartSelect from '@/views/modules/chart/components/ChartSelect.vue';
  import ChartData from '@/views/modules/chart/components/ChartData.vue';
  import ChartConfig from '@/views/modules/chart/components/ChartConfig.vue';
  import ChartExport from '@/views/modules/chart/components/ChartExport.vue';
  let pptx = new Pptxgen();
  const pptxRef = ref();

  onMounted(() => {
    PizZipUtils.getBinaryContent('https://docxtemplater.com/tag-example.docx', (err, data) => {
      const zip = new PizZip(data);
      const doc = new Docxtemplater(zip, { paragraphLoop: true, linebreaks: true });
      doc.render({
        first_name: 'TyCoding',
        last_name: 'Tumo',
        phone: '0652455478',
        description: 'New Website',
      });
      const out = doc.getZip().generate({
        type: 'blob',
        mimeType: 'application/vnd.openxmlformats-officedocument.wordprocessingml.document',
      });
      // downloadBlob(out, 'output.docx');
    });
  });
</script>

<template>
  <div class="h-full w-full p-2">
    <div id="pptxRef" ref="pptxRef">
      <h1>H1</h1>
      <h2>H2</h2>
      <p>事实上司收拾收拾</p>
    </div>
    <iframe
      width="100%"
      height="100%"
      src="https://view.officeapps.live.com/op/embed.aspx?src=https://djgurnpwsdoqjscwqbsj.supabase.co/storage/v1/object/public/presentations/3d68fd2ac262bdbd.pptx"
    ></iframe>
  </div>
</template>

<style scoped lang="less"></style>
