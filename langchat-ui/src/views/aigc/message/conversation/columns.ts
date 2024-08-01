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
import { FormSchema } from '@/components/Form';

export const columns: BasicColumn[] = [
  {
    title: '用户名',
    key: 'username',
    align: 'center',
  },
  {
    title: '窗口标题',
    key: 'title',
    align: 'center',
  },
  {
    title: '对话次数',
    key: 'chatTotal',
    align: 'center',
    width: 180,
  },
  {
    title: 'Token消耗量',
    key: 'tokenUsed',
    align: 'center',
    width: 180,
  },
  {
    title: '最后一次对话时间',
    key: 'endTime',
    align: 'center',
    width: 180,
  },
  {
    title: '创建时间',
    key: 'createTime',
    width: 180,
    align: 'center',
  },
];

export const searchSchemas: FormSchema[] = [
  {
    field: 'text',
    component: 'NInput',
    label: '内容',
    componentProps: {
      placeholder: '请输入内容',
    },
  },
];
