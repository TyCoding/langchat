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
  <n-grid cols="2 s:2 m:2 l:3 xl:3 2xl:3" responsive="screen">
    <n-grid-item>
      <n-form ref="formRef" :label-width="80" :model="form" :rules="rules">
        <n-form-item label="账户" path="username">
          <n-input
            v-model:value="form.username"
            :disabled="RoleEnum.ADMINISTRATOR === form.username"
            placeholder="请输入账户"
          />
        </n-form-item>
        <n-form-item label="昵称" path="realName">
          <n-input v-model:value="form.realName" placeholder="请输入昵称" />
        </n-form-item>
        <n-form-item label="邮箱" path="email">
          <n-input v-model:value="form.email" placeholder="请输入邮箱" />
        </n-form-item>
        <n-form-item label="联系电话" path="phone">
          <n-input v-model:value="form.phone" placeholder="请输入联系电话" />
        </n-form-item>
        <n-form-item label="性别" path="sex">
          <n-select
            v-model:value="form.sex"
            :options="[
              {
                label: '男',
                value: '男',
              },
              {
                label: '女',
                value: '女',
              },
            ]"
          />
        </n-form-item>

        <div>
          <n-space>
            <n-button
              :disabled="RoleEnum.ADMINISTRATOR === form.username"
              type="primary"
              @click="formSubmit"
              >更新基本信息</n-button
            >
          </n-space>
        </div>
      </n-form>
    </n-grid-item>
  </n-grid>
</template>

<script lang="ts" setup>
  import { onMounted, ref, toRaw } from 'vue';
  import { useMessage } from 'naive-ui';
  import { getUserInfo } from '@/api/auth';
  import { RoleEnum } from '@/enums/roleEnum';
  import { update } from '@/api/upms/user';

  const rules = {
    username: { required: true, message: '请输入账号', trigger: 'blur' },
    realName: { required: true, message: '请输入昵称', trigger: 'blur' },
    email: { required: true, message: '请输入邮箱', trigger: 'blur' },
    phone: { required: true, message: '请输入联系电话', trigger: 'blur' },
    sex: { required: true, message: '请选择性别', trigger: 'blur' },
  };
  const message = useMessage();
  const formRef: any = ref(null);
  const form = ref({
    username: '',
    realName: '',
    phone: '',
    email: '',
    sex: '',
  });

  onMounted(async () => {
    form.value = await getUserInfo();
  });

  function formSubmit() {
    formRef.value.validate(async (errors) => {
      if (!errors) {
        await update(toRaw(form.value));
      } else {
        message.error('验证失败，请填写完整信息');
      }
    });
  }
</script>
