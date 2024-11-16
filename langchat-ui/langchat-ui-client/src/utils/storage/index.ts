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

interface StorageData<T = any> {
  data: T;
  expire: number | null;
}

export function createLocalStorage(options?: { expire?: number | null }) {
  const DEFAULT_CACHE_TIME = 60 * 60 * 24 * 7;

  const { expire } = Object.assign({ expire: DEFAULT_CACHE_TIME }, options);

  function set<T = any>(key: string, data: T) {
    const storageData: StorageData<T> = {
      data,
      expire: expire !== null ? new Date().getTime() + expire * 1000 : null,
    };

    const json = JSON.stringify(storageData);
    window.localStorage.setItem(key, json);
  }

  function get(key: string) {
    const json = window.localStorage.getItem(key);
    if (json) {
      let storageData: StorageData | null = null;

      try {
        storageData = JSON.parse(json);
      } catch {
        // Prevent failure
      }

      if (storageData) {
        const { data, expire } = storageData;
        if (expire === null || expire >= Date.now()) return data;
      }

      remove(key);
      return null;
    }
  }

  function remove(key: string) {
    window.localStorage.removeItem(key);
  }

  function clear() {
    window.localStorage.clear();
  }

  return { set, get, remove, clear };
}

export const storage = createLocalStorage();
