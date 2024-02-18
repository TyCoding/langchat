import { http } from '@/utils/request';

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

export function update(params: any) {
  return http.put({
    url: '/langchat/conversation',
    params,
  });
}

export function del(id: string) {
  return http.delete({
    url: `/langchat/conversation/${id}`,
  });
}

export function getMessages(conversationId: string) {
  return http.get({
    url: `/langchat/conversation/messages/${conversationId}`,
  });
}
