import { http } from '@/utils/request';

export function getPrompts(data: any) {
  return http.get({
    url: '/aigc/prompt/list',
    data: data,
  });
}
