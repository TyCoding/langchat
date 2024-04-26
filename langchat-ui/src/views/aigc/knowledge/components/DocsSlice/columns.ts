import { BasicColumn } from '@/components/Table';
import { FormSchema } from '@/components/Form';
import { h } from 'vue';
import { NTag } from 'naive-ui';

export const columns: BasicColumn[] = [
  {
    title: '文档名称',
    key: 'name',
    width: 150,
  },
  {
    title: '字符数',
    key: 'wordNum',
    width: 110,
    align: 'center',
  },
  {
    title: '切片内容',
    key: 'content',
  },
  {
    title: '切片状态',
    key: 'status',
    width: 100,
    align: 'center',
    render(row) {
      return h(
        NTag,
        {
          size: 'small',
          type: row.status == true ? 'success' : 'info',
        },
        {
          default: () => (row.status == true ? '已训练' : '未训练'),
        }
      );
    },
  },
  {
    title: '创建时间',
    key: 'createTime',
    width: 160,
  },
];

export const searchSchemas: FormSchema[] = [
  {
    field: 'docsId',
    component: 'NInput',
    label: '文档',
    slot: 'docsSlot',
    componentProps: {
      placeholder: '请选择文档',
    },
  },
];
