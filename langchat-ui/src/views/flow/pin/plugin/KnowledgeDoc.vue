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

<script setup lang="ts">
  import { ref } from 'vue';
  import { basicModal, useModal } from '@/components/Modal';
  import { SparklesOutline, DocumentTextOutline } from '@vicons/ionicons5';

  const docList = ref(['1', '2']);

  const [
    modalRegister,
    { openModal: openModal, closeModal: closeModal, setSubLoading: setSubLoading },
  ] = useModal({
    title: '新增/编辑',
    showSubBtn: false,
  });

  function onDocClick() {
    openModal();
  }
</script>

<template>
  <n-form ref="formRef">
    <n-form-item path="age" class="custom-form-item">
      <template #label>
        <n-popover placement="bottom" class="custom-popover">
          从历史数据中获取
          <template #trigger>
            <div class="tips-line pin-label">从数据库中选择</div>
          </template>
        </n-popover>
      </template>
      <n-select v-model="docList" size="small" />
    </n-form-item>

    <n-form-item path="age" class="custom-form-item">
      <template #label>
        <n-popover placement="bottom" class="custom-popover">
          仅支持XX文档
          <template #trigger>
            <div class="tips-line pin-label">知识库文档名称</div>
          </template>
        </n-popover>
      </template>
      <n-input size="small" />
    </n-form-item>
  </n-form>
  <div class="">
    <n-upload
      multiple
      directory-dnd
      action="https://www.mocky.io/v2/5e4bafc63100007100d8b70f"
      :max="5"
      class="text-[#bdbdbd]"
    >
      <n-upload-dragger>
        <div class="text-[12px]">
          点击上传或拖拽文件到这里
          <br />
          仅支持（.pdf .html .text .doc .docx）文件
        </div>
      </n-upload-dragger>
    </n-upload>
  </div>

  <n-space vertical v-if="docList.length">
    <n-thing v-for="item in docList" :key="item" class="custom-list" @click="onDocClick">
      <template #avatar>
        <n-icon size="18">
          <DocumentTextOutline />
        </n-icon>
      </template>
      <template #header>
        <div class="text-[12px]">{{ item }}</div>
      </template>
      <template #description> <div class="text-[10px]">描述</div> </template>
    </n-thing>
  </n-space>

  <basicModal @register="modalRegister" style="width: 30%"> 1111 </basicModal>
</template>

<style scoped lang="less">
  ::v-deep(.n-upload-dragger) {
    background: transparent !important;
    padding: 15px !important;
    &:hover {
      color: #2d8cf0 !important;
    }
  }
  .custom-list {
    border: 1px solid rgb(224, 224, 230);
    border-radius: 5px;
    padding: 4px;
    cursor: pointer;
    &:hover {
      color: #2d8cf0 !important;
      ::v-deep(.n-thing-header__title) {
        color: #2d8cf0 !important;
      }
      ::v-deep(.n-thing-main__description) {
        color: #2d8cf0 !important;
      }
    }
    ::v-deep(.n-thing-header__title) {
    }
  }

  .backdrop {
    background: rgba(255, 255, 255, 0.04);
    backdrop-filter: blur(4px);
    -webkit-backdrop-filter: blur(4px);
  }
</style>
