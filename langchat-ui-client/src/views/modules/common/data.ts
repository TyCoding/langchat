export enum EmbeddingProviderEnum {
  OPENAI = 'openai',
  AZURE_OPENAI = 'azure-openai',
  GOOGLE = 'google',
  OLLAMA = 'ollama',
  BAIDU = 'baidu',
  ALIBABA = 'alibaba',
  ZHIPU = 'zhipu',
}

export enum ProviderEnum {
  OPENAI = 'openai',
  AZURE_OPENAI = 'azure-openai',
  GOOGLE = 'google',
  OLLAMA = 'ollama',
  BAIDU = 'baidu',
  ALIBABA = 'alibaba',
  ZHIPU = 'zhipu',
  TEXT_IMAGE = 'text-image',
  EMBEDDING = 'embedding',
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
    model: ProviderEnum.GOOGLE,
    name: 'Google',
  },
  {
    model: ProviderEnum.OLLAMA,
    name: 'Ollama',
  },
  {
    model: ProviderEnum.BAIDU,
    name: '百度千帆大模型',
    models: [
      'ernie_bot_8k',
      'eb-instant',
      'ai_apaas',
      'yi_34b_chat',
      'bloomz_7b1',
      'qianfan_bloomz_7b_compressed',
      'mixtral_8x7b_instruct',
      'llama_2_7b',
      'llama_2_13b',
      'llama_2_70b',
      'qianfan_chinese_llama_2_7b',
      'chatglm2_6b_32k',
      'aquilachat_7b',
    ],
  },
  {
    model: ProviderEnum.ALIBABA,
    name: '阿里千帆大模型',
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
  {
    model: ProviderEnum.TEXT_IMAGE,
    name: 'Text-Image',
    models: ['dall-e-2', 'dall-e-3'],
  },
  {
    model: ProviderEnum.EMBEDDING,
    name: 'Embedding',
    models: [
      'text-embedding-3-small',
      'text-embedding-3-large',
      'text-embedding-ada-002',
      'embedding-v1',
      'bge_large_zh',
      'bge_large_en',
      'tao_8k',
    ],
  },
  // {
  //   model: 'web-search',
  //   name: 'RAG-WebSearch',
  // },
];