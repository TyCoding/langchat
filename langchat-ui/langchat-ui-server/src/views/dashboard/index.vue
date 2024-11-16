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
  import { onMounted, ref } from 'vue';
  import VisiTab from './components/VisiTab.vue';
  import { CountTo } from '@/components/CountTo';
  import { CaretUpOutlined } from '@vicons/antd';
  import { getHomeData } from '@/api/aigc/statictic';

  const loading = ref(true);
  const list = ref([
    {
      key: 'req',
      label: 'LLM请求量',
      value: 0,
      totalLabel: 'LLM总请求量',
      totalValue: 0,
      category: '日',
      type: 'success',
    },
    {
      key: 'token',
      label: 'Token消耗量',
      value: 0,
      totalLabel: 'Token总消耗量',
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
      label: '知识库数量',
      value: 0,
      totalLabel: 'AI应用数量',
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
    <n-grid :x-gap="12" :y-gap="8" cols="1 s:2 m:3 l:4 xl:4 2xl:4" responsive="screen">
      <n-grid-item v-for="item in list" :key="item.key">
        <NCard
          :bordered="false"
          :segmented="{ content: true, footer: true }"
          :title="item.label"
          size="small"
        >
          <template #header-extra>
            <n-tag :bordered="false" :type="item.type">{{ item.category }}</n-tag>
          </template>
          <div class="flex justify-between px-1 py-1">
            <n-skeleton v-if="loading" :width="100" size="medium" />
            <CountTo v-else :endVal="item.value" :startVal="0" class="text-2xl" />
          </div>
          <div class="flex justify-between px-1 py-1">
            <div class="text-gray-600">
              <n-skeleton v-if="loading" :width="100" size="medium" />
              <template v-else>
                {{ item.totalLabel }}
                <CountTo :endVal="item.totalValue" :startVal="0" suffix=" " />
                <n-icon color="#00ff6f" size="12">
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
