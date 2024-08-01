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

import { isBlank } from '@/utils/is';
import { t } from '@/locales';

export const rules = {
  email: {
    key: 'email',
    required: true,
    trigger: ['blur'],
    validator: (rule: any, value: string) => {
      if (isBlank(value)) {
        return new Error(t('login.namePlaceholder'));
      }
      if (!/^\w+([-+.]\w+)*@\w+([-.]\w+)*\.\w+([-.]\w+)*$/.test(value)) {
        return new Error(t('login.emailFormatErr'));
      }
      return;
    },
  },
  code: {
    key: 'code',
    required: true,
    trigger: ['blur'],
    validator: (rule: any, value: string) => {
      if (isBlank(value)) {
        return new Error(t('login.codePlaceholder'));
      }
      if (String(value).length !== 6) {
        return new Error(t('login.codeFormatErr'));
      }
      return true;
    },
  },
  password: {
    key: 'password',
    required: true,
    trigger: ['blur'],
    validator: (rule: any, value: string) => {
      if (isBlank(value)) {
        return new Error(t('login.passPlaceholder'));
      }
      if (value.length < 6) {
        return new Error(t('login.passFormat'));
      }
      return true;
    },
  },
};
