import { BasicColumn } from '@/components/Table';
import { FormSchema } from '@/components/Form';
import { h } from 'vue';
import { NTag } from 'naive-ui';

export const columns: BasicColumn[] = [
  {
    title: '用户名',
    key: 'username',
    align: 'center',
  },
  {
    title: '真实姓名',
    key: 'realName',
    align: 'center',
  },
  {
    title: '角色',
    key: 'roles',
    align: 'center',
    render(row: any) {
      return row.roles.map((item: any) => {
        return h(
          NTag,
          {
            style: {
              marginRight: '6px',
            },
            size: 'small',
            type: 'info',
            bordered: false,
          },
          {
            default: () => item.name,
          }
        );
      });
    },
  },
  {
    title: '部门',
    key: 'deptName',
    align: 'center',
  },
  {
    title: '账号状态',
    key: 'status',
    align: 'center',
    width: 120,
    render(row) {
      return h(
        NTag,
        {
          type: row.status ? 'success' : 'error',
          size: 'small',
        },
        {
          default: () => (row.status ? '启用' : '禁用'),
        }
      );
    },
  },
  {
    title: '邮箱',
    key: 'email',
    align: 'center',
  },
  {
    title: '手机',
    key: 'phone',
    align: 'center',
    width: 120,
  },
];

export const searchSchemas: FormSchema[] = [
  {
    field: 'name',
    component: 'NInput',
    label: '用户名',
    componentProps: {
      placeholder: '请输入用户名',
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
    field: 'username',
    label: '用户名',
    component: 'NInput',
    componentProps: {
      placeholder: '请输入用户名',
    },
    rules: [{ required: true, message: '请输入用户名', trigger: ['blur'] }],
  },
  {
    field: 'roleIds',
    label: '角色',
    slot: 'roleSlot',
    component: 'NInput',
    componentProps: {
      placeholder: '请选择角色',
    },
    rules: [{ type: 'array', required: true, message: '请选择角色', trigger: ['blur', 'change'] }],
  },
  {
    field: 'deptId',
    label: '部门',
    slot: 'deptSlot',
    component: 'NInput',
    componentProps: {
      placeholder: '请选择部门',
    },
    rules: [{ required: true, message: '请选择部门', trigger: ['blur', 'change'] }],
  },
  {
    field: 'realName',
    label: '真实姓名',
    component: 'NInput',
    componentProps: {
      placeholder: '请输入真实姓名',
    },
    rules: [{ required: true, message: '请输入真实姓名', trigger: ['blur'] }],
  },
  {
    field: 'status',
    component: 'NRadioGroup',
    label: '角色状态',
    componentProps: {
      options: [
        {
          label: '启用',
          value: true,
        },
        {
          label: '禁用',
          value: false,
        },
      ],
    },
  },
  {
    field: 'sex',
    label: '性别',
    component: 'NRadioGroup',
    componentProps: {
      options: [
        {
          label: '男',
          value: '男',
        },
        {
          label: '女',
          value: '女',
        },
      ],
    },
  },
  {
    field: 'phone',
    label: '手机号',
    component: 'NInput',
    componentProps: {
      placeholder: '请输入手机号',
    },
  },
  {
    field: 'email',
    label: '邮箱',
    component: 'NInput',
    componentProps: {
      placeholder: '请输入邮箱',
    },
  },
];
