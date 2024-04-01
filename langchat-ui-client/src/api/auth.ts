import { http } from '@/utils/request';
import { TokenInfo, User } from '@/api/models/index';

/**
 * @description: 获取邮箱验证码
 */
export function getEmailCode(email: string) {
  return http.get({
    url: `/client/auth/code/email?email=${email}`,
  });
}

/**
 * @description: 邮箱注册
 */
export function emailRegister(data: any) {
  return http.post({
    url: `/client/auth/register/email`,
    data,
  });
}

/**
 * @description: 用户登录
 */
export function login(data: any): Promise<TokenInfo> {
  return http.post({
    url: `/client/auth/login`,
    data,
  });
}

/**
 * @description: 用户登录
 */
export function info(): Promise<User> {
  return http.get({
    url: `/client/auth/info`,
  });
}

/**
 * @description: 用户修改密码
 */
export function changePassword(data: any, uid: any) {
  return http.post({
    url: `/user/u${uid}/changepw`,
    method: 'POST',
    data,
  });
}

/**
 * @description: 用户登出
 */
export function logout() {
  return http.delete({
    url: '/client/auth/logout',
  });
}
