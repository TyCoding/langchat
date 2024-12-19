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

import { http } from '@/utils/http/axios';

/**
 * @description: 获取用户信息
 */
export function getUserInfo() {
  return http.request({
    url: '/auth/info',
    method: 'get',
  });
}

/**
 * Token获取
 * @description: 用户登录
 */
export function login(data: any) {
  return http.request({
    url: `/auth/login`,
    method: 'POST',
    data,
  });
}

export function register(data: any) {
  return http.request({
    url: `/auth/register`,
    method: 'POST',
    data,
  });
}

/**
 * @description: 用户登出
 */
export function logout() {
  return http.request({
    url: '/auth/logout',
    method: 'delete',
  });
}

/**
 * @description: 动态加载菜单
 * 需要在/settings/projectSettings.ts中配置permissionMode=BACK
 * 本项目不考虑从后段获取菜单
 */
export function getMenus() {
  return http.request({
    url: '/upms/menu/build',
    method: 'get',
  });
}

/**
 * 分页获取Token
 */
export function getTokens(params: any) {
  return http.request({
    url: '/auth/token/page',
    method: 'get',
    params,
  });
}

/**
 * 删除Token
 */
export function delToken(id?: string) {
  return http.request({
    url: `/auth/token/${id}`,
    method: 'delete',
  });
}
