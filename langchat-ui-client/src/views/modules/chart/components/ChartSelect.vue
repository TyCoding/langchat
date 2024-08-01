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
  import {
    areaBasic,
    areaSimple,
    areaStack,
    barSimple,
    barWaterfall2,
    barYCategory,
    lineSimple,
    lineStack,
    mixLineBar,
    multipleYAxis,
    pieBorderRadius,
    pieRoseType,
    pieSimple,
  } from '@/assets/chart';
  import { useChartStore } from '@/views/modules/chart/store';

  const chartStore = useChartStore();

  const charts = [
    {
      group: '折线图',
      list: [
        { key: 'line-simple', name: '基础折线图', img: lineSimple },
        { key: 'line-stack', name: '折线图堆叠', img: lineStack },
        { key: 'area-basic', name: '基础面积图', img: areaBasic },
        { key: 'area-stack', name: '堆叠面积图', img: areaStack },
        { key: 'area-simple', name: '大数据量面积图', img: areaSimple },
      ],
    },
    {
      group: '柱状图',
      list: [
        { key: 'bar-simple', name: '基础柱状图', img: barSimple },
        { key: 'bar-waterfall', name: '阶梯瀑布图', img: barWaterfall2 },
        { key: 'bar-y-category', name: '条形图', img: barYCategory },
        { key: 'mix-line-bar', name: '折柱混合图', img: mixLineBar },
        { key: 'multiple-y-axis', name: '多Y轴柱状图', img: multipleYAxis },
      ],
    },
    {
      group: '饼状图',
      list: [
        { key: 'pie-simple', name: '基础饼状图', img: pieSimple },
        { key: 'pie-border-radius', name: '圆角环形图', img: pieBorderRadius },
        { key: 'pie-rose-type', name: '基础南丁格尔玫瑰图', img: pieRoseType },
      ],
    },
  ];

  function onSelect(key: string) {
    chartStore.setKey(key);
    chartStore.setStep(1);
  }
</script>

<template>
  <div class="flex flex-col gap-4">
    <div v-for="item in charts" :key="item">
      <div class="mb-2 text-gray-700 dark:text-white text-[17px]">{{ item.group }}</div>
      <n-grid :cols="5" :x-gap="18" :y-gap="18" class="pl-0.5 pr-0.5">
        <n-gi v-for="child in item.list" :key="child">
          <n-card
            :class="child.key == chartStore.key ? 'card-active' : ''"
            class="cursor-pointer !rounded-md"
            content-style="padding: 10px"
            hoverable
            @click="onSelect(child.key)"
          >
            <template #cover>
              <div class="p-2 img-hover">
                <img :src="child.img" />
              </div>
            </template>
            <div class="text-gray-700 dark:text-gray-400">{{ child.name }}</div>
          </n-card>
        </n-gi>
      </n-grid>
    </div>
  </div>
</template>

<style lang="less" scoped>
  .img-hover:hover {
    img {
      transition: 0.3s ease-in-out;
      transform: scale(1.1);
    }
  }
  .card-active {
    outline: 2px solid #70c0e8;
  }
</style>
