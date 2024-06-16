import { FormSchema } from '@/components/Form';

export const LLMProviders: any[] = [
  {
    model: 'openai',
    name: 'OpenAI',
  },
  {
    model: 'google',
    name: 'Google',
  },
  {
    model: 'ollama',
    name: 'Ollama',
  },
  {
    model: 'baidu',
    name: '百度大模型',
  },
  {
    model: 'alibaba',
    name: '阿里大模型',
  },
];

export const columns = [
  {
    title: '供应商',
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
  {
    title: 'Api Key',
    key: 'apiKey',
  },
];

export const formSchemas: FormSchema[] = [
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
    field: 'model',
    label: '模型名称',
    labelMessage: '该LLM供应商对应的模型版本号',
    component: 'NInput',
    rules: [{ required: true, message: '请输入模型别名', trigger: ['blur'] }],
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
    // rules: [{ required: true, message: '请输入模型ApiKey', trigger: ['blur'] }],
  },
  {
    field: 'baseUrl',
    label: 'Base Url',
    labelMessage: '注意对于大多数模型此参数仅代表中转地址，但是对于Ollama这类本地模型则是必填的',
    component: 'NInput',
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
