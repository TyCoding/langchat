import { http } from '@/utils/request';
import { TokenInfo, User } from '@/api/models';

/**
 * @description: 获取邮箱验证码
 */
export function getEmailCode(email: string) {
  return http.get({
    url: `/client/auth/email/code?email=${email}`,
  });
}

/**
 * @description: 邮箱注册
 */
export function emailRegister(data: any) {
  return http.post({
    url: `/client/auth/email/register`,
    data,
  });
}

export function login(data: any): Promise<TokenInfo> {
  return http.post({
    url: `/client/auth/login`,
    data,
  });
}

export function info(): Promise<User> {
  return http.get({
    url: `/client/auth/info`,
  });
}

export function forget(data: any) {
  return http.post({
    url: `/client/auth/forget`,
    method: 'POST',
    data,
  });
}

export function logout() {
  return http.delete({
    url: '/client/auth/logout',
  });
}
