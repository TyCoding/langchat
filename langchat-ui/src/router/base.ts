import { ErrorPage, Layout, RedirectName } from '@/router/constant';
import { RouteRecordRaw } from 'vue-router';

// 404 on a page
export const ErrorPageRoute: RouteRecordRaw = {
  path: '/:path(.*)*',
  name: 'ErrorPage',
  component: Layout,
  meta: {
    title: 'ErrorPage',
    hideBreadcrumb: true,
  },
  children: [
    {
      path: '/:path(.*)*',
      name: 'ErrorPageSon',
      component: ErrorPage,
      meta: {
        title: 'ErrorPage',
        hideBreadcrumb: true,
      },
    },
  ],
};

export const RedirectRoute: RouteRecordRaw = {
  path: '/redirect',
  name: RedirectName,
  component: Layout,
  meta: {
    title: RedirectName,
    hideBreadcrumb: true,
  },
  children: [
    {
      path: '/redirect/:path(.*)',
      name: RedirectName,
      component: () => import('@/views/base/redirect/index.vue'),
      meta: {
        title: RedirectName,
        hideBreadcrumb: true,
      },
    },
  ],
};

export const BaseRoute: Array<any> = [
  {
    path: '/dashboard',
    name: 'Dashboard',
    component: 'LAYOUT',
    meta: {
      icon: 'FileTrayOutline',
      title: 'Dashboard',
    },
    children: [
      {
        path: 'console',
        name: 'dashboard_console',
        component: '/dashboard/index',
        meta: {
          title: '主控台',
        },
      },
    ],
  },
  {
    path: '/aigc',
    name: 'aigc',
    component: 'LAYOUT',
    show: false,
    meta: {
      title: '知识库',
    },
    children: [
      {
        path: 'knowledge/:id?',
        name: 'knowledge_info',
        component: '/aigc/knowledge/components/index',
        show: false,
        meta: {
          title: '知识库数据',
          activeMenu: 'knowledge',
        },
      },
    ],
  },
];
