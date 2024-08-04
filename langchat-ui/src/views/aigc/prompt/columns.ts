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
    title: '名称',
    key: 'name',
    width: 300,
  },
  {
    title: '提示词',
    key: 'prompt',
  },
  {
    title: '创建时间',
    key: 'createTime',
    align: 'center',
    width: 160,
  },
];

export const searchSchemas: FormSchema[] = [
  {
    field: 'title',
    component: 'NInput',
    label: '标题',
    componentProps: {
      placeholder: '请输入Prompt标题查询',
    },
  },
];

export const formSchemas: FormSchema[] = [
  {
    field: 'id',
    label: 'ID',
    component: 'NInput',
    isHidden: true,
  },
  {
    field: 'name',
    label: '标题',
    component: 'NInput',
    rules: [{ required: true, message: '请输入标题', trigger: ['blur'] }],
  },
  {
    field: 'prompt',
    component: 'NInput',
    label: '提示词',
    isFull: true,
    componentProps: {
      isFull: true,
      placeholder: '请输入提示词',
      type: 'textarea',
      autosize: {
        minRows: 15,
      },
    },
    rules: [{ required: true, message: '请输入Prompt', trigger: ['blur'] }],
  },
];
