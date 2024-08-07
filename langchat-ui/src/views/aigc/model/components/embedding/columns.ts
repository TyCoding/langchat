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

import { FormSchema } from '@/components/Form';
import { ProviderEnum } from '@/views/aigc/model/components/chat/data';
import { ModelTypeEnum } from '@/api/models';
import { isNullOrWhitespace } from '@/utils/is';

export const LLMProviders: any[] = [
  {
    model: ProviderEnum.OPENAI,
    name: 'OpenAI',
    models: ['text-embedding-3-small', 'text-embedding-3-large', 'text-embedding-ada-002'],
  },
  {
    model: ProviderEnum.AZURE_OPENAI,
    name: 'Azure OpenAI',
    models: [
      'text-embedding-3-small',
      'text-embedding-3-small-1',
      'text-embedding-3-large',
      'text-embedding-3-large-1',
      'text-embedding-ada-002',
      'text-embedding-ada-002-1',
      'text-embedding-ada-002-2',
    ],
  },
  {
    model: ProviderEnum.OLLAMA,
    name: 'Ollama',
    models: [],
  },
  {
    model: ProviderEnum.Q_FAN,
    name: '百度千帆大模型',
    models: ['Embedding-V1', 'bge-large-zh', 'bge-large-en', 'tao-8k'],
  },
  {
    model: ProviderEnum.Q_WEN,
    name: '阿里千问大模型',
    models: ['text-embedding-v1', 'text-embedding-v2'],
  },
  {
    model: ProviderEnum.ZHIPU,
    name: '智普AI',
    models: ['embedding-2', 'text_embedding'],
  },
];

export const baseSchemas: FormSchema[] = [
  {
    field: 'id',
    label: 'ID',
    component: 'NInput',
    isHidden: true,
  },
  {
    field: 'type',
    label: 'type',
    component: 'NInput',
    isHidden: true,
    defaultValue: ModelTypeEnum.EMBEDDING,
  },
  {
    field: 'provider',
    label: 'LLM供应商',
    component: 'NSelect',
    slot: 'providerSlot',
    componentProps: {
      options: LLMProviders,
      labelField: 'name',
      valueField: 'model',
    },
    rules: [{ required: true, message: '请选择LLM供应商', trigger: ['blur'] }],
  },
  {
    field: 'name',
    label: '模型别名',
    component: 'NInput',
    rules: [{ required: true, message: '请输入模型别名', trigger: ['blur'] }],
  },
  {
    field: 'apiKey',
    label: 'Api Key',
    labelMessage: '模型链接的秘钥，注意有些模型例如Gemini是本地认证方式，则不是通过这种方式',
    component: 'NInput',
    rules: [{ required: true, message: '请输入API Key', trigger: ['blur'] }],
  },
  {
    field: 'model',
    label: '模型',
    labelMessage: '该LLM供应商对应的模型版本号',
    component: 'NSelect',
    rules: [{ required: true, message: '请选择模型', trigger: ['blur'] }],
    componentProps: {
      filterable: true,
      tag: true,
      options: [],
    },
  },
  {
    field: 'baseUrl',
    label: 'Base Url',
    labelMessage: '注意对于大多数模型此参数仅代表中转地址，但是对于Ollama这类本地模型则是必填的',
    component: 'NInput',
    rules: [
      {
        required: false,
        trigger: ['blur'],
        validator: (_, value: string) => {
          const urlRegex = /^(https?:\/\/)?([a-zA-Z0-9-]+\.)+[a-zA-Z]{2,}(\/.*)?$/;
          if (isNullOrWhitespace(value) || urlRegex.test(value)) {
            return true;
          }
          return new Error('URL格式错误');
        },
      },
    ],
  },
  {
    field: 'dimensions',
    label: 'Dimensions',
    labelMessage: '此参数代表向量纬数，注意此参数需要和向量数据库匹配',
    component: 'NSelect',
    rules: [{ type: 'number', required: true, message: '请选择向量维数', trigger: ['blur'] }],
    componentProps: {
      tag: true,
      filterable: true,
      options: [
        {
          label: '384',
          value: 384,
        },
        {
          label: '1536',
          value: 1536,
        },
        {
          label: '3072',
          value: 3072,
        },
      ],
    },
  },
];

export const openaiSchemas: FormSchema[] = [...baseSchemas];
export const azureOpenaiSchemas: FormSchema[] = [...baseSchemas];
export const ollamaSchemas: FormSchema[] = [...baseSchemas];
export const qfanSchemas: FormSchema[] = [
  ...baseSchemas,
  {
    field: 'secretKey',
    label: 'Secret Key',
    labelMessage: '对于某些模型需要此配置',
    isHidden: false,
    component: 'NInput',
  },
];
export const qwenSchemas: FormSchema[] = [...baseSchemas];
export const zhipuSchemas: FormSchema[] = [...baseSchemas];

export function getSchemas(provider: string) {
  switch (provider) {
    case ProviderEnum.OPENAI: {
      return openaiSchemas;
    }
    case ProviderEnum.AZURE_OPENAI: {
      return azureOpenaiSchemas;
    }
    case ProviderEnum.OLLAMA: {
      return ollamaSchemas;
    }
    case ProviderEnum.Q_FAN: {
      return qfanSchemas;
    }
    case ProviderEnum.Q_WEN: {
      return qwenSchemas;
    }
    case ProviderEnum.ZHIPU: {
      return zhipuSchemas;
    }
  }
  return baseSchemas;
}
