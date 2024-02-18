<script setup lang="ts">
  import { NaiveProvider } from '@/components/common';
  import { useTheme } from '@/hooks/useTheme';
  import { useLanguage } from '@/hooks/useLanguage';
  import { NWatermark } from 'naive-ui';

  const showWatermark = true;
  const { theme, themeOverrides } = useTheme();
  const { language } = useLanguage();
</script>

<template>
  <n-config-provider
    class="h-full w-full"
    :theme="theme"
    :theme-overrides="themeOverrides"
    :locale="language"
  >
    <NaiveProvider>
      <RouterView />
    </NaiveProvider>
  </n-config-provider>

  <NWatermark
    v-if="showWatermark"
    content="LangChat"
    cross
    fullscreen
    :z-index="9999"
    :font-size="14"
    :line-height="10"
    :width="284"
    :height="384"
    :x-offset="22"
    :y-offset="100"
    :rotate="-20"
  />
</template>
