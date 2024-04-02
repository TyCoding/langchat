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

export type Log = {
  /** 主键 */
  id: string;
  /** 操作用户 */
  username: string;
  /** 操作状态 */
  type: number;
  /** 操作描述 */
  operation: string;
  /** 请求URL */
  url: string;
  /** 耗时(毫秒) */
  time: string;
  /** 操作方法 */
  method: string;
  /** 操作参数 */
  params: string;
  /** IP地址 */
  ip: string;
  /** 用户代理 */
  userAgent: string;
  /** 操作时间 */
  createTime: string;
};
