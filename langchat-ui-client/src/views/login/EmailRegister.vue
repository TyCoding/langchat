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
  import { CountdownInst, useMessage } from 'naive-ui';
  import { useUserStore } from '@/store/modules/user';
  import { useRouter } from 'vue-router';
  import { emailRegister, getEmailCode } from '@/api/auth';
  import { t } from '@/locales';
  import { rules } from '@/views/login/data';

  const router = useRouter();
  const userStore = useUserStore();
  const formRef = ref();
  const message = useMessage();
  const loading = ref(false);
  const codeLoading = ref(false);
  const countdownRef = ref<CountdownInst | null>();

  const form = reactive({
    email: '',
    password: '',
    code: '',
  });

  const onSubmit = (e: any) => {
    e.preventDefault();
    formRef.value.validate(async (errors: any) => {
      if (!errors) {
        loading.value = true;

        try {
          await emailRegister(toRaw(form));
          message.loading(t('login.regSuccess'));

          const response = await userStore.login({
            username: form.email,
            password: form.password,
          });
          if (response.token !== undefined) {
            await router.replace('/');
          } else {
            message.error(response.message || t('login.error'));
          }
        } finally {
          loading.value = false;
        }
      }
    });
  };

  async function onGetCode() {
    formRef.value?.validate(
      async (errors: any) => {
        if (!errors) {
          codeLoading.value = true;
          getEmailCode(form.email)
            .then(() => {
              message.success(t('login.codeSendSuccess'));
            })
            .catch(() => {
              message.error(t('login.codeSendFail'));
              countdownRef.value?.reset();
            });
        }
      },
      (rule: any) => {
        return rule?.key === 'email';
      }
    );
  }
</script>

<template>
  <div class="mt-4 login-content-form">
    <n-form ref="formRef" :model="form" :rules="rules" label-placement="left" size="large">
      <n-form-item class="login-animation1" path="email">
        <n-input
          v-model:value="form.email"
          :input-props="{ type: 'email' }"
          :placeholder="t('login.namePlaceholder')"
        >
          <template #prefix>
            <n-icon color="#808695" size="18">
              <SvgIcon icon="material-symbols:person-outline" />
            </n-icon>
          </template>
        </n-input>
      </n-form-item>
      <n-form-item class="login-animation2" path="code">
        <n-input v-model:value="form.code" :placeholder="t('login.codePlaceholder')">
          <template #prefix>
            <n-icon color="#808695" size="18">
              <SvgIcon icon="ph:key" />
            </n-icon>
          </template>
          <template #suffix>
            <n-button :disabled="codeLoading" text type="success" @click="onGetCode()">
              <n-countdown
                v-if="codeLoading"
                :active="codeLoading"
                :duration="59000"
                :render="({ seconds }) => `${String(seconds) + t('login.codeExp')}`"
                @finish="codeLoading = false"
              />
              <template v-else>{{ t('login.getCode') }}</template>
            </n-button>
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
          <n-button :loading="loading" block secondary type="primary" @click="onSubmit">
            {{ t('login.register') }}
          </n-button>
        </n-space>
      </n-form-item>
    </n-form>
  </div>
</template>

<style lang="less" scoped></style>
