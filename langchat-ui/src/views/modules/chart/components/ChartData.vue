<script setup lang="ts">
  import { onMounted, ref } from 'vue';
  import { useMessage } from 'naive-ui';
  import { SvgIcon } from '@/components/common';
  import { genChart } from '@/api/chat';
  import { useChartStore } from '@/views/modules/chart/store';

  const chartStore = useChartStore();
  const message = useMessage();
  const emit = defineEmits(['update']);
  const content = ref('');
  const loading = ref(false);

  async function onSubmit() {
    if (chartStore.key === '') {
      message.error('请先选择图表类型');
      return;
    }
    loading.value = true;
    const data = await genChart({
      type: chartStore.key,
      content: content.value,
    });
    const chartData = { ...JSON.parse(data.content), ...{ legend: { show: true } } };

    chartStore.setData(chartData);
    chartStore.setStep(2);
    loading.value = false;
  }
</script>

<template>
  <n-spin :show="loading">
    <n-grid :x-gap="20" :cols="2">
      <n-gi>
        <div class="h-full w-full flex flex-col gap-2">
          <n-button @click="onSubmit" size="small" type="primary">开始解析</n-button>
          <n-input
            v-model:value="content"
            type="textarea"
            :autosize="{
              minRows: 30,
            }"
          />
        </div>
      </n-gi>

      <n-gi>
        <n-upload
          multiple
          directory-dnd
          action="https://www.mocky.io/v2/5e4bafc63100007100d8b70f"
          :max="5"
        >
          <n-upload-dragger>
            <div style="margin-bottom: 12px">
              <n-icon size="48" :depth="3">
                <SvgIcon icon="entypo:upload" />
              </n-icon>
            </div>
            <n-text style="font-size: 16px"> 点击或者拖动文件到该区域来上传 </n-text>
            <n-p depth="3" style="margin: 8px 0 0 0">
              请不要上传敏感数据，比如你的银行卡号和密码，信用卡号有效期和安全码
            </n-p>
          </n-upload-dragger>
        </n-upload>
      </n-gi>
    </n-grid>
  </n-spin>
</template>

<style scoped lang="less"></style>
