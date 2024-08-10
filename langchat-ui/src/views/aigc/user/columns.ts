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
    title: '用户名',
    key: 'username',
    align: 'center',
  },
  {
    title: '用户昵称',
    key: 'nickname',
    align: 'center',
  },
  {
    title: '会话次数',
    key: 'chatLimit',
    align: 'center',
  },
  {
    title: 'Token消耗量',
    key: 'tokenUsed',
    align: 'center',
  },
  {
    title: '操作权限',
    key: 'isPerms',
    align: 'center',
    width: 120,
    render(row) {
      return h(
        NTag,
        {
          type: row.isPerms ? 'success' : 'error',
          size: 'small',
        },
        {
          default: () => (row.isPerms ? '可用' : '无权限'),
        }
      );
    },
  },
  {
    title: '账号状态',
    key: 'status',
    align: 'center',
    width: 120,
    render(row) {
      return h(
        NTag,
        {
          type: row.status ? 'success' : 'error',
          size: 'small',
        },
        {
          default: () => (row.status ? '启用' : '禁用'),
        }
      );
    },
  },
  {
    title: '邮箱',
    key: 'email',
    align: 'center',
  },
  {
    title: '手机',
    key: 'phone',
    align: 'center',
    width: 120,
  },
];

export const searchSchemas: FormSchema[] = [
  {
    field: 'name',
    component: 'NInput',
    label: '用户名',
    componentProps: {
      placeholder: '请输入用户名',
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
    field: 'username',
    label: '用户名',
    component: 'NInput',
    componentProps: {
      placeholder: '请输入用户名',
    },
    rules: [{ required: true, message: '请输入用户名', trigger: ['blur'] }],
  },
  {
    field: 'password',
    label: '密码',
    component: 'NInput',
    componentProps: {
      type: 'password',
      placeholder: '请输入密码',
    },
  },
  {
    field: 'nickname',
    label: '用户昵称',
    component: 'NInput',
    componentProps: {
      placeholder: '请输入用户昵称',
    },
    rules: [{ required: true, message: '请输入用户昵称', trigger: ['blur'] }],
  },
  {
    field: 'isPerms',
    component: 'NRadioGroup',
    label: '操作权限',
    componentProps: {
      options: [
        {
          label: '启用',
          value: true,
        },
        {
          label: '禁用',
          value: false,
        },
      ],
    },
  },
  {
    field: 'phone',
    label: '手机号',
    component: 'NInput',
    componentProps: {
      placeholder: '请输入手机号',
    },
  },
  {
    field: 'email',
    label: '邮箱',
    component: 'NInput',
    componentProps: {
      placeholder: '请输入邮箱',
    },
  },
];
