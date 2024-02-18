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
        path: '/translate',
        name: 'Translate',
        meta: {
          label: t('menu.translate'),
          icon: 'iconoir:translate',
        },
        component: () => import('@/views/modules/translate/index.vue'),
      },
      {
        path: '/chart',
        name: 'Chart',
        meta: {
          label: t('menu.chart'),
          icon: 'fluent:data-area-24-regular',
        },
        component: () => import('@/views/modules/chart/index.vue'),
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
    path: '/user',
    component: Layout,
    children: [
      {
        path: 'index',
        name: 'User',
        meta: {
          label: t('menu.user'),
        },
        component: () => import('@/views/user/index.vue'),
      },
    ],
  },
  {
    path: '/prompt',
    component: Layout,
    children: [
      {
        path: 'index',
        name: 'Prompt',
        meta: {
          label: t('menu.user'),
        },
        component: () => import('@/views/prompt/index.vue'),
      },
    ],
  },

  {
    path: '/login',
    name: 'Login',
    component: () => import('@/views/login/index.vue'),
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
