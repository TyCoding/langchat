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

export type User = {
  /** 主键 */
  id?: string;
  /** 用户名 */
  username?: string;
  /** 密码 */
  password?: string;
  /** 真实姓名 */
  realName?: string;
  /** 性别 */
  sex?: string;
  /** 邮箱 */
  email?: string;
  /** 头像 */
  avatar?: string;
  /** 手机 */
  phone?: string;
  /** 状态 0锁定 1有效 */
  status?: boolean;
  /** 创建时间 */
  createTime?: string;
};

export const modelList = [
  {
    label: 'OpenAI',
    key: 'OpenAI',
    type: 'group',
    children: [
      {
        label: 'GPT-4o',
        value: 'gpt-4o',
      },
      {
        label: 'GPT-4 Turbo',
        value: 'gpt-4-turbo',
      },
      {
        label: 'GPT-4',
        value: 'gpt-4',
      },
      {
        label: 'GPT-3.5 Turbo',
        value: 'gpt-3.5-turbo',
      },
    ],
  },
  {
    label: 'Google Gemini',
    value: 'gemini',
    type: 'group',
    children: [
      {
        label: 'Gemini 1.5 Flash',
        value: 'gemini-1.5-flash',
      },
      {
        label: 'Gemini 1.5 Pro',
        value: 'gemini-1.5-pro',
      },
    ],
  },
  {
    label: 'Ollama',
    value: 'ollama',
    type: 'group',
    children: [],
  },
];
