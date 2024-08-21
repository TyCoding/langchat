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

import { FormSchema } from '@/components/Form';

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
    label: '应用名称',
    component: 'NInput',
    rules: [{ required: true, message: '请输入应用名称', trigger: ['blur'] }],
  },
  {
    field: 'modelId',
    label: '关联模型',
    component: 'NInput',
    slot: 'modelIdSlot',
    rules: [{ required: true, message: '请选择关联模型', trigger: ['blur'] }],
  },
  {
    field: 'cover',
    label: '应用封面',
    slot: 'coverSlot',
    component: 'NInput',
  },
  {
    field: 'des',
    label: '应用描述',
    component: 'NInput',
    componentProps: {
      isFull: true,
      placeholder: '请输入应用描述',
      type: 'textarea',
      autosize: {
        minRows: 5,
        maxRows: 8,
      },
    },
    // rules: [{ required: true, message: '请输入应用描述', trigger: ['blur'] }],
  },
];
