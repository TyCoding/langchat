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

export function page(params: any) {
  return http.get({
    url: '/client/conversation/page',
    params,
  });
}

export function list(data: any) {
  return http.get({
    url: '/client/conversation/list',
    data,
  });
}

export function add(data: any) {
  return http.post({
    url: '/client/conversation',
    data,
  });
}

export function update(data: any) {
  return http.put({
    url: '/client/conversation',
    data,
  });
}

export function del(conversationId: string) {
  return http.delete({
    url: `/client/conversation/${conversationId}`,
  });
}

export function clearMessage(conversationId: string | undefined) {
  return http.delete({
    url: `/client/conversation/message/${conversationId}`,
  });
}

export function getMessages(conversationId: string) {
  return http.get({
    url: `/client/conversation/messages/${conversationId}`,
  });
}

export function addMessage(data: any) {
  return http.post({
    url: `/client/conversation/message`,
    data,
  });
}
