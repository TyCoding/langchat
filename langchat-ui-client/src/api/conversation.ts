import { http } from '@/utils/request';

export function page(params: any) {
  return http.get({
    url: '/client/conversation/page',
    params,
  });
}

export function list(data: any) {
  return http.get({
    url: '/client/conversation/list',
    data,
  });
}

export function add(data: any) {
  return http.post({
    url: '/client/conversation',
    data,
  });
}

export function update(data: any) {
  return http.put({
    url: '/client/conversation',
    data,
  });
}

export function del(conversationId: string) {
  return http.delete({
    url: `/client/conversation/${conversationId}`,
  });
}

export function clearMessage(conversationId: string | undefined) {
  return http.delete({
    url: `/client/conversation/message/${conversationId}`,
  });
}

export function getMessages(conversationId: string) {
  return http.get({
    url: `/client/conversation/messages/${conversationId}`,
  });
}

export function addMessage(data: any) {
  return http.post({
    url: `/client/conversation/message`,
    data,
  });
}
