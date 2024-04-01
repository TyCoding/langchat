import { BasicColumn } from '@/components/Table';
import { FormSchema } from '@/components/Form';
import { Model } from '@/api/models/flow';
import { h } from 'vue';
import { formatToDate } from '@/utils/dateUtil';

export const columns: BasicColumn<Model>[] = [
  {
    title: '模型名称',
    key: 'name',
    align: 'center',
  },
  {
    title: '模型类型',
    key: 'model',
    align: 'center',
    width: 110,
  },
  {
    title: '对话模型',
    key: 'chatModel',
    width: 120,
  },
  {
    title: '温度',
    key: 'temperature',
    align: 'center',
    width: 80,
  },
  {
    title: 'Token上限',
    key: 'tokenLimit',
    align: 'center',
    width: 120,
  },
  {
    title: '模型描述',
    key: 'des',
  },
  {
    title: '创建时间',
    key: 'createTime',
    align: 'center',
    width: 120,
    render(row: Model) {
      return h('span', {}, formatToDate(new Date(Number(row.createTime))));
    },
  },
];

export const searchSchemas: FormSchema[] = [
  {
    field: 'name',
    component: 'NInput',
    label: '模型名称',
    componentProps: {
      placeholder: '请输入模型名称',
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
    label: '模型名称',
    component: 'NInput',
    componentProps: {
      placeholder: '请输入模型名称',
    },
    rules: [{ required: true, message: '请输入模型名称', trigger: ['blur'] }],
  },
  {
    field: 'model',
    label: '模型类型',
    slot: 'modelSlot',
    componentProps: {
      placeholder: '请选择模型类型',
    },
    rules: [{ required: true, message: '请选择模型类型', trigger: ['blur'] }],
  },
  {
    field: 'chatModel',
    slot: 'chatModelSlot',
    label: '对话模型',
    componentProps: {
      placeholder: '请选择对话模型',
    },
    rules: [{ required: true, message: '请选择对话模型', trigger: ['blur'] }],
  },
  {
    field: 'temperature',
    component: 'NInput',
    label: '温度',
    componentProps: {
      placeholder: '请输入温度',
    },
  },
  {
    field: 'tokenLimit',
    component: 'NInput',
    label: 'Token上限',
    componentProps: {
      placeholder: '请输入Token上限',
    },
  },
  {
    field: 'systemPrompt',
    component: 'NInput',
    label: '提示词',
    componentProps: {
      placeholder: '请输入提示词',
    },
  },
  {
    field: 'limitPrompt',
    component: 'NInput',
    label: '限定词',
    componentProps: {
      placeholder: '请输入限定词',
    },
  },
  {
    field: 'des',
    component: 'NInput',
    label: '模型描述',
    isFull: true,
    componentProps: {
      isFull: true,
      placeholder: '请输入模型描述',
      type: 'textarea',
      autosize: {
        minRows: 5,
        maxRows: 8,
      },
    },
  },
];
