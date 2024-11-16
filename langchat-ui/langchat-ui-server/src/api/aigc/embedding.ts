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

export function embeddingText(params: any) {
  return http.request({
    url: '/aigc/embedding/text',
    method: 'post',
    params,
  });
}

export function embeddingSearch(data: any) {
  return http.request({
    url: '/aigc/embedding/search',
    method: 'post',
    data,
  });
}

export function embeddingDocs(
  knowledgeId: string,
  data: any,
  onUploadProgress?: (progressEvent: AxiosProgressEvent) => void
) {
  return http.request({
    url: `/aigc/embedding/docs/${knowledgeId}`,
    method: 'post',
    data,
    headers: {
      'Content-Type': 'multipart/form-data',
    },
    onUploadProgress,
  });
}
