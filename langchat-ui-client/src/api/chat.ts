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
    url: '/client/chat',
    data,
    onDownloadProgress: onDownloadProgress,
  });
}

/**
 * @description: Doc聊天
 */
export function docsChat(
  knowledgeId: string,
  data: any,
  onDownloadProgress?: (progressEvent: AxiosProgressEvent) => void
) {
  return http.post({
    url: `/client/chat/docs/${knowledgeId}`,
    data,
    onDownloadProgress: onDownloadProgress,
  });
}

export function getPrompts(data: any) {
  return http.get({
    url: '/client/prompt/list',
    data: data,
  });
}

export function getChatModels() {
  return http.get({
    url: '/aigc/model/getChatModels',
  });
}

export function genWrite(
  data: ChatR,
  onDownloadProgress?: (progressEvent: AxiosProgressEvent) => void
) {
  return http.post({
    url: `/client/chat/write`,
    data: data,
    onDownloadProgress: onDownloadProgress,
  });
}

export function genChart(data: ChatR) {
  return http.post({
    url: '/client/chat/chart',
    data: data,
  });
}

/**
 * @description 生成图片
 */
export function genImage(data: ImageR): Promise<Oss> {
  return http.post({
    url: '/client/chat/image',
    data: data,
  });
}

/**
 * @description: 生成思维导图
 */
export function genMindMap(data: ChatR) {
  return http.post({
    url: '/client/chat/mindmap',
    data: data,
  });
}
