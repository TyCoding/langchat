<script setup lang="ts">
  import { onMounted, ref } from 'vue';
  import { SvgIcon } from '@/components/common';
  import Typed from 'typed.js';
  import CardList from './components/CardList.vue';
  import { Bot } from '@/api/models';
  import { getBotPage } from '@/api/prompt';
  import { useRouter } from 'vue-router';
  import { t } from '@/locales';

  const router = useRouter();
  const pageInfo = {
    page: 1,
    limit: 20,
    total: 0,
  };
  const botList = ref<Bot[]>([]);
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
    const data = await getBotPage({ ...pageInfo, title: title.value });
    botList.value = data.rows;
    pageInfo.total = data.total;
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
            <n-button @click="router.push({ name: 'Chat' })" type="success" secondary round>
              <template #icon>
                <SvgIcon icon="bx:chat" />
              </template>
              {{ t('home.chat') }}
            </n-button>
            <n-button @click="router.push({ name: 'Image' })" type="success" secondary round>
              <template #icon>
                <SvgIcon icon="radix-icons:image" />
              </template>
              {{ t('home.image') }}
            </n-button>
            <n-button @click="router.push({ name: 'Doc' })" type="success" secondary round>
              <template #icon>
                <SvgIcon icon="mingcute:doc-line" />
              </template>
              {{ t('home.doc') }}
            </n-button>
            <n-button @click="router.push({ name: 'Chart' })" type="success" secondary round>
              <template #icon>
                <SvgIcon icon="fluent:data-area-24-regular" />
              </template>
              {{ t('home.chart') }}
            </n-button>
          </div>
        </div>

        <n-input
          v-model:value="title"
          :placeholder="t('home.search')"
          @keyup.enter="fetchData"
          round
        >
          <template #suffix>
            <SvgIcon class="text-lg cursor-pointer" icon="mingcute:search-line" />
          </template>
        </n-input>

        <n-spin size="large" :show="loading">
          <CardList :list="botList" />
        </n-spin>
      </div>
    </div>
  </div>
</template>

<style scoped lang="less">
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
