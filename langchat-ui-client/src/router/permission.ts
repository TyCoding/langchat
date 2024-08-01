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

import type { Router } from 'vue-router';
import { useAuthStoreWithout } from '@/store/modules/auth';

export function setupPageGuard(router: Router) {
  router.beforeEach(async (to, from, next) => {
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
}
