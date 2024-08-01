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
  import { reactive, ref, toRaw } from 'vue';
  import { useRouter } from 'vue-router';
  import { useUserStore } from '@/store/modules/user';
  import { useMessage } from 'naive-ui';
  import { LockClosedOutline, PersonOutline, PhonePortraitOutline } from '@vicons/ionicons5';
  import { PageEnum } from '@/enums/pageEnum';
  import { websiteConfig } from '@/config/website.config';

  const formRef = ref();
  const message = useMessage();
  const loading = ref(false);
  const userStore = useUserStore();
  const router = useRouter();
  const form = reactive({
    username: '',
    password: '',
    phone: '',
  });

  const rules = {
    username: { required: true, message: '请输入用户名', trigger: 'blur' },
    password: {
      required: true,
      trigger: 'blur',
      validator: (rule: any, value: string) => {
        return new Promise<void>((resolve, reject) => {
          if (value.length == 0) {
            reject(new Error('请输入密码'));
          }
          if (value.length < 6) {
            reject(new Error('密码长度至少6位'));
          } else {
            resolve();
          }
        });
      },
    },
    phone: {
      required: true,
      trigger: 'blur',
      validator: (rule: any, value: string) => {
        return new Promise<void>((resolve, reject) => {
          if (value.length == 0) {
            reject(new Error('请输入手机号'));
          }
          if (!/^[1]+[3,8]+\d{9}$/.test(value)) {
            reject(new Error('手机号格式错误'));
          } else {
            resolve();
          }
        });
      },
    },
  };

  const handleSubmit = (e) => {
    e.preventDefault();
    formRef.value.validate(async (errors: any) => {
      if (!errors) {
        loading.value = true;
        try {
          await userStore.register(toRaw(form));
          message.success('注册成功，请重新登陆');
          await router.push(PageEnum.BASE_LOGIN);
        } finally {
          loading.value = false;
        }
      }
    });
  };
</script>

<template>
  <div class="view-account">
    <div class="view-account-header"></div>
    <div class="view-account-container">
      <div class="view-account-top">
        <div class="view-account-top-logo">
          <img :src="websiteConfig.loginImage" alt="" />
        </div>
        <div class="view-account-top-desc">{{ websiteConfig.loginDesc }}</div>
      </div>
      <div class="view-account-form">
        <div class="text-center text-2xl mb-8">Register {{ websiteConfig.title }}</div>
        <n-form ref="formRef" :model="form" :rules="rules" label-placement="left" size="large">
          <n-form-item class="login-animation1" path="username">
            <n-input v-model:value="form.username" placeholder="请输入用户名">
              <template #prefix>
                <n-icon color="#808695" size="18">
                  <PersonOutline />
                </n-icon>
              </template>
            </n-input>
          </n-form-item>
          <n-form-item class="login-animation2" path="password">
            <n-input
              v-model:value="form.password"
              placeholder="请输入密码"
              showPasswordOn="click"
              type="password"
            >
              <template #prefix>
                <n-icon color="#808695" size="18">
                  <LockClosedOutline />
                </n-icon>
              </template>
            </n-input>
          </n-form-item>
          <n-form-item class="login-animation3" path="phone">
            <n-input v-model:value="form.phone" placeholder="请输入手机号" showPasswordOn="click">
              <template #prefix>
                <n-icon color="#808695" size="18">
                  <PhonePortraitOutline />
                </n-icon>
              </template>
            </n-input>
          </n-form-item>
          <n-form-item class="login-animation3 mt-2">
            <n-button :loading="loading" block size="large" type="primary" @click="handleSubmit">
              注册
            </n-button>
          </n-form-item>
          <div class="login-animation4 mb-3">
            <div class="w-full flex justify-end gap-2">
              <n-text class="cursor-pointer" type="info" @click="router.push(PageEnum.BASE_LOGIN)">
                返回登录
              </n-text>
            </div>
          </div>
          <div class="mt-8 text-xs text-gray-300 login-animation5 text-center">
            * 温馨提示：建议使用谷歌、Microsoft Edge，版本 80 及以上浏览器，360浏览器请使用极速模式
          </div>
        </n-form>
      </div>
    </div>
  </div>
</template>

<style lang="less" scoped>
  .view-account {
    background-color: #f8f8f8;
    display: flex;
    flex-direction: column;
    height: 100vh;
    overflow: auto;

    &-container {
      flex: 1;
      padding: 32px 12px;
      max-width: 400px;
      min-width: 400px;
      margin: 0 auto;

      .view-account-form {
        background: white;
        border-radius: 15px;
        padding: 30px 45px 40px 45px;
      }
    }

    &-top {
      padding: 32px 0 25px 0;
      text-align: center;

      &-desc {
        font-size: 14px;
        color: #808695;
      }
    }

    &-other {
      width: 100%;
    }

    .default-color {
      color: #515a6e;

      .ant-checkbox-wrapper {
        color: #515a6e;
      }
    }
  }

  @media (min-width: 768px) {
    .view-account {
      background-image: url('../../assets/images/login.svg');
      background-repeat: no-repeat;
      background-position: 50%;
      background-size: 100%;
    }

    .page-account-container {
      padding: 32px 0 24px 0;
    }
  }

  @keyframes anim-num {
    0% {
      transform: translateY(60px);
      opacity: 0;
    }
    100% {
      transform: translateY(0);
      opacity: 1;
    }
  }
  .login-animation1 {
    opacity: 0;
    animation-name: anim-num;
    animation-duration: 0.5s;
    animation-fill-mode: forwards;
    animation-delay: 0.1s;
  }
  .login-animation2 {
    opacity: 0;
    animation-name: anim-num;
    animation-duration: 0.5s;
    animation-fill-mode: forwards;
    animation-delay: 0.2s;
  }
  .login-animation3 {
    opacity: 0;
    animation-name: anim-num;
    animation-duration: 0.5s;
    animation-fill-mode: forwards;
    animation-delay: 0.3s;
  }
  .login-animation4 {
    opacity: 0;
    animation-name: anim-num;
    animation-duration: 0.5s;
    animation-fill-mode: forwards;
    animation-delay: 0.4s;
  }
  .login-animation5 {
    opacity: 0;
    animation-name: anim-num;
    animation-duration: 0.5s;
    animation-fill-mode: forwards;
    animation-delay: 0.5s;
  }
</style>
