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
