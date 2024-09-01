<script lang="ts" setup>
  import { SvgIcon } from '@/components/common';
  import { reactive, ref, toRaw } from 'vue';
  import { CountdownInst, useMessage } from 'naive-ui';
  import { useUserStore } from '@/store/modules/user';
  import { useRouter } from 'vue-router';
  import { emailRegister, getEmailCode } from '@/api/auth';
  import { t } from '@/locales';
  import { rules } from '@/layout/login/data';

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
          message.loading('注册成功');

          const response = await userStore.login({
            username: form.email,
            password: form.password,
          });
          if (response.token !== undefined) {
            userStore.changeIsLogin();
          } else {
            message.error(response.message || '注册失败');
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
  <div class="mt-6 flex-col gap-3">
    <n-form ref="formRef" :model="form" :rules="rules" size="large">
      <n-form-item class="!block" path="email">
        <n-input v-model:value="form.email" class="!rounded-lg" placeholder="请输入邮箱">
          <template #prefix>
            <n-icon color="#808695" size="18">
              <SvgIcon icon="ic:outline-email" />
            </n-icon>
          </template>
        </n-input>
      </n-form-item>
      <n-form-item class="!block" path="code">
        <n-input
          v-model:value="form.code"
          class="!rounded-lg"
          placeholder="请输入验证码"
          showPasswordOn="click"
        >
          <template #prefix>
            <n-icon color="#808695" size="18">
              <SvgIcon icon="ph:key-duotone" />
            </n-icon>
          </template>
          <template #suffix>
            <n-button
              :disabled="codeLoading"
              class="!rounded-lg"
              text
              type="success"
              @click="onGetCode()"
            >
              <n-countdown
                v-if="codeLoading"
                :active="codeLoading"
                :duration="59000"
                :render="({ seconds }) => `${String(seconds) + '秒失效'}`"
                @finish="codeLoading = false"
              />
              <template v-else>获取验证码</template>
            </n-button>
          </template>
        </n-input>
      </n-form-item>
      <n-form-item class="!block">
        <n-button attr-type="button" block class="!rounded-lg" type="primary" @click="onSubmit">
          立即注册
        </n-button>
      </n-form-item>
    </n-form>
  </div>
</template>

<style lang="less" scoped></style>
