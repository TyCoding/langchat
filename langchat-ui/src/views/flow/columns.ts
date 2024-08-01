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
import { NButton, NTag } from 'naive-ui';
import router from '@/router';
import { isNullOrWhitespace } from '@/utils/is';

export const columns: BasicColumn<any>[] = [
  // {
  //   title: '流程ID',
  //   key: 'id',
  //   width: 200,
  // },
  {
    title: '流程名称',
    key: 'name',
    width: 300,
    align: 'center',
    render(row: any) {
      return h(
        NButton,
        {
          text: true,
          tag: 'a',
          type: 'success',
          onClick: () => {
            if (isNullOrWhitespace(row.flow)) {
              router.push({ name: 'flow_initialize', params: { id: row.id } });
            } else {
              router.push({ name: 'flow_edit', params: { id: row.id } });
            }
          },
        },
        {
          default: () => row.name,
        }
      );
    },
  },
  {
    title: '发布状态',
    key: 'type',
    width: 90,
    align: 'center',
    render(row) {
      return h(
        NTag,
        {
          size: 'small',
          type: row.isPublish ? 'success' : 'error',
        },
        {
          default: () => (row.isPublish ? '已发布' : '未发布'),
        }
      );
    },
  },
  {
    title: '流程描述',
    key: 'des',
    align: 'center',
  },
  // {
  //   title: '创建时间',
  //   key: 'createTime',
  //   align: 'center',
  //   width: 110,
  // },
  {
    title: '编辑时间',
    key: 'updateTime',
    align: 'center',
    width: 110,
  },
  {
    title: '发布时间',
    key: 'publishTime',
    align: 'center',
    width: 110,
  },
];

export const searchSchemas: FormSchema[] = [
  {
    field: 'name',
    component: 'NInput',
    label: '流程名称',
    componentProps: {
      placeholder: '请输入流程名称',
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
    label: '流程名称',
    component: 'NInput',
    componentProps: {
      placeholder: '请输入流程名称',
    },
    rules: [{ required: true, message: '请输入流程名称', trigger: ['blur'] }],
  },
  {
    field: 'des',
    component: 'NInput',
    label: '流程描述',
    isFull: true,
    componentProps: {
      placeholder: '请输入流程描述',
      type: 'textarea',
      autosize: {
        minRows: 5,
        maxRows: 8,
      },
    },
  },
];
