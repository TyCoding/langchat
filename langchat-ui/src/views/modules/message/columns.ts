import { BasicColumn } from '@/components/Table';
import { FormSchema } from '@/components/Form';
import { Conversation } from '@/api/models/flow';
import { h } from 'vue';
import { formatToDate } from '@/utils/dateUtil';

export const columns: BasicColumn<Conversation>[] = [
  {
    title: '应用名称',
    key: 'appName',
    align: 'center',
  },
  {
    title: '窗口标题',
    key: 'title',
    align: 'center',
  },
  {
    title: '对话模型',
    key: 'chatModel',
    align: 'center',
    width: 180,
  },
  {
    title: '创建时间',
    key: 'createTime',
    width: 140,
    align: 'center',
    render(row: Conversation) {
      return h('span', {}, formatToDate(new Date(Number(row.createTime))));
    },
  },
];

export const searchSchemas: FormSchema[] = [
  {
    field: 'text',
    component: 'NInput',
    label: '内容',
    componentProps: {
      placeholder: '请输入内容',
    },
  },
];
