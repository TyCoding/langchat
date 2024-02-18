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
