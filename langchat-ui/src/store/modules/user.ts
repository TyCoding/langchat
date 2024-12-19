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

import { defineStore } from 'pinia';
import { store } from '@/store';
import { ACCESS_TOKEN, CURRENT_USER, IS_SCREENLOCKED } from '@/store/mutation-types';

import { getUserInfo, login, logout, register } from '@/api/auth';
import { storage } from '@/utils/Storage';
import { useAsyncRouteStore } from '@/store/modules/asyncRoute';

export type UserInfoType = {
  id: string;
  username: string;
  realName: string;
  avatar: string;
};

export interface IUserState {
  token: string;
  username: string;
  avatar: string;
  permissions: any[];
  info: UserInfoType;
}

export const useUserStore = defineStore({
  id: 'app-user',
  state: (): IUserState => ({
    token: storage.get(ACCESS_TOKEN, ''),
    username: '',
    avatar: '',
    permissions: [],
    info: storage.get(CURRENT_USER, {}),
  }),
  getters: {
    getToken(): string {
      return this.token;
    },
    getAvatar(): string {
      return this.avatar;
    },
    getNickname(): string {
      return this.username;
    },
    getPermissions(): [any][] {
      return this.permissions;
    },
    getUserInfo(): UserInfoType {
      return this.info;
    },
  },
  actions: {
    setToken(token: string) {
      this.token = token;
    },
    setAvatar(avatar: string) {
      this.avatar = avatar;
    },
    setPermissions(permissions: any) {
      this.permissions = permissions;
    },
    setUserInfo(info: UserInfoType) {
      this.info = info;
    },
    // 登录
    async login(params: any) {
      const response = await login(params);
      if (response.token !== undefined) {
        const ex = 24 * 60 * 60;
        storage.set(ACCESS_TOKEN, response.token, ex);
        storage.set(IS_SCREENLOCKED, false);
        this.setToken(response.token);
      }
      return response;
    },
    // 注册
    async register(params: any) {
      return await register(params);
    },

    // 获取用户信息
    async getInfo() {
      try {
        const data = await getUserInfo();

        if (data.perms !== null && data.perms.length) {
          this.setPermissions(data.perms);
          this.setUserInfo(data);
        } else {
          this.setPermissions([]);
          // throw new Error('getInfo: permissionsList must be a non-null array !');
        }
        this.setUserInfo(data);
        this.setAvatar(data.avatar);
        return data;
      } catch (e) {
        console.error(e);
        await this.logout();
      }
    },

    // 登出
    async logout() {
      await logout();
      this.setPermissions([]);
      this.setToken('');
      this.setAvatar('');
      this.setUserInfo({
        id: '',
        username: '',
        realName: '',
        avatar: '',
      });
      storage.clear();

      const routerStore = useAsyncRouteStore();
      routerStore.clear();
    },
  },
});

// Need to be used outside the setup
export function useUser() {
  return useUserStore(store);
}
