import { Conversation, Message } from '@/typings/chat';
import { AiApp } from '@/api/models/flow';

export interface ChatState {
  isEdit: string; //当前编辑的id
  active: string; //当前激活的id
  siderCollapsed: boolean; //侧边栏展开状态
  sideIsLoading: boolean; //侧边栏加载状态
  chatIsLoading: boolean; //会话窗口加载状态
  conversations: Conversation[]; //左侧会话窗口列表
  curConversation: Conversation | undefined; //当前选中的会话窗口
  messages: Message[]; //当前选中的消息内容
  apps: AiApp[]; //App应用列表
}
