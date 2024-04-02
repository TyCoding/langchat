import { http } from '@/utils/http/axios';
import { AxiosProgressEvent, GenericAbortSignal } from 'axios';

export function chat(
  data: {
    isRegenerate?: boolean;
    promptId?: string;
    parentRefId?: string;
    content?: string;
    conversationId?: string;
    appId?: string;
    flowId?: string;
  },
  signal?: GenericAbortSignal,
  onDownloadProgress?: (progressEvent: AxiosProgressEvent) => void
) {
  return http.request(
    {
      url: '/langchat/chat',
      method: 'post',
      data,
      timeout: 1000 * 60 * 30,
      signal: signal,
      onDownloadProgress: onDownloadProgress,
    },
    {
      isTransformResponse: false,
    }
  );
}

export function del(id: string) {
  return http.request({
    url: `/langchat/chat/${id}`,
    method: 'delete',
  });
}
