import { BasicColumn } from '@/components/Table';
import { FormSchema } from '@/components/Form';

export const columns: BasicColumn[] = [
  {
    title: '用户名',
    key: 'username',
    align: 'center',
  },
  {
    title: '模型名称',
    key: 'model',
    align: 'center',
  },
  {
    title: 'Tokens',
    key: 'tokens',
    align: 'center',
  },
  {
    title: 'Prompt Tokens',
    key: 'promptTokens',
    align: 'center',
  },
  {
    title: 'Prompt Tokens',
    key: 'promptTokens',
    align: 'center',
  },
  {
    title: 'IP地址',
    key: 'ip',
    align: 'center',
  },
  {
    title: '调用时间',
    key: 'createTime',
    align: 'center',
    width: 180,
  },
];

export const searchSchemas: FormSchema[] = [
  {
    field: 'name',
    component: 'NInput',
    label: '用户名',
    componentProps: {
      placeholder: '请输入用户名查询',
    },
  },
];
