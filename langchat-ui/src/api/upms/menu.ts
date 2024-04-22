import { http } from '@/utils/http/axios';
import { Menu } from '@/api/models/upms';

export function list(params: any) {
  return http.request({
    url: '/upms/menu/list',
    method: 'get',
    params,
  });
}

export function tree(params: any) {
  return http.request({
    url: '/upms/menu/tree',
    method: 'get',
    params,
  });
}

export function getById(id: string): Promise<Menu> {
  return http.request({
    url: `/upms/menu/${id}`,
    method: 'get',
  });
}

export function add(params: any) {
  return http.request({
    url: '/upms/menu',
    method: 'post',
    params,
  });
}

export function update(params: any) {
  return http.request({
    url: '/upms/menu',
    method: 'put',
    params,
  });
}

export function del(id?: String) {
  return http.request({
    url: `/upms/menu/${id}`,
    method: 'delete',
  });
}
