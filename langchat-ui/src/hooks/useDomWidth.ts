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

import { onMounted, onUnmounted, ref } from 'vue';
import { debounce } from 'lodash-es';

/**
 * description: 获取页面宽度
 */

export function useDomWidth() {
  const domWidth = ref(window.innerWidth);

  function resize() {
    domWidth.value = document.body.clientWidth;
  }

  onMounted(() => {
    window.addEventListener('resize', debounce(resize, 80));
  });
  onUnmounted(() => {
    window.removeEventListener('resize', resize);
  });

  return domWidth;
}
