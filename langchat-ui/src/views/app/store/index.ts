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
import { update } from '@/api/app/app';

export interface AppState {
  info: any;
  modelId: string | null;
  knowledgeIds: any[];
  knowledges: any[];
}

export const useAppStore = defineStore('app-store', {
  state: (): AppState =>
    <AppState>{
      info: {},
      modelId: '',
      knowledgeIds: [],
      knowledges: [],
    },

  getters: {},

  actions: {
    addKnowledge(item: any) {
      this.knowledgeIds.push(item.id);
      this.knowledges.push(item);
      this.updateInfo();
    },

    removeKnowledge(item: any) {
      this.knowledgeIds = this.knowledgeIds.filter((i) => i !== item.id);
      this.knowledges = this.knowledges.filter((i) => i.id !== item.id);
      this.updateInfo();
    },

    async updateInfo() {
      this.info.modelId = this.modelId;
      this.info.knowledgeIds = this.knowledgeIds;
      this.info.knowledges = this.knowledges;
      await update({ ...this.info });
    },
  },
});
