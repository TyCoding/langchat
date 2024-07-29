import { http } from '@/utils/http/axios';

export function list(params: any) {
  return http.request({
    url: '/aigc/app/web/list',
    method: 'get',
    params,
  });
}

export function page(params: any) {
  return http.request({
    url: '/aigc/app/web/page',
    method: 'get',
    params,
  });
}

export function getById(id: any) {
  return http.request({
    url: `/aigc/app/web/${id}`,
    method: 'get',
  });
}

export function add(params: any) {
  return http.request({
    url: '/aigc/app/web',
    method: 'post',
    params,
  });
}

export function update(params: any) {
  return http.request({
    url: '/aigc/app/web',
    method: 'put',
    params,
  });
}

export function del(id?: string) {
  return http.request({
    url: `/aigc/app/web/${id}`,
    method: 'delete',
  });
}

export function generateKey() {
  return http.request({
    url: `/aigc/app/web/generate/key`,
    method: 'get',
  });
}
