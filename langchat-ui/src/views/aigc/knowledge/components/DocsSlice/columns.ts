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
import { h } from 'vue';
import { NTag } from 'naive-ui';

export const columns: BasicColumn[] = [
  {
    title: '文档名称',
    key: 'name',
    width: 150,
  },
  {
    title: '字符数',
    key: 'wordNum',
    width: 110,
    align: 'center',
  },
  {
    title: '切片内容',
    key: 'content',
  },
  {
    title: '切片状态',
    key: 'status',
    width: 100,
    align: 'center',
    render(row) {
      return h(
        NTag,
        {
          size: 'small',
          type: row.status == true ? 'success' : 'info',
        },
        {
          default: () => (row.status == true ? '已训练' : '未训练'),
        }
      );
    },
  },
  {
    title: '创建时间',
    key: 'createTime',
    width: 160,
  },
];

export const searchSchemas: FormSchema[] = [
  {
    field: 'docsId',
    component: 'NInput',
    label: '所属文档',
    slot: 'docsSlot',
    componentProps: {
      placeholder: '请选择文档',
    },
  },
];
