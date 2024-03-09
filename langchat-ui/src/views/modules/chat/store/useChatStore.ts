import { defineStore } from 'pinia';
import {
  add as addConversations,
  del as delConversations,
  getMessages,
  list as getConversations,
  update as updateConversations,
} from '@/api/conversation';
import { ChatState } from './chat';
import { formatToDateTime } from '@/utils/dateUtil';
import { Conversation, Message } from '@/typings/chat';
import { router } from '@/router';
import { toRaw } from 'vue';

export const useChatStore = defineStore('chat-store', {
  state: (): ChatState =>
    <ChatState>{
      model: '',
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
     * init conversation list and messages
     */
    async loadData() {
      try {
        const data = await getConversations({});
        const conversationId = router.currentRoute.value.query.conversationId as string;
        if (conversationId !== undefined && conversationId !== null) {
          this.active = conversationId;
          this.messages = await getMessages(conversationId);
        }
        if (data && data.length > 0) {
          this.conversations = data;
          this.curConversation = data[0];
        } else {
          this.active = '';
          this.conversations = [];
          await router.replace({ path: router.currentRoute.value.path, query: {} });
        }
      } finally {
        this.sideIsLoading = false;
        this.chatIsLoading = false;
      }
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
      if (this.active !== '') {
        this.messages = await getMessages(params.id);
      }
      await this.setActive(params.id);
      await this.setEdit('');
      this.curConversation = params;

      await this.replaceUrl();
    },

    async replaceUrl() {
      if (this.curConversation == undefined) {
        return;
      }
      const { id, promptId } = this.curConversation;
      // replace url path
      const query: any = { conversationId: id };
      if (promptId) {
        query.promptId = promptId;
      }
      await router.replace({ path: router.currentRoute.value.path, query });
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
        message,
        model: this.model,
        createTime: formatToDateTime(new Date()),
      };
      this.messages.push(data);
      return data;
    },

    /**
     * 更新消息
     */
    async updateMessage(chatId: string | undefined, message: string, isError?: boolean) {
      const index = this.messages.findIndex((item) => item?.chatId == chatId);
      if (index !== -1) {
        this.messages[index].message = message;
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
