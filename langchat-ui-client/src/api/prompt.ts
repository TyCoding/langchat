import { http } from '@/utils/request';

export function getBotPage(data: any) {
  return http.get({
    url: '/aigc/prompt/page',
    data: data,
  });
}

export function getById(id: string) {
  return http.get({
    url: '/aigc/prompt/' + id,
  });
}
