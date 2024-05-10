import { http } from '@/utils/http/axios';
import { AxiosProgressEvent } from 'axios';

export function chat(
  data: {
    promptId?: string;
    chatId?: string;
    message?: string;
    role?: string;
    conversationId?: string;
    model?: string;
  },
  onDownloadProgress?: (progressEvent: AxiosProgressEvent) => void
) {
  return http.request({
    method: 'post',
    url: '/aigc/chat/knowledge',
    data,
    onDownloadProgress: onDownloadProgress,
  });
}

export function del(id: string) {
  return http.request({
    url: `/aigc/chat/${id}`,
    method: 'delete',
  });
}
