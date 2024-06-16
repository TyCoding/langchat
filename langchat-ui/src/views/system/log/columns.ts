import { BasicColumn } from '@/components/Table';
import { h } from 'vue';
import { NTag } from 'naive-ui';

export const columns: BasicColumn<any>[] = [
  {
    title: '操作用户',
    key: 'username',
    align: 'center',
  },
  {
    title: '操作状态',
    key: 'type',
    align: 'center',
    width: 80,
    render(row) {
      return h(
        NTag,
        {
          size: 'small',
          type: row.type ? 'success' : 'error',
        },
        {
          default: () => (row.type ? '正常' : '异常'),
        }
      );
    },
  },
  {
    title: '操作描述',
    key: 'operation',
    align: 'center',
  },
  {
    title: '请求URL',
    key: 'url',
    align: 'center',
  },
  {
    title: '耗时/毫秒',
    key: 'time',
    align: 'center',
    width: 100,
  },
  {
    title: '操作方法',
    key: 'method',
    align: 'center',
  },
  {
    title: 'IP地址',
    key: 'ip',
    align: 'center',
  },
  {
    title: '操作时间',
    width: 180,
    align: 'center',
    key: 'createTime',
  },
];
