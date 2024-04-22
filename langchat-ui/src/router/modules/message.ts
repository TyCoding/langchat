import { RouteRecordRaw } from 'vue-router';
import { Layout } from '@/router/constant';
import { ChatbubblesOutline } from '@vicons/ionicons5';
import { renderIcon } from '@/utils/index';

const routeName = 'message';

/**
 * @param name 路由名称, 必须设置,且不能重名
 * @param meta 路由元信息（路由附带扩展信息）
 * @param redirect 重定向地址, 访问这个路由时,自定进行重定向
 * @param meta.disabled 禁用整个菜单
 * @param meta.title 菜单名称
 * @param meta.icon 菜单图标
 * @param meta.keepAlive 缓存该路由
 * @param meta.sort 排序越小越排前
 * */
const routes: Array<RouteRecordRaw> = [
  {
    path: `/${routeName}`,
    name: routeName,
    redirect: `/${routeName}/index`,
    component: Layout,
    meta: {
      icon: renderIcon(ChatbubblesOutline),
      sort: 0,
    },
    children: [
      {
        path: 'index',
        name: `${routeName}_index`,
        meta: {
          title: '消息管理',
        },
        component: () => import('@/views/aigc/message/index.vue'),
      },
    ],
  },
];

export default routes;
