export enum ProviderEnum {
  OPENAI = 'OPENAI',
  AZURE_OPENAI = 'AZURE_OPENAI',
  GEMINI = 'GEMINI',
  OLLAMA = 'OLLAMA',
  CLAUDE = 'CLAUDE',
  Q_FAN = 'Q_FAN',
  Q_WEN = 'Q_WEN',
  ZHIPU = 'ZHIPU',
  DEEPSEEK = 'DEEPSEEK',
  DOUYIN = 'DOUYIN',
  SILICON = 'SILICON',
  YI = 'YI',
  SPARK = 'SPARK',
}

export function getModels(provider: string, providers: Array<any>) {
  const arr = providers.filter((i) => i.model === provider);
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

export function getTitle(provider: string, providers: Array<any>) {
  return providers.filter((i) => i.model === provider)[0].name;
}
