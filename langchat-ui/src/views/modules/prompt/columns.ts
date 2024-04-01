import { BasicColumn } from '@/components/Table';
import { FormSchema } from '@/components/Form';
import { h } from 'vue';
import { NButton, NTag } from 'naive-ui';
import { Bot } from '@/api/models/flow';
import router from '@/router';

export const columns: BasicColumn<Bot>[] = [
  {
    title: '名称',
    key: 'name',
    render(row: Bot) {
      return h(
        NButton,
        {
          text: true,
          tag: 'a',
          type: 'success',
          onClick: () => {
            router.push({ name: 'bot_config_index', params: { id: row.id } });
          },
        },
        {
          default: () => row.name,
        }
      );
    },
  },
  {
    title: '标签',
    key: 'tags',
    render(row: Bot) {
      if (row.tags == undefined) {
        return;
      }
      return row.tags.map((val: string) => {
        return h(
          NTag,
          {
            style: {
              marginRight: '6px',
            },
            type: 'info',
            bordered: false,
          },
          {
            default: () => val,
          }
        );
      });
    },
  },
  {
    title: '描述',
    key: 'des',
  },
  {
    title: '创建时间',
    key: 'createTime',
    align: 'center',
    width: 120,
  },
];

export const searchSchemas: FormSchema[] = [
  {
    field: 'title',
    component: 'NInput',
    label: '标题',
    componentProps: {
      placeholder: '请输入Prompt标题查询',
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
    field: 'title',
    label: '标题',
    component: 'NInput',
    rules: [{ required: true, message: '请输入标题', trigger: ['blur'] }],
  },
  {
    field: 'des',
    label: '描述',
    component: 'NInput',
  },
  {
    field: 'icon',
    label: '图标',
    component: 'NInput',
  },
  {
    field: 'prompt',
    component: 'NInput',
    label: 'Prompt',
    isFull: true,
    componentProps: {
      isFull: true,
      placeholder: '请输入Prompt',
      type: 'textarea',
      autosize: {
        minRows: 20,
      },
    },
    rules: [{ required: true, message: '请输入Prompt', trigger: ['blur'] }],
  },
];
