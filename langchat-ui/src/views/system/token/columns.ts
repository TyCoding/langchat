import { BasicColumn } from '@/components/Table';

export const columns: BasicColumn<any>[] = [
  {
    title: '令牌',
    key: 'token',
    align: 'center',
  },
  {
    title: '账户',
    key: 'username',
    align: 'center',
  },
  {
    title: '姓名',
    key: 'realName',
    align: 'center',
  },
  {
    title: '过期时间',
    key: 'expiration',
    align: 'center',
  },
];
