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

const LOCAL_NAME = 'settingsStorage';

export interface SettingsState {
  systemMessage: string;
  temperature: number;
  top_p: number;
}

export function defaultSetting(): SettingsState {
  return {
    systemMessage:
      "You are ChatGPT, a large language model trained by OpenAI. Follow the user's instructions carefully. Respond using markdown.",
    temperature: 0.8,
    top_p: 1,
  };
}

export function getLocalState(): SettingsState {
  const localSetting: SettingsState | undefined = storage.get(LOCAL_NAME);
  return { ...defaultSetting(), ...localSetting };
}

export function setLocalState(setting: SettingsState): void {
  storage.set(LOCAL_NAME, setting);
}

export function removeLocalState() {
  storage.remove(LOCAL_NAME);
}
