import { http } from '@/utils/http/axios';
import { AxiosProgressEvent } from 'axios';

export function upload(
  id: string,
  data: any,
  onUploadProgress?: (progressEvent: AxiosProgressEvent) => void
) {
  return http.request({
    url: `/langchat/oss/put/${id}`,
    method: 'post',
    data,
    headers: {
      'Content-Type': 'multipart/form-data',
    },
    onUploadProgress,
  });
}

export function list(params: any) {
  return http.request({
    url: '/langchat/kb/file/list',
    method: 'get',
    params,
  });
}

export function page(params: any) {
  return http.request({
    url: '/langchat/kb/file/page',
    method: 'get',
    params,
  });
}

export function getById(id: string) {
  return http.request({
    url: `/langchat/kb/file/${id}`,
    method: 'get',
  });
}

export function add(params: any) {
  return http.request({
    url: '/langchat/kb/file',
    method: 'post',
    params,
  });
}

export function update(params: any) {
  return http.request({
    url: '/langchat/kb/file',
    method: 'put',
    params,
  });
}

export function del(id: string) {
  return http.request({
    url: `/langchat/kb/file/${id}`,
    method: 'delete',
  });
}
