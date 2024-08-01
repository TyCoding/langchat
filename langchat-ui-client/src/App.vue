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
  import { NaiveProvider } from '@/components/common';
  import { useTheme } from '@/hooks/useTheme';
  import { useLanguage } from '@/hooks/useLanguage';
  import { NWatermark } from 'naive-ui';

  const { theme, themeOverrides } = useTheme();
  const { language } = useLanguage();
  const waterMark = import.meta.env.VITE_WATER_MARK;
</script>

<template>
  <n-config-provider
    :locale="language"
    :theme="theme"
    :theme-overrides="themeOverrides"
    class="h-full w-full"
  >
    <NaiveProvider>
      <RouterView />
    </NaiveProvider>
  </n-config-provider>

  <NWatermark
    v-if="waterMark !== ''"
    :content="waterMark"
    :font-size="14"
    :height="384"
    :line-height="10"
    :rotate="-20"
    :width="284"
    :x-offset="22"
    :y-offset="100"
    :z-index="9999"
    cross
    fullscreen
  />
</template>
