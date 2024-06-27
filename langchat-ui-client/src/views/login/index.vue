<script lang="ts" setup>
  import LoginForm from './Login.vue';
  import PhoneLoginForm from './PhoneLogin.vue';
  import { SvgIcon } from '@/components/common';
  import { ref } from 'vue';
  import EmailRegister from './EmailRegister.vue';
  import PhoneRegister from './PhoneRegister.vue';
  import { t } from '@/locales';
  import { router } from '@/router';
  import { useMessage } from 'naive-ui';

  const ms = useMessage();
  const isLogin = ref(true);

  function onSocialLogin() {
    ms.warning(t('login.socialLogin'));
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
          <template v-if="isLogin">
            <div class="user-account">{{ t('login.loginTip') }}</div>
            <div class="user-register">
              <span>{{ t('login.noAccount') }}</span>
              <n-button @click="isLogin = false" type="success" text>
                {{ t('login.toRegister') }}
              </n-button>
            </div>
          </template>
          <template v-else>
            <div class="user-account">{{ t('login.registerTip') }}</div>
            <div class="user-register">
              <span>{{ t('login.hasAccount') }}</span>
              <n-button @click="isLogin = true" type="success" text>
                {{ t('login.toLogin') }}
              </n-button>
            </div>
          </template>
        </div>

        <n-tabs v-if="isLogin" type="line" animated>
          <n-tab-pane name="chap1" :tab="t('login.accountType')"><LoginForm /></n-tab-pane>
          <n-tab-pane name="chap2" :tab="t('login.emailType')"> <PhoneLoginForm /> </n-tab-pane>
        </n-tabs>
        <n-tabs v-else type="line" animated>
          <n-tab-pane name="chap1" :tab="t('login.emailRegType')"><EmailRegister /></n-tab-pane>
          <n-tab-pane name="chap2" :tab="t('login.phoneRegType')"> <PhoneRegister /> </n-tab-pane>
        </n-tabs>
        <div class="flex !justify-end w-full text-end">
          <n-button @click="router.push('/forget')" text> {{ t('login.forget') }} </n-button>
        </div>

        <n-divider>
          <template #default>
            <span class="social">{{ t('login.otherType') }}</span>
          </template>
        </n-divider>
        <div class="pb-8">
          <n-space vertical>
            <n-button @click="onSocialLogin" dashed block round>
              <template #icon>
                <SvgIcon icon="uiw:weixin" />
              </template>
              {{ t('login.wxType') }}
            </n-button>
            <n-button @click="onSocialLogin" block round dashed>
              <template #icon>
                <SvgIcon icon="ri:google-fill" />
              </template>
              {{ t('login.googleType') }}
            </n-button>
            <n-button @click="onSocialLogin" block round dashed>
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
