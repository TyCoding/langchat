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
import { formatToDateTime } from '@/utils/dateUtil';
import { ChatState } from '@/views/aigc/chat/components/store/chat';

export const useChatStore = defineStore('chat-store', {
  state: (): ChatState =>
    <ChatState>{
      modelId: '',
      modelName: '',
      modelProvider: '',
      conversationId: null,
      docsId: null,
      messages: [],
      prompt: null,
      knowledge: null,
      isGoogleSearch: false,
      selectExcelId: undefined,
      selectExcelData: {},
    },

  getters: {},

  actions: {
    /**
     * 新增消息
     */
    async addMessage(
      message: string,
      role: 'user' | 'assistant' | 'system',
      chatId: string
    ): Promise<boolean> {
      this.messages.push({
        chatId,
        role: role,
        message: message,
        createTime: formatToDateTime(new Date()),
      });
      return true;
    },

    /**
     * 更新消息
     * chatId 仅仅用于更新流式消息内容
     */
    async updateMessage(chatId: string, message: string, isError?: boolean) {
      const index = this.messages.findIndex((item) => item?.chatId == chatId);
      if (index !== -1) {
        this.messages[index].message = message;
        this.messages[index].isError = isError;
      }
    },

    /**
     * 删除消息
     */
    async delMessage(item: any) {
      this.messages = this.messages.filter((i) => i.promptId !== item.promptId);
    },
  },
});
