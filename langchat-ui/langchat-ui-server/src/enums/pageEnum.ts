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

export enum PageEnum {
  // 登录
  BASE_LOGIN = '/login',
  BASE_LOGIN_NAME = 'Login',
  BASE_REGISTER = '/register',
  //重定向
  REDIRECT = '/redirect',
  REDIRECT_NAME = 'Redirect',
  // 首页
  BASE_HOME = '/dashboard',
  //首页跳转默认路由
  BASE_HOME_REDIRECT = '/dashboard',
  // 错误
  ERROR_PAGE_NAME = 'ErrorPage',
}
