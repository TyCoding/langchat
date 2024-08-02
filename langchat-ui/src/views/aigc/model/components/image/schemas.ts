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
    defaultValue: ModelTypeEnum.TEXT_IMAGE,
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
  {
    field: 'imageSize',
    label: '图片大小',
    labelMessage: '生成图片的大小尺寸',
    component: 'NSelect',
    rules: [{ required: true, message: '请选择图片大小', trigger: ['blur'] }],
    componentProps: {
      options: [
        {
          label: '1024x1024',
          value: '1024x1024',
        },
        {
          label: '1024x1792',
          value: '1024x1792',
        },
        {
          label: '1792x1024',
          value: '1792x1024',
        },
      ],
    },
  },
  {
    field: 'imageQuality',
    label: '图片质量',
    labelMessage: '生成图片的质量',
    component: 'NSelect',
    rules: [{ required: true, message: '请选择图片的质量', trigger: ['blur'] }],
    componentProps: {
      options: [
        {
          label: 'standard',
          value: 'standard',
        },
        {
          label: 'hd',
          value: 'hd',
        },
      ],
    },
  },
  {
    field: 'imageStyle',
    label: '图片风格',
    labelMessage: '生成图片的风格',
    component: 'NSelect',
    rules: [{ required: true, message: '请选择图片的风格', trigger: ['blur'] }],
    componentProps: {
      options: [
        {
          label: 'vivid',
          value: 'vivid',
        },
        {
          label: 'natural',
          value: 'natural',
        },
      ],
    },
  },
  ...keySchemas,
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
    field: 'imageSize',
    label: '图片大小',
    labelMessage: '生成图片的大小尺寸',
    component: 'NSelect',
    rules: [{ required: true, message: '请选择图片大小', trigger: ['blur'] }],
    componentProps: {
      options: [
        {
          label: '1024x1024',
          value: '1024x1024',
        },
        {
          label: '1024x1792',
          value: '1024x1792',
        },
        {
          label: '1792x1024',
          value: '1792x1024',
        },
      ],
    },
  },
  {
    field: 'imageQuality',
    label: '图片质量',
    labelMessage: '生成图片的质量',
    component: 'NSelect',
    rules: [{ required: true, message: '请选择图片的质量', trigger: ['blur'] }],
    componentProps: {
      options: [
        {
          label: 'standard',
          value: 'standard',
        },
        {
          label: 'hd',
          value: 'hd',
        },
      ],
    },
  },
  {
    field: 'imageStyle',
    label: '图片风格',
    labelMessage: '生成图片的风格',
    component: 'NSelect',
    rules: [{ required: true, message: '请选择图片的风格', trigger: ['blur'] }],
    componentProps: {
      options: [
        {
          label: 'vivid',
          value: 'vivid',
        },
        {
          label: 'natural',
          value: 'natural',
        },
      ],
    },
  },
  ...keySchemas,
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
];

export function getSchemas(provider: string) {
  switch (provider) {
    case ProviderEnum.OPENAI: {
      return openaiSchemas;
    }
    case ProviderEnum.AZURE_OPENAI: {
      return azureOpenaiSchemas;
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
