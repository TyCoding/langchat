/*
 * Copyright (c) 2024 LangChat. TyCoding All Rights Reserved.
 *
 * Licensed under the GNU Affero General Public License, Version 3 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     https://www.gnu.org/licenses/agpl-3.0.html
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

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
    component: 'Layout',
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
    component: 'Layout',
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
    component: 'Layout',
    show: false,
    meta: {
      title: '应用集成',
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
      {
        path: 'knowledge/info',
        name: 'knowledge_info_index',
        component: '/aigc/knowledge/components/index',
        show: false,
        meta: {
          title: '知识库数据',
        },
      },
      {
        path: 'app/channel/api/:id?',
        name: 'API_CHANNEL',
        component: '/channel/api/index',
        show: false,
        meta: {
          title: 'API渠道',
        },
      },
      {
        path: 'app/channel/web/:id?',
        name: 'WEB_CHANNEL',
        component: '/channel/web/index',
        show: false,
        meta: {
          title: 'Web渠道',
        },
      },
      {
        path: 'app/weixin/:id?',
        name: 'WEIXIN_CHANNEL',
        component: '/app/weixin/index',
        show: false,
        meta: {
          title: '微信渠道',
        },
      },
      {
        path: 'app/info/:id?',
        name: 'appInfo',
        component: '/app/info',
        show: false,
        meta: {
          title: '应用配置',
        },
      },
    ],
  },
];
