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
  import { SvgIcon } from '@/components/common';
  import { genImage, getImageModels } from '@/api/chat';
  import { onMounted, ref, toRaw } from 'vue';
  import { isBlank } from '@/utils/is';
  import { useMessage } from 'naive-ui';
  import { useBasicLayout } from '@/hooks/useBasicLayout';

  const { isMobile } = useBasicLayout();
  const emit = defineEmits(['ok']);
  const loading = ref(false);
  const ms = useMessage();
  const message = ref('');
  const form = ref<any>({
    modelName: 'cogview-3',
    size: '1024x1024', // 预留，只有cogview-3-plus支持该参数
  });

  const modelProviders = ref<any[]>([]);
  onMounted(async () => {
    modelProviders.value = toRaw(await getImageModels());
  });

  async function onSubmit() {
    if (isBlank(message.value)) {
      ms.error('请输入内容');
      return;
    }
    const models = toRaw(modelProviders.value).filter((i) => i.model === form.value.modelName);
    console.log(models);
    if (models.length === 0) {
      ms.error('没有找到匹配的模型，请联系管理员');
      return;
    }
    form.value.modelId = models[0].id;
    form.value.modelProvider = models[0].provider;

    loading.value = true;
    ms.success('图片生成中，请稍后...');
    const data = await genImage({
      message: message.value,
      ...toRaw(form.value),
    })
      .catch(() => {
        ms.error('图片生成失败');
      })
      .finally(() => {
        loading.value = false;
      });
    emit('ok', data);
  }

  const modelList = [
    { value: 'cogview-3', label: 'cogview-3' },
    { value: 'cogview-3-plus', label: 'cogview-3-plus' },
  ];
  const sizeList = [
    { value: '768x1344', label: '768x1344' },
    { value: '1344x768', label: '1344x768' },
    { value: '864x1152', label: '864x1152' },
    { value: '1152x864', label: '1152x864' },
    { value: '1024x1024', label: '1024x1024' },
    { value: '1440x720', label: '1440x720' },
    { value: '720x1440', label: '720x1440' },
  ];
</script>

<template>
  <div class="flex flex-wrap w-full gap-3">
    <div class="w-full">
      <div class="pb-2 flex flex-wrap gap-2 justify-start items-center">
        <SvgIcon class="text-lg" icon="solar:pen-2-broken" />
        <span>图片内容描述</span>
      </div>
      <n-input
        v-model:value="message"
        :disabled="loading"
        :rows="isMobile ? 2 : 5"
        type="textarea"
      />
    </div>

    <div class="w-full">
      <n-button :loading="loading" block secondary type="success" @click="onSubmit">
        <template #icon>
          <SvgIcon class="text-lg" icon="ion:sparkles-outline" />
        </template>
        生成内容
      </n-button>
    </div>

    <div class="w-full">
      <div class="pb-2 flex flex-wrap gap-2 justify-start items-center">
        <SvgIcon class="text-lg" icon="carbon:model-alt" />
        <span>CogView 模型</span>
      </div>
      <div class="flex justify-start gap-2">
        <n-button
          v-for="item in modelList"
          :key="item.label"
          :type="form.modelName == item.value ? 'success' : 'default'"
          secondary
          size="small"
          @click="form.modelName = item.value"
        >
          {{ item.label }}
        </n-button>
      </div>
    </div>

    <!-- 预留选项，目前只有`cogview-3-plus`支持图片尺寸选择。doc: https://bigmodel.cn/dev/api#cogview-->
    <div class="w-full">
      <div class="pb-2 flex flex-wrap gap-2 justify-start items-center">
        <SvgIcon class="text-lg" icon="fluent:resize-large-16-regular" />
        <span>图片尺寸</span>
      </div>
      <div class="flex justify-start gap-2 flex-wrap">
        <n-button
          v-for="item in sizeList"
          :key="item"
          :disabled="form.modelName != 'cogview-3-plus'"
          :type="form.size == item.value ? 'success' : 'default'"
          secondary
          size="small"
          @click="form.size = item.value"
        >
          {{ item.label }}
        </n-button>
      </div>
    </div>
  </div>
</template>

<style lang="less" scoped></style>
