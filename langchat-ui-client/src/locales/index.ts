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

import type { App } from 'vue';
import { createI18n } from 'vue-i18n';
import enUS from './en-US';
import zhCN from './zh-CN';
import { useAppStoreWithOut } from '@/store/modules/app';
import type { Language } from '@/store/modules/app/helper';

const appStore = useAppStoreWithOut();

const defaultLocale = appStore.language || 'zh-CN';

const i18n = createI18n({
  locale: defaultLocale,
  fallbackLocale: 'en-US',
  allowComposition: true,
  messages: {
    'en-US': enUS,
    'zh-CN': zhCN,
  },
});

export const t = i18n.global.t;

export function setLocale(locale: Language) {
  i18n.global.locale = locale;
}

export function setupI18n(app: App) {
  app.use(i18n);
}

export default i18n;
