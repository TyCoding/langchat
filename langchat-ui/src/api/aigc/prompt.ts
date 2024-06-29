import { http } from '@/utils/http/axios';

export function list(params: any) {
  return http.request({
    url: '/aigc/prompt/list',
    method: 'get',
    params,
  });
}

export function page(params: any) {
  return http.request({
    url: '/aigc/prompt/page',
    method: 'get',
    params,
  });
}

export function getById(id: string) {
  return http.request({
    url: `/aigc/prompt/${id}`,
    method: 'get',
  });
}

export function getByModelId(id: string) {
  return http.request({
    url: `/aigc/prompt/byModelId/${id}`,
    method: 'get',
  });
}

export function add(params: any) {
  return http.request({
    url: '/aigc/prompt',
    method: 'post',
    params,
  });
}

export function update(params: any) {
  return http.request({
    url: '/aigc/prompt',
    method: 'put',
    params,
  });
}

export function del(id: string) {
  return http.request({
    url: `/aigc/prompt/${id}`,
    method: 'delete',
  });
}
