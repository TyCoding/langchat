<!--
  - Copyright (c) 2024 LangChat. TyCoding All Rights Reserved.
  -
  - Licensed under the GNU Affero General Public License, Version 3 (the "License");
  - you may not use this file except in compliance with the License.
  - You may obtain a copy of the License at
  -
  -     https://www.gnu.org/licenses/agpl-3.0.html
  -
  - Unless required by applicable law or agreed to in writing, software
  - distributed under the License is distributed on an "AS IS" BASIS,
  - WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
  - See the License for the specific language governing permissions and
  - limitations under the License.
  -->

<template>
  <div class="tableAction">
    <div class="flex items-center justify-center">
      <template v-for="(action, index) in getActions" :key="`${index}-${action.label}`">
        <n-button class="mx-1" v-bind="action">
          {{ action.label }}
          <template v-if="action.hasOwnProperty('icon')" #icon>
            <n-icon :component="action.icon" />
          </template>
        </n-button>
      </template>
      <n-dropdown
        v-if="dropDownActions && getDropdownList.length"
        :options="getDropdownList"
        trigger="hover"
        @select="select"
      >
        <slot name="more"></slot>
        <n-button v-if="!$slots.more" class="mx-1" icon-placement="right" v-bind="getMoreProps">
          <div class="flex items-center">
            <span>更多</span>
            <n-icon class="ml-1" size="14">
              <DownOutlined />
            </n-icon>
          </div>
          <!--          <template #icon>-->
          <!--            -->
          <!--          </template>-->
        </n-button>
      </n-dropdown>
    </div>
  </div>
</template>

<script lang="ts">
  import { defineComponent, PropType, computed, toRaw } from 'vue';
  import { ActionItem } from '@/components/Table';
  import { usePermission } from '@/hooks/web/usePermission';
  import { isBoolean, isFunction } from '@/utils/is';
  import { DownOutlined } from '@vicons/antd';

  export default defineComponent({
    name: 'TableAction',
    components: { DownOutlined },
    props: {
      actions: {
        type: Array as PropType<ActionItem[]>,
        default: null,
        required: true,
      },
      dropDownActions: {
        type: Array as PropType<ActionItem[]>,
        default: null,
      },
      style: {
        type: String as PropType<String>,
        default: 'button',
      },
      select: {
        type: Function as PropType<Function>,
        default: () => {},
      },
    },
    setup(props) {
      const { hasPermission } = usePermission();

      const actionType =
        props.style === 'button' ? 'default' : props.style === 'text' ? 'primary' : 'default';
      const actionText =
        props.style === 'button' ? undefined : props.style === 'text' ? true : undefined;

      const getMoreProps = computed(() => {
        return {
          text: actionText,
          type: actionType,
          size: 'small',
        };
      });

      const getDropdownList = computed(() => {
        return (toRaw(props.dropDownActions) || [])
          .filter((action) => {
            return hasPermission(action.auth as string[]) && isIfShow(action);
          })
          .map((action) => {
            const { popConfirm } = action;
            return {
              size: 'small',
              text: actionText,
              type: actionType,
              ...action,
              ...popConfirm,
              onConfirm: popConfirm?.confirm,
              onCancel: popConfirm?.cancel,
            };
          });
      });

      function isIfShow(action: ActionItem): boolean {
        const ifShow = action.ifShow;

        let isIfShow = true;

        if (isBoolean(ifShow)) {
          isIfShow = ifShow;
        }
        if (isFunction(ifShow)) {
          isIfShow = ifShow(action);
        }
        return isIfShow;
      }

      const getActions = computed(() => {
        return (toRaw(props.actions) || [])
          .filter((action) => {
            return hasPermission(action.auth as string[]) && isIfShow(action);
          })
          .map((action) => {
            const { popConfirm } = action;
            //需要展示什么风格，自己修改一下参数
            return {
              size: 'small',
              text: actionText,
              type: actionType,
              ...action,
              ...(popConfirm || {}),
              onConfirm: popConfirm?.confirm,
              onCancel: popConfirm?.cancel,
              enable: !!popConfirm,
            };
          });
      });

      return {
        getActions,
        getDropdownList,
        getMoreProps,
      };
    },
  });
</script>
