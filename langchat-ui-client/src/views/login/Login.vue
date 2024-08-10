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
  import { reactive, ref, toRaw } from 'vue';
  import { useMessage } from 'naive-ui';
  import { useUserStore } from '@/store/modules/user';
  import { useRouter } from 'vue-router';
  import { t } from '@/locales';
  import { rules } from '@/views/login/data';

  const formRef = ref();
  const message = useMessage();
  const loading = ref(false);
  const userStore = useUserStore();
  const router = useRouter();

  const form = reactive({
    username: 'langchat@outlook.com',
    password: 'langchat',
  });

  const handleSubmit = (e: any) => {
    e.preventDefault();
    formRef.value.validate(async (errors: any) => {
      if (!errors) {
        loading.value = true;

        userStore
          .login(toRaw(form))
          .then(async () => {
            message.success(t('login.success'));
            await router.replace('/');
          })
          .catch(() => {
            loading.value = false;
          });
      }
    });
  };
</script>

<template>
  <div class="mt-4 login-content-form">
    <n-form ref="formRef" :model="form" :rules="rules" label-placement="left" size="large">
      <n-form-item class="login-animation1" path="username">
        <n-input v-model:value="form.username" :placeholder="t('login.namePlaceholder')">
          <template #prefix>
            <n-icon color="#808695" size="18">
              <SvgIcon icon="material-symbols:person-outline" />
            </n-icon>
          </template>
        </n-input>
      </n-form-item>
      <n-form-item class="login-animation2" path="password">
        <n-input
          v-model:value="form.password"
          :placeholder="t('login.passPlaceholder')"
          showPasswordOn="click"
          type="password"
        >
          <template #prefix>
            <n-icon color="#808695" size="18">
              <SvgIcon icon="mdi:lock-outline" />
            </n-icon>
          </template>
        </n-input>
      </n-form-item>
      <n-form-item class="login-animation3">
        <n-space class="w-full" vertical>
          <n-button :loading="loading" block secondary type="primary" @click="handleSubmit">
            {{ t('login.title') }}
          </n-button>
        </n-space>
      </n-form-item>
    </n-form>
  </div>
</template>

<style lang="less" scoped></style>
