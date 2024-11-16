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
  import { defineComponent, h } from 'vue';
  import {
    NDialogProvider,
    NLoadingBarProvider,
    NMessageProvider,
    NNotificationProvider,
    useDialog,
    useLoadingBar,
    useMessage,
    useNotification,
  } from 'naive-ui';

  function registerNaiveTools() {
    window.$loadingBar = useLoadingBar();
    window.$dialog = useDialog();
    window.$message = useMessage();
    window.$notification = useNotification();
  }

  const NaiveProviderContent = defineComponent({
    name: 'NaiveProviderContent',
    setup() {
      registerNaiveTools();
    },
    render() {
      return h('div');
    },
  });
</script>

<template>
  <NLoadingBarProvider>
    <NDialogProvider>
      <NNotificationProvider>
        <NMessageProvider>
          <slot></slot>
          <NaiveProviderContent />
        </NMessageProvider>
      </NNotificationProvider>
    </NDialogProvider>
  </NLoadingBarProvider>
</template>
