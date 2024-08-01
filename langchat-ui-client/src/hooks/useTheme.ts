/*
 * Copyright (c) 2024 LangChat. TyCoding All Rights Reserved.
 *
 * Licensed under the GNU Affero General Public License, Version 3 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     https://www.gnu.org/licenses/agpl-3.0.html
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

import type { GlobalThemeOverrides } from 'naive-ui';
import { darkTheme, useOsTheme } from 'naive-ui';
import { computed, watch } from 'vue';
import { useAppStore } from '@/store';

export function useTheme() {
  const appStore = useAppStore();

  const OsTheme = useOsTheme();

  const isDark = computed(() => {
    if (appStore.theme === 'auto') return OsTheme.value === 'dark';
    else return appStore.theme === 'dark';
  });

  const theme = computed(() => {
    return isDark.value ? darkTheme : undefined;
  });

  const themeOverrides = computed<GlobalThemeOverrides>(() => {
    if (isDark.value) {
      return {
        common: {},
      };
    }
    return {};
  });

  watch(
    () => isDark.value,
    (dark) => {
      if (dark) document.documentElement.classList.add('dark');
      else document.documentElement.classList.remove('dark');
    },
    { immediate: true }
  );

  return { theme, themeOverrides };
}
