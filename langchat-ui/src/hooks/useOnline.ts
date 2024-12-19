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

/**
 * @description 用户网络是否可用
 * */
export function useOnline() {
  const online = ref(true);

  const showStatus = (val) => {
    online.value = typeof val == 'boolean' ? val : val.target.online;
  };

  // 在页面加载后，设置正确的网络状态
  navigator.onLine ? showStatus(true) : showStatus(false);

  onMounted(() => {
    // 开始监听网络状态的变化
    window.addEventListener('online', showStatus);

    window.addEventListener('offline', showStatus);
  });
  onUnmounted(() => {
    // 移除监听网络状态的变化
    window.removeEventListener('online', showStatus);

    window.removeEventListener('offline', showStatus);
  });

  return { online };
}
