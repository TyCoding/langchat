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

import { storage } from '@/utils/storage';

const LOCAL_NAME = 'appSetting';

export type Theme = 'light' | 'dark' | 'auto';

export type Language = 'zh-CN' | 'en-US';

export interface AppState {
  siderCollapsed: boolean;
  theme: Theme;
  language: Language;
}

export function defaultSetting(): AppState {
  return { siderCollapsed: false, theme: 'light', language: 'zh-CN' };
}

export function getLocalSetting(): AppState {
  const localSetting: AppState | undefined = storage.get(LOCAL_NAME);
  return { ...defaultSetting(), ...localSetting };
}

export function setLocalSetting(setting: AppState): void {
  storage.set(LOCAL_NAME, setting);
}
