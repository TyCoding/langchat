import { http } from '@/utils/request';

export function page(params: any) {
  return http.get({
    url: '/aigc/conversation/page',
    params,
  });
}

export function list(data: any) {
  return http.get({
    url: '/aigc/conversation/list',
    data,
  });
}

export function add(data: any) {
  return http.post({
    url: '/aigc/conversation',
    data,
  });
}

export function update(data: any) {
  return http.put({
    url: '/aigc/conversation',
    data,
  });
}

export function del(conversationId: string) {
  return http.delete({
    url: `/aigc/conversation/${conversationId}`,
  });
}

export function clearMessage(conversationId: string | undefined) {
  return http.delete({
    url: `/aigc/conversation/message/${conversationId}`,
  });
}

export function getMessages(conversationId: string) {
  return http.get({
    url: `/aigc/conversation/messages/${conversationId}`,
  });
}

export function addMessage(data: any) {
  return http.post({
    url: `/aigc/conversation/message`,
    data,
  });
}
