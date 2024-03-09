<script lang="ts" setup>
  import { SvgIcon } from '@/components/common';
  import { genImage } from '@/api/chat';
  import { ref } from 'vue';
  import { isBlank } from '@/utils/is';
  import { useMessage } from 'naive-ui';
  import { ImageR } from '@/api/models';

  const emit = defineEmits(['ok']);
  const loading = ref(false);
  const ms = useMessage();
  const message = ref('');
  const form = ref<ImageR>({
    message: '',
    model: 'dall-e-2',
    quality: 'standard',
    size: '1024x1024',
    style: 'vivid',
  });

  async function onSubmit() {
    if (isBlank(message.value)) {
      ms.error('请输入内容');
      return;
    }
    loading.value = true;
    ms.success('图片生成中，请稍后...');
    const data = await genImage({
      message: message.value,
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
    { value: 'dall-e-2', label: 'dall-e-2' },
    { value: 'dall-e-3', label: 'dall-e-3' },
  ];
  const qualityList = [
    { value: 'standard', label: '常规' },
    { value: 'hd', label: '细粒度' },
  ];
  const sizeList = [
    { value: '256x256', label: '256x256' },
    { value: '512x512', label: '512x512' },
    { value: '1024x1024', label: '1024x1024' },
    { value: '1792x1024', label: '1792x1024' },
    { value: '1024x1792', label: '1024x1792' },
  ];
  const styleList = [
    { value: 'vivid', label: '生动' },
    { value: 'natural', label: '自然' },
  ];
</script>

<template>
  <div class="flex flex-wrap w-full gap-3">
    <div class="w-full">
      <div class="pb-2 flex flex-wrap gap-2 justify-start items-center">
        <SvgIcon class="text-lg" icon="solar:pen-2-broken" />
        <span>图片内容描述</span>
      </div>
      <n-input v-model:value="message" :disabled="loading" :rows="5" type="textarea" />
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
        <span>DALL·E 模型</span>
      </div>
      <div class="flex justify-start gap-2">
        <n-button
          v-for="item in modelList"
          :key="item"
          :type="form.model == item.value ? 'success' : 'default'"
          secondary
          size="small"
          @click="form.model = item.value"
        >
          {{ item.label }}
        </n-button>
      </div>
    </div>

    <div class="w-full">
      <div class="pb-2 flex flex-wrap gap-2 justify-start items-center">
        <SvgIcon class="text-lg" icon="fluent:slide-size-20-regular" />
        <span>图片质量</span>
      </div>
      <div class="flex justify-start gap-2">
        <n-button
          v-for="item in qualityList"
          :key="item"
          :disabled="form.model == 'dall-e-2'"
          :type="form.quality == item.value ? 'success' : 'default'"
          secondary
          size="small"
          @click="form.quality = item.value"
        >
          {{ item.label }}
        </n-button>
      </div>
    </div>

    <div class="w-full">
      <div class="pb-2 flex flex-wrap gap-2 justify-start items-center">
        <SvgIcon class="text-lg" icon="fluent:resize-large-16-regular" />
        <span>图片尺寸</span>
      </div>
      <div class="flex justify-start gap-2 flex-wrap">
        <n-button
          v-for="item in sizeList"
          :key="item"
          :disabled="
            form.model == 'dall-e-2' && (item.value == '1792x1024' || item.value == '1024x1792')
          "
          :type="form.size == item.value ? 'success' : 'default'"
          secondary
          size="small"
          @click="form.size = item.value"
        >
          {{ item.label }}
        </n-button>
      </div>
    </div>

    <div class="w-full">
      <div class="pb-2 flex flex-wrap gap-2 justify-start items-center">
        <SvgIcon class="text-lg" icon="solar:people-nearby-broken" />
        <span>图片风格</span>
      </div>
      <div class="flex justify-start gap-2">
        <n-button
          v-for="item in styleList"
          :key="item"
          :disabled="form.model == 'dall-e-2'"
          :type="form.style == item.value ? 'success' : 'default'"
          secondary
          size="small"
          @click="form.style = item.value"
        >
          {{ item.label }}
        </n-button>
      </div>
    </div>
  </div>
</template>

<style scoped lang="less"></style>
