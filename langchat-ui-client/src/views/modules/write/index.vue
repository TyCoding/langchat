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
  import { SvgIcon } from '@/components/common';
  import Write from './components/Write.vue';
  import Beautify from './components/Beautify.vue';
  import { ref } from 'vue';
  import ModelProvider from '@/views/modules/common/ModelProvider.vue';

  const message = ref('');
  function onOk(val: string) {
    message.value = val;
  }
</script>

<template>
  <n-layout class="h-full w-full" has-sider>
    <n-layout-sider
      :collapsed-width="0"
      :width="500"
      bordered
      collapse-mode="width"
      show-trigger="arrow-circle"
    >
      <div class="flex justify-center items-center m-4 rounded">
        <n-tabs type="segment">
          <n-tab-pane display-directive="show" name="chap1" tab="文案撰写">
            <Write @ok="onOk" />
          </n-tab-pane>
          <n-tab-pane display-directive="show" name="chap2" tab="内容美化">
            <Beautify @ok="onOk" />
          </n-tab-pane>
        </n-tabs>
      </div>
    </n-layout-sider>

    <div class="flex justify-center items-center w-full mt-4">
      <div class="p-8 w-full h-full mb-14">
        <div class="mb-2 flex flex-wrap justify-between items-center">
          <div class="font-bold flex items-center flex-wrap gap-2">
            <SvgIcon class="text-lg" icon="ion:sparkles-outline" />
            <span>输出内容预览</span>
            <ModelProvider />
          </div>
          <div>
            <n-button secondary size="small" type="success">
              <template #icon>
                <SvgIcon class="text-[14px]" icon="ion:sparkles-outline" />
              </template>
              复制内容
            </n-button>
          </div>
        </div>
        <n-input v-model:value="message" class="h-full" type="textarea" />
      </div>
    </div>
  </n-layout>
</template>

<style lang="less" scoped></style>
