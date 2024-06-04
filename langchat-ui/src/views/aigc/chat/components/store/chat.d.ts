export interface ChatState {
  messages: any[];
  model: string;
  conversationId: string | null;
  prompt: any;
  knowledge: any;
}
