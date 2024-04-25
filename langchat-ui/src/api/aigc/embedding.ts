import { http } from '@/utils/http/axios';

export function embeddingText(params: any) {
  return http.request({
    url: '/aigc/embedding/text',
    method: 'post',
    params,
  });
}
