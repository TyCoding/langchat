import { http } from '@/utils/http/axios';

export function list(params: any) {
  return http.request({
    url: '/aigc/app/api/list',
    method: 'get',
    params,
  });
}

export function page(params: any) {
  return http.request({
    url: '/aigc/app/api/page',
    method: 'get',
    params,
  });
}

export function getById(id: any) {
  return http.request({
    url: `/aigc/app/api/${id}`,
    method: 'get',
  });
}

export function add(params: any) {
  return http.request({
    url: '/aigc/app/api',
    method: 'post',
    params,
  });
}

export function update(params: any) {
  return http.request({
    url: '/aigc/app/api',
    method: 'put',
    params,
  });
}

export function del(id?: string) {
  return http.request({
    url: `/aigc/app/api/${id}`,
    method: 'delete',
  });
}

export function generateKey() {
  return http.request({
    url: `/aigc/app/api/generate/key`,
    method: 'get',
  });
}
