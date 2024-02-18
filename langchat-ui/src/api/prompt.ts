import { http } from '@/utils/request';

export function getBotPage(data: any) {
  return http.get({
    url: '/langchat/prompt/page',
    data: data,
  });
}

export function getById(id: string) {
  return http.get({
    url: '/langchat/prompt/' + id,
  });
}

export function del(id: string) {
  return http.delete({
    url: '/langchat/prompt/' + id,
  });
}

export function add(data: any) {
  return http.post({
    url: '/langchat/prompt',
    data,
  });
}

export function update(data: any) {
  return http.put({
    url: '/langchat/prompt',
    data,
  });
}
