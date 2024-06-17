import { http } from '@/utils/http/axios';
import { AxiosProgressEvent } from 'axios';

export function list(params: any) {
  return http.request({
    url: '/langchat/flow/list',
    method: 'get',
    params,
  });
}

export function page(params: any) {
  return http.request({
    url: '/langchat/flow/page',
    method: 'get',
    params,
  });
}

export function getById(id: string) {
  return http.request({
    url: `/langchat/flow/${id}`,
    method: 'get',
  });
}

export function add(params: any) {
  return http.request({
    url: '/langchat/flow',
    method: 'post',
    params,
  });
}

export function update(params: any) {
  return http.request({
    url: '/langchat/flow',
    method: 'put',
    params,
  });
}

export function del(id: string) {
  return http.request({
    url: `/langchat/flow/${id}`,
    method: 'delete',
  });
}

export function publish(params: any) {
  return http.request({
    url: '/langchat/flow/publish',
    method: 'put',
    params,
  });
}

export function exec(
  id: string,
  params: any,
  onDownloadProgress?: (progressEvent: AxiosProgressEvent) => void
) {
  return http.request(
    {
      url: `/langchat/flow/exec/${id}`,
      method: 'post',
      params,
      onDownloadProgress: onDownloadProgress,
    },
    {
      isTransformResponse: false,
    }
  );
}
