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
import { getBotPage as getAppInfo } from '@/api/prompt';

export const useChatStore = defineStore('chat-store', {
  state: (): ChatState =>
    <ChatState>{
      active: '',
      isEdit: '',
      siderCollapsed: true,
      sideIsLoading: true,
      chatIsLoading: true,
      conversations: [],
      curConversation: undefined,
      messages: [],
      apps: [],
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
          }
          this.conversations = data;
        } else {
          this.conversations = [];
          // 设置一个默认的App应用
          if (this.apps.length > 0) {
            const app = this.apps[0];
            if (app.id != undefined) {
              const appInfo = await getAppInfo(app.id);
              this.setConversation(<Conversation>appInfo);
            }
          }
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
      await this.setActive(params.id);
      await this.setEdit('');
      this.curConversation = params;
      this.messages = await getMessages(params.id);
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
      await updateConversations(params);
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
      this.messages = [];
    },

    /**
     * 新增消息
     */
    async addMessage(
      message: string,
      role: 'user' | 'assistant' | 'system',
      promptId: string,
      parentRefId: string
    ): Promise<boolean> {
      const conversation = this.curConversation;
      if (conversation != null) {
        this.messages.push({
          promptId: promptId,
          parentRefId: parentRefId,
          conversationId: conversation.id,
          appId: conversation.appId,
          chatModel: conversation.chatModel,
          role: role,
          content: message,
          createTime: formatToDateTime(new Date()),
        });

        // 判断如果当前conversation没有在会话列表中，就加载一次会话列表（对于初次会话）
        if (this.conversations.length == 0) {
          await this.loadData();
        } else if (role == 'user') {
          // await this.selectConversation(conversation);
        }
        return true;
      }
      return false;
    },

    /**
     * 更新消息
     * promptId 仅仅用于更新流式消息内容
     */
    async updateMessage(promptId: string, content: string, isError?: boolean) {
      const promptIndex = this.messages.findIndex((item) => item?.promptId == promptId);
      if (promptIndex !== -1) {
        this.messages[promptIndex].content = content;
        this.messages[promptIndex].isError = isError;
      }
    },

    /**
     * 删除消息
     */
    async delMessage(item: Message) {
      this.messages = this.messages.filter((i) => i.promptId !== item.promptId);
    },
  },
});
