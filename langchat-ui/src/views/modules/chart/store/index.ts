import { defineStore } from 'pinia';

export interface ChartState {
  step: number;
  key: string;
  data: Object;
}

export const useChartStore = defineStore({
  id: 'flow-store',
  state: (): ChartState => ({
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
