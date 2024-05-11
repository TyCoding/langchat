import { BasicColumn } from '@/components/Table';
import { FormSchema } from '@/components/Form';

export const columns: BasicColumn[] = [
  {
    title: '名称',
    key: 'name',
    width: 300,
  },
  {
    title: '提示词',
    key: 'prompt',
  },
  {
    title: '创建时间',
    key: 'createTime',
    align: 'center',
    width: 160,
  },
];

export const searchSchemas: FormSchema[] = [
  {
    field: 'title',
    component: 'NInput',
    label: '标题',
    componentProps: {
      placeholder: '请输入Prompt标题查询',
    },
  },
];

export const formSchemas: FormSchema[] = [
  {
    field: 'id',
    label: 'ID',
    component: 'NInput',
    isHidden: true,
  },
  {
    field: 'name',
    label: '标题',
    component: 'NInput',
    rules: [{ required: true, message: '请输入标题', trigger: ['blur'] }],
  },
  {
    field: 'icon',
    label: '图标',
    component: 'NInput',
  },
  {
    field: 'prompt',
    component: 'NInput',
    label: '提示词',
    isFull: true,
    componentProps: {
      isFull: true,
      placeholder: '请输入提示词',
      type: 'textarea',
      autosize: {
        minRows: 20,
      },
    },
    rules: [{ required: true, message: '请输入Prompt', trigger: ['blur'] }],
  },
];
