import { http } from '@/utils/http/axios';

export function list(params: any) {
  return http.request({
    url: '/langchat/kb/doc/list',
    method: 'get',
    params,
  });
}

export function page(params: any) {
  return http.request({
    url: '/langchat/kb/doc/page',
    method: 'get',
    params,
  });
}

export function getById(id: string) {
  return http.request({
    url: `/langchat/kb/doc/${id}`,
    method: 'get',
  });
}

export function add(params: any) {
  return http.request({
    url: '/langchat/kb/doc',
    method: 'post',
    params,
  });
}

export function update(params: any) {
  return http.request({
    url: '/langchat/kb/doc',
    method: 'put',
    params,
  });
}

export function del(id: string) {
  return http.request({
    url: `/langchat/kb/doc/${id}`,
    method: 'delete',
  });
}
