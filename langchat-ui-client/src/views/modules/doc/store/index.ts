import { defineStore } from 'pinia';
import { toRaw } from 'vue';
import { getMessages } from '@/api/conversation';

export interface DocState {
  file: any;
  messages:
    | {
        id: string;
        list: any[];
      }[]
    | any[];
}

export const useDocStore = defineStore({
  id: 'doc-store',
  state: (): DocState => ({
    file: {},
    messages: [],
  }),

  actions: {
    async onSelect(item: any) {
      // get messages
      this.messages = await getMessages(item.id);
      this.file = item;
    },
    addMessage(item: any) {
      const list = this.messages.filter((i) => i.id == this.file.id);
      if (list.length > 0) {
        list[0].list.push(toRaw(item));
      } else {
        this.messages.push({
          id: this.file.id,
          list: [toRaw(item)],
        });
      }
    },
  },
});
