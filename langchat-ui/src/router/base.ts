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
    path: '/profile',
    name: 'profile',
    component: 'LAYOUT',
    show: false,
    meta: {
      title: '个人中心',
    },
    children: [
      {
        path: 'index',
        name: 'profile_index',
        component: '/base/profile/index',
        show: false,
        meta: {
          title: '个人中心',
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
        },
      },
    ],
  },

  {
    path: '/workflow',
    name: 'flow',
    component: 'LAYOUT',
    show: false,
    meta: {
      title: '流程编辑',
    },
    children: [
      {
        path: 'flow/:id/initialize',
        name: 'flow_initialize',
        component: '/flow/initialize/FlowTemplate',
        show: false,
        meta: {
          title: '流程初始化',
        },
      },
      {
        path: 'flow/:id/edit',
        name: 'flow_edit',
        component: '/flow/layout/Layout',
        show: false,
        meta: {
          title: '流程编辑',
          activeMenu: '流程编排',
        },
      },
    ],
  },
];
