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
import { LLMProviders, ProviderEnum } from './consts';
import { ModelTypeEnum } from '@/api/models';
import { isNullOrWhitespace } from '@/utils/is';

const baseSchemas: FormSchema[] = [
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
    isHidden: true,
    componentProps: {
      placeholder: 'LLM供应商',
      options: LLMProviders,
      labelField: 'name',
      valueField: 'model',
    },
    // rules: [{ required: true, message: '请选择LLM供应商', trigger: ['blur'] }],
  },
  {
    field: 'name',
    label: '模型别名',
    component: 'NInput',
    rules: [{ required: true, message: '请输入模型别名', trigger: ['blur'] }],
    componentProps: {
      placeholder: '请输入模型别名',
    },
  },
  {
    field: 'apiKey',
    label: 'Api Key',
    labelMessage: '模型链接的秘钥，注意有些模型例如Gemini是本地认证方式，则不是通过这种方式',
    component: 'NInput',
    rules: [{ required: true, message: '请输入API Key', trigger: ['blur'] }],
    componentProps: {
      placeholder: '请输入Api Key',
    },
  },
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
      defaultValue: 0.2,
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
      defaultValue: 0.8,
      step: 0.1,
      min: 0,
      max: 1,
    },
  },
];

export function getModels(provider: string) {
  const arr = LLMProviders.filter((i) => i.model === provider);
  if (arr.length === 0) {
    return [];
  }
  if (typeof arr[0].models[0] === 'string') {
    return arr[0].models.map((i) => {
      return {
        label: i,
        value: i,
      };
    });
  } else {
    return arr[0].models;
  }
}

export function getSchemas(provider: string) {
  const list = JSON.parse(JSON.stringify(baseSchemas));
  const modelSchema: any = {
    field: 'model',
    label: '模型',
    labelMessage: '该LLM供应商对应的模型版本号',
    component: 'NSelect',
    rules: [{ required: true, message: '请选择模型', trigger: ['blur'] }],
    componentProps: {
      placeholder: '模型名称（可以自由输入）',
      filterable: true,
      tag: true,
      options: getModels(provider),
    },
  };
  list.splice(1, 0, modelSchema);

  let defaultValue: any = undefined;
  let labelMessage: any =
    '注意对于大多数模型此参数仅代表中转地址，但是对于Ollama这类本地模型则是必填的';
  let disabled = false;
  switch (provider) {
    case ProviderEnum.DEEPSEEK:
      disabled = true;
      defaultValue = 'https://api.deepseek.com/v1';
      labelMessage = '对于DeepSeek模型，此Url固定不可修改';
      break;
    case ProviderEnum.SILICON:
      disabled = true;
      defaultValue = 'https://api.siliconflow.cn/v1';
      labelMessage = '对于硅基流动模型，此Url固定不可修改';
      break;
    case ProviderEnum.DOUYIN:
      disabled = true;
      defaultValue = 'https://ark.cn-beijing.volces.com/api/v3';
      labelMessage = '对于抖音豆包模型，此Url固定不可修改';
      break;
    case ProviderEnum.YI:
      disabled = true;
      defaultValue = 'https://api.lingyiwanwu.com/v1';
      labelMessage = '对于零一模型，此Url固定不可修改';
      break;
    case ProviderEnum.SPARK:
      disabled = true;
      defaultValue = 'https://spark-api-open.xf-yun.com/v1';
      labelMessage = '对于讯飞星火大模型，此Url固定不可修改';
      break;
  }
  const baseUlrSchema: any = {
    field: 'baseUrl',
    label: 'Base Url',
    labelMessage,
    component: 'NInput',
    defaultValue,
    componentProps: {
      disabled,
      placeholder: '请输入BaseUrl',
    },
    rules: [
      {
        required: false,
        trigger: ['blur'],
        validator: (_, value: string) => {
          if (!value) {
            return;
          }
          const urlRegex =
            /^(https?:\/\/)?((([a-zA-Z0-9-]+\.)+[a-zA-Z]{2,}|localhost|(\d{1,3}\.){3}\d{1,3})(:\d{1,5})?(\/.*)?)$/;
          if (isNullOrWhitespace(value) || urlRegex.test(value)) {
            return true;
          }
          return new Error('URL格式错误');
        },
      },
    ],
  };
  list.splice(3, 0, baseUlrSchema);
  return list;
}
