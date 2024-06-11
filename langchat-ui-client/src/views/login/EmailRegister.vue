<script setup lang="ts">
  import { SvgIcon } from '@/components/common';
  import { reactive, ref, toRaw } from 'vue';
  import { useMessage, CountdownInst } from 'naive-ui';
  import { useUserStore } from '@/store/modules/user';
  import { useRouter } from 'vue-router';
  import { getEmailCode, emailRegister } from '@/api/auth';
  import { isBlank } from '@/utils/is';
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

  const onSubmit = (e) => {
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
    <n-form ref="formRef" label-placement="left" size="large" :model="form" :rules="rules">
      <n-form-item path="email" class="login-animation1">
        <n-input
          v-model:value="form.email"
          :placeholder="t('login.namePlaceholder')"
          :input-props="{ type: 'email' }"
        >
          <template #prefix>
            <n-icon size="18" color="#808695">
              <SvgIcon icon="material-symbols:person-outline" />
            </n-icon>
          </template>
        </n-input>
      </n-form-item>
      <n-form-item path="code" class="login-animation2">
        <n-input v-model:value="form.code" :placeholder="t('login.codePlaceholder')">
          <template #prefix>
            <n-icon size="18" color="#808695">
              <SvgIcon icon="ph:key" />
            </n-icon>
          </template>
          <template #suffix>
            <n-button @click="onGetCode()" :disabled="codeLoading" text type="success">
              <n-countdown
                v-if="codeLoading"
                :active="codeLoading"
                @finish="codeLoading = false"
                :duration="59000"
                :render="({ seconds }) => `${String(seconds) + t('login.codeExp')}`"
              />
              <template v-else>{{ t('login.getCode') }}</template>
            </n-button>
          </template>
        </n-input>
      </n-form-item>
      <n-form-item path="password" class="login-animation2">
        <n-input
          v-model:value="form.password"
          type="password"
          showPasswordOn="click"
          :placeholder="t('login.passPlaceholder')"
        >
          <template #prefix>
            <n-icon size="18" color="#808695">
              <SvgIcon icon="mdi:lock-outline" />
            </n-icon>
          </template>
        </n-input>
      </n-form-item>
      <n-form-item class="login-animation3">
        <n-space vertical class="w-full">
          <n-button type="primary" @click="onSubmit" :loading="loading" block secondary>
            {{ t('login.register') }}
          </n-button>
        </n-space>
      </n-form-item>
    </n-form>
  </div>
</template>

<style scoped lang="less"></style>
