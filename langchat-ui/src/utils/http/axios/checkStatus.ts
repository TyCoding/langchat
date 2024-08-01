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

export function checkStatus(status: number, msg: string | undefined): void {
  if (msg === '') {
    msg = undefined;
  }
  console.log('进入', status, msg);
  const $message = window['$message'];
  switch (status) {
    case 400:
      $message.error(msg);
      break;
    // 401: 未登录
    // 未登录则跳转登录页面，并携带当前页面的路径
    // 在登录成功后返回当前页面，这一步需要在登录页操作。
    case 401:
      $message.error(msg ?? '用户没有权限（令牌、用户名、密码错误）!');
      break;
    case 403:
      $message.error(msg ?? '用户得到授权，但是访问是被禁止的。!');
      break;
    // 404请求不存在
    case 404:
      $message.error(msg ?? '网络请求错误，未找到该资源!');
      break;
    case 405:
      $message.error(msg ?? '网络请求错误，请求方法未允许!');
      break;
    case 408:
      $message.error(msg ?? '网络请求超时');
      break;
    case 500:
      $message.error(msg ?? '服务器错误,请联系管理员!');
      break;
    case 501:
      $message.error(msg ?? '网络未实现');
      break;
    case 502:
      $message.error(msg ?? '网络错误');
      break;
    case 503:
      $message.error(msg ?? '服务不可用，服务器暂时过载或维护!');
      break;
    case 504:
      $message.error(msg ?? '网络超时');
      break;
    case 505:
      $message.error(msg ?? 'http版本不支持该请求!');
      break;
    default:
      $message.error(msg);
  }
}
