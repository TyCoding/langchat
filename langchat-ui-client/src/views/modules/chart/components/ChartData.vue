<!--
  - Copyright (c) 2024 LangChat. TyCoding All Rights Reserved.
  -
  - Licensed under the GNU Affero General Public License, Version 3 (the "License");
  - you may not use this file except in compliance with the License.
  - You may obtain a copy of the License at
  -
  -     https://www.gnu.org/licenses/agpl-3.0.html
  -
  - Unless required by applicable law or agreed to in writing, software
  - distributed under the License is distributed on an "AS IS" BASIS,
  - WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
  - See the License for the specific language governing permissions and
  - limitations under the License.
  -->

<script lang="ts" setup>
  import { ref } from 'vue';
  import { useMessage } from 'naive-ui';
  import { SvgIcon } from '@/components/common';
  import { genChart } from '@/api/chat';
  import { useChartStore } from '@/views/modules/chart/store';

  const chartStore = useChartStore();
  const ms = useMessage();
  const emit = defineEmits(['update']);
  const message = ref('');
  const loading = ref(false);

  async function onSubmit() {
    if (chartStore.key === '') {
      ms.error('请先选择图表类型');
      return;
    }
    ms.info('Under development...');
    return;
    loading.value = true;
    const data = await genChart({
      type: chartStore.key,
      message: message.value,
    });
    const chartData = { ...JSON.parse(data.message), ...{ legend: { show: true } } };

    chartStore.setData(chartData);
    chartStore.setStep(2);
    loading.value = false;
  }
</script>

<template>
  <n-spin :show="loading">
    <n-grid :cols="2" :x-gap="20">
      <n-gi>
        <div class="h-full w-full flex flex-col gap-2">
          <n-button size="small" type="primary" @click="onSubmit">开始解析</n-button>
          <n-input
            v-model:value="message"
            :autosize="{
              minRows: 30,
            }"
            type="textarea"
          />
        </div>
      </n-gi>

      <n-gi>
        <n-upload
          :max="5"
          action="https://www.mocky.io/v2/5e4bafc63100007100d8b70f"
          directory-dnd
          multiple
        >
          <n-upload-dragger>
            <div style="margin-bottom: 12px">
              <n-icon :depth="3" size="48">
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

<style lang="less" scoped></style>
