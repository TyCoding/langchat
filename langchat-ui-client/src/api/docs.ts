import { http } from '@/utils/request';
import { AxiosProgressEvent } from 'axios';

/**
 * @description: 上传文件
 */
export function upload(data: any, onUploadProgress?: (progressEvent: AxiosProgressEvent) => void) {
  return http.post({
    url: `/client/docs/upload`,
    data,
    headers: {
      'Content-Type': 'multipart/form-data',
    },
    onUploadProgress,
  });
}

export function list() {
  return http.get({
    url: '/client/oss/list',
  });
}

export function update(data: any) {
  return http.put({
    url: '/client/oss',
    data: data,
  });
}

export function del(id?: number) {
  return http.delete({
    url: `/client/docs/${id}`,
  });
}

export function task() {
  return http.get({
    url: `/client/task`,
  });
}
