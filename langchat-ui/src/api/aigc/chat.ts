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
import { AxiosProgressEvent } from 'axios';

export function chat(data: any, onDownloadProgress?: (progressEvent: AxiosProgressEvent) => void) {
  return http.request(
    {
      method: 'post',
      url: '/aigc/chat/completions',
      data,
      onDownloadProgress: onDownloadProgress,
    },
    {
      isReturnNativeResponse: true,
    }
  );
}

export function clean(conversationId: string | null) {
  return http.request({
    url: `/aigc/chat/messages/clean/${conversationId}`,
    method: 'delete',
  });
}

export function getMessages(conversationId?: string) {
  return http.request({
    url: `/aigc/chat/messages/${conversationId}`,
    method: 'get',
  });
}

export function getAppInfo(params: any) {
  return http.request({
    url: `/aigc/app/info`,
    method: 'get',
    params,
  });
}
