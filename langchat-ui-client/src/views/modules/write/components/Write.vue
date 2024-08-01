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
  import { ref, toRaw } from 'vue';
  import { genWrite } from '@/api/chat';
  import { isBlank } from '@/utils/is';
  import { useMessage } from 'naive-ui';
  import { useChatStore } from '@/views/modules/chat/store/useChatStore';

  const emit = defineEmits(['ok']);
  const ms = useMessage();
  const chatStore = useChatStore();
  const loading = ref(false);
  const form = ref<any>({
    message: '',
    profession: '自动',
    type: '自动',
    tone: '自动',
    language: '自动',
    length: '自动',
  });

  const lengthList = ['自动', '短', '中等', '长'];
  const professionList = ['自动', '金融', '互联网', '医疗', '销售'];
  const typeList = ['自动', '邮件', '文章', '检讨书', '大纲'];
  const toneList = ['自动', '友善', '随意', '专业', '诙谐', '幽默', '正式'];
  const languageList = ['自动', '中文', '英文', '韩语', '日语'];

  async function onSubmit() {
    if (isBlank(form.value.message)) {
      ms.error('请输入内容');
      return;
    }
    emit('ok', '');
    loading.value = true;
    await genWrite(
      {
        ...toRaw(form.value),
        modelId: chatStore.modelId,
        modelName: chatStore.modelName,
        modelProvider: chatStore.modelProvider,
      },
      ({ event }) => {
        const list = event.target.responseText.split('\n\n');
        let text = '';
        list.forEach((i: any) => {
          console.log(i);
          if (!i.startsWith('data:{')) {
            return;
          }
          const { done, message } = JSON.parse(i.substring(5, i.length));
          if (done) {
            ms.success('编写完成');
          } else {
            text += message;
          }
        });
        emit('ok', text);
      }
    ).finally(() => (loading.value = false));
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
          :type="form.length == item ? 'success' : 'default'"
          secondary
          size="small"
          @click="form.length = item"
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
          v-for="item in professionList"
          :key="item"
          :type="form.profession == item ? 'success' : 'default'"
          secondary
          size="small"
          @click="form.profession = item"
        >
          {{ item }}
        </n-button>
        <n-input v-model:value="form.profession" class="!w-[90px]" size="small" />
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

<style lang="less" scoped></style>
