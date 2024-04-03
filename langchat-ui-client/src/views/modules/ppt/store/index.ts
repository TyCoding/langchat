import { defineStore } from 'pinia';

export interface PptState {
  step: number;
  key: string;
  data: Object;
}

export const usePptStore = defineStore({
  id: 'ppt-store',
  state: (): PptState => ({
    step: 0,
    key: '',
    data: {},
  }),

  actions: {
    setStep(step: number) {
      this.step = step;
    },
    setKey(key: string) {
      this.key = key;
    },
    setData(data: Object) {
      this.data = data;
    },
  },
});
