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
        <img alt="" src="@/assets/login/logo.png" />
        <div class="stand-title ml-1">{{ t('app') }}</div>
      </div>
      <div class="root-left-title">{{ t('login.slogan') }}</div>
      <div class="root-left-desc" v-html="t('login.des')"> </div>
      <div class="coding-img">
        <img alt="" src="@/assets/login/login_bg.svg" />
      </div>
    </div>
    <div class="account-root-item root-right-item">
      <div class="account-form">
        <div class="account-top">
          <template v-if="isLogin">
            <div class="user-account">{{ t('login.loginTip') }}</div>
            <div class="user-register">
              <span>{{ t('login.noAccount') }}</span>
              <n-button text type="success" @click="isLogin = false">
                {{ t('login.toRegister') }}
              </n-button>
            </div>
          </template>
          <template v-else>
            <div class="user-account">{{ t('login.registerTip') }}</div>
            <div class="user-register">
              <span>{{ t('login.hasAccount') }}</span>
              <n-button text type="success" @click="isLogin = true">
                {{ t('login.toLogin') }}
              </n-button>
            </div>
          </template>
        </div>

        <n-tabs v-if="isLogin" animated type="line">
          <n-tab-pane :tab="t('login.accountType')" name="chap1"><LoginForm /></n-tab-pane>
          <n-tab-pane :tab="t('login.emailType')" name="chap2"> <PhoneLoginForm /> </n-tab-pane>
        </n-tabs>
        <n-tabs v-else animated type="line">
          <n-tab-pane :tab="t('login.emailRegType')" name="chap1"><EmailRegister /></n-tab-pane>
          <n-tab-pane :tab="t('login.phoneRegType')" name="chap2"> <PhoneRegister /> </n-tab-pane>
        </n-tabs>
        <div class="flex !justify-end w-full text-end">
          <n-button text @click="router.push('/forget')"> {{ t('login.forget') }} </n-button>
        </div>

        <n-divider>
          <template #default>
            <span class="social">{{ t('login.otherType') }}</span>
          </template>
        </n-divider>
        <div class="pb-8">
          <n-space vertical>
            <n-button block dashed round @click="onSocialLogin">
              <template #icon>
                <SvgIcon icon="uiw:weixin" />
              </template>
              {{ t('login.wxType') }}
            </n-button>
            <n-button block dashed round @click="onSocialLogin">
              <template #icon>
                <SvgIcon icon="ri:google-fill" />
              </template>
              {{ t('login.googleType') }}
            </n-button>
            <n-button block dashed round @click="onSocialLogin">
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
