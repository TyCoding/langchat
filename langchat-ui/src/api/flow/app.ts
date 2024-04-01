import { http } from '@/utils/http/axios';
import { AppInfo } from '@/api/models/flow';

export function list(params: any) {
  return http.request({
    url: '/langchat/app/list',
    method: 'get',
    params,
  });
}

export function page(params: any) {
  return http.request({
    url: '/langchat/app/page',
    method: 'get',
    params,
  });
}

export function appInfo(id: string): Promise<AppInfo> {
  return http.request({
    url: `/langchat/app/info/${id}`,
    method: 'get',
  });
}

export function getById(id: string) {
  return http.request({
    url: `/langchat/app/${id}`,
    method: 'get',
  });
}

export function add(params: any) {
  return http.request({
    url: '/langchat/app',
    method: 'post',
    params,
  });
}

export function update(params: any) {
  return http.request({
    url: '/langchat/app',
    method: 'put',
    params,
  });
}

export function del(id: string) {
  return http.request({
    url: `/langchat/app/${id}`,
    method: 'delete',
  });
}
