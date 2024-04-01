import { http } from '@/utils/request';

export function getUserPage(data: any) {
  return http.get({
    url: '/langchat/user/page',
    data: data,
  });
}

export function getById(id: string) {
  return http.get({
    url: '/langchat/user/' + id,
  });
}

export function del(id: string) {
  return http.delete({
    url: '/langchat/user/' + id,
  });
}

export function add(data: any) {
  return http.post({
    url: '/langchat/user',
    data,
  });
}

export function update(data: any) {
  return http.put({
    url: '/langchat/user',
    data,
  });
}
