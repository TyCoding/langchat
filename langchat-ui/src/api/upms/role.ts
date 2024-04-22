import { http } from '@/utils/http/axios';
import { Role } from '@/api/models/upms';

export function list(params: any) {
  return http.request({
    url: '/upms/role/list',
    method: 'get',
    params,
  });
}

export function page(params: any) {
  return http.request({
    url: '/upms/role/page',
    method: 'get',
    params,
  });
}

export function getById(id: string): Promise<Role> {
  return http.request({
    url: `/upms/role/${id}`,
    method: 'get',
  });
}

export function add(params: any) {
  return http.request({
    url: '/upms/role',
    method: 'post',
    params,
  });
}

export function update(params: any) {
  return http.request({
    url: '/upms/role',
    method: 'put',
    params,
  });
}

export function del(id?: String) {
  return http.request({
    url: `/upms/role/${id}`,
    method: 'delete',
  });
}
