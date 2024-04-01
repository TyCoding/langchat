export type Role = {
  /** 主键 */
  id?: string;
  /** 角色名称 */
  name?: string;
  /** 角色别名 */
  alias?: string;
  /** 描述 */
  des?: string;
  /** 菜单列表 */
  menuIds?: Array;
};

export type Menu = {
  /** 主键 */
  id?: string;
  /** 资源名称 */
  name: string;
  /** 父级ID */
  parentId?: string;
  /** 路由地址 */
  path?: string;
  /** 权限标识 */
  perms?: string;
  /** 菜单类型：如button按钮 menu菜单 */
  type?: string;
  /** 菜单图标 */
  icon?: string;
  /** 组件路径 */
  component?: string;
  /** 排序 */
  orderNo?: number;
  /** 是否禁用 */
  isDisabled?: boolean;
  /** 是否外链 */
  isExt?: boolean;
  /** 是否缓存 */
  isKeepalive?: boolean;
  /** 是否显示 */
  isShow?: boolean;
  meta: {
    title: string;
    icon: string;
  };
  disabled: boolean;
};

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
  /** 部门ID */
  deptId?: string;
  /** 头像 */
  avatar?: string;
  /** 手机 */
  phone?: string;
  /** 状态 0锁定 1有效 */
  status?: boolean;
  /** 创建时间 */
  createTime?: string;
  /** 角色列表 */
  roles?: Array;
  roleIds?: Array;
};
