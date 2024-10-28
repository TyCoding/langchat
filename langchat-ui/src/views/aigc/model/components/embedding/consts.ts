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

import { ProviderEnum } from '@/views/aigc/model/provider';

export const LLMProviders: any[] = [
  {
    model: ProviderEnum.OPENAI,
    name: 'OpenAI',
    models: ['text-embedding-3-small', 'text-embedding-3-large', 'text-embedding-ada-002'],
  },
  {
    model: ProviderEnum.Q_FAN,
    name: '百度千帆',
    models: ['bge-large-zh', 'bge-large-en', 'tao-8k'],
  },
  {
    model: ProviderEnum.Q_WEN,
    name: '阿里百炼',
    models: ['text-embedding-v3'],
  },
  {
    model: ProviderEnum.ZHIPU,
    name: '智谱清言',
    models: ['embedding-2', 'embedding-3'],
  },
  {
    model: ProviderEnum.DOUYIN,
    name: '抖音豆包',
    models: ['text-240715', 'text-240515'],
  },
];
