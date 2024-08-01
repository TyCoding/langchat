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
import { storage } from '@/utils/storage';
import { login, logout } from '@/api/auth';
import { TokenInfo, User } from '@/api/models/index';

const TokenStoreKey = 'tokenStore';
const UserStoreKey = 'userStore';

export interface UserState {
  user: User | null;
  token: string | null;
}

export const useUserStore = defineStore('user-store', {
  state: (): UserState => ({
    user: storage.get(UserStoreKey),
    token: storage.get(TokenStoreKey),
  }),
  actions: {
    setUser(user: Partial<User>) {
      this.user = user;
      storage.set(UserStoreKey, user);
    },
    resetUser() {
      this.user = null;
      storage.set(UserStoreKey, null);
    },

    setToken(token: string) {
      this.token = token;
      storage.set(TokenStoreKey, token);
    },
    resetToken() {
      this.token = null;
      storage.set(TokenStoreKey, null);
    },

    // 登录
    async login(params: User): Promise<TokenInfo> {
      const response = await login(params);
      if (response.token == undefined || response.user == undefined) {
        throw Error('Error');
      }

      this.setToken(response.token);
      this.setUser(response.user);
      return response;
    },

    // 登出
    async logout() {
      await logout();
      this.resetToken();
      this.resetUser();
    },
  },
});
