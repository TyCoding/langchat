import { BasicColumn } from '@/components/Table';
import { Token } from '@/api/models/auth';
import { h } from 'vue';

export const columns: BasicColumn<Token>[] = [
  {
    title: '令牌',
    key: 'value',
    align: 'center',
  },
  {
    title: '账户',
    key: 'username',
    align: 'center',
    render: (row) => {
      return h('span', row.principal.username);
    },
  },
  {
    title: '过期时间',
    key: 'expiration',
    align: 'center',
  },
];
