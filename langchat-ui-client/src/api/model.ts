import { http } from '@/utils/request';

export function getList() {
  return http.get({
    url: '/aigc/model/list',
  });
}
