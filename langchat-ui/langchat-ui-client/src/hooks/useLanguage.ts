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

import { computed } from 'vue';
import { enUS, koKR, zhCN, zhTW } from 'naive-ui';
import { useAppStore } from '@/store';
import { setLocale } from '@/locales';

export function useLanguage() {
  const appStore = useAppStore();

  const language = computed(() => {
    switch (appStore.language) {
      case 'en-US':
        setLocale('en-US');
        return enUS;
      case 'ru-RU':
        setLocale('ru-RU');
        return enUS;
      case 'ko-KR':
        setLocale('ko-KR');
        return koKR;
      case 'zh-CN':
        setLocale('zh-CN');
        return zhCN;
      case 'zh-TW':
        setLocale('zh-TW');
        return zhTW;
      default:
        setLocale('zh-CN');
        return zhCN;
    }
  });

  return { language };
}
