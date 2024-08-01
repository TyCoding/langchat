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
  import { computed, ref, watch } from 'vue';
  import { SvgIcon } from '@/components/common';
  import { t } from '@/locales';

  const props = defineProps<{
    loading: boolean;
    genText: string;
  }>();
  const loading = computed(() => {
    return props.loading;
  });
  const emit = defineEmits(['generate', 'case']);
  const text = ref('');
  const gen = ref('');

  watch(
    () => props.genText,
    (val) => {
      gen.value = val;
    }
  );

  function onCase() {
    text.value = `番茄炒蛋怎么做`;
    gen.value = `
# 番茄炒蛋的制作流程

## 材料准备
- 购买新鲜的番茄和鸡蛋
- 准备调味料，如盐，糖，酱油等

## 制作步骤
### 步骤1：准备食材
- 清洗番茄，切成块状
- 破开鸡蛋，搅拌均匀

### 步骤2：炒制
- 热锅凉油，倒入鸡蛋液翻炒至熟
- 将炒好的鸡蛋盛出备用
- 锅里重新加油，放入番茄翻炒至汁液出来

### 步骤3：调味
- 倒入炒好的鸡蛋，加入适量的盐，糖，酱油等调料翻炒均匀

### 步骤4：出锅
- 炒至食材完全融合，出锅即可

## 注意事项
- 注意控制火候，避免炒焦
- 根据个人口味调整调料比例
		`;
    emit('case', gen.value);
  }

  function onGenerate() {
    emit('generate', text.value);
  }
</script>

<template>
  <div class="p-4">
    <n-input
      v-model:value="text"
      :disabled="loading"
      :placeholder="t('mindmap.inputTips')"
      :rows="6"
      type="textarea"
    />
    <div class="mt-2 mb-2">
      <n-button :loading="loading" block secondary type="success" @click="onGenerate">
        <template #icon>
          <SvgIcon class="text-lg" icon="ion:sparkles-outline" />
        </template>
        {{ t('mindmap.confirm') }}
      </n-button>
    </div>

    <div class="mt-6">
      <div class="flex flex-wrap justify-between items-center mb-2">
        <div>{{ t('mindmap.output') }}</div>
        <n-button text type="success" @click="onCase">{{ t('mindmap.example') }}</n-button>
      </div>
    </div>
    <n-input
      v-model:value="gen"
      :placeholder="t('mindmap.outputTips')"
      :rows="16"
      type="textarea"
    />
  </div>
</template>

<style lang="less" scoped></style>
