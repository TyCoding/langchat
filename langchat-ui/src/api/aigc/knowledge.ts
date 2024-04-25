import { http } from '@/utils/http/axios';

export function list(params: any) {
  return http.request({
    url: '/aigc/kb/list',
    method: 'get',
    params,
  });
}

export function page(params: any) {
  return http.request({
    url: '/aigc/kb/page',
    method: 'get',
    params,
  });
}

export function getById(id: string) {
  return http.request({
    url: `/aigc/kb/${id}`,
    method: 'get',
  });
}

export function add(params: any) {
  return http.request({
    url: '/aigc/kb',
    method: 'post',
    params,
  });
}

export function update(params: any) {
  return http.request({
    url: '/aigc/kb',
    method: 'put',
    params,
  });
}

export function del(id?: String) {
  return http.request({
    url: `/aigc/kb/${id}`,
    method: 'delete',
  });
}
