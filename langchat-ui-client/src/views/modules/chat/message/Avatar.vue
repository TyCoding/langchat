<script setup lang="ts">
  import { computed } from 'vue';
  import { NAvatar } from 'naive-ui';
  import { isString } from '@/utils/is';
  import { useUserStore } from '@/store/modules/user';
  import defaultAvatar from '@/assets/avatar.jpg';
  import logoAvatar from '@/assets/login/logo.png';

  interface Props {
    image?: boolean;
  }
  defineProps<Props>();

  const userStore = useUserStore();

  const avatar = computed(() => {
    if (userStore.user == undefined) {
      return '';
    }
    return userStore.user.avatar;
  });
</script>

<template>
  <template v-if="image">
    <NAvatar
      v-if="isString(avatar) && avatar.length > 0"
      :src="avatar"
      :fallback-src="defaultAvatar"
    />
    <NAvatar v-else round :src="defaultAvatar" />
  </template>
  <span v-else class="">
    <n-avatar :src="logoAvatar" object-fit="contain" />
  </span>
</template>

<style scoped lang="less"></style>
