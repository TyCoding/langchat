import { http } from '@/utils/http/axios';

export function page(params: any) {
  return http.request({
    url: '/aigc/log/page',
    method: 'get',
    params,
  });
}

export function getById(id: string) {
  return http.request({
    url: `/aigc/log/${id}`,
    method: 'get',
  });
}

export function del(id?: String) {
  return http.request({
    url: `/aigc/log/${id}`,
    method: 'delete',
  });
}
