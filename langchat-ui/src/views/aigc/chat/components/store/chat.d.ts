export interface ChatState {
  messages: any[];
  modelId: string;
  modelName: string;
  modelProvider: string;
  conversationId: string | null;
  docsId: string | null;
  prompt: any;
  knowledge: any;
  isGoogleSearch: boolean;
  selectExcelId: any;
  selectExcelData: any;
}
