import { http } from '@/utils/http/axios';

export function list(params: any) {
  return http.request({
    url: '/langchat/message/list',
    method: 'get',
    params,
  });
}

export function page(params: any) {
  return http.request({
    url: '/langchat/message/page',
    method: 'get',
    params,
  });
}

export function add(params: any) {
  return http.request({
    url: '/langchat/message',
    method: 'post',
    params,
  });
}

export function update(params: any) {
  return http.request({
    url: '/langchat/message',
    method: 'put',
    params,
  });
}

export function del(id: string) {
  return http.request({
    url: `/langchat/message/${id}`,
    method: 'delete',
  });
}
