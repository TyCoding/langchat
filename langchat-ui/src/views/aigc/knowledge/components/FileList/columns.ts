import { BasicColumn } from '@/components/Table';
import { FormSchema } from '@/components/Form';
import { KbFile } from '@/api/models/flow';
import { h } from 'vue';

export const columns: BasicColumn<KbFile>[] = [
  {
    title: '文件名称',
    key: 'fileName',
  },
  {
    title: '文件描述',
    key: 'des',
  },
  {
    title: '大小/mb',
    key: 'size',
    align: 'center',
    width: 100,
    render(row) {
      const mb = Number(row.size) / (1024 * 1024);
      return h('span', {}, { default: () => Math.floor(mb * 100) / 100 });
    },
  },
  {
    title: '类型',
    key: 'type',
    align: 'center',
    width: 90,
  },
  {
    title: '来源',
    key: 'source',
    align: 'center',
    width: 100,
  },
];

export const searchSchemas: FormSchema[] = [
  {
    field: 'fileName',
    component: 'NInput',
    label: '文件名称',
    componentProps: {
      placeholder: '请输入文件名称',
    },
  },
];
