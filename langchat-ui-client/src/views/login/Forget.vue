<script lang="ts" setup>
  import { SvgIcon } from '@/components/common';
  import { reactive, ref, toRaw } from 'vue';
  import { t } from '@/locales';
  import { useMessage } from 'naive-ui';
  import { useRouter } from 'vue-router';
  import { forget, getEmailCode } from '@/api/auth';
  import { rules } from '@/views/login/data';

  const formRef = ref();
  const message = useMessage();
  const loading = ref(false);
  const router = useRouter();
  const codeLoading = ref(false);
  const countdownRef = ref();
  const form = reactive({
    email: '',
    password: '',
    code: '',
  });

  const handleSubmit = (e: any) => {
    e.preventDefault();
    formRef.value.validate(async (errors: any) => {
      if (!errors) {
        loading.value = true;

        forget(toRaw(form))
          .then(async () => {
            message.success(t('login.forgetSubmitSuccess'));
            await router.replace('/login');
          })
          .catch(() => {
            loading.value = false;
          });
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
  <div class="account-root">
    <div class="account-root-item root-left-item">
      <div class="root-left-logo">
        <img src="@/assets/login/logo.png" alt="" />
        <div class="stand-title ml-1">{{ t('app') }}</div>
      </div>
      <div class="root-left-title">{{ t('login.slogan') }}</div>
      <div class="root-left-desc" v-html="t('login.des')"> </div>
      <div class="coding-img">
        <img src="@/assets/login/login_bg.svg" alt="" />
      </div>
    </div>
    <div class="account-root-item root-right-item">
      <div class="account-form">
        <div class="account-top">
          <div class="user-account">{{ t('login.forgetTitle') }}</div>
          <div class="user-register">
            <span>{{ t('login.noAccount') }}</span>
            <n-button @click="router.push('/register')" type="success" text>
              {{ t('login.toRegister') }}
            </n-button>
          </div>
        </div>

        <div class="mt-4 login-content-form">
          <n-form ref="formRef" label-placement="left" size="large" :model="form" :rules="rules">
            <n-form-item path="username" class="login-animation1">
              <n-input v-model:value="form.email" :placeholder="t('login.emailPlaceholder')">
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
                :placeholder="t('login.forgetPassPlaceholder')"
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
                  {{ t('login.forgetSubmit') }}
                </n-button>
              </n-space>
            </n-form-item>
          </n-form>
        </div>

        <n-divider>
          <template #default>
            <span class="social">{{ t('login.otherType') }}</span>
          </template>
        </n-divider>
        <div class="pb-8">
          <n-space vertical>
            <n-button dashed block round>
              <template #icon>
                <SvgIcon icon="uiw:weixin" />
              </template>
              {{ t('login.wxType') }}
            </n-button>
            <n-button block round dashed>
              <template #icon>
                <SvgIcon icon="ri:google-fill" />
              </template>
              {{ t('login.googleType') }}
            </n-button>
            <n-button block round dashed>
              <template #icon>
                <SvgIcon icon="ri:github-fill" />
              </template>
              {{ t('login.githubType') }}
            </n-button>
          </n-space>
        </div>
      </div>
    </div>
  </div>
</template>

<style lang="less">
  @import '@/styles/login';
</style>
