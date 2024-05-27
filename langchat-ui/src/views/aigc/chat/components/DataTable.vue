<script setup lang="ts"></script>

<template>
  <div class="w-full h-full overflow-y-auto">
    <n-table size="small" :striped="true" class="w-full">
      <thead>
        <tr>
          <th>Abandon</th>
          <th>Abnormal</th>
          <th>Abolish</th>
          <th>...</th>
          <th>万事开头难</th>
        </tr>
      </thead>
      <tbody>
        <tr v-for="i in 100">
          <td>放弃</td>
          <td>反常的</td>
          <td>彻底废除</td>
          <td>...</td>
          <td>干！我刚才背的是啥</td>
        </tr>
      </tbody>
    </n-table>
  </div>
</template>

<style scoped lang="less"></style>
