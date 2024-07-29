import { FormSchema } from '@/components/Form';

export const formSchemas: FormSchema[] = [
  {
    field: 'name',
    label: '应用名称',
    component: 'NInput',
    rules: [{ required: true, message: '请输入应用名称', trigger: ['blur'] }],
  },
  {
    field: 'channel',
    label: '应用渠道',
    component: 'NSelect',
    rules: [{ required: true, message: '请选择应用渠道', trigger: ['blur'] }],
    componentProps: {
      placeholder: '请选择应用渠道',
      options: [
        {
          label: 'API渠道',
          value: 'CHANNEL_API',
        },
        {
          label: 'WEB渠道',
          value: 'CHANNEL_WEB',
        },
        {
          label: '微信渠道',
          value: 'CHANNEL_WEIXIN',
        },
      ],
    },
  },
  {
    field: 'type',
    label: '应用类型',
    component: 'NSelect',
    rules: [{ required: true, message: '请选择应用类型', trigger: ['blur'] }],
    componentProps: {
      placeholder: '请选择应用类型',
      options: [
        {
          label: '窗口模式',
          value: 'WEB_WINDOW',
        },
        {
          label: '浮窗模式',
          value: 'WEB_FLOAT',
        },
        {
          label: 'API模式',
          value: 'WEB_API',
        },
      ],
    },
  },
  {
    field: 'link',
    label: '跳转链接',
    component: 'NInput',
  },
  {
    field: 'des',
    label: '应用描述',
    component: 'NInput',
    componentProps: {
      isFull: true,
      placeholder: '请输入应用描述',
      type: 'textarea',
      autosize: {
        minRows: 5,
        maxRows: 8,
      },
    },
  },
];
