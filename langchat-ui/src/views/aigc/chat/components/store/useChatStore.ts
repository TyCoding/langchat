import { defineStore } from 'pinia';
import { ChatState } from './chat';
import { formatToDateTime } from '@/utils/dateUtil';
import { getMessages } from '@/api/aigc/conversation';

export const useChatStore = defineStore('chat-store', {
  state: (): ChatState =>
    <ChatState>{
      active: '',
      isEdit: '',
      siderCollapsed: true,
      sideIsLoading: true,
      chatIsLoading: true,
      messages: [],
    },

  getters: {},

  actions: {
    setSiderCollapsed(collapsed: boolean) {
      this.siderCollapsed = collapsed;
    },
    async setActive(id: string) {
      this.active = id;
    },
    async setEdit(id: string) {
      this.isEdit = id;
    },

    /**
     * 加载会话窗口和聊天信息
     */
    async loadData() {
      try {
      } finally {
        this.sideIsLoading = false;
        this.chatIsLoading = false;
      }
    },

    /**
     * 选择会话窗口
     */
    async selectConversation(params: any) {
      if (params.id == undefined) {
        return;
      }
      console.log('set', params.id);
      await this.setActive(params.id);
      await this.setEdit('');
      this.messages = await getMessages(params.id);
      console.log(this.messages);
    },

    /**
     * 新增消息
     */
    async addMessage(
      message: string,
      role: 'user' | 'assistant' | 'system',
      promptId: string
    ): Promise<boolean> {
      this.messages.push({
        promptId: promptId,
        role: role,
        message: message,
        createTime: formatToDateTime(new Date()),
      });
      return true;
    },

    /**
     * 更新消息
     * promptId 仅仅用于更新流式消息内容
     */
    async updateMessage(promptId: string, message: string, isError?: boolean) {
      const promptIndex = this.messages.findIndex((item) => item?.promptId == promptId);
      if (promptIndex !== -1) {
        this.messages[promptIndex].message = message;
        this.messages[promptIndex].isError = isError;
      }
      console.log(this.messages);
    },

    /**
     * 删除消息
     */
    async delMessage(item: any) {
      this.messages = this.messages.filter((i) => i.promptId !== item.promptId);
    },
  },
});
