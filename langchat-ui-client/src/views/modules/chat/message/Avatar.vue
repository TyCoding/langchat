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
      :fallback-src="defaultAvatar"
      :src="avatar"
    />
    <NAvatar v-else :src="defaultAvatar" round />
  </template>
  <span v-else class="">
    <n-avatar :src="logoAvatar" object-fit="contain" />
  </span>
</template>

<style lang="less" scoped></style>
