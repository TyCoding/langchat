import { http } from '@/utils/http/axios';

export function getReqChartBy30() {
  return http.request({
    url: `/aigc/statistic/requestBy30`,
    method: 'get',
  });
}

export function getReqChart() {
  return http.request({
    url: `/aigc/statistic/request`,
    method: 'get',
  });
}

export function getTokenChart() {
  return http.request({
    url: `/aigc/statistic/token`,
    method: 'get',
  });
}

export function getHomeData() {
  return http.request({
    url: `/aigc/statistic/home`,
    method: 'get',
  });
}
