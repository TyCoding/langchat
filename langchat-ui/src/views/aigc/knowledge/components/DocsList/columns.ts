import { BasicColumn } from '@/components/Table';
import { FormSchema } from '@/components/Form';

export const columns: BasicColumn[] = [
  {
    title: '文档名称',
    key: 'name',
  },
  {
    title: '文档内容/链接',
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
    field: 'name',
    component: 'NInput',
    label: '文档名称',
    componentProps: {
      placeholder: '请输入文档名称',
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
    component: 'NInput',
    label: '文档名称',
    componentProps: {
      placeholder: '请输入文档名称',
    },
    rules: [{ required: true, message: '请输入文档名称', trigger: ['blur'] }],
  },
  {
    field: 'content',
    component: 'NInput',
    label: '文档内容',
    componentProps: {
      placeholder: '请输入文档内容',
      type: 'textarea',
      autosize: {
        minRows: 8,
        maxRows: 12,
      },
    },
    rules: [{ required: true, message: '请输入文档内容', trigger: ['blur'] }],
  },
];
