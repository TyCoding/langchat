import { http } from '@/utils/http/axios';

export function list(params: any) {
  return http.request({
    url: '/upms/dept/list',
    method: 'get',
    params,
  });
}

export function tree(params: any) {
  return http.request({
    url: '/upms/dept/tree',
    method: 'get',
    params,
  });
}

export function getById(id: string) {
  return http.request({
    url: `/upms/dept/${id}`,
    method: 'get',
  });
}

export function add(params: any) {
  return http.request({
    url: '/upms/dept',
    method: 'post',
    params,
  });
}

export function update(params: any) {
  return http.request({
    url: '/upms/dept',
    method: 'put',
    params,
  });
}

export function del(id?: String) {
  return http.request({
    url: `/upms/dept/${id}`,
    method: 'delete',
  });
}
