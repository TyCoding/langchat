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
import { BasicColumn } from '@/components/Table';
import { h } from 'vue';
import { NTag } from 'naive-ui';
import { constantRouterIcon } from '@/router/icons';

export const columns: BasicColumn[] = [
  {
    title: '菜单名称',
    key: 'name',
  },
  {
    title: '菜单路径',
    key: 'path',
  },
  {
    title: '菜单类型',
    key: 'type',
    align: 'center',
    width: 100,
    render(row) {
      return h(
        NTag,
        {
          size: 'small',
          type: row.type == 'button' ? 'success' : 'info',
        },
        {
          default: () => (row.type == 'button' ? '按钮' : '菜单'),
        }
      );
    },
  },
  {
    title: '组件路径',
    key: 'component',
  },
  {
    title: '图标',
    key: 'icon',
    align: 'center',
    width: 80,
    render(row: any) {
      if (row.meta == null || row.meta.icon == null || constantRouterIcon[row.meta.icon] == null) {
        return h('span', {});
      }
      return h(constantRouterIcon[row.meta.icon], {});
    },
  },
  {
    title: '排序',
    key: 'orderNo',
    align: 'center',
    width: 80,
  },
  {
    title: '权限标识',
    key: 'perms',
  },
  {
    title: '状态',
    key: 'disabled',
    align: 'center',
    width: 70,
    render(row) {
      return h(
        NTag,
        {
          size: 'small',
          type: row.disabled ? 'error' : 'success',
        },
        {
          default: () => (row.disabled ? '禁用' : '启用'),
        }
      );
    },
  },
];

export const searchSchemas: FormSchema[] = [
  {
    field: 'name',
    component: 'NInput',
    label: '菜单名称',
    componentProps: {
      placeholder: '请输入菜单名称',
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
    label: '菜单名称',
    component: 'NInput',
    componentProps: {
      placeholder: '请输入菜单名称',
    },
    rules: [{ required: true, message: '请输入菜单名称', trigger: ['blur'] }],
  },
  {
    field: 'parentId',
    label: '上级菜单',
    slot: 'menuSlot',
    component: 'NInput',
  },
  {
    field: 'type',
    component: 'NRadioGroup',
    label: '菜单类型',
    slot: 'typeSlot',
    rules: [{ required: true, message: '请选择菜单类型', trigger: ['blur'] }],
  },
  {
    field: 'isDisabled',
    component: 'NRadioGroup',
    label: '是否禁用',
    componentProps: {
      options: [
        {
          label: '启用',
          value: false,
        },
        {
          label: '禁用',
          value: true,
        },
      ],
    },
  },
  {
    field: 'perms',
    label: '权限标识',
    labelMessage: '访问权限的Code',
    component: 'NInput',
    componentProps: {
      placeholder: '请输入权限标识',
    },
    rules: [{ required: true, message: '请输入权限标识', trigger: ['blur'] }],
  },
  {
    field: 'path',
    label: '菜单路径',
    labelMessage: '如果是根目录菜单没有父级菜单时为Layout',
    component: 'NInput',
    componentProps: {
      placeholder: '请输入菜单路径',
    },
    rules: [{ required: true, message: '请输入菜单路径', trigger: ['blur'] }],
  },
  {
    field: 'icon',
    label: '菜单图标',
    slot: 'iconSlot',
    component: 'NInput',
  },
  {
    field: 'component',
    label: '组件路径',
    slot: 'componentSlot',
    labelMessage: '.vue文件所在src/views目录位置',
    component: 'NInput',
    componentProps: {
      placeholder: '请输入组件路径',
    },
    rules: [{ required: true, message: '请输入组件路径', trigger: ['blur'] }],
  },
  {
    field: 'orderNo',
    label: '排序',
    labelMessage: '菜单的显示顺序',
    component: 'NInputNumber',
    componentProps: {
      placeholder: '请输入排序',
    },
    rules: [{ type: 'number', required: true, message: '请输入排序', trigger: ['blur'] }],
  },
  {
    field: 'isExt',
    component: 'NRadioGroup',
    label: '是否外链',
    labelMessage: '为外链的时候点击弹出新窗口',
    componentProps: {
      options: [
        {
          label: '组件',
          value: false,
        },
        {
          label: '外链',
          value: true,
        },
      ],
    },
  },
  {
    field: 'isKeepalive',
    component: 'NRadioGroup',
    label: '是否缓存',
    labelMessage: '使用在页面Tab中缓存上次页面数据，缓存则不会主动刷新',
    componentProps: {
      options: [
        {
          label: '缓存',
          value: true,
        },
        {
          label: '不缓存',
          value: false,
        },
      ],
    },
  },
];

export const typeOptions = [
  {
    label: '菜单',
    value: 'menu',
  },
  {
    label: '按钮',
    value: 'button',
  },
];
