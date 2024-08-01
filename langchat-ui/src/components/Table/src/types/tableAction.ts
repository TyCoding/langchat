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

import { NButton } from 'naive-ui';
import type { Component } from 'vue';
import { PermissionsEnum } from '@/enums/permissionsEnum';
export interface ActionItem extends Partial<InstanceType<typeof NButton>> {
  onClick?: Fn;
  label?: string;
  type?: 'success' | 'error' | 'warning' | 'info' | 'primary' | 'default';
  // 设定 color 后会覆盖 type 的样式
  color?: string;
  icon?: Component;
  popConfirm?: PopConfirm;
  disabled?: boolean;
  divider?: boolean;
  // 权限编码控制是否显示
  auth?: PermissionsEnum | PermissionsEnum[] | string | string[];
  // 业务控制是否显示
  ifShow?: boolean | ((action: ActionItem) => boolean);
}

export interface PopConfirm {
  title: string;
  okText?: string;
  cancelText?: string;
  confirm: Fn;
  cancel?: Fn;
  icon?: Component;
}
