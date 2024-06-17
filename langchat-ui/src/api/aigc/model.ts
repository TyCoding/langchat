import { http } from '@/utils/http/axios';

export function page(params: any) {
  return http.request({
    url: '/aigc/model/page',
    method: 'get',
    params,
  });
}

export function list(params: any) {
  return http.request({
    url: '/aigc/model/list',
    method: 'get',
    params,
  });
}

export function getChatModels() {
  return http.request({
    url: '/aigc/model/getChatModels',
    method: 'get',
  });
}

export function getImageModels() {
  return http.request({
    url: '/aigc/model/getImageModels',
    method: 'get',
  });
}

export function getEmbeddingModels() {
  return http.request({
    url: '/aigc/model/getEmbeddingModels',
    method: 'get',
  });
}

export function getById(id: any) {
  return http.request({
    url: `/aigc/model/${id}`,
    method: 'get',
  });
}

export function add(params: any) {
  return http.request({
    url: '/aigc/model',
    method: 'post',
    params,
  });
}

export function update(params: any) {
  return http.request({
    url: '/aigc/model',
    method: 'put',
    params,
  });
}

export function del(id: string) {
  return http.request({
    url: `/aigc/model/${id}`,
    method: 'delete',
  });
}
