import { http } from '@/utils/http/axios';

export function list(params: any) {
  return http.request({
    url: '/aigc/message/list',
    method: 'get',
    params,
  });
}

export function page(params: any) {
  return http.request({
    url: '/aigc/message/page',
    method: 'get',
    params,
  });
}

export function add(params: any) {
  return http.request({
    url: '/aigc/message',
    method: 'post',
    params,
  });
}

export function update(params: any) {
  return http.request({
    url: '/aigc/message',
    method: 'put',
    params,
  });
}

export function del(id: string) {
  return http.request({
    url: `/aigc/message/${id}`,
    method: 'delete',
  });
}

export function charts() {
  return http.request({
    url: `/aigc/message/charts`,
    method: 'get',
  });
}
