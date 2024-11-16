<script lang="ts" setup>
  import defaultAvatar from '@/assets/avatar.jpg';
  import { computed } from 'vue';
  import { useDialog } from 'naive-ui';
  import { useUserStore } from '@/store';
  import { useRouter } from 'vue-router';
  import { SvgIcon } from '@/components/common';
  import { useBasicLayout } from '@/hooks/useBasicLayout';

  const { isMobile } = useBasicLayout();
  const router = useRouter();
  const dialog = useDialog();
  const userStore = useUserStore();
  const user = computed(() => userStore.user);

  async function onLogout() {
    dialog.warning({
      title: '提示',
      content: '你确定注销当前账户吗',
      positiveText: '注销',
      negativeText: '取消',
      onPositiveClick: async () => {
        await userStore.logout();
        userStore.changeIsLogin();
      },
    });
  }
  async function onLogin() {
    userStore.changeIsLogin();
  }

  function onProfile() {
    router.push({ name: 'Profile' });
  }
</script>

<template>
  <template v-if="user == null">
    <n-avatar class="cursor-pointer !text-black" round>
      <SvgIcon class="text-2xl" icon="solar:user-broken" />
    </n-avatar>
    <n-button
      :class="isMobile ? '!py-4' : '!py-4'"
      :size="isMobile ? 'tiny' : 'small'"
      block
      class="!rounded-lg"
      secondary
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
</template>

<style lang="less" scoped></style>
