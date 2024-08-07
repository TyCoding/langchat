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

export enum ProviderEnum {
  OPENAI = 'OPENAI',
  AZURE_OPENAI = 'AZURE_OPENAI',
  GEMINI = 'GEMINI',
  OLLAMA = 'OLLAMA',
  CLAUDE = 'CLAUDE',
  Q_FAN = 'Q_FAN',
  Q_WEN = 'Q_WEN',
  ZHIPU = 'ZHIPU',
}

export const LLMProviders: any[] = [
  {
    model: ProviderEnum.OPENAI,
    name: 'OpenAI',
    models: [
      'gpt-3.5-turbo',
      'gpt-3.5-turbo-0613',
      'gpt-3.5-turbo-1106',
      'gpt-3.5-turbo-0125',
      'gpt-3.5-turbo-16k',
      'gpt-3.5-turbo-16k-0613',
      'gpt-4',
      'gpt-4-0314',
      'gpt-4-0613',
      'gpt-4-turbo-preview',
      'gpt-4-1106-preview',
      'gpt-4-0125-preview',
      'gpt-4-32k',
      'gpt-4-32k-0314',
      'gpt-4-32k-0613',
      'gpt-4-vision-preview',
      'gpt-4o',
    ],
  },
  {
    model: ProviderEnum.AZURE_OPENAI,
    name: 'Azure OpenAI',
    models: [
      'gpt-3.5-turbo',
      'gpt-3.5-turbo-0613',
      'gpt-3.5-turbo-0125',
      'gpt-3.5-turbo-1106',
      'gpt-3.5-turbo-16k',
      'gpt-3.5-turbo-16k-0613',
      'gpt-4',
      'gpt-4-1106-preview',
      'gpt-4-0613',
      'gpt-4-32k',
      'gpt-4-32k-0613',
      'gpt-4o',
      'text-davinci-002',
      'gpt-3.5-turbo-instruct',
    ],
  },
  {
    model: ProviderEnum.GEMINI,
    name: 'Gemini',
  },
  {
    model: ProviderEnum.OLLAMA,
    name: 'Ollama',
  },
  {
    model: ProviderEnum.CLAUDE,
    name: 'Claude',
    models: [
      'claude-3-opus-20240229',
      'claude-3-sonnet-20240229',
      'claude-3-haiku-20240307',
      'claude-2.1',
      'claude-2.0',
      'claude-instant-1.2',
    ],
  },
  {
    model: ProviderEnum.Q_FAN,
    name: '百度千帆大模型',
    models: [
      'ERNIE-Bot',
      'ERNIE-Bot 4.0',
      'ERNIE-Bot-8K',
      'ERNIE-Bot-turbo',
      'ERNIE-Speed-128K',
      'EB-turbo-AppBuilder',
      'Yi-34B-Chat',
      'BLOOMZ-7B',
      'Qianfan-BLOOMZ-7B-compressed',
      'Mixtral-8x7B-Instruct',
      'Llama-2-7b-chat',
      'Llama-2-13b-chat',
      'Llama-2-70b-chat',
      'Qianfan-Chinese-Llama-2-7B',
      'ChatGLM2-6B-32K',
      'AquilaChat-7B',
    ],
  },
  {
    model: ProviderEnum.Q_WEN,
    name: '阿里千问大模型',
    models: [
      'qwen-turbo',
      'qwen-plus',
      'qwen-max',
      'qwen-max-longcontext',
      'qwen-7b-chat',
      'qwen-14b-chat',
      'qwen-72b-chat',
      'qwen1.5-7b-chat',
      'qwen1.5-14b-chat',
      'qwen1.5-32b-chat',
      'qwen1.5-72b-chat',
      'qwen-vl-plus',
      'qwen-vl-max',
    ],
  },
  {
    model: ProviderEnum.ZHIPU,
    name: '智普AI',
    models: ['glm-4', 'glm-3-turbo', 'chatglm_turbo'],
  },
];

export function getTitle(provider: string) {
  return LLMProviders.filter((i) => i.model === provider)[0].name;
}
