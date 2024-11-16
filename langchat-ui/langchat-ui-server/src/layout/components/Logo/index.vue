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

<template>
  <div class="logo">
    <img :class="{ 'mr-2': !collapsed }" :src="websiteConfig.logo" alt="" />
    <h2 v-show="!collapsed" class="title text-lg">{{ websiteConfig.title }}</h2>
  </div>
</template>

<script lang="ts">
  import { websiteConfig } from '@/config/website.config';

  export default {
    name: 'Index',
    props: {
      collapsed: {
        type: Boolean,
      },
    },
    data() {
      return {
        websiteConfig,
      };
    },
  };
</script>

<style lang="less" scoped>
  .logo {
    display: flex;
    align-items: center;
    justify-content: center;
    height: 64px;
    line-height: 64px;
    overflow: hidden;
    white-space: nowrap;

    img {
      width: auto;
      height: 32px;
    }

    .title {
      margin: 0;
    }
  }
</style>
