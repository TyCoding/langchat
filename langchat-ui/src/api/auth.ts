import { http } from '@/utils/http/axios';

export function login(data: any) {
  return http.request({
    url: `/auth/administrator`,
    method: 'POST',
    data,
  });
}
