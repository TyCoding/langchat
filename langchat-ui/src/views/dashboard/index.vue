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
  import { ref, onMounted } from 'vue';
  import VisiTab from './components/VisiTab.vue';
  import { CountTo } from '@/components/CountTo';
  import { CaretUpOutlined } from '@vicons/antd';
  import { getHomeData, getReqChart } from '@/api/aigc/statictic';

  const loading = ref(true);
  const list = ref([
    {
      key: 'req',
      label: 'AI请求量',
      value: 0,
      totalLabel: 'AI总请求量',
      totalValue: 0,
      category: '日',
      type: 'success',
    },
    {
      key: 'token',
      label: 'Token消耗量',
      value: 0,
      totalLabel: 'AI总消耗Token量',
      totalValue: 0,
      category: '日',
      type: 'primary',
    },
    {
      key: 'user',
      label: '用户增长量',
      value: 0,
      totalLabel: '平台用户总量',
      totalValue: 0,
      category: '月',
      type: 'warning',
    },
    {
      key: 'knowledge',
      label: 'AI知识库数量',
      value: 0,
      totalLabel: 'Prompt提示词数量',
      totalValue: 0,
      category: '合',
      type: 'error',
    },
  ]);

  onMounted(async () => {
    const {
      totalReq,
      curReq,
      totalToken,
      curToken,
      totalUser,
      curUser,
      totalKnowledge,
      totalPrompt,
    } = await getHomeData();
    list.value.forEach((i) => {
      if (i.key === 'req') {
        i.value = Number(curReq);
        i.totalValue = Number(totalReq);
      }
      if (i.key === 'token') {
        i.value = Number(curToken);
        i.totalValue = Number(totalToken);
      }
      if (i.key === 'user') {
        i.value = Number(curUser);
        i.totalValue = Number(totalUser);
      }
      if (i.key === 'knowledge') {
        i.value = Number(totalKnowledge);
        i.totalValue = Number(totalPrompt);
      }
    });
    loading.value = false;
  });
</script>

<template>
  <div class="h-full overflow-y-auto">
    <!--数据卡片-->
    <n-grid cols="1 s:2 m:3 l:4 xl:4 2xl:4" responsive="screen" :x-gap="12" :y-gap="8">
      <n-grid-item v-for="item in list" :key="item.key">
        <NCard
          :title="item.label"
          :segmented="{ content: true, footer: true }"
          size="small"
          :bordered="false"
        >
          <template #header-extra>
            <n-tag :type="item.type" :bordered="false">{{ item.category }}</n-tag>
          </template>
          <div class="flex justify-between px-1 py-1">
            <n-skeleton v-if="loading" :width="100" size="medium" />
            <CountTo v-else :startVal="0" :endVal="item.value" class="text-2xl" />
          </div>
          <div class="flex justify-between px-1 py-1">
            <div class="text-gray-600">
              <n-skeleton v-if="loading" :width="100" size="medium" />
              <template v-else>
                {{ item.totalLabel }}
                <CountTo :startVal="0" suffix=" " :endVal="item.totalValue" />
                <n-icon size="12" color="#00ff6f">
                  <CaretUpOutlined />
                </n-icon>
              </template>
            </div>
          </div>
        </NCard>
      </n-grid-item>
    </n-grid>

    <VisiTab />
  </div>
</template>

<style lang="less" scoped></style>
