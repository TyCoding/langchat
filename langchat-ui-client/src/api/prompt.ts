import { http } from '@/utils/request';

export function getPrompts(data: any) {
  return http.get({
    url: '/aigc/prompt/list',
    data: data,
  });
}

export function getById(id: string) {
  return http.get({
    url: '/aigc/prompt/' + id,
  });
}
