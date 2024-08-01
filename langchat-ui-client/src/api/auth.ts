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

import { http } from '@/utils/request';
import { TokenInfo, User } from '@/api/models';

/**
 * @description: 获取邮箱验证码
 */
export function getEmailCode(email: string) {
  return http.get({
    url: `/client/auth/email/code?email=${email}`,
  });
}

/**
 * @description: 邮箱注册
 */
export function emailRegister(data: any) {
  return http.post({
    url: `/client/auth/email/register`,
    data,
  });
}

export function login(data: any): Promise<TokenInfo> {
  return http.post({
    url: `/client/auth/login`,
    data,
  });
}

export function info(): Promise<User> {
  return http.get({
    url: `/client/auth/info`,
  });
}

export function forget(data: any) {
  return http.post({
    url: `/client/auth/forget`,
    method: 'POST',
    data,
  });
}

export function logout() {
  return http.delete({
    url: '/client/auth/logout',
  });
}
