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
  import { useFlowStore } from '@/views/flow/store';
  import { onMounted, ref } from 'vue';
  import { Flow } from '@/api/models/flow';

  const flowStore = useFlowStore();
  const form = ref<Flow>({});

  onMounted(() => {
    if (!flowStore.data) {
      return;
    }
    const { name, des } = flowStore.data;
    form.value.name = name;
    form.value.des = des;
  });
</script>

<template>
  <div class="mb-2">
    <div class="pin-title">
      <n-input class="hover-input text-lg" v-model:value="form.name" />
    </div>
    <n-input
      type="textarea"
      size="small"
      class="hover-input text-xs text-gray-300"
      placeholder="Node description..."
      v-model:value="form.des"
    />
  </div>
</template>

<style scoped lang="less"></style>
