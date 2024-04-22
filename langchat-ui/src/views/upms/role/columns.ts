import { BasicColumn } from '@/components/Table';
import { FormSchema } from '@/components/Form';

export const columns: BasicColumn[] = [
  {
    title: '角色名称',
    key: 'name',
    align: 'center',
  },
  {
    title: '角色标识',
    key: 'alias',
    align: 'center',
  },
  {
    title: '角色描述',
    key: 'des',
    align: 'center',
  },
];

export const searchSchemas: FormSchema[] = [
  {
    field: 'name',
    component: 'NInput',
    label: '角色名称',
    componentProps: {
      placeholder: '请输入角色名称',
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
    label: '角色名称',
    component: 'NInput',
    componentProps: {
      placeholder: '请输入角色名称',
    },
    rules: [{ required: true, message: '请输入角色名称', trigger: ['blur'] }],
  },
  {
    field: 'alias',
    label: '角色标识',
    component: 'NInput',
    componentProps: {
      placeholder: '请输入角色标识',
    },
    rules: [{ required: true, message: '请输入角色标识', trigger: ['blur'] }],
  },
  {
    field: 'menuIds',
    label: '菜单权限',
    slot: 'menuSlot',
    component: 'NInput',
  },
  {
    field: 'des',
    component: 'NInput',
    label: '角色描述',
    isFull: true,
    componentProps: {
      isFull: true,
      placeholder: '请输入角色描述',
      type: 'textarea',
      autosize: {
        minRows: 5,
        maxRows: 8,
      },
    },
  },
];
