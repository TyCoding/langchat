import { FormSchema } from '@/components/Form';
import { LLMProviders, ProviderEnum } from '@/views/aigc/model/data';

const baseHeadSchemas: FormSchema[] = [
  {
    field: 'id',
    label: 'ID',
    component: 'NInput',
    isHidden: true,
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

export const googleSchemas: FormSchema[] = [
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
    rules: [{ required: true, message: '请输入Base Url', trigger: ['blur'] }],
  },
  ...baseSchemas,
];

export const baiduSchemas: FormSchema[] = [
  ...baseHeadSchemas,
  {
    field: 'model',
    label: '模型',
    labelMessage: '该LLM供应商对应的模型版本号',
    component: 'NSelect',
    rules: [{ required: true, message: '请选择模型', trigger: ['blur'] }],
    componentProps: {
      options: getModels(ProviderEnum.BAIDU),
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

export const alibabaSchemas: FormSchema[] = [
  ...baseHeadSchemas,
  {
    field: 'model',
    label: '模型',
    labelMessage: '该LLM供应商对应的模型版本号',
    component: 'NSelect',
    rules: [{ required: true, message: '请选择模型', trigger: ['blur'] }],
    componentProps: {
      options: getModels(ProviderEnum.ALIBABA),
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
      options: getModels(ProviderEnum.ZHIPU),
    },
  },
  ...keySchemas,
  ...baseSchemas,
];

export const imageSchemas: FormSchema[] = [
  ...baseHeadSchemas,
  {
    field: 'modelType',
    label: '模型类型',
    labelMessage: '代表模型的类型',
    component: 'NSelect',
    rules: [{ required: true, message: '请选择模型类型', trigger: ['blur'] }],
    componentProps: {
      options: [
        {
          label: 'openai',
          value: 'openai',
        },
        {
          label: 'azure-openai',
          value: 'azure-openai',
        },
      ],
    },
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
      options: getModels(ProviderEnum.TEXT_IMAGE),
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
];

export const embeddingSchemas: FormSchema[] = [
  ...baseHeadSchemas,
  {
    field: 'modelType',
    label: '模型类型',
    labelMessage: '代表模型的类型',
    component: 'NSelect',
    rules: [{ required: true, message: '请选择模型类型', trigger: ['blur'] }],
    componentProps: {
      options: [
        {
          label: 'openai',
          value: 'openai',
        },
        {
          label: 'azure-openai',
          value: 'azure-openai',
        },
      ],
    },
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
      options: getModels(ProviderEnum.EMBEDDING),
    },
  },
  {
    field: 'dimensions',
    label: 'Dimensions',
    labelMessage: '此参数代表向量纬数，注意此参数需要和向量数据库匹配',
    component: 'NSelect',
    rules: [{ required: true, message: '请选择向量维数', trigger: ['blur'] }],
    componentProps: {
      options: [
        {
          label: '384',
          value: '384',
        },
        {
          label: '1536',
          value: '1536',
        },
        {
          label: '3072',
          value: '3072',
        },
      ],
    },
  },
];

export function getSchemas(provider: string) {
  switch (provider) {
    case ProviderEnum.OPENAI: {
      return openaiSchemas;
    }
    case ProviderEnum.AZURE_OPENAI: {
      return azureOpenaiSchemas;
    }
    case ProviderEnum.GOOGLE: {
      return googleSchemas;
    }
    case ProviderEnum.OLLAMA: {
      return ollamaSchemas;
    }
    case ProviderEnum.BAIDU: {
      return baiduSchemas;
    }
    case ProviderEnum.ALIBABA: {
      return alibabaSchemas;
    }
    case ProviderEnum.ZHIPU: {
      return zhipuSchemas;
    }
    case ProviderEnum.TEXT_IMAGE: {
      return imageSchemas;
    }
    case ProviderEnum.EMBEDDING: {
      return embeddingSchemas;
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
