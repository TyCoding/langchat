/*
 * Copyright (c) 2024 LangChat. TyCoding All Rights Reserved.
 *
 * Licensed under the GNU Affero General Public License, Version 3 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     https://www.gnu.org/licenses/agpl-3.0.html
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

import { FormSchema } from '@/components/Form';
import router from '@/router';
import { h } from 'vue';
import SvgIcon from '@/components/SvgIcon/index.vue';

export enum ChannelEnum {
  CHANNEL_API = 'CHANNEL_API',
  CHANNEL_WEB = 'CHANNEL_WEB',
}

export async function onInfo(item: any) {
  if (item.channel === ChannelEnum.CHANNEL_API) {
    await router.push('/aigc/app/channel/api/' + item.id);
  }
  if (item.channel === ChannelEnum.CHANNEL_WEB) {
    await router.push('/aigc/app/channel/web/' + item.id);
  }
}

export function renderTitle(channel: string) {
  if (channel === ChannelEnum.CHANNEL_API) return 'HTTP API渠道';
  if (channel === ChannelEnum.CHANNEL_WEB) return 'WEB渠道';
}

export function renderIcon(channel: string) {
  return {
    render() {
      if (channel === ChannelEnum.CHANNEL_API) {
        return h(SvgIcon, {
          class: 'text-4xl text-blue-500',
          icon: 'hugeicons:api',
        });
      }
      if (channel === ChannelEnum.CHANNEL_WEB) {
        return h(SvgIcon, {
          class: 'text-4xl text-blue-500',
          icon: 'mdi:web-sync',
        });
      }
    },
  };
}

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
          value: ChannelEnum.CHANNEL_API,
        },
        {
          label: 'WEB渠道',
          value: ChannelEnum.CHANNEL_WEB,
        },
      ],
    },
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
