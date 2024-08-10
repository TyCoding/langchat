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

import type { AxiosProgressEvent, AxiosResponse, GenericAbortSignal } from 'axios';
import request from './axios';
import { useUserStore } from '@/store';
import { isNullOrWhitespace } from '@/utils/is';

export interface HttpOption {
  url: string;
  data?: any;
  params?: any;
  method?: string;
  headers?: any;
  onDownloadProgress?: (progressEvent: AxiosProgressEvent) => void;
  onUploadProgress?: (progressEvent: AxiosProgressEvent) => void;
  signal?: GenericAbortSignal;
  beforeRequest?: () => void;
  afterRequest?: () => void;
  isTransformResponse?: boolean;
}

export interface Response<T = any> {
  result: T;
  message: string | null;
  code: number;
}

function axios<T = any>({
  url,
  data,
  method,
  headers,
  onDownloadProgress,
  signal,
  beforeRequest,
  afterRequest,
  isTransformResponse,
}: HttpOption): any {
  const successHandler = (res: AxiosResponse<Response<T>>) => {
    const $message = window['$message'];
    if (res.status !== 200) {
      const message = res.data.message ?? res.statusText;
      $message!.error(message);
      return Promise.reject(res.data);
    }

    if (isTransformResponse) {
      return res.data;
    }

    if (res.data.code === 401) {
      const message = res.data.message ?? '没有操作权限';
      $message?.destroyAll();
      $message!.error(message);
      return Promise.reject(res.data);
    }

    if (res.data.code === 200) return res.data.result;

    return Promise.reject(res.data);
  };

  const failHandler = async (error: any) => {
    console.error(error);

    const $message = window['$message']!;
    const { status, data } = error.response;
    const userStore = useUserStore();

    if (data.code === 403) {
      $message!.error('未登录或没有操作权限');
      if (userStore.token !== null) {
        await userStore.logout();
      }
      return;
    }

    if (status === 401) {
      if (isNullOrWhitespace(userStore.token)) {
        $message!.error('未登录或没有操作权限');
        throw new Error('unthorization');
      } else {
        $message!.warning('当前账号没有操作权限，请联系管理员授权');
      }
      return;
    }

    if (error.response !== undefined && error.response.data != undefined) {
      $message!.error(error.response.data.message ?? '接口请求异常');
    }

    afterRequest?.();
    throw new Error(error?.message || 'Error');
  };

  beforeRequest?.();

  method = method || 'GET';

  const params = Object.assign(typeof data === 'function' ? data() : data ?? {}, {});

  switch (method) {
    case 'get':
      return request
        .get(url, { params, signal, onDownloadProgress })
        .then(successHandler, failHandler);
    case 'post':
      return request
        .post(url, params, { headers, signal, onDownloadProgress })
        .then(successHandler, failHandler);
    case 'delete':
      return request
        .delete(url, { headers, signal, onDownloadProgress })
        .then(successHandler, failHandler);
    case 'put':
      return request
        .put(url, params, { headers, signal, onDownloadProgress })
        .then(successHandler, failHandler);
    default:
      break;
  }
}

export function get<T = any>(options: HttpOption): Promise<T | Response<T>> {
  options.method = 'get';
  return axios<T>(options);
}

export function post<T = any>(options: HttpOption): Promise<T | Response<T>> {
  options.method = 'post';
  return axios<T>(options);
}

export class http {
  constructor() {}
  static get(options: HttpOption) {
    options.method = 'get';
    return axios(options);
  }
  static post(options: HttpOption) {
    options.method = 'post';
    return axios(options);
  }
  static delete(options: HttpOption) {
    options.method = 'delete';
    return axios(options);
  }
  static put(options: HttpOption) {
    options.method = 'put';
    return axios(options);
  }
}
