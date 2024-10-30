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

import { h } from 'vue';
import { NTag } from 'naive-ui';

export const baseColumns = [
  {
    title: '模型别名',
    key: 'name',
  },
  {
    title: '模型版本',
    key: 'model',
    width: '160',
  },
  {
    title: '向量纬度',
    key: 'dimension',
    align: 'center',
    width: '100',
    render(row) {
      return h(
        NTag,
        {
          size: 'small',
          type: 'error',
        },
        {
          default: () => row.dimension,
        }
      );
    },
  },
  {
    title: 'Api Key',
    key: 'apiKey',
  },
  {
    title: 'Base Url',
    key: 'baseUrl',
  },
];

export function getColumns() {
  return baseColumns;
}
