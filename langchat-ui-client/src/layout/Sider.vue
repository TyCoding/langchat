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
  import { computed } from 'vue';
  import { useDialog } from 'naive-ui';
  import { routesConst } from '@/router';
  import { useRouter } from 'vue-router';
  import { useAppStore, useUserStore } from '@/store';
  import type { Language } from '@/store/modules/app/helper';
  import { t } from '@/locales';
  import defaultAvatar from '@/assets/avatar.jpg';

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
    { label: 'English', key: 'en-US', value: 'en-US' },
  ];

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
      <div class="flex flex-col gap-3 border-neutral-800 pt-3 pl-3 pr-3">
        <n-button
          v-for="item in routesConst"
          :key="item.name"
          :class="router.currentRoute.value.name == item.name ? '!text-[#7fe7c4]' : ''"
          text
          @click="router.push({ name: item.name })"
        >
          <div
            class="rounded-[10px] bg-white dark:bg-[#34373f] w-[60px] pt-2 pb-1 flex justify-center items-center cursor-pointer flex-col"
          >
            <SvgIcon :icon="item.meta?.icon" class="text-2xl" />
            <div class="mt-1 mb-1 text-[11px]"> {{ item.meta?.label }} </div>
          </div>
        </n-button>
      </div>
    </n-scrollbar>

    <div class="m-2 flex flex-col justify-center items-center gap-2 mb-4 bottom-0">
      <n-space class="mb-2" vertical>
        <n-popover placement="right" trigger="hover">
          <template #trigger>
            <n-button
              v-if="appStore.theme == 'light'"
              size="small"
              text
              @click="appStore.setTheme('dark')"
            >
              <template #icon>
                <SvgIcon icon="ri:sun-foggy-line" />
              </template>
            </n-button>
            <n-button
              v-if="appStore.theme == 'dark'"
              size="small"
              text
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
          :options="languageOptions"
          placement="right"
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
        <n-button secondary size="tiny" type="success" @click="onLogin()">
          {{ t('side.login') }}
        </n-button>
      </template>
      <template v-else>
        <n-popover placement="right" trigger="hover">
          <template #trigger>
            <n-avatar
              :fallback-src="defaultAvatar"
              :src="user.avatar ?? '/avatar.jpg'"
              class="cursor-pointer"
              round
              @click="router.push({ name: 'Profile' })"
            />
          </template>
          <span>{{ t('side.profile') }}</span>
        </n-popover>
        <n-ellipsis style="max-width: 70px">
          {{ user.username }}
        </n-ellipsis>
        <n-button secondary size="tiny" type="warning" @click="onLogout()">
          {{ t('side.logout') }}
        </n-button>
      </template>
    </div>
  </div>
</template>

<style lang="less" scoped></style>
