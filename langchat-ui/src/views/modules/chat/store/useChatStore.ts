import { defineStore } from 'pinia';
import {
  list as getConversations,
  add as addConversations,
  update as updateConversations,
  del as delConversations,
  getMessages,
} from '@/api/conversation';
import { ChatState } from './chat';
import { formatToDateTime } from '@/utils/dateUtil';
import { Conversation, Message } from '@/typings/chat';
import { router } from '@/router';
import { toRaw } from 'vue';

export const useChatStore = defineStore('chat-store', {
  state: (): ChatState =>
    <ChatState>{
      model: 'gpt-3',
      active: '',
      isEdit: '',
      siderCollapsed: true,
      sideIsLoading: true,
      chatIsLoading: true,
      conversations: [],
      curConversation: undefined,
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
        // 加载conversation列表
        const data = await getConversations({});
        if (data && data.length > 0) {
          if (!this.active) {
            this.active = data[0].id;
            this.curConversation = data[0];
            await this.selectConversation(data[0]);
            await this.selectPath(data[0].id);
          }
          this.conversations = data;
        } else {
          this.conversations = [];
          await this.selectPath(undefined);
        }
      } finally {
        this.sideIsLoading = false;
        this.chatIsLoading = false;
      }
    },

    async selectPath(id: string | undefined) {
      const chatPath = '/' + router.currentRoute.value.path.split('/')[1];
      if (id) {
        return router.replace(chatPath + '/' + id);
      }
      return router.replace(chatPath);
    },

    /**
     * 设置当前会话窗口数据
     */
    setConversation(params: Conversation) {
      this.curConversation = params;
    },

    /**
     * 选择会话窗口
     */
    async selectConversation(params: Conversation) {
      if (params.id == undefined) {
        return;
      }
      await this.setActive(params.id);
      await this.setEdit('');
      this.curConversation = params;
      this.messages = await getMessages(params.id);
      await this.selectPath(params.id);
    },

    /**
     * 添加会话窗口
     */
    async addConversation(params: Partial<Conversation>) {
      await addConversations(params);
      await this.loadData();
    },

    /**
     * 更新会话信息
     */
    async updateConversation(params: Partial<Conversation>) {
      await updateConversations(toRaw(params));
      await this.setEdit('');
      await this.loadData();
    },

    /**
     * 删除会话窗口
     */
    async delConversation(id: string) {
      await delConversations(id);
      await this.setActive('');
      await this.loadData();
    },

    /**
     * 新增消息
     */
    async addMessage(message: string, role: 'user' | 'assistant' | 'system', chatId: string) {
      const data = {
        chatId,
        conversationId: this.curConversation?.id,
        role: role,
        content: message,
        createTime: formatToDateTime(new Date()),
      };
      this.messages.push(data);
      return data;
    },

    /**
     * 更新消息
     */
    async updateMessage(chatId: string | undefined, content: string, isError?: boolean) {
      const index = this.messages.findIndex((item) => item?.chatId == chatId);
      if (index !== -1) {
        this.messages[index].content = content;
        this.messages[index].isError = isError;
      }
    },

    /**
     * 删除消息
     */
    async delMessage(item: Message) {
      this.messages = this.messages.filter((i) => i.chatId !== item.chatId);
    },
  },
});
