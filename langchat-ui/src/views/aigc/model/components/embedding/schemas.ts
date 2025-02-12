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
import { ModelTypeEnum } from '@/api/models';
import { getModels, ProviderEnum } from '@/views/aigc/model/provider';
import { LLMProviders } from './consts';

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
    defaultValue: ModelTypeEnum.EMBEDDING,
  },
  {
    field: 'provider',
    label: 'LLM供应商',
    component: 'NSelect',
    slot: 'providerSlot',
    isHidden: true,
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
    labelMessage: '模型的ApiKey',
    component: 'NInput',
    // rules: [{ required: true, message: '请输入ApiKey', trigger: ['blur'] }],
    componentProps: {
      placeholder: '请输入ApiKey',
    },
  },
  {
    field: 'dimension',
    label: '向量纬度',
    component: 'NSelect',
    defaultValue: 1024,
    labelMessage: '慎重修改此参数，纬度高会消耗更多的算力，但纬度高并不代表搜索更精确',
    componentProps: {
      placeholder: '请输入向量纬度',
      options: [
        {
          label: '512',
          value: 512,
        },
        {
          label: '768',
          value: 768,
        },
        {
          label: '1024',
          value: 1024,
        },
        {
          label: '1536',
          value: 1536,
        },
      ],
    },
    rules: [{ type: 'number', required: true, message: '请输入向量纬度', trigger: ['blur'] }],
  },
];

export function getSchemas(provider: string) {
  const list = JSON.parse(JSON.stringify(baseSchemas));

  const modelSchema: any = {
    field: 'model',
    label: '模型版本',
    labelMessage: '该LLM供应商对应的模型版本号',
    component: 'NSelect',
    rules: [{ required: true, message: '请选择模型', trigger: ['blur'] }],
    componentProps: {
      placeholder: '请选择模型版本',
      filterable: true,
      tag: true,
      options: getModels(provider, LLMProviders),
    },
  };
  list.splice(list.length, 0, modelSchema);

  let defaultValue: any = undefined;
  let labelMessage: any = '模型的基础请求URL地址（或中转地址）';
  let disabled = false;
  switch (provider) {
    case ProviderEnum.DOUYIN:
      disabled = true;
      defaultValue = 'https://ark.cn-beijing.volces.com/api/v3';
      labelMessage = '对于抖音豆包模型，此Url固定不可修改';
      break;
    case ProviderEnum.Q_FAN:
      disabled = true;
      labelMessage = '对于百度千帆模型，此Url固定不可修改';
      break;
    case ProviderEnum.Q_WEN:
      disabled = true;
      labelMessage = '对于阿里千问模型，此Url固定不可修改';
      break;
    case ProviderEnum.ZHIPU:
      disabled = true;
      labelMessage = '对于智谱清言模型，此Url固定不可修改';
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
  };
  list.splice(list.length, 0, baseUlrSchema);
  return list;
}
