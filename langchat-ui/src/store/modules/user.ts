import { defineStore } from 'pinia';
import { store } from '@/store';
import { ACCESS_TOKEN, CURRENT_USER, IS_SCREENLOCKED } from '@/store/mutation-types';

import { getUserInfo, login, logout } from '@/api/auth';
import { storage } from '@/utils/Storage';

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
      this.setUserInfo({
        id: '',
        username: '',
        realName: '',
        avatar: '',
      });
      storage.remove(ACCESS_TOKEN);
      storage.remove(CURRENT_USER);
    },
  },
});

// Need to be used outside the setup
export function useUser() {
  return useUserStore(store);
}
