import { http } from '@/utils/request';
import { Oss } from '@/api/models';
import { AxiosProgressEvent } from 'axios';

/**
 * @description: 上传文件
 */
export function upload(data: any, onUploadProgress?: (progressEvent: AxiosProgressEvent) => void) {
  return http.post({
    url: `/aigc/chat/docs/upload`,
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
  knowledgeId: string,
  data: any,
  onDownloadProgress?: (progressEvent: AxiosProgressEvent) => void
) {
  return http.post({
    url: `/aigc/chat/docs/${knowledgeId}`,
    data,
    onDownloadProgress: onDownloadProgress,
  });
}

export function list(): Promise<Oss[]> {
  return http.get({
    url: '/aigc/file/list',
  });
}

export function update(data: Oss) {
  return http.put({
    url: '/aigc/file',
    data: data,
  });
}

export function del(id?: number) {
  return http.delete({
    url: `/aigc/chat/docs/${id}`,
  });
}

export function task() {
  return http.get({
    url: `/aigc/docs/task`,
  });
}
