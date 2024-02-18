import { http } from '@/utils/request';
import { ChatR, Oss } from '@/api/models';
import { AxiosProgressEvent } from 'axios';

/**
 * @description: 列表查询
 */
export function list(): Promise<Oss[]> {
  return http.get({
    url: '/langchat/file/list',
  });
}

/**
 * @description: 上传文件
 */
export function upload(data: any, onUploadProgress?: (progressEvent: AxiosProgressEvent) => void) {
  return http.post({
    url: `/langchat/file/upload`,
    data,
    headers: {
      'Content-Type': 'multipart/form-data',
    },
    onUploadProgress,
  });
}

/**
 * @description: 生成文章
 */
export function chat(
  data: ChatR,
  onDownloadProgress?: (progressEvent: AxiosProgressEvent) => void
) {
  return http.post({
    url: `/langchat/file/chat`,
    data: data,
    onDownloadProgress: onDownloadProgress,
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
    url: `/langchat/file/task`,
  });
}
