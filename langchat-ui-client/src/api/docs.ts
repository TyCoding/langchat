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
import { AxiosProgressEvent } from 'axios';

/**
 * @description: 上传文件
 */
export function upload(data: any, onUploadProgress?: (progressEvent: AxiosProgressEvent) => void) {
  return http.post({
    url: `/client/docs/upload`,
    data,
    headers: {
      'Content-Type': 'multipart/form-data',
    },
    onUploadProgress,
  });
}

export function list() {
  return http.get({
    url: '/client/oss/list',
  });
}

export function update(data: any) {
  return http.put({
    url: '/client/oss',
    data: data,
  });
}

export function del(id?: number) {
  return http.delete({
    url: `/client/docs/${id}`,
  });
}

export function task() {
  return http.get({
    url: `/client/task`,
  });
}
