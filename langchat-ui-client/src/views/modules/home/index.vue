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
  import { SvgIcon } from '@/components/common';
  import Typed from 'typed.js';
  import CardList from './components/CardList.vue';
  import { Bot } from '@/api/models';
  import { getPrompts } from '@/api/chat';
  import { useRouter } from 'vue-router';
  import { t } from '@/locales';

  const router = useRouter();
  const prompts = ref<Bot[]>([]);
  const loading = ref(true);
  const title = ref('');

  onMounted(async () => {
    const des = t('home.des');
    new Typed('#typed', {
      strings: des.split('\n'),
      typeSpeed: 70,
      backSpeed: 70,
      loop: true,
    });

    await fetchData();
  });

  async function fetchData() {
    prompts.value = await getPrompts({ title: title.value });
    loading.value = false;
  }
</script>

<template>
  <div class="h-full w-full flex grid-mask">
    <div class="w-full max-w-screen-2xl m-auto">
      <div class="flex h-screen justify-center items-center flex-col p-2">
        <div class="flex flex-col justify-center w-full items-center mt-14">
          <NGradientText :size="37" type="success"> {{ $t('home.title') }} </NGradientText>
          <div class="text-[18px] h-[28px] text-gray-600">
            <span id="typed"></span>
          </div>

          <div class="flex flex-wrap gap-2 mt-4 mb-8">
            <n-button round secondary type="success" @click="router.push({ name: 'Chat' })">
              <template #icon>
                <SvgIcon icon="bx:chat" />
              </template>
              {{ t('home.chat') }}
            </n-button>
            <n-button round secondary type="success" @click="router.push({ name: 'Image' })">
              <template #icon>
                <SvgIcon icon="radix-icons:image" />
              </template>
              {{ t('home.image') }}
            </n-button>
            <n-button round secondary type="success" @click="router.push({ name: 'Doc' })">
              <template #icon>
                <SvgIcon icon="mingcute:doc-line" />
              </template>
              {{ t('home.doc') }}
            </n-button>
          </div>
        </div>

        <n-input
          v-model:value="title"
          :placeholder="t('home.search')"
          round
          @keyup.enter="fetchData"
        >
          <template #suffix>
            <SvgIcon class="text-lg cursor-pointer" icon="mingcute:search-line" />
          </template>
        </n-input>

        <n-spin :show="loading" size="large">
          <CardList :list="prompts" />
        </n-spin>
      </div>
    </div>
  </div>
</template>

<style lang="less" scoped>
  .grid-mask {
    height: 100%;
    background-image: radial-gradient(circle at center, rgba(0, 0, 0, 0.13) 0.8px, transparent 0);
    background-size: 16px 16px;
    background-repeat: round;
  }
  ::v-deep(.n-spin-container) {
    width: 100%;
    height: 100%;
  }
</style>
