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
  import { getExcelRows } from '@/api/aigc/embedding';
  import { ref } from 'vue';

  const rows = ref([]);
  const cols = ref([]);

  async function init(id: string) {
    const data = await getExcelRows(id);
    rows.value = data.rows;
    cols.value = data.cols;
  }

  defineExpose({ init });
</script>

<template>
  <div class="w-full h-full overflow-y-auto">
    <n-table size="small" :striped="true" class="w-full">
      <thead>
        <tr>
          <th v-for="item in cols" :key="item">{{ item }}</th>
        </tr>
      </thead>
      <tbody>
        <tr v-for="item in rows" :key="item">
          <td v-for="i in item" :key="i">{{ i }}</td>
        </tr>
      </tbody>
    </n-table>
  </div>
</template>

<style scoped lang="less"></style>
