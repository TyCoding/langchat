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

import { defineStore } from 'pinia';
import { IS_SCREENLOCKED } from '@/store/mutation-types';
import { storage } from '@/utils/Storage';

// 长时间不操作默认锁屏时间
const initTime = 60 * 60;

const isLocked = storage.get(IS_SCREENLOCKED, false);

export type IScreenLockState = {
  isLocked: boolean; // 是否锁屏
  lockTime: number;
};

export const useScreenLockStore = defineStore({
  id: 'app-screen-lock',
  state: (): IScreenLockState => ({
    isLocked: isLocked === true, // 是否锁屏
    lockTime: isLocked == 'true' ? initTime : 0,
  }),
  getters: {},
  actions: {
    setLock(payload: boolean) {
      this.isLocked = payload;
      storage.set(IS_SCREENLOCKED, this.isLocked);
    },
    setLockTime(payload = initTime) {
      this.lockTime = payload;
    },
  },
});
