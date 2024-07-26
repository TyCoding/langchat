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
  promptText?: string;
  role?: 'user' | 'assistant' | 'system';
  createTime?: string;
  type?: string;
  modelId?: string;
  modelName?: string;
  modelProvider?: string;
  language?: string;
  tone?: string;
  length?: string;
}
