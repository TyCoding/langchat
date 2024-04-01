import { http } from '@/utils/request';
import { Conversation } from '@/typings/chat';

export function page(params: any) {
  return http.get({
    url: '/langchat/conversation/page',
    params,
  });
}

export function list(params: any) {
  return http.get({
    url: '/langchat/conversation/list',
    params,
  });
}

export function add(params: any) {
  return http.post({
    url: '/langchat/conversation',
    params,
  });
}

export function update(params: Partial<Conversation>) {
  return http.put({
    url: '/langchat/conversation',
    data: params,
  });
}

export function del(conversationId: string) {
  return http.delete({
    url: `/langchat/conversation/${conversationId}`,
  });
}

export function clearMessage(conversationId: string | undefined) {
  return http.delete({
    url: `/langchat/conversation/message/${conversationId}`,
  });
}

export function getMessages(conversationId: string) {
  return http.get({
    url: `/langchat/conversation/messages/${conversationId}`,
  });
}

export function addMessage(data: any) {
  return http.post({
    url: `/langchat/conversation/message`,
    data,
  });
}
