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

export type TokenInfo = {
  token?: string;
  expires?: number;
  user?: User;
} & R;

export type User = {
  id?: string;
  username?: string;
  password?: string;
  name?: string;
  avatar?: string;
  status?: boolean;
  email?: string;
  phone?: string;
  createTime?: string;
};

export type R = {
  code: number;
  message: string;
  data: Object;
};

export type Bot = {
  id: number;
  name?: string;
  prompt?: string;
  tags?: number;
  des?: string;
  icon?: number;
  createTime?: string;
};

export interface ChatR {
  chatId?: string;
  parentChatId?: string;
  conversationId?: string;
  message?: string;
  promptId?: string;
  promptText?: string;
  role?: 'user' | 'assistant' | 'system';
  createTime?: string;
  type?: string;
  modelId?: string;
  modelName?: string;
  modelProvider?: string;
  language?: string;
  tone?: string;
  length?: string;
}
