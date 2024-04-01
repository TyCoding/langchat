import { http } from '@/utils/http/axios';
import { AxiosProgressEvent } from 'axios';

export function list(params: any) {
  return http.request({
    url: '/langchat/bot/list',
    method: 'get',
    params,
  });
}

export function page(params: any) {
  return http.request({
    url: '/langchat/bot/page',
    method: 'get',
    params,
  });
}

export function getById(id: string) {
  return http.request({
    url: `/langchat/bot/${id}`,
    method: 'get',
  });
}

export function getByModelId(id: string): Promise<Model> {
  return http.request({
    url: `/langchat/bot/byModelId/${id}`,
    method: 'get',
  });
}

export function add(params: any) {
  return http.request({
    url: '/langchat/bot',
    method: 'post',
    params,
  });
}

export function update(params: any) {
  return http.request({
    url: '/langchat/bot',
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
      url: `/langchat/chat/bot`,
      method: 'post',
      params,
      onDownloadProgress: onDownloadProgress,
    },
    {
      isTransformResponse: false,
    }
  );
}
