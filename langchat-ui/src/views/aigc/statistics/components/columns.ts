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
    title: '模型名称',
    key: 'model',
    align: 'center',
  },
  {
    title: 'Tokens',
    key: 'tokens',
    align: 'center',
  },
  {
    title: 'Prompt Tokens',
    key: 'promptTokens',
    align: 'center',
  },
  {
    title: 'Prompt Tokens',
    key: 'promptTokens',
    align: 'center',
  },
  {
    title: 'IP地址',
    key: 'ip',
    align: 'center',
  },
  {
    title: '调用时间',
    key: 'createTime',
    align: 'center',
    width: 180,
  },
];

export const searchSchemas: FormSchema[] = [
  {
    field: 'name',
    component: 'NInput',
    label: '用户名',
    componentProps: {
      placeholder: '请输入用户名查询',
    },
  },
];
