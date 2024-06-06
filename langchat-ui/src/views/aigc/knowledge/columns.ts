import { BasicColumn } from '@/components/Table';
import { FormSchema } from '@/components/Form';
import { h } from 'vue';
import { NButton } from 'naive-ui';
import router from '@/router';
import { formatToDate } from '@/utils/dateUtil';

export const columns: BasicColumn[] = [
  {
    title: '知识库名称',
    key: 'name',
    align: 'center',
    width: 300,
    render(row: any) {
      return h(
        NButton,
        {
          text: true,
          tag: 'a',
          type: 'primary',
          onClick: () => {
            router.push({ name: 'knowledge_info', params: { id: row.id } });
          },
        },
        {
          default: () => row.name,
        }
      );
    },
  },
  {
    title: '知识库描述',
    key: 'des',
    align: 'center',
  },
  {
    title: '创建时间',
    key: 'createTime',
    align: 'center',
    width: 120,
    render(row: any) {
      return h('span', {}, formatToDate(new Date(Number(row.createTime))));
    },
  },
];

export const searchSchemas: FormSchema[] = [
  {
    field: 'name',
    component: 'NInput',
    label: '知识库名称',
    componentProps: {
      placeholder: '请输入知识库名称',
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
    component: 'NInput',
    label: '知识库名称',
    componentProps: {
      placeholder: '请输入知识库名称',
    },
    rules: [{ required: true, message: '请输入知识库名称', trigger: ['blur'] }],
  },
  {
    field: 'isExcel',
    component: 'NRadioGroup',
    label: '结构化文档',
    labelMessage: '是否填充的结构化文档数据',
    defaultValue: false,
    componentProps: {
      options: [
        {
          label: '非结构化数据',
          value: false,
        },
        {
          label: '结构化数据',
          value: true,
        },
      ],
    },
  },
  {
    field: 'des',
    component: 'NInput',
    label: '知识库描述',
    componentProps: {
      placeholder: '请输入知识库描述',
      type: 'textarea',
      autosize: {
        minRows: 5,
        maxRows: 8,
      },
    },
  },
];
