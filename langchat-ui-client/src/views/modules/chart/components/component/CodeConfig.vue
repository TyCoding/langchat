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
  import { Codemirror } from 'vue-codemirror';
  import { oneDark } from '@codemirror/theme-one-dark';
  import { json } from '@codemirror/lang-json';
  import { js } from 'js-beautify';
  import { ref, watch } from 'vue';

  const emit = defineEmits(['update']);
  const props = defineProps<{
    option: any;
  }>();
  const extensions = [oneDark, json()];
  const code = ref(js(JSON.stringify(props.option)));

  watch(props.option, (val) => {
    code.value = js(JSON.stringify(val));
  });
  function onChange(val: string) {
    emit('update', val);
  }
</script>

<template>
  <n-collapse-item title="编辑源码">
    <Codemirror
      v-model="code"
      :autofocus="true"
      :extensions="extensions"
      :indent-with-tab="true"
      :style="{ height: '400px' }"
      :tab-size="2"
      placeholder="Code goes here..."
      @update:model-value="onChange"
    />
  </n-collapse-item>
</template>

<style lang="less" scoped>
  ::v-deep(.cm-focused) {
    outline: none !important;
  }
</style>
