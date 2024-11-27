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
  import SvgIcon from '@/components/SvgIcon/index.vue';
  import { basicModal, useModal } from '@/components/Modal';
  import { nextTick } from 'vue';

  const [modalRegister, { openModal: openModal, closeModal: closeModal }] = useModal({
    title: '应用配置',
    closable: true,
    maskClosable: false,
    showCloseBtn: false,
    showSubBtn: false,
  });

  async function show(id: string) {
    openModal();
    await nextTick();
    if (id) {
    } else {
    }
  }

  async function handleSubmit() {}
</script>

<template>
  <n-button @click="show">
    <template #icon>
      <SvgIcon icon="uil:setting" />
    </template>
    配置
  </n-button>

  <basicModal style="width: 500px" @register="modalRegister">
    <div class="my-3 mx-2"></div>
  </basicModal>
</template>

<style lang="less" scoped></style>
