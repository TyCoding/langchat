<script setup lang="ts">
  import { SvgIcon } from '@/components/common';
  import { reactive, ref } from 'vue';
  import { useMessage } from 'naive-ui';
  import { useUserStore } from '@/store/modules/user';
  import { useRoute, useRouter } from 'vue-router';

  interface FormState {
    email: string;
    password: string;
    code: string;
  }

  const formRef = ref();
  const message = useMessage();
  const loading = ref(false);
  const LOGIN_NAME = 'Login';

  const form = reactive({
    email: '',
    password: '',
    code: '',
    isCaptcha: true,
  });

  const rules = {
    username: { required: true, message: '请输入用户名', trigger: 'blur' },
    password: { required: true, message: '请输入密码', trigger: 'blur' },
  };

  const userStore = useUserStore();

  const router = useRouter();
  const route = useRoute();

  const handleSubmit = (e) => {
    e.preventDefault();
    formRef.value.validate(async (errors) => {
      if (!errors) {
        const { username, password } = form;
        message.loading('登录中...');
        loading.value = true;

        const params: FormState = {
          username,
          password,
        };

        try {
          const response = await userStore.login(params);
          message.destroyAll();
          if (response.access_token !== undefined) {
            const toPath = decodeURIComponent((route.query?.redirect || '/') as string);
            message.success('登录成功，即将进入系统');
            if (route.name === LOGIN_NAME) {
              router.replace('/');
            } else router.replace(toPath);
          } else {
            message.info(response.message || '登录失败');
          }
        } finally {
          loading.value = false;
        }
      } else {
        message.error('请填写完整信息，并且进行验证码校验');
      }
    });
  };

  const codeLoading = ref(false);
  function onGetCode() {
    formRef.value?.validate(
      (errors: any) => {
        if (errors) {
          console.error(errors);
        }
      },
      (rule: any) => {
        return rule?.key === 'email';
      }
    );
    codeLoading.value = true;
  }
</script>

<template>
  <div class="mt-4 login-content-form">
    <n-form ref="formRef" label-placement="left" size="large" :model="form" :rules="rules">
      <n-form-item path="email" class="login-animation1">
        <n-input v-model:value="form.email" placeholder="请输入邮箱">
          <template #prefix>
            <n-icon size="18" color="#808695">
              <SvgIcon icon="material-symbols:person-outline" />
            </n-icon>
          </template>
        </n-input>
      </n-form-item>
      <n-form-item path="code" class="login-animation2">
        <n-input v-model:value="form.code" placeholder="请输入验证码">
          <template #prefix>
            <n-icon size="18" color="#808695">
              <SvgIcon icon="ph:key" />
            </n-icon>
          </template>
          <template #suffix>
            <n-button @click="onGetCode()" :loading="codeLoading" text type="success"
              >获取验证码</n-button
            >
          </template>
        </n-input>
      </n-form-item>
      <n-form-item path="password" class="login-animation2">
        <n-input
          v-model:value="form.password"
          type="password"
          showPasswordOn="click"
          placeholder="请输入密码"
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
            登录
          </n-button>
        </n-space>
      </n-form-item>
    </n-form>
  </div>
</template>

<style scoped lang="less"></style>
