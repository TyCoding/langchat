import { defineStore } from 'pinia';
import { formatToDateTime } from '@/utils/dateUtil';

export interface ChatState {
  messages: any[];
}

export const useChatStore = defineStore('chat-store', {
  state: (): ChatState =>
    <ChatState>{
      messages: [],
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
