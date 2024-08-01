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

import { ObjectDirective } from 'vue';
import { usePermission } from '@/hooks/web/usePermission';

export const permission: ObjectDirective = {
  mounted(el: HTMLButtonElement, binding) {
    if (binding.value == undefined) return;
    const { action, effect } = binding.value;
    const { hasPermission } = usePermission();
    if (!hasPermission(action)) {
      if (effect == 'disabled') {
        el.disabled = true;
        el.style['disabled'] = 'disabled';
        el.classList.add('n-button--disabled');
      } else {
        el.remove();
      }
    }
  },
};
