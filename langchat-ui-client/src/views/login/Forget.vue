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
          <div class="user-account">{{ t('login.forgetTitle') }}</div>
          <div class="user-register">
            <span>{{ t('login.noAccount') }}</span>
            <n-button text type="success" @click="router.push('/login')">
              {{ t('login.toLogin') }}
            </n-button>
          </div>
        </div>

        <div class="mt-4 login-content-form">
          <n-form ref="formRef" :model="form" :rules="rules" label-placement="left" size="large">
            <n-form-item class="login-animation1" path="username">
              <n-input v-model:value="form.email" :placeholder="t('login.emailPlaceholder')">
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
                :placeholder="t('login.forgetPassPlaceholder')"
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
                <n-button :loading="loading" block secondary type="primary" @click="handleSubmit">
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
            <n-button block dashed round>
              <template #icon>
                <SvgIcon icon="uiw:weixin" />
              </template>
              {{ t('login.wxType') }}
            </n-button>
            <n-button block dashed round>
              <template #icon>
                <SvgIcon icon="ri:google-fill" />
              </template>
              {{ t('login.googleType') }}
            </n-button>
            <n-button block dashed round>
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
