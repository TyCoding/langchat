import { http } from '@/utils/http/axios';

export function list(params: any) {
  return http.request({
    url: '/langchat/kb/list',
    method: 'get',
    params,
  });
}

export function page(params: any) {
  return http.request({
    url: '/langchat/kb/page',
    method: 'get',
    params,
  });
}

export function getTags() {
  return http.request({
    url: '/langchat/kb/getTags',
    method: 'get',
  });
}

export function getById(id: string) {
  return http.request({
    url: `/langchat/kb/${id}`,
    method: 'get',
  });
}

export function add(params: any) {
  return http.request({
    url: '/langchat/kb',
    method: 'post',
    params,
  });
}

export function update(params: any) {
  return http.request({
    url: '/langchat/kb',
    method: 'put',
    params,
  });
}

export function del(id?: String) {
  return http.request({
    url: `/langchat/kb/${id}`,
    method: 'delete',
  });
}
