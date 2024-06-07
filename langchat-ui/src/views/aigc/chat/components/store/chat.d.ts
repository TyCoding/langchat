export interface ChatState {
  messages: any[];
  model: string;
  conversationId: string | null;
  docsId: string | null;
  prompt: any;
  knowledge: any;
  isGoogleSearch: boolean;
  selectExcelId: any;
  selectExcelData: any;
}
