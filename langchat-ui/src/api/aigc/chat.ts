import { http } from '@/utils/http/axios';
import { AxiosProgressEvent } from 'axios';

export function chat(data: any, onDownloadProgress?: (progressEvent: AxiosProgressEvent) => void) {
  return http.request(
    {
      method: 'post',
      url: '/aigc/chat/knowledge',
      data,
      onDownloadProgress: onDownloadProgress,
    },
    {
      isReturnNativeResponse: true,
    }
  );
}

export function clean(conversationId: string | null) {
  return http.request({
    url: `/aigc/chat/knowledge/cleanMessage/${conversationId}`,
    method: 'delete',
  });
}

export function getMessages(conversationId?: String) {
  return http.request({
    url: `/aigc/chat/knowledge/messages/${conversationId}`,
    method: 'get',
  });
}
