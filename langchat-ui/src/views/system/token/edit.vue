<template>
  <n-drawer v-model:show="isShow" width="40%" placement="right">
    <n-drawer-content :title="info.principal.username" closable>
      <n-descriptions
        label-placement="left"
        size="small"
        :column="1"
        bordered
        label-style="width: 110px"
      >
        <n-descriptions-item label="账户ID">{{ info.principal.id }}</n-descriptions-item>
        <n-descriptions-item label="账户名">{{ info.principal.username }}</n-descriptions-item>
        <n-descriptions-item label="令牌">{{ info.value }}</n-descriptions-item>
        <n-descriptions-item label="刷新令牌">{{ info.refreshToken }}</n-descriptions-item>
        <n-descriptions-item label="失效时间">{{ info.expiration }}</n-descriptions-item>
        <n-descriptions-item label="权限列表">
          <n-space>
            <n-tag v-for="item in info.principal.authorities" :key="item">
              {{ item.authority }}
            </n-tag>
          </n-space>
        </n-descriptions-item>
      </n-descriptions>
    </n-drawer-content>
  </n-drawer>
</template>
<script lang="ts" setup>
  import { ref } from 'vue';
  import { useMessage } from 'naive-ui';
  import { Token } from '@/api/models/auth';

  const emit = defineEmits(['reload']);
  const message = useMessage();
  const isShow = ref(false);
  let info: Token = {
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

  async function show(token: Token) {
    info = token;
    isShow.value = true;
  }

  defineExpose({ show });
</script>

<style scoped lang="less"></style>
