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

import {http} from '../../utils/http/axios';

export function list(params: any) {
  return http.request({
    url: '/aigc/app/list',
    method: 'get',
    params,
  });
}

export function getAppApiChannel(appId: any) {
  return http.request({
    url: `/aigc/app/channel/api/${appId}`,
    method: 'get',
  });
}

export function page(params: any) {
  return http.request({
    url: '/aigc/app/page',
    method: 'get',
    params,
  });
}

export function getById(id: string) {
  return http.request({
    url: `/aigc/app/${id}`,
    method: 'get',
  });
}

export function getByModelId(id: string) {
  return http.request({
    url: `/aigc/app/byModelId/${id}`,
    method: 'get',
  });
}

export function add(params: any) {
  return http.request({
    url: '/aigc/app',
    method: 'post',
    params,
  });
}

export function update(params: any) {
  return http.request({
    url: '/aigc/app',
    method: 'put',
    params,
  });
}

export function del(id: string) {
  return http.request({
    url: `/aigc/app/${id}`,
    method: 'delete',
  });
}
