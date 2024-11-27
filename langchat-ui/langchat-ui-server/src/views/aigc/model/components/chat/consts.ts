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
    models: ['gpt-3.5-turbo', 'gpt-4', 'gpt-4-32k', 'gpt-4-turbo', 'gpt-4o'],
  },
  {
    model: ProviderEnum.Q_FAN,
    name: '百度千帆',
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
    name: '阿里百炼',
    models: [
      'qwen-turbo',
      'qwen-plus',
      'qwen-max',
      'qwen-max-longcontext',
      'qwen-7b-chat',
      'qwen-14b-chat',
      'qwen-72b-chat',
    ],
  },
  {
    model: ProviderEnum.ZHIPU,
    name: '智谱清言',
    models: [
      'glm-4',
      'glm-4v',
      'glm-4-air',
      'glm-4-airx',
      'glm-4-flash',
      'glm-3-turbo',
      'chatglm_turbo',
    ],
  },
  {
    model: ProviderEnum.GITEEAI,
    name: 'Gitee AI',
    models: [
      'Qwen2-72B-Instruct',
      'Qwen2-7B-Instruct',
      'Qwen2.5-72B-Instruct',
      'glm-4-9b-chat',
      'deepseek-coder-33B-instruct',
      'codegeex4-all-9b',
      'Yi-34B-Chat',
      'code-raccoon-v1',
      'Qwen2.5-Coder-32B-Instruct',
    ],
  },
  {
    model: ProviderEnum.DEEPSEEK,
    name: 'DeepSeek',
    models: ['deepseek-chat', 'deepseek-coder'],
  },
  {
    model: ProviderEnum.DOUYIN,
    name: '抖音豆包',
    models: [],
  },
  {
    model: ProviderEnum.SILICON,
    name: '硅基流动',
    models: [
      'deepseek-ai/DeepSeek-V2-Chat',
      'deepseek-ai/DeepSeek-Coder-V2-Instruct',
      'deepseek-ai/DeepSeek-V2.5',
      'Qwen/Qwen2.5-72B-Instruct-128K',
      'Qwen/Qwen2.5-72B-Instruct',
      'Qwen/Qwen2-VL-72B-Instruct',
      'Qwen/Qwen2.5-32B-Instruct',
      'Qwen/Qwen2.5-14B-Instruct',
      'Qwen/Qwen2.5-7B-Instruct',
      'Qwen/Qwen2.5-Math-72B-Instruct',
      'Qwen/Qwen2.5-Coder-7B-Instruct',
      'Qwen/Qwen2-72B-Instruct',
      'Qwen/Qwen2-7B-Instruct',
      'Qwen/Qwen2-1.5B-Instruct',
      'Qwen/Qwen2-57B-A14B-Instruct',
      'TeleAI/TeleChat2',
      '01-ai/Yi-1.5-34B-Chat-16K',
      '01-ai/Yi-1.5-9B-Chat-16K',
      '01-ai/Yi-1.5-6B-Chat',
      'THUDM/chatglm3-6b',
      'THUDM/glm-4-9b-chat',
      'Vendor-A/Qwen/Qwen2-72B-Instruct',
      'Vendor-A/Qwen/Qwen2.5-72B-Instruct',
      'internlm/internlm2_5-7b-chat',
      'internlm/internlm2_5-20b-chat',
      'OpenGVLab/InternVL2-Llama3-76B',
      'OpenGVLab/InternVL2-26B',
      'nvidia/Llama-3.1-Nemotron-70B-Instruct',
      'meta-llama/Meta-Llama-3.1-405B-Instruct',
      'meta-llama/Meta-Llama-3.1-70B-Instruct',
      'meta-llama/Meta-Llama-3.1-8B-Instruct',
      'meta-llama/Meta-Llama-3-8B-Instruct',
      'meta-llama/Meta-Llama-3-70B-Instruct',
      'google/gemma-2-27b-it',
      'google/gemma-2-9b-it',
      'Pro/Qwen/Qwen2.5-7B-Instruct',
      'Pro/Qwen/Qwen2-7B-Instruct',
      'Pro/Qwen/Qwen2-1.5B-Instruct',
      'Pro/Qwen/Qwen2-VL-7B-Instruct',
      'Pro/01-ai/Yi-1.5-9B-Chat-16K',
      'Pro/01-ai/Yi-1.5-6B-Chat',
      'Pro/THUDM/chatglm3-6b',
      'Pro/THUDM/glm-4-9b-chat',
      'Pro/internlm/internlm2_5-7b-chat',
      'Pro/OpenGVLab/InternVL2-8B',
      'Pro/meta-llama/Meta-Llama-3-8B-Instruct',
      'Pro/meta-llama/Meta-Llama-3.1-8B-Instruct',
      'Pro/google/gemma-2-9b-it',
    ],
  },
  {
    model: ProviderEnum.YI,
    name: '零一万物',
    models: [
      'yi-lightning',
      'yi-large',
      'yi-medium',
      'yi-medium-200k',
      'yi-spark',
      'yi-large-rag',
      'yi-large-turbo',
    ],
  },
  {
    model: ProviderEnum.SPARK,
    name: '讯飞星火',
    // models: ['lite', 'generalv3', 'pro-128k', 'generalv3.5', 'max-32k', '4.0Ultra'],
    models: [
      { label: 'Spark Lite', value: 'lite' },
      { label: 'Spark Pro', value: 'generalv3' },
      { label: 'Spark Pro-128K', value: 'pro-128k' },
      { label: 'Spark Max', value: 'generalv3.5' },
      { label: 'Spark Max-32K', value: 'max-32k' },
      { label: 'Spark4.0 Ultra', value: '4.0Ultra' },
    ],
  },
  {
    model: ProviderEnum.OLLAMA,
    name: 'Ollama',
    models: [],
  },
  {
    model: ProviderEnum.AZURE_OPENAI,
    name: 'Azure OpenAI',
    models: ['gpt-3.5-turbo', 'gpt-4', 'gpt-4-32k', 'gpt-4-turbo', 'gpt-4o'],
  },
  {
    model: ProviderEnum.GEMINI,
    name: 'Gemini',
    models: ['gemini-1.5-pro'],
  },
  {
    model: ProviderEnum.CLAUDE,
    name: 'Claude',
    models: ['claude-3-opus', 'claude-3-opus-20240229', 'claude-3-sonnet', 'claude-3-haiku'],
  },
];
