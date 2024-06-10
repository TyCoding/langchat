export type TokenInfo = {
  token?: string;
  expires?: number;
  user?: User;
} & R;

export type User = {
  id?: string;
  username?: string;
  password?: string;
  name?: string;
  avatar?: string;
  status?: boolean;
  email?: string;
  phone?: string;
  createTime?: string;
};

export type R = {
  code: number;
  message: string;
  data: Object;
};

export type Bot = {
  id: number;
  name?: string;
  prompt?: string;
  tags?: number;
  des?: string;
  icon?: number;
  createTime?: string;
};

export interface ChatR {
  chatId?: string;
  parentChatId?: string;
  conversationId?: string;
  message?: string;
  promptId?: string;
  role?: 'user' | 'assistant' | 'system';
  createTime?: string;
  type?: string;
  model?: string;
  language?: string;
  tone?: string;
  length?: string;
}

export interface Oss {
  id?: number;
  client?: number;
  fileName?: string;
  targetName?: string;
  bucket?: string;
  type?: string;
  size?: string;
  url?: string;
  des?: string;
  createTime?: string;
}

export interface ImageR {
  message?: string;
  model?: string;
  quality?: string;
  size?: string;
  style?: string;
}

export const modelList = [
  {
    label: 'OpenAI',
    key: 'OpenAI',
    type: 'group',
    children: [
      {
        label: 'GPT-4o',
        value: 'gpt-4o',
      },
      {
        label: 'GPT-4 Turbo',
        value: 'gpt-4-turbo',
      },
      {
        label: 'GPT-4',
        value: 'gpt-4',
      },
      {
        label: 'GPT-3.5 Turbo',
        value: 'gpt-3.5-turbo',
      },
    ],
  },
  {
    label: 'Google Gemini',
    value: 'gemini',
    type: 'group',
    children: [
      {
        label: 'Gemini 1.5 Flash',
        value: 'gemini-1.5-flash',
      },
      {
        label: 'Gemini 1.5 Pro',
        value: 'gemini-1.5-pro',
      },
    ],
  },
  {
    label: 'Ollama',
    value: 'ollama',
    type: 'group',
    children: [],
  },
];
