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

<template>
  <n-drawer v-model:show="isShow" width="40%" placement="right">
    <n-drawer-content :title="info.username" closable>
      <n-descriptions
        label-placement="left"
        size="small"
        :column="1"
        bordered
        label-style="width: 110px"
      >
        <n-descriptions-item label="账户ID">{{ info.id }}</n-descriptions-item>
        <n-descriptions-item label="账户名">{{ info.username }}</n-descriptions-item>
        <n-descriptions-item label="用户名">{{ info.realName }}</n-descriptions-item>
        <n-descriptions-item label="令牌">{{ info.token }}</n-descriptions-item>
        <n-descriptions-item label="失效时间">{{ info.expiration }}</n-descriptions-item>
        <n-descriptions-item label="角色列表">
          <n-space>
            <n-tag v-for="item in info.roles" :key="item" size="small" type="success">
              {{ item.name }}
            </n-tag>
          </n-space>
        </n-descriptions-item>
        <n-descriptions-item label="权限列表">
          <n-space>
            <n-tag v-for="item in info.perms" :key="item" size="small" type="info">
              {{ item }}
            </n-tag>
          </n-space>
        </n-descriptions-item>
      </n-descriptions>
    </n-drawer-content>
  </n-drawer>
</template>
<script lang="ts" setup>
  import { ref } from 'vue';

  const emit = defineEmits(['reload']);
  const isShow = ref(false);
  let info: any = {
    value: '',
    tokenType: '',
    expiration: '',
    refreshToken: '',
    principal: {
      deptId: '',
      enabled: '',
      id: '',
      password: '',
      username: '',
      authorities: [],
    },
  };

  async function show(token: any) {
    info = token;
    isShow.value = true;
  }

  defineExpose({ show });
</script>

<style scoped lang="less"></style>
