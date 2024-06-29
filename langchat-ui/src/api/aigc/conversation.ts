import { http } from '@/utils/http/axios';

export function page(params: any) {
  return http.request({
    url: '/aigc/conversation/page',
    method: 'get',
    params,
  });
}

export function del(id: string) {
  return http.request({
    url: `/aigc/conversation/${id}`,
    method: 'delete',
  });
}

export function getMessages(conversationId: string) {
  return http.request({
    url: `/aigc/conversation/messages/${conversationId}`,
    method: 'get',
  });
}
