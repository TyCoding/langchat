export interface ChatState {
  modelId?: string;
  modelName?: string;
  modelProvider?: string;
  isGoogleSearch?: boolean;
  isEdit: string; //当前编辑的id
  active: string; //当前激活的id
  siderCollapsed: boolean; //侧边栏展开状态
  sideIsLoading: boolean; //侧边栏加载状态
  chatIsLoading: boolean; //会话窗口加载状态
  conversations: any[]; //左侧会话窗口列表
  curConversation: any; //当前选中的会话窗口
  messages: any[]; //当前选中的消息内容
  prompts: any[];
  selectPromptId: any;
}
