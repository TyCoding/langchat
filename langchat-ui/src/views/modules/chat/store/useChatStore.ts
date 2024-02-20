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
        } else {
          this.conversations = [];
        }
      } finally {
        this.sideIsLoading = false;
        this.chatIsLoading = false;
      }
    },

    async selectPath(conversationId: string | undefined, promptId: string | undefined) {
      const query: any = {};
      const cur = router.currentRoute.value;
      if (conversationId !== undefined) {
        query.conversationId = conversationId;
      }
      if (promptId !== undefined) {
        query.promptId = promptId;
      }
      if (cur.query.promptId !== undefined) {
        query.promptId = cur.query.promptId;
      }

      await router.replace({ path: router.currentRoute.value.path, query });
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
      await this.selectPath(params.id, undefined);
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
