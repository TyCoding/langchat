<script lang="ts" setup>
  import LoginForm from './Login.vue';
  import PhoneLoginForm from './PhoneLogin.vue';
  import { SvgIcon } from '@/components/common';
  import { ref } from 'vue';
  import EmailRegister from './EmailRegister.vue';
  import PhoneRegister from './PhoneRegister.vue';

  const isLogin = ref(true);
</script>

<template>
  <div class="account-root">
    <div class="account-root-item root-left-item">
      <div class="root-left-logo">
        <img src="@/assets/login/logo.png" alt="" />
        <div class="stand-title ml-1">LangChain Chat</div>
      </div>
      <div class="root-left-title">开箱即用，中后台前端/设计解决方案</div>
      <div class="root-left-desc">多生态支持、功能丰富、高颜值模板</div>
      <div class="coding-img">
        <img src="@/assets/login/login_bg.svg" alt="" />
      </div>
    </div>
    <div class="account-root-item root-right-item">
      <div class="account-form">
        <div class="account-top">
          <template v-if="isLogin">
            <div class="user-account">登录你的账户</div>
            <div class="user-register">
              <span>没有账户？</span>
              <n-button @click="isLogin = false" type="success" text>去注册</n-button>
            </div>
          </template>
          <template v-else>
            <div class="user-account">注册你的账户</div>
            <div class="user-register">
              <span>已经有账户？</span>
              <n-button @click="isLogin = true" type="success" text>去登录</n-button>
            </div>
          </template>
        </div>

        <n-tabs v-if="isLogin" type="line" animated>
          <n-tab-pane name="chap1" tab="账号登录"><LoginForm /></n-tab-pane>
          <n-tab-pane name="chap2" tab="短信登录"> <PhoneLoginForm /> </n-tab-pane>
        </n-tabs>
        <n-tabs v-else type="line" animated>
          <n-tab-pane name="chap1" tab="邮箱注册"><EmailRegister /></n-tab-pane>
          <n-tab-pane name="chap2" tab="手机号注册"> <PhoneRegister /> </n-tab-pane>
        </n-tabs>

        <NDivider>
          <template #default>
            <span class="social">其他登录方式</span>
          </template>
        </NDivider>
        <div class="pb-8">
          <n-space justify="space-around">
            <n-button type="info" ghost circle>
              <template #icon>
                <SvgIcon icon="uiw:weixin" />
              </template>
            </n-button>
            <n-button type="info" ghost circle>
              <template #icon>
                <SvgIcon icon="bi:phone-fill" />
              </template>
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
