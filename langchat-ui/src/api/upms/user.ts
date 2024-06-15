import { http } from '@/utils/http/axios';

export function list(params: any) {
  return http.request({
    url: '/upms/user/list',
    method: 'get',
    params,
  });
}

export function page(params: any) {
  return http.request({
    url: '/upms/user/page',
    method: 'get',
    params,
  });
}

export function getById(id: string) {
  return http.request({
    url: `/upms/user/${id}`,
    method: 'get',
  });
}

export function add(data: any) {
  return http.request({
    url: '/upms/user',
    method: 'post',
    data,
  });
}

export function update(data: any) {
  return http.request({
    url: '/upms/user',
    method: 'put',
    data,
  });
}

export function del(id?: String) {
  return http.request({
    url: `/upms/user/${id}`,
    method: 'delete',
  });
}

export function updatePassword(data: any) {
  return http.request({
    url: '/upms/user/updatePass',
    method: 'put',
    data,
  });
}

export function resetPass(data: any) {
  return http.request({
    url: '/upms/user/resetPass',
    method: 'put',
    data,
  });
}
