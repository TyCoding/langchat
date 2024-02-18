<script setup lang="ts">
  import { SvgIcon } from '@/components/common';
  import { computed } from 'vue';
  import { useDialog } from 'naive-ui';
  import { routesConst } from '@/router';
  import { useRouter } from 'vue-router';
  import { useAppStore, useUserStore } from '@/store';
  import type { Language } from '@/store/modules/app/helper';
  import { t } from '@/locales';

  const appStore = useAppStore();
  const dialog = useDialog();
  const userStore = useUserStore();
  const router = useRouter();
  const user = computed(() => userStore.user);

  const language = computed({
    get() {
      return appStore.language;
    },
    set(value: Language) {
      appStore.setLanguage(value);
    },
  });
  const languageOptions: { label: string; key: Language; value: Language }[] = [
    { label: '简体中文', key: 'zh-CN', value: 'zh-CN' },
    { label: '繁體中文', key: 'zh-TW', value: 'zh-TW' },
    { label: 'English', key: 'en-US', value: 'en-US' },
    { label: '한국어', key: 'ko-KR', value: 'ko-KR' },
    { label: 'Русский язык', key: 'ru-RU', value: 'ru-RU' },
  ];

  const routerName = computed({
    get() {
      return router.currentRoute.value.name;
    },
    set() {},
  });

  async function onLogout() {
    await userStore.logout();
    await router.push({ name: 'Login' });
  }
  async function onLogin() {
    dialog.warning({
      title: t('login.title'),
      content: t('login.content'),
      positiveText: t('login.positiveText'),
      negativeText: t('login.negativeText'),
      onPositiveClick: async () => {
        await router.push({ name: 'Login' });
      },
    });
  }
</script>

<template>
  <div class="h-full bg-gray-100 dark:bg-transparent flex flex-col">
    <n-scrollbar class="flex-1">
      <div class="flex flex-col gap-3 border-neutral-800 pt-6 pl-3 pr-3">
        <n-button
          v-for="item in routesConst"
          text
          :key="item.name"
          @click="router.push({ name: item.name })"
          :class="router.currentRoute.value.name == item.name ? '!text-[#7fe7c4]' : ''"
        >
          <div
            class="rounded-[10px] bg-white dark:bg-[#34373f] w-[60px] pt-2 pb-1 flex justify-center items-center cursor-pointer flex-col"
          >
            <SvgIcon class="text-2xl" :icon="item.meta?.icon" />
            <div class="mt-1 mb-1 text-[11px]"> {{ item.meta?.label }} </div>
          </div>
        </n-button>
      </div>
    </n-scrollbar>

    <div class="m-2 flex flex-col justify-center items-center gap-2 mb-4 bottom-0">
      <n-space vertical class="mb-2">
        <n-popover trigger="hover" placement="right">
          <template #trigger>
            <n-button
              @click="router.push({ name: 'User' })"
              :type="routerName == 'User' ? 'primary' : 'default'"
              text
            >
              <SvgIcon class="text-xl" icon="lucide:user-cog" />
            </n-button>
          </template>
          <span>{{ t('side.user') }}</span>
        </n-popover>

        <n-popover trigger="hover" placement="right">
          <template #trigger>
            <n-button
              @click="router.push({ name: 'Prompt' })"
              :type="routerName == 'Prompt' ? 'primary' : 'default'"
              text
            >
              <SvgIcon class="text-xl" icon="bx:bot" />
            </n-button>
          </template>
          <span>{{ t('side.prompt') }}</span>
        </n-popover>

        <n-popover trigger="hover" placement="right">
          <template #trigger>
            <n-button
              text
              v-if="appStore.theme == 'light'"
              size="small"
              @click="appStore.setTheme('dark')"
            >
              <template #icon>
                <SvgIcon icon="ri:sun-foggy-line" />
              </template>
            </n-button>
            <n-button
              text
              v-if="appStore.theme == 'dark'"
              size="small"
              @click="appStore.setTheme('light')"
            >
              <template #icon>
                <SvgIcon icon="ri:moon-foggy-line" />
              </template>
            </n-button>
          </template>
          <span>{{ t('side.theme') }}</span>
        </n-popover>

        <n-popselect
          v-model:value="language"
          placement="right"
          :options="languageOptions"
          trigger="click"
          @update-value="(value) => appStore.setLanguage(value)"
        >
          <n-button text>
            <SvgIcon class="text-xl" icon="ph:translate-bold" />
          </n-button>
        </n-popselect>
      </n-space>

      <template v-if="user == null">
        <n-avatar class="cursor-pointer" round>
          <SvgIcon class="text-lg" icon="solar:user-broken" />
        </n-avatar>
        <n-button @click="onLogin()" size="tiny" type="success" secondary>
          {{ t('side.login') }}
        </n-button>
      </template>
      <template v-else>
        <n-popover trigger="hover" placement="right">
          <template #trigger>
            <n-avatar
              @click="router.push({ name: 'Profile' })"
              class="cursor-pointer"
              :src="user.avatar ?? '/avatar.jpg'"
              round
            />
          </template>
          <span>{{ t('side.profile') }}</span>
        </n-popover>
        <n-ellipsis style="max-width: 70px">
          {{ user.username }}
        </n-ellipsis>
        <n-button @click="onLogout()" size="tiny" secondary type="warning">
          {{ t('side.logout') }}
        </n-button>
      </template>
    </div>
  </div>
</template>

<style scoped lang="less"></style>
