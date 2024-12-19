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

import { useUserStore } from '@/store/modules/user';

export function usePermission() {
  const userStore = useUserStore();

  /**
   * 检查权限
   * @param accesses
   */
  function _somePermissions(accesses: string[]) {
    return userStore.getPermissions.some((item) => {
      const { value }: any = item;
      return accesses.includes(value);
    });
  }

  /**
   * 判断是否存在权限
   * 可用于 v-if 显示逻辑
   * */
  function hasPermission(accesses: string[]): boolean {
    if (!accesses || !accesses.length) return true;
    return _somePermissions(accesses);
  }

  /**
   * 是否包含指定的所有权限
   * @param accesses
   */
  function hasEveryPermission(accesses: string[]): boolean {
    const permissionsList = userStore.getPermissions;
    if (Array.isArray(accesses)) {
      return permissionsList.every((access: any) => accesses.includes(access.value));
    }
    throw new Error(`[hasEveryPermission]: ${accesses} should be a array !`);
  }

  /**
   * 是否包含其中某个权限
   * @param accesses
   * @param accessMap
   */
  function hasSomePermission(accesses: string[]): boolean {
    const permissionsList = userStore.getPermissions;
    if (Array.isArray(accesses)) {
      return permissionsList.some((access: any) => accesses.includes(access.value));
    }
    throw new Error(`[hasSomePermission]: ${accesses} should be a array !`);
  }

  return { hasPermission, hasEveryPermission, hasSomePermission };
}
