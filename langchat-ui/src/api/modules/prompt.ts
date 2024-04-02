import { http } from '@/utils/http/axios';
import { AxiosProgressEvent } from 'axios';

export function list(params: any) {
  return http.request({
    url: '/langchat/prompt/list',
    method: 'get',
    params,
  });
}

export function page(params: any) {
  return http.request({
    url: '/langchat/prompt/page',
    method: 'get',
    params,
  });
}

export function getById(id: string) {
  return http.request({
    url: `/langchat/prompt/${id}`,
    method: 'get',
  });
}

export function getByModelId(id: string) {
  return http.request({
    url: `/langchat/prompt/byModelId/${id}`,
    method: 'get',
  });
}

export function add(params: any) {
  return http.request({
    url: '/langchat/prompt',
    method: 'post',
    params,
  });
}

export function update(params: any) {
  return http.request({
    url: '/langchat/prompt',
    method: 'put',
    params,
  });
}

export function del(id: string) {
  return http.request({
    url: `/langchat/prompt/${id}`,
    method: 'delete',
  });
}

export function chat(
  params: any,
  onDownloadProgress?: (progressEvent: AxiosProgressEvent) => void
) {
  return http.request(
    {
      url: `/langchat/chat/prompt`,
      method: 'post',
      params,
      onDownloadProgress: onDownloadProgress,
    },
    {
      isTransformResponse: false,
    }
  );
}
