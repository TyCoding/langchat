import type { Router } from 'vue-router';
import { useAuthStoreWithout } from '@/store/modules/auth';

export function setupPageGuard(router: Router) {
  console.log('路由开始');
  router.beforeEach(async (to, from, next) => {
    console.log('校验路由', to.path);
    next();
    return;
    const authStore = useAuthStoreWithout();
    if (!authStore.session) {
      try {
        const data = (await authStore.getSession()) as any;
        if (String(data.auth) === 'false' && authStore.token) authStore.removeToken();
        if (to.path === '/500') next({ name: 'Root' });
        else next();
      } catch (error) {
        if (to.path !== '/500') next({ name: '500' });
        else next();
      }
    } else {
      next();
    }
  });
  console.log('路由结束');
}
