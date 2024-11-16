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

import { App } from 'vue';

import { permission } from '@/directives/permission';
import copy from '@/directives/copy';
import debounce from '@/directives/debounce';
import throttle from '@/directives/throttle';
import draggable from '@/directives/draggable';

/**
 * 注册全局自定义指令
 * @param app
 */
export function setupDirectives(app: App) {
  // 权限控制指令（演示）
  app.directive('permission', permission);
  // 复制指令
  app.directive('copy', copy);
  // 防抖指令
  app.directive('debounce', debounce);
  // 节流指令
  app.directive('throttle', throttle);
  // 拖拽指令
  app.directive('draggable', draggable);
}
