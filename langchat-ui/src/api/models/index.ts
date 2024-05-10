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
    label: 'OpenAI ChatGPT',
    value: 'openai',
  },
  {
    label: 'Google Gemini',
    value: 'gemini',
  },
  {
    label: 'Ollama',
    value: 'ollama',
  },
];
