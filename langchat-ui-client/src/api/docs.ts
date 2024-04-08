import { http } from '@/utils/request';
import { Oss } from '@/api/models';
import { AxiosProgressEvent } from 'axios';

/**
 * @description: 上传文件
 */
export function upload(data: any, onUploadProgress?: (progressEvent: AxiosProgressEvent) => void) {
  return http.post({
    url: `/langchat/docs/upload`,
    data,
    headers: {
      'Content-Type': 'multipart/form-data',
    },
    onUploadProgress,
  });
}

/**
 * @description: Doc聊天
 */
export function chat(
  data: {
    id: string;
    message: string;
  },
  onDownloadProgress?: (progressEvent: AxiosProgressEvent) => void
) {
  return http.post({
    url: `/langchat/docs/chat`,
    data: data,
    onDownloadProgress: onDownloadProgress,
  });
}

export function list(): Promise<Oss[]> {
  return http.get({
    url: '/langchat/file/list',
  });
}

export function update(data: Oss) {
  return http.put({
    url: '/langchat/file',
    data: data,
  });
}

export function del(id?: number) {
  return http.delete({
    url: `/langchat/file/${id}`,
  });
}

export function task() {
  return http.get({
    url: `/langchat/docs/task`,
  });
}
