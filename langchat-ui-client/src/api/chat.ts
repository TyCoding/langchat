import { http } from '@/utils/request';
import { ChatR, ImageR, Oss } from '@/api/models';
import { AxiosProgressEvent } from 'axios';

/**
 * @description: 聊天
 */
export function chat(
  data: ChatR,
  onDownloadProgress?: (progressEvent: AxiosProgressEvent) => void
) {
  return http.post({
    url: '/aigc/chat',
    data,
    onDownloadProgress: onDownloadProgress,
  });
}

/**
 * @description: 翻译
 */
export function genTranslate(
  data: ChatR,
  onDownloadProgress?: (progressEvent: AxiosProgressEvent) => void
) {
  return http.post({
    url: `/aigc/chat/translate`,
    data: data,
    onDownloadProgress: onDownloadProgress,
  });
}

/**
 * @description: 生成文章
 */
export function genWrite(
  data: ChatR,
  onDownloadProgress?: (progressEvent: AxiosProgressEvent) => void
) {
  return http.post({
    url: `/aigc/chat/write`,
    data: data,
    onDownloadProgress: onDownloadProgress,
  });
}

/**
 * @description: 生成思维导图
 */
export function genMindMap(data: ChatR) {
  return http.post({
    url: '/langchat/chat/mindmap',
    data: data,
  });
}

/**
 * @description: 生成思维导图
 */
export function genChart(data: ChatR) {
  return http.post({
    url: '/aigc/chat/chart',
    data: data,
  });
}

/**
 * @description 生成图片
 */
export function genImage(data: ImageR): Promise<Oss> {
  return http.post({
    url: '/aigc/chat/image',
    data: data,
  });
}

/**
 * @description: generate Mermaid
 */
export function genMermaid(
  data: ChatR,
  onDownloadProgress?: (progressEvent: AxiosProgressEvent) => void
) {
  return http.post({
    url: '/aigc/chat/mermaid',
    data: data,
    onDownloadProgress: onDownloadProgress,
  });
}
