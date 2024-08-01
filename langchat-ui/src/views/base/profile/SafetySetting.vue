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
        <n-form-item label="旧密码" path="oldpass">
          <n-input v-model:value="form.oldpass" placeholder="请输入旧密码" type="password" />
        </n-form-item>
        <n-form-item label="新密码" path="password">
          <n-input v-model:value="form.password" placeholder="请输入新密码" type="password" />
        </n-form-item>
        <n-form-item label="确定密码" path="repass">
          <n-input v-model:value="form.repass" placeholder="请确认新密码" type="password" />
        </n-form-item>

        <div>
          <n-space>
            <n-button
              :disabled="RoleEnum.ADMINISTRATOR === userStore.getUserInfo.username"
              type="primary"
              @click="formSubmit"
              >更新密码</n-button
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
  import { updatePassword } from '@/api/upms/user';
  import { useUserStore } from '@/store/modules/user';

  const rules = {
    oldpass: { required: true, message: '请输入旧密码', trigger: 'blur' },
    password: { required: true, message: '请输入新密码', trigger: 'blur' },
    repass: { required: true, message: '请确认新密码', trigger: 'blur' },
  };
  const userStore = useUserStore();
  const message = useMessage();
  const formRef: any = ref(null);
  const form = ref({
    oldpass: '',
    password: '',
    repass: '',
  });

  onMounted(async () => {
    form.value = await getUserInfo();
  });

  function formSubmit() {
    formRef.value.validate(async (errors) => {
      if (!errors) {
        if (form.value.password !== form.value.repass) {
          message.error('两次输入的新密码不一致');
          return;
        }
        await updatePassword(toRaw(form.value));
      } else {
        message.error('验证失败，请填写完整信息');
      }
    });
  }
</script>
