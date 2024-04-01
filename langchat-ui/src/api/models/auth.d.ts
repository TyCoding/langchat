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
