<script lang="ts" setup>
  import { SvgIcon } from '@/components/common';
  import { ref } from 'vue';
  import { genWrite } from '@/api/chat';
  import { isBlank } from '@/utils/is';
  import { useMessage } from 'naive-ui';
  import { ChatR } from '@/api/models';

  const emit = defineEmits(['ok']);
  const message = useMessage();
  const loading = ref(false);
  const form = ref<ChatR>({
    message: '',
    role: '自动',
    type: '自动',
    tone: '自动',
    language: '自动',
    length: '自动',
  });

  const lengthList = ['自动', '短', '中等', '长'];
  const roleList = ['自动', '金融', '互联网', '医疗', '销售'];
  const typeList = ['自动', '邮件', '文章', '检讨书', '大纲'];
  const toneList = ['自动', '友善', '随意', '专业', '诙谐', '幽默', '正式'];
  const languageList = ['自动', '中文', '英文', '韩语', '日语'];

  async function onSubmit() {
    if (isBlank(form.value.message)) {
      message.error('请输入内容');
      return;
    }
    loading.value = true;
    await genWrite(form.value, ({ event }) => {
      const list = event.target.responseText.split('\n\n');
      let text = '';
      list.forEach((i: any) => {
        console.log(i);
        if (!i.startsWith('data:{')) {
          return;
        }
        const { done, contmessageent } = JSON.parse(i.substring(5, i.length));
        if (done) {
          message.success('翻译完成');
        } else {
          text += message;
        }
      });
      emit('ok', text);
    }).finally(() => (loading.value = false));
  }
</script>

<template>
  <div class="flex flex-wrap w-full gap-3">
    <div class="w-full">
      <div class="pb-2 flex flex-wrap gap-2 justify-start items-center">
        <SvgIcon class="text-lg" icon="solar:pen-2-broken" />
        <span>文案描述</span>
      </div>
      <n-input v-model:value="form.message" :disabled="loading" :rows="6" type="textarea" />
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
        <SvgIcon class="text-lg" icon="ri:mist-line" />
        <span>输出内容长度</span>
      </div>
      <div class="flex justify-start gap-2 flex-wrap">
        <n-button
          v-for="item in lengthList"
          :key="item"
          :type="form.role == item ? 'success' : 'default'"
          secondary
          size="small"
          @click="form.role = item"
        >
          {{ item }}
        </n-button>
      </div>
    </div>

    <div class="w-full">
      <div class="pb-2 flex gap-2 justify-start items-center">
        <SvgIcon class="text-lg" icon="gg:work-alt" />
        <span>行业</span>
      </div>
      <div class="flex justify-start gap-2 flex-wrap">
        <n-button
          v-for="item in roleList"
          :key="item"
          :type="form.role == item ? 'success' : 'default'"
          secondary
          size="small"
          @click="form.role = item"
        >
          {{ item }}
        </n-button>
        <n-input v-model:value="form.role" class="!w-[90px]" size="small" />
      </div>
    </div>

    <div class="w-full">
      <div class="pb-2 flex flex-wrap gap-2 justify-start items-center">
        <SvgIcon class="text-lg" icon="icon-park-outline:format" />
        <span>内容格式</span>
      </div>
      <div class="flex justify-start gap-2">
        <n-button
          v-for="item in typeList"
          :key="item"
          :type="form.type == item ? 'success' : 'default'"
          secondary
          size="small"
          @click="form.type = item"
        >
          {{ item }}
        </n-button>
        <n-input v-model:value="form.type" class="!w-[90px]" size="small" />
      </div>
    </div>

    <div class="w-full">
      <div class="pb-2 flex flex-wrap gap-2 justify-start items-center">
        <SvgIcon class="text-lg" icon="solar:people-nearby-broken" />
        <span>语气</span>
      </div>
      <div class="flex justify-start gap-2">
        <n-button
          v-for="item in toneList"
          :key="item"
          :type="form.tone == item ? 'success' : 'default'"
          secondary
          size="small"
          @click="form.tone = item"
        >
          {{ item }}
        </n-button>
      </div>
    </div>

    <div class="w-full">
      <div class="pb-2 flex flex-wrap gap-2 justify-start items-center">
        <SvgIcon class="text-lg" icon="clarity:language-line" />
        <span>语言</span>
      </div>
      <div class="flex justify-start gap-2">
        <n-button
          v-for="item in languageList"
          :key="item"
          :type="form.language == item ? 'success' : 'default'"
          secondary
          size="small"
          @click="form.language = item"
        >
          {{ item }}
        </n-button>
        <n-input v-model:value="form.language" class="!w-[90px]" size="small" />
      </div>
    </div>
  </div>
</template>

<style scoped lang="less"></style>
