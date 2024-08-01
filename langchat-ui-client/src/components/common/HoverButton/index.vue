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

<script lang="ts" setup>
  import { computed } from 'vue';
  import type { PopoverPlacement } from 'naive-ui';
  import { NTooltip } from 'naive-ui';
  import Button from './Button.vue';

  interface Props {
    tooltip?: string;
    placement?: PopoverPlacement;
  }

  interface Emit {
    (e: 'click'): void;
  }

  const props = withDefaults(defineProps<Props>(), {
    tooltip: '',
    placement: 'bottom',
  });

  const emit = defineEmits<Emit>();

  const showTooltip = computed(() => Boolean(props.tooltip));

  function handleClick() {
    emit('click');
  }
</script>

<template>
  <div v-if="showTooltip">
    <NTooltip :placement="placement" trigger="hover">
      <template #trigger>
        <Button @click="handleClick">
          <slot></slot>
        </Button>
      </template>
      {{ tooltip }}
    </NTooltip>
  </div>
  <div v-else>
    <Button @click="handleClick">
      <slot></slot>
    </Button>
  </div>
</template>
