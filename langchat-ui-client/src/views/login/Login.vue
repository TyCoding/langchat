<script setup lang="ts">
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
    password: '123456',
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
    <n-form ref="formRef" label-placement="left" size="large" :model="form" :rules="rules">
      <n-form-item path="username" class="login-animation1">
        <n-input v-model:value="form.username" :placeholder="t('login.namePlaceholder')">
          <template #prefix>
            <n-icon size="18" color="#808695">
              <SvgIcon icon="material-symbols:person-outline" />
            </n-icon>
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
          <n-button type="primary" @click="handleSubmit" :loading="loading" block secondary>
            {{ t('login.title') }}
          </n-button>
        </n-space>
      </n-form-item>
    </n-form>
  </div>
</template>

<style scoped lang="less"></style>
