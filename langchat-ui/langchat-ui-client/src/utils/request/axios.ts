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

import axios, { type AxiosResponse } from 'axios';
import { useUserStore } from '@/store';

const service = axios.create({
  baseURL: import.meta.env.VITE_GLOB_API_URL,
});

service.interceptors.request.use(
  (config) => {
    const token = useUserStore().token;
    if (token) config.headers.Authorization = `${token}`;
    return config;
  },
  (error) => {
    return Promise.reject(error.response);
  }
);

service.interceptors.response.use(
  (response: AxiosResponse): AxiosResponse => {
    if (response.status === 200) return response;

    throw new Error(response.status.toString());
  },
  (error) => {
    return Promise.reject(error);
  }
);

export default service;
