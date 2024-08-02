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
import { LLMProviders, ProviderEnum } from './data';
import { ModelTypeEnum } from '@/api/models';
import { isNullOrWhitespace } from '@/utils/is';

const baseHeadSchemas: FormSchema[] = [
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
    defaultValue: ModelTypeEnum.CHAT,
  },
  {
    field: 'provider',
    label: 'LLM供应商',
    component: 'NSelect',
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
];
const baseSchemas: FormSchema[] = [
  {
    field: 'responseLimit',
    label: '回复上限',
    labelMessage: '控制模型输出的Tokens长度上限。通常 100 Tokens 约等于150个中文汉字',
    component: 'NSlider',
    rules: [{ type: 'number', required: true, message: '请输入回复上限', trigger: ['blur'] }],
    componentProps: {
      showTooltip: true,
      defaultValue: 2000,
      step: 1,
      min: 1,
      max: 8192,
    },
  },
  {
    field: 'temperature',
    label: '生成随机性',
    labelMessage: '调高参数会使得模型的输出更多样性和创新性，反之降低参数将会减少多样性',
    component: 'NSlider',
    rules: [{ type: 'number', required: true, message: '请输入生成随机性', trigger: ['blur'] }],
    componentProps: {
      showTooltip: true,
      defaultValue: 0.8,
      step: 0.05,
      min: 0,
      max: 2,
    },
  },
  {
    field: 'topP',
    label: 'Top P',
    labelMessage:
      '模型在生成输出时会从概率最高的词汇开始选择，直到这些词汇的总概率累积达到Top p值。这样可以限制模型只选择这些高概率的词汇，从而控制输出内容的多样性。建议不要与“生成随机性“同时调整',
    component: 'NSlider',
    rules: [{ type: 'number', required: true, message: '请输入', trigger: ['blur'] }],
    componentProps: {
      showTooltip: true,
      defaultValue: 1,
      step: 0.1,
      min: 0,
      max: 1,
    },
  },
];
const keySchemas: FormSchema[] = [
  {
    field: 'apiKey',
    label: 'Api Key',
    labelMessage: '模型链接的秘钥，注意有些模型例如Gemini是本地认证方式，则不是通过这种方式',
    component: 'NInput',
    rules: [{ required: true, message: '请输入API Key', trigger: ['blur'] }],
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
];

export const openaiSchemas: FormSchema[] = [
  ...baseHeadSchemas,
  {
    field: 'model',
    label: '模型',
    labelMessage: '该LLM供应商对应的模型版本号',
    component: 'NSelect',
    rules: [{ required: true, message: '请选择模型', trigger: ['blur'] }],
    componentProps: {
      filterable: true,
      options: getModels(ProviderEnum.OPENAI),
    },
  },
  ...keySchemas,
  ...baseSchemas,
];

export const azureOpenaiSchemas: FormSchema[] = [
  ...baseHeadSchemas,
  {
    field: 'model',
    label: '模型',
    labelMessage: '该LLM供应商对应的模型版本号',
    component: 'NSelect',
    rules: [{ required: true, message: '请选择模型', trigger: ['blur'] }],
    componentProps: {
      filterable: true,
      options: getModels(ProviderEnum.AZURE_OPENAI),
    },
  },
  {
    field: 'endpoint',
    label: 'Endpoint',
    labelMessage: 'Endpoint',
    component: 'NInput',
    rules: [{ required: true, message: '请输入Endpoint', trigger: ['blur'] }],
  },
  {
    field: 'azureDeploymentName',
    label: 'Deployment Name',
    labelMessage: 'Deployment Name',
    component: 'NInput',
    rules: [{ required: true, message: '请输入Deployment Name', trigger: ['blur'] }],
  },
  ...keySchemas,
  ...baseSchemas,
];

export const geminiSchemas: FormSchema[] = [
  ...baseHeadSchemas,
  {
    field: 'geminiProject',
    label: 'Project',
    labelMessage: '对于Gemini模型，此参数代表模型的项目ID',
    component: 'NInput',
    rules: [{ required: true, message: '请输入Project ID', trigger: ['blur'] }],
  },
  {
    field: 'geminiLocation',
    label: 'Location',
    labelMessage: '对于Gemini模型，瓷参数代表模型的区域',
    component: 'NInput',
    rules: [{ required: true, message: '请输入Location', trigger: ['blur'] }],
  },
  ...baseSchemas,
];

export const ollamaSchemas: FormSchema[] = [
  ...baseHeadSchemas,
  {
    field: 'model',
    label: '模型',
    labelMessage: '该LLM供应商对应的模型版本号',
    component: 'NInput',
    rules: [{ required: true, message: '请选择模型', trigger: ['blur'] }],
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
  ...baseSchemas,
];

export const claudeSchemas: FormSchema[] = [
  ...baseHeadSchemas,
  {
    field: 'model',
    label: '模型',
    labelMessage: '该LLM供应商对应的模型版本号',
    component: 'NSelect',
    rules: [{ required: true, message: '请选择模型', trigger: ['blur'] }],
    componentProps: {
      filterable: true,
      options: getModels(ProviderEnum.CLAUDE),
    },
  },
  ...keySchemas,
  ...baseSchemas,
];

export const qfanSchemas: FormSchema[] = [
  ...baseHeadSchemas,
  {
    field: 'model',
    label: '模型',
    labelMessage: '该LLM供应商对应的模型版本号',
    component: 'NSelect',
    rules: [{ required: true, message: '请选择模型', trigger: ['blur'] }],
    componentProps: {
      filterable: true,
      options: getModels(ProviderEnum.Q_FAN),
    },
  },
  {
    field: 'secretKey',
    label: 'Secret Key',
    labelMessage: '百度大模型认证需要的秘钥',
    component: 'NInput',
    rules: [{ required: true, message: '请输入秘钥', trigger: ['blur'] }],
  },
  ...keySchemas,
  ...baseSchemas,
];

export const qwenSchemas: FormSchema[] = [
  ...baseHeadSchemas,
  {
    field: 'model',
    label: '模型',
    labelMessage: '该LLM供应商对应的模型版本号',
    component: 'NSelect',
    rules: [{ required: true, message: '请选择模型', trigger: ['blur'] }],
    componentProps: {
      filterable: true,
      options: getModels(ProviderEnum.Q_WEN),
    },
  },
  ...keySchemas,
  ...baseSchemas,
];

export const zhipuSchemas: FormSchema[] = [
  ...baseHeadSchemas,
  {
    field: 'model',
    label: '模型',
    labelMessage: '该LLM供应商对应的模型版本号',
    component: 'NSelect',
    rules: [{ required: true, message: '请选择模型', trigger: ['blur'] }],
    componentProps: {
      filterable: true,
      options: getModels(ProviderEnum.ZHIPU),
    },
  },
  ...keySchemas,
  ...baseSchemas,
];

export function getSchemas(provider: string) {
  switch (provider) {
    case ProviderEnum.OPENAI: {
      return openaiSchemas;
    }
    case ProviderEnum.AZURE_OPENAI: {
      return azureOpenaiSchemas;
    }
    case ProviderEnum.GEMINI: {
      return geminiSchemas;
    }
    case ProviderEnum.OLLAMA: {
      return ollamaSchemas;
    }
    case ProviderEnum.CLAUDE: {
      return claudeSchemas;
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
  return [];
}

export function getModels(provider: string) {
  const arr = LLMProviders.filter((i) => i.model === provider);
  if (arr.length === 0) {
    return [];
  }
  return arr[0].models.map((i) => {
    return {
      label: i,
      value: i,
    };
  });
}
