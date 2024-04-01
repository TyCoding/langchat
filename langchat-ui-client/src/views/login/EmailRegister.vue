<script setup lang="ts">
  import { SvgIcon } from '@/components/common';
  import { reactive, ref, toRaw } from 'vue';
  import { useMessage, CountdownInst } from 'naive-ui';
  import { useUserStore } from '@/store/modules/user';
  import { useRouter } from 'vue-router';
  import { getEmailCode, emailRegister } from '@/api/auth';
  import { isBlank } from '@/utils/is';

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

  const rules = {
    email: {
      key: 'email',
      required: true,
      trigger: ['blur'],
      validator: (rule: any, value: string) => {
        if (isBlank(value)) {
          return new Error('请输入邮箱');
        }
        if (!/^\w+([-+.]\w+)*@\w+([-.]\w+)*\.\w+([-.]\w+)*$/.test(value)) {
          return new Error('邮箱格式错误');
        }
        return;
      },
    },
    code: {
      key: 'code',
      required: true,
      trigger: ['blur'],
      validator: (rule: any, value: string) => {
        if (isBlank(value)) {
          return new Error('请输入验证码');
        }
        if (String(value).length !== 6) {
          return new Error('验证码格式错误');
        }
        return true;
      },
    },
    password: {
      key: 'password',
      required: true,
      trigger: ['blur'],
      validator: (rule: any, value: string) => {
        if (isBlank(value)) {
          return new Error('请输入密码');
        }
        if (value.length < 6) {
          return new Error('密码长度至少为六位');
        }
        return true;
      },
    },
  };

  const onSubmit = (e) => {
    e.preventDefault();
    formRef.value.validate(async (errors: any) => {
      if (!errors) {
        loading.value = true;

        try {
          await emailRegister(toRaw(form));
          message.loading('注册成功，登录中...');

          const response = await userStore.login({
            username: form.email,
            password: form.password,
          });
          if (response.token !== undefined) {
            await router.replace('/');
          } else {
            message.error(response.message || '登录失败');
          }
        } finally {
          loading.value = false;
        }
      } else {
        message.error('请完善表单信息');
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
              message.success('验证码已成功发送，请检查邮箱');
            })
            .catch(() => {
              message.error('验证码发送失败，请稍后重试');
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
          placeholder="请输入邮箱"
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
        <n-input v-model:value="form.code" placeholder="请输入验证码">
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
                :render="({ seconds }) => `${String(seconds)}秒后重新获取`"
              />
              <template v-else>获取验证码</template>
            </n-button>
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
          <n-button type="primary" @click="onSubmit" :loading="loading" block secondary>
            注册
          </n-button>
        </n-space>
      </n-form-item>
    </n-form>
  </div>
</template>

<style scoped lang="less"></style>
