import { http } from '@/utils/request';
import { TokenInfo, User } from '@/api/models';

/**
 * @description: 获取邮箱验证码
 */
export function getEmailCode(email: string) {
  return http.get({
    url: `/aigc/auth/email/code?email=${email}`,
  });
}

/**
 * @description: 邮箱注册
 */
export function emailRegister(data: any) {
  return http.post({
    url: `/aigc/auth/email/register`,
    data,
  });
}

/**
 * @description: 用户登录
 */
export function login(data: any): Promise<TokenInfo> {
  return http.post({
    url: `/aigc/auth/login`,
    data,
  });
}

/**
 * @description: 用户登录
 */
export function info(): Promise<User> {
  return http.get({
    url: `/aigc/auth/info`,
  });
}

export function forget(data: any) {
  return http.post({
    url: `/aigc/auth/forget`,
    method: 'POST',
    data,
  });
}

/**
 * @description: 用户登出
 */
export function logout() {
  return http.delete({
    url: '/aigc/auth/logout',
  });
}
