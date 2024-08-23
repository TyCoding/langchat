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
    dialog.warning({
      title: '提示',
      content: '你确定注销当前账户吗',
      positiveText: '注销',
      negativeText: '取消',
      onPositiveClick: async () => {
        await userStore.logout();
        await router.push({ name: 'Login' });
      },
    });
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

  function onProfile() {
    router.push({ name: 'Profile' });
  }
</script>

<template>
  <div
    class="h-full bg-[#fafafa] dark:bg-transparent flex border dark:border-[#ffffff17] flex-col w-full rounded-lg"
  >
    <n-scrollbar class="flex-1 w-full">
      <div
        class="flex flex-col gap-3 border-neutral-800 dark:border-[#ffffff17] pt-3 pl-3 pr-3 w-full"
      >
        <div class="text-lg gap-2 text-center flex justify-center items-center">
          <img height="30" src="@/assets/login/logo.png" width="30" />
          <span class="font-bold">LangChat</span>
        </div>
        <n-divider class="!my-0 !py-0" />
        <n-button
          v-for="item in routesConst"
          :key="item.name"
          :class="
            router.currentRoute.value.name == item.name
              ? '!text-[#18a058] w-full !bg-[#eff0f0] dark:!bg-[#ffffff1a] dark:border-[#ffffff17] !rounded-[8px]'
              : 'w-full'
          "
          text
          @click="router.push({ name: item.name })"
        >
          <div class="w-full px-4 py-3 flex items-center cursor-pointer gap-3.5">
            <SvgIcon :icon="item.meta?.icon" class="text-lg" />
            <div class="mt-1 mb-1 font-medium text-[14px]"> {{ item.meta?.label }} </div>
          </div>
        </n-button>
      </div>
    </n-scrollbar>

    <div class="m-2 flex flex-col justify-center items-center gap-2 mb-4 bottom-0">
      <n-divider class="!my-0 !py-0" />
      <!--      <n-space class="mb-2" vertical>
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
			</n-space>-->

      <template v-if="user == null">
        <n-avatar class="cursor-pointer !text-black" round>
          <SvgIcon class="text-2xl" icon="solar:user-broken" />
        </n-avatar>
        <n-button
          block
          class="!rounded-lg"
          secondary
          size="small"
          type="success"
          @click="onLogin()"
        >
          <span class="text-center w-full">登录系统</span>
        </n-button>
      </template>
      <div v-else class="flex w-full flex-col gap-3 mb-2 px-2">
        <div class="flex gap-2 items-center px-2">
          <n-avatar
            :fallback-src="defaultAvatar"
            :src="user.avatar ?? '/avatar.jpg'"
            class="cursor-pointer w-[30px]"
            round
          />
          <n-ellipsis style="max-width: 85px">
            {{ user.username }}
          </n-ellipsis>
        </div>

        <n-button class="!rounded-lg" size="small" tertiary type="info" @click="onProfile()">
          <div class="w-full text-center flex justify-center items-center gap-2">
            <SvgIcon class="text-lg" icon="iconamoon:profile" />
            <span>个人中心</span>
          </div>
        </n-button>

        <n-button class="!rounded-lg" secondary size="small" type="warning" @click="onLogout()">
          <div class="w-full text-center flex justify-center items-center gap-2">
            <SvgIcon class="text-lg" icon="material-symbols:logout" />
            <span>注销账户</span>
          </div>
        </n-button>
      </div>
    </div>
  </div>
</template>

<style lang="less" scoped>
  ::v-deep(.n-button .n-button__content) {
    width: 100% !important;
  }
</style>
