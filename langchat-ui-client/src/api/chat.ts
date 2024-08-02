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
import { ChatR } from '@/api/models';
import { AxiosProgressEvent } from 'axios';

/**
 * @description: 聊天
 */
export function chat(
  data: ChatR,
  onDownloadProgress?: (progressEvent: AxiosProgressEvent) => void
) {
  return http.post({
    url: '/client/chat',
    data,
    onDownloadProgress: onDownloadProgress,
  });
}

/**
 * @description: Doc聊天
 */
export function docsChat(
  knowledgeId: string,
  data: any,
  onDownloadProgress?: (progressEvent: AxiosProgressEvent) => void
) {
  return http.post({
    url: `/client/docs/${knowledgeId}`,
    data,
    onDownloadProgress: onDownloadProgress,
  });
}

export function getPrompts(data: any) {
  return http.get({
    url: '/client/prompt/list',
    data: data,
  });
}

export function getChatModels() {
  return http.get({
    url: '/client/getChatModels',
  });
}

export function getImageModels() {
  return http.get({
    url: '/client/getImageModels',
  });
}

export function genWrite(
  data: any,
  onDownloadProgress?: (progressEvent: AxiosProgressEvent) => void
) {
  return http.post({
    url: `/client/chat/write`,
    data: data,
    onDownloadProgress: onDownloadProgress,
  });
}

/**
 * @description 生成图片
 */
export function genImage(data: any) {
  return http.post({
    url: '/client/chat/image',
    data: data,
  });
}

/**
 * @description: 生成思维导图
 */
export function genMindMap(data: ChatR) {
  return http.post({
    url: '/client/chat/mindmap',
    data: data,
  });
}
