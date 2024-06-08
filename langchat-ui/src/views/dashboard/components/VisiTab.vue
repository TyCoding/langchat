<template>
  <div class="mt-4">
    <NRow :gutter="24">
      <NCol :span="24">
        <n-card content-style="padding: 0;" :bordered="false">
          <n-tabs type="line" size="large" :tabs-padding="20" pane-style="padding: 20px;">
            <n-tab-pane name="AI请求量统计">
              <AiReqChart />
            </n-tab-pane>
            <n-tab-pane name="Token消耗量统计">
              <AiTokenChart />
            </n-tab-pane>
          </n-tabs>
        </n-card>
      </NCol>
    </NRow>
  </div>
</template>
<script lang="ts" setup>
  import AiReqChart from './AiReqChart.vue';
  import AiTokenChart from './AiTokenChart.vue';
</script>
