import { ProviderEnum } from '@/views/aigc/model/data';

export const baseColumns = [
  {
    title: '模型别名',
    key: 'name',
  },
  {
    title: '模型名称',
    key: 'model',
  },
  {
    title: '回复上限',
    key: 'responseLimit',
    width: '120',
  },
  {
    title: '生成随机性',
    key: 'temperature',
    width: '120',
  },
  {
    title: 'Top P',
    key: 'topP',
    width: '120',
  },
];

export const openaiColumns = [
  ...baseColumns,
  {
    title: 'Api Key',
    key: 'apiKey',
  },
];

export const azureOpenaiColumns = [
  ...baseColumns,
  {
    title: 'Api Key',
    key: 'apiKey',
  },
  {
    title: 'Endpoint',
    key: 'endpoint',
  },
  {
    title: 'Deployment Name',
    key: 'azureDeploymentName',
  },
];

export const googleColumns = [
  ...baseColumns,
  {
    title: 'Project',
    key: 'geminiProject',
  },
  {
    title: 'Location',
    key: 'geminiLocation',
  },
];

export const ollamaColumns = [
  ...baseColumns,
  {
    title: 'Project',
    key: 'project',
  },
  {
    title: 'Base Url',
    key: 'baseUrl',
  },
];

export const baiduColumns = [
  ...baseColumns,
  {
    title: 'Secret Key',
    key: 'secretKey',
  },
];

export const alibabaColumns = [...baseColumns];
export const zhipuColumns = [...baseColumns];

export const imageColumns = [
  {
    title: '模型类型',
    key: 'modelType',
  },
  {
    title: 'Model Name',
    key: 'model',
  },
  {
    title: 'Api Key',
    key: 'apiKey',
  },
  {
    title: '图片大小',
    key: 'imageSize',
  },
  {
    title: '图片质量',
    key: 'imageQuality',
  },
  {
    title: '图片风格',
    key: 'imageStyle',
  },
];

export const embeddingColumns = [
  {
    title: '模型类型',
    key: 'modelType',
  },
  {
    title: 'Model Name',
    key: 'model',
  },
  {
    title: 'Api Key',
    key: 'apiKey',
  },
  {
    title: 'Dimensions',
    key: 'dimensions',
  },
];

export function getColumns(provider: string) {
  switch (provider) {
    case ProviderEnum.OPENAI: {
      return openaiColumns;
    }
    case ProviderEnum.AZURE_OPENAI: {
      return azureOpenaiColumns;
    }
    case ProviderEnum.GOOGLE: {
      return googleColumns;
    }
    case ProviderEnum.OLLAMA: {
      return ollamaColumns;
    }
    case ProviderEnum.BAIDU: {
      return baiduColumns;
    }
    case ProviderEnum.ALIBABA: {
      return alibabaColumns;
    }
    case ProviderEnum.ZHIPU: {
      return zhipuColumns;
    }
    case ProviderEnum.TEXT_IMAGE: {
      return imageColumns;
    }
    case ProviderEnum.EMBEDDING: {
      return embeddingColumns;
    }
  }
  return [];
}
