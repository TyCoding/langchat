import { defineStore } from 'pinia';
import { Oss } from '@/api/models';
import { toRaw } from 'vue';

export interface DocState {
  file: Oss | any;
  messages:
    | {
        id: string;
        list: any[];
      }[]
    | any[];
  curMessage: any[];
}

export const useDocStore = defineStore({
  id: 'doc-store',
  state: (): DocState => ({
    file: {},
    messages: [],
    curMessage: [],
  }),

  actions: {
    onSelect(item: Oss) {
      this.file = item;
      const list = this.messages.filter((i) => i.id == this.file.id);
      if (list.length > 0) {
        this.curMessage = list[0].list;
      } else {
        this.curMessage = [];
      }
      console.log(this.curMessage);
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
