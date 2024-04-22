import { http } from '@/utils/http/axios';

export function page(params: any) {
  return http.request({
    url: '/upms/log/page',
    method: 'get',
    params,
  });
}

export function getById(id: string) {
  return http.request({
    url: `/upms/log/${id}`,
    method: 'get',
  });
}

export function del(id?: String) {
  return http.request({
    url: `/upms/log/${id}`,
    method: 'delete',
  });
}
