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

import type { App } from 'vue';
import type { RouteRecordRaw } from 'vue-router';
import { createRouter, createWebHashHistory } from 'vue-router';
import { Layout } from '@/layout';
import { setupPageGuard } from '@/router/permission';
import { t } from '@/locales';

const routes: RouteRecordRaw[] = [
  {
    path: '/',
    name: 'Root',
    component: Layout,
    redirect: '/home',
    children: [
      {
        path: '/home',
        name: 'Home',
        meta: {
          label: t('menu.home'),
          icon: 'tabler:smart-home',
        },
        component: () => import('@/views/modules/home/index.vue'),
      },
      {
        path: '/chat/:uuid?',
        name: 'Chat',
        meta: {
          label: t('menu.chat'),
          icon: 'bx:chat',
        },
        component: () => import('@/views/modules/chat/index.vue'),
      },
      {
        path: '/doc',
        name: 'Doc',
        meta: {
          label: t('menu.doc'),
          icon: 'mingcute:doc-line',
        },
        component: () => import('@/views/modules/doc/index.vue'),
      },
      {
        path: '/write',
        name: 'Write',
        meta: {
          label: t('menu.write'),
          icon: 'solar:document-add-linear',
        },
        component: () => import('@/views/modules/write/index.vue'),
      },
      {
        path: '/image',
        name: 'Image',
        meta: {
          label: t('menu.image'),
          icon: 'radix-icons:image',
        },
        component: () => import('@/views/modules/image/index.vue'),
      },
      {
        path: '/mindmap',
        name: 'MindMap',
        meta: {
          label: t('menu.mindmap'),
          icon: 'ri:mind-map',
        },
        component: () => import('@/views/modules/mindmap/index.vue'),
      },
    ],
  },
];

const baseRoutes: RouteRecordRaw[] = [
  {
    path: '/profile',
    component: Layout,
    children: [
      {
        path: 'index',
        name: 'Profile',
        meta: {
          label: t('menu.user'),
        },
        component: () => import('@/views/profile/index.vue'),
      },
    ],
  },

  {
    path: '/login',
    name: 'Login',
    component: () => import('@/views/login/index.vue'),
  },
  {
    path: '/forget',
    name: 'Forget',
    component: () => import('@/views/login/Forget.vue'),
  },

  {
    path: '/404',
    name: '404',
    component: () => import('@/views/exception/404/index.vue'),
  },

  {
    path: '/500',
    name: '500',
    component: () => import('@/views/exception/500/index.vue'),
  },

  {
    path: '/:pathMatch(.*)*',
    name: 'notFound',
    redirect: '/404',
  },
];

export const routesConst: RouteRecordRaw[] = routes.flatMap((route) => route.children ?? []);

export const router = createRouter({
  history: createWebHashHistory(),
  routes: [...baseRoutes, ...routes],
  scrollBehavior: () => ({ left: 0, top: 0 }),
});

export async function setupRouter(app: App) {
  app.use(router);
  // await router.isReady()
  setupPageGuard(router);
}
