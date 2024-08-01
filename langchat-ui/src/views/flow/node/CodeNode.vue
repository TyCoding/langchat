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
  import { NodeLayout } from '@/views/flow/node';
  import { useNode } from '@vue-flow/core';
  import { Codemirror } from 'vue-codemirror';
  import { ref } from 'vue';
  import { oneDark } from '@codemirror/theme-one-dark';
  import { javascript } from '@codemirror/lang-javascript';

  const { node } = useNode();
  const code = ref('');
  const extensions = [oneDark, javascript()];
</script>

<template>
  <NodeLayout :node="node">
    <template #content>
      <div class="rounded overflow-hidden">
        <codemirror
          v-model="code"
          placeholder="Code goes here..."
          :style="{ height: '100px' }"
          :autofocus="true"
          :indent-with-tab="true"
          :tab-size="2"
          :extensions="extensions"
        />
      </div>
    </template>
  </NodeLayout>
</template>

<style scoped lang="less">
  ::v-deep(.cm-focused) {
    outline: none !important;
  }
</style>
