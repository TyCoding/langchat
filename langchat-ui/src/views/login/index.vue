<script lang="ts" setup>
  import { reactive, ref } from 'vue';
  import { useRoute, useRouter } from 'vue-router';
  import { useUserStore } from '@/store/modules/user';
  import { useMessage } from 'naive-ui';
  import { LockClosedOutline, PersonOutline } from '@vicons/ionicons5';
  import { PageEnum } from '@/enums/pageEnum';
  import { websiteConfig } from '@/config/website.config';

  interface FormState {
    username: string;
    password: string;
  }

  const formRef = ref();
  const message = useMessage();
  const loading = ref(false);
  const LOGIN_NAME = PageEnum.BASE_LOGIN_NAME;

  const formInline = reactive({
    username: 'administrator',
    password: '123456',
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
        const { username, password } = formInline;
        message.loading('登录中...');
        loading.value = true;

        const params: FormState = {
          username,
          password,
        };

        try {
          await userStore.login(params);
          const toPath = decodeURIComponent((route.query?.redirect || '/') as string);
          message.success('登录成功，即将进入系统');
          if (route.name === LOGIN_NAME) {
            router.replace('/');
          } else router.replace(toPath);
        } finally {
          loading.value = false;
        }
      } else {
        message.error('请填写完整信息，并且进行验证码校验');
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
        <n-form
          ref="formRef"
          :model="formInline"
          :rules="rules"
          label-placement="left"
          size="large"
        >
          <n-form-item class="login-animation1" path="username">
            <n-input v-model:value="formInline.username" placeholder="请输入用户名">
              <template #prefix>
                <n-icon color="#808695" size="18">
                  <PersonOutline />
                </n-icon>
              </template>
            </n-input>
          </n-form-item>
          <n-form-item class="login-animation2" path="password">
            <n-input
              v-model:value="formInline.password"
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
          <n-form-item class="login-animation3 mt-4">
            <n-button :loading="loading" block size="large" type="primary" @click="handleSubmit">
              登录
            </n-button>
          </n-form-item>

          <div class="relative flex items-center justify-between login-animation4">
            <div class="text-sm ml-auto">
              <a class="text-blue-500 hover:text-blue-600" href="#"> 验证码登录 </a>
              <a class="ml-2 text-blue-500 hover:text-blue-600" href="#"> 注册账号 </a>
            </div>
          </div>
          <div class="mt-10 text-xs text-gray-300 login-animation5 text-center">
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
        padding: 60px 45px;
      }
    }

    &-top {
      padding: 32px 0 20px 0;
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
