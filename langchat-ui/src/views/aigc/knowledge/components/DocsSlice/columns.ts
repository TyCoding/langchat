import { BasicColumn } from '@/components/Table';
import { FormSchema } from '@/components/Form';

export const columns: BasicColumn[] = [
  {
    title: '文档描述',
    key: 'des',
  },
  {
    title: '文档内容',
    key: 'content',
  },
  {
    title: '创建时间',
    key: 'createTime',
    width: 160,
  },
];

export const searchSchemas: FormSchema[] = [
  {
    field: 'des',
    component: 'NInput',
    label: '文档描述',
    componentProps: {
      placeholder: '请输入文档描述',
    },
  },
];
