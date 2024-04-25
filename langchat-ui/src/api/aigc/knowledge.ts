import { http } from '@/utils/http/axios';

export function list(params: any) {
  return http.request({
    url: '/aigc/knowledge/list',
    method: 'get',
    params,
  });
}

export function page(params: any) {
  return http.request({
    url: '/aigc/knowledge/page',
    method: 'get',
    params,
  });
}

export function getById(id: string) {
  return http.request({
    url: `/aigc/knowledge/${id}`,
    method: 'get',
  });
}

export function add(params: any) {
  return http.request({
    url: '/aigc/knowledge',
    method: 'post',
    params,
  });
}

export function update(params: any) {
  return http.request({
    url: '/aigc/knowledge',
    method: 'put',
    params,
  });
}

export function del(id?: String) {
  return http.request({
    url: `/aigc/knowledge/${id}`,
    method: 'delete',
  });
}
