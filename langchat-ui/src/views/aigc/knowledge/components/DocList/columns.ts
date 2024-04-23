import { BasicColumn } from '@/components/Table';
import { FormSchema } from '@/components/Form';
import { KbDoc } from '@/api/models/flow';
import { h } from 'vue';
import { formatToDate } from '@/utils/dateUtil';

export const columns: BasicColumn<KbDoc>[] = [
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
    width: 120,
    render(row: KbDoc) {
      return h('span', {}, formatToDate(new Date(Number(row.createTime))));
    },
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

export const formSchemas: FormSchema[] = [
  {
    field: 'id',
    label: 'ID',
    component: 'NInput',
    isHidden: true,
  },
  {
    field: 'kbId',
    label: 'KbId',
    component: 'NInput',
    isHidden: true,
  },
  {
    field: 'des',
    component: 'NInput',
    label: '文档描述',
    componentProps: {
      placeholder: '请输入文档描述',
      type: 'textarea',
      autosize: {
        minRows: 2,
        maxRows: 4,
      },
    },
    rules: [{ required: true, message: '请输入文档描述', trigger: ['blur'] }],
  },
  {
    field: 'content',
    component: 'NInput',
    label: '文档内容',
    componentProps: {
      placeholder: '请输入文档内容',
      type: 'textarea',
      autosize: {
        minRows: 12,
        maxRows: 20,
      },
    },
    rules: [{ required: true, message: '请输入文档内容', trigger: ['blur'] }],
  },
];
