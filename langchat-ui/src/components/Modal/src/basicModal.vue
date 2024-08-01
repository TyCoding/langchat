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
  <n-modal id="basic-modal" v-model:show="isModal" v-bind="getBindValue" @close="onCloseModal">
    <template #header>
      <div id="basic-modal-bar" class="w-full cursor-move">{{ getBindValue.title }}</div>
    </template>
    <template #default>
      <slot name="default"></slot>
    </template>
    <template v-if="!$slots.action" #action>
      <n-space>
        <n-button v-if="showCloseBtn" @click="closeModal">{{ subCloseText }}</n-button>
        <n-button v-if="showSubBtn" :loading="subLoading" type="primary" @click="handleSubmit">{{
          subBtuText
        }}</n-button>
      </n-space>
    </template>
    <template v-else #action>
      <slot name="action"></slot>
    </template>
  </n-modal>
</template>

<script lang="ts" setup>
  import { getCurrentInstance, ref, nextTick, unref, computed, useAttrs } from 'vue';
  import { basicProps } from './props';
  import startDrag from '@/utils/Drag';
  import { deepMerge } from '@/utils';
  import { FormProps } from '@/components/Form';
  import { ModalProps, ModalMethods } from './type';

  const attrs = useAttrs();
  const props = defineProps({ ...basicProps });
  const emit = defineEmits(['on-close', 'on-ok', 'register']);

  const propsRef = ref<Partial<ModalProps> | null>(null);

  const isModal = ref(false);
  const subLoading = ref(false);

  const getProps = computed((): FormProps => {
    return { ...props, ...(unref(propsRef) as any) };
  });

  const subBtuText = computed(() => {
    const { subBtuText } = propsRef.value as any;
    return subBtuText || props.subBtuText;
  });
  const subCloseText = computed(() => {
    const { subCloseText } = propsRef.value as any;
    return subCloseText || props.subCloseText;
  });
  const showCloseBtn = computed(() => {
    const { showCloseBtn } = propsRef.value as any;
    return showCloseBtn === undefined ? props.showCloseBtn : showCloseBtn;
  });
  const showSubBtn = computed(() => {
    const { showSubBtn } = propsRef.value as any;
    return showSubBtn === undefined ? props.showSubBtn : showSubBtn;
  });

  async function setProps(modalProps: Partial<ModalProps>): Promise<void> {
    propsRef.value = deepMerge(unref(propsRef) || ({} as any), modalProps);
  }

  const getBindValue = computed(() => {
    return {
      ...attrs,
      ...unref(getProps),
      ...unref(propsRef),
    };
  });

  function setSubLoading(status: boolean) {
    subLoading.value = status;
  }

  function openModal() {
    isModal.value = true;
    nextTick(() => {
      const oBox = document.getElementById('basic-modal');
      const oBar = document.getElementById('basic-modal-bar');
      startDrag(oBar, oBox);
    });
  }

  function closeModal() {
    isModal.value = false;
    subLoading.value = false;
    emit('on-close');
  }

  function onCloseModal() {
    isModal.value = false;
    emit('on-close');
  }

  function handleSubmit() {
    subLoading.value = true;
    emit('on-ok');
  }

  const modalMethods: ModalMethods = {
    setProps,
    openModal,
    closeModal,
    setSubLoading,
  };

  const instance = getCurrentInstance();
  if (instance) {
    emit('register', modalMethods);
  }
</script>

<style lang="less">
  .cursor-move {
    cursor: move;
  }
</style>
