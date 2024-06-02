export type User = {
  /** 主键 */
  id?: string;
  /** 用户名 */
  username?: string;
  /** 密码 */
  password?: string;
  /** 真实姓名 */
  realName?: string;
  /** 性别 */
  sex?: string;
  /** 邮箱 */
  email?: string;
  /** 头像 */
  avatar?: string;
  /** 手机 */
  phone?: string;
  /** 状态 0锁定 1有效 */
  status?: boolean;
  /** 创建时间 */
  createTime?: string;
};

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
