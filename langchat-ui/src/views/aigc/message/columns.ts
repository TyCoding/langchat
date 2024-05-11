import { BasicColumn } from '@/components/Table';
import { FormSchema } from '@/components/Form';
import { h } from 'vue';
import { NTag } from 'naive-ui';

export const columns: BasicColumn[] = [
  {
    title: '用户名',
    key: 'username',
    align: 'center',
    width: 120,
  },
  {
    title: '请求ip',
    key: 'ip',
    align: 'center',
    width: 120,
  },
  {
    title: '对话角色',
    key: 'role',
    align: 'center',
    width: 100,
    render(row) {
      return h(
        NTag,
        {
          size: 'small',
          type: row.role === 'user' ? 'success' : 'error',
        },
        {
          default: () => row.role,
        }
      );
    },
  },
  {
    title: '模型名称',
    key: 'model',
    align: 'center',
    width: 100,
  },
  {
    title: 'Token消耗',
    key: 'tokens',
    align: 'center',
    width: 100,
  },
  {
    title: '提示词Token消耗',
    key: 'promptTokens',
    align: 'center',
    width: 120,
  },
  {
    title: '消息内容',
    key: 'message',
    render(row) {
      return h(
        'div',
        {},
        {
          default: () => String(row.message).replace(/```|\n/g, ''),
        }
      );
    },
  },
  {
    title: '会话时间',
    key: 'createTime',
    width: 180,
    align: 'center',
  },
];

export const searchSchemas: FormSchema[] = [
  {
    field: 'text',
    component: 'NInput',
    label: '内容',
    componentProps: {
      placeholder: '请输入内容查询',
    },
  },
  {
    field: 'username',
    component: 'NInput',
    label: '用户名',
    componentProps: {
      placeholder: '请输入用户名查询',
    },
  },
  {
    field: 'role',
    component: 'NSelect',
    label: '对话角色',
    componentProps: {
      placeholder: '请选择对话角色查询',
      options: [
        {
          label: 'user',
          value: 'user',
        },
        {
          label: 'assistant',
          value: 'assistant',
        },
      ],
    },
  },
];
