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

import { BasicColumn } from '@/components/Table';
import { h } from 'vue';
import { NTag } from 'naive-ui';

export const columns: BasicColumn<any>[] = [
  {
    title: '操作用户',
    key: 'username',
    align: 'center',
  },
  {
    title: '操作状态',
    key: 'type',
    align: 'center',
    width: 80,
    render(row) {
      return h(
        NTag,
        {
          size: 'small',
          type: row.type ? 'success' : 'error',
        },
        {
          default: () => (row.type ? '正常' : '异常'),
        }
      );
    },
  },
  {
    title: '操作描述',
    key: 'operation',
    align: 'center',
  },
  {
    title: '请求URL',
    key: 'url',
    align: 'center',
  },
  {
    title: '耗时/毫秒',
    key: 'time',
    align: 'center',
    width: 100,
  },
  {
    title: '操作方法',
    key: 'method',
    align: 'center',
  },
  {
    title: 'IP地址',
    key: 'ip',
    align: 'center',
  },
  {
    title: '操作时间',
    width: 180,
    align: 'center',
    key: 'createTime',
  },
];
