<script lang="ts" setup>
  import { reactive, ref, toRaw } from 'vue';
  import { SvgIcon } from '@/components/common';
  import { useMessage } from 'naive-ui';
  import { useUserStore } from '@/store';
  import Register from '@/layout/login/Register.vue';

  const userStore = useUserStore();
  const isRegister = ref(false);
  const isPhone = ref(false);
  const rules = [];
  const formRef = ref();
  const message = useMessage();
  const loading = ref(false);
  const codeLoading = ref(false);
  const form = reactive({
    username: 'langchat@outlook.com',
    password: 'langchat',
    code: '',
  });

  const handleSubmit = (e: any) => {
    e.preventDefault();
    formRef.value.validate(async (errors: any) => {
      if (!errors) {
        loading.value = true;

        userStore
          .login(toRaw(form))
          .then(async () => {
            message.success('登录成功');
            userStore.changeIsLogin();
          })
          .catch(() => {
            loading.value = false;
          });
      }
    });
  };

  function onGetCode() {
    message.warning('暂时未接入短信登录方式');
  }

  function onClose() {
    userStore.changeIsLogin();
  }
</script>

<template>
  <div>
    <n-modal v-model:show="userStore.isLogin" :mask-closable="false">
      <div
        class="rounded-2xl overflow-hidden flex justify-between items-center !text-white h-[450px] relative"
      >
        <div class="absolute right-3 top-3 cursor-pointer" @click="onClose">
          <SvgIcon class="text-black text-xl" icon="material-symbols:close" />
        </div>
        <div class="hidden sm:flex bg-blue-500 h-full justify-center items-start w-[350px]">
          <div class="px-[40px] py-[80px] flex flex-col gap-4 text-center">
            <div class="text-2xl font-bold">欢迎登录LangChat</div>
            <div class="text-sm">开启你的AI创作之路吧！</div>

            <div class="flex flex-col gap-4 mt-6 justify-start items-start text-gray-100 text-sm">
              <div>• 基于Java全生态，更易对接企业级应用</div>
              <div>• 精美的UI设计，舒适的视觉体验</div>
              <div>• 支持接入国内外数十家模型</div>
              <div>• 完整且规范的项目架构设计</div>
              <div>• 更多的应用场景</div>
              <div>......</div>
            </div>
          </div>
        </div>

        <div v-if="!isRegister" class="bg-white h-full justify-center items-start w-[350px]">
          <div
            v-if="isPhone"
            class="p-10 flex flex-col gap-4 !text-black px-[40px] py-[80px] w-full"
          >
            <div class="text-xl text-center">手机验证码登录</div>
            <div class="mt-6 flex-col gap-3">
              <n-form ref="formRef" :model="form" :rules="rules" size="large">
                <n-form-item class="!block" path="username">
                  <n-input
                    v-model:value="form.username"
                    class="!rounded-lg"
                    placeholder="请输入手机号"
                  >
                    <template #prefix>
                      <n-icon color="#808695" size="18">
                        <SvgIcon icon="fluent:phone-20-regular" />
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
                  <div class="flex flex-col gap-2 w-full">
                    <n-button attr-type="button" block class="!rounded-lg" type="primary">
                      立即登录
                    </n-button>
                    <n-button
                      block
                      class="!rounded-lg"
                      secondary
                      type="info"
                      @click="isPhone = !isPhone"
                      >账户密码登录</n-button
                    >
                  </div>
                </n-form-item>
              </n-form>
            </div>
            <n-button text @click="isRegister = true">没有账号？去注册</n-button>
          </div>

          <div v-else class="p-10 flex flex-col gap-4 !text-black px-[40px] py-[80px] w-full">
            <div class="text-xl text-center">邮箱密码登录</div>
            <div class="mt-6 flex-col gap-3">
              <n-form ref="formRef" :model="form" :rules="rules" size="large">
                <n-form-item class="!block" path="username">
                  <n-input
                    v-model:value="form.username"
                    class="!rounded-lg"
                    placeholder="请输入用户名/邮箱"
                  >
                    <template #prefix>
                      <n-icon color="#808695" size="18">
                        <SvgIcon icon="fluent:phone-20-regular" />
                      </n-icon>
                    </template>
                  </n-input>
                </n-form-item>
                <n-form-item class="!block" path="password">
                  <n-input
                    v-model:value="form.password"
                    class="!rounded-lg"
                    placeholder="请输入密码"
                    showPasswordOn="click"
                  >
                    <template #prefix>
                      <n-icon color="#808695" size="18">
                        <SvgIcon icon="mdi:lock-outline" />
                      </n-icon>
                    </template>
                  </n-input>
                </n-form-item>
                <n-form-item class="!block">
                  <div class="flex flex-col gap-2 w-full">
                    <n-button
                      attr-type="button"
                      block
                      class="!rounded-lg"
                      type="primary"
                      @click="handleSubmit"
                    >
                      立即登录
                    </n-button>
                    <!--                    <n-button-->
                    <!--                      block-->
                    <!--                      class="!rounded-lg"-->
                    <!--                      secondary-->
                    <!--                      type="info"-->
                    <!--                      @click="isPhone = !isPhone"-->
                    <!--                      >手机验证码登录</n-button-->
                    <!--                    >-->
                  </div>
                </n-form-item>
              </n-form>
            </div>
            <n-button text @click="isRegister = true">没有账号？去注册</n-button>
          </div>
        </div>

        <div v-else class="bg-white h-full justify-center items-start w-[350px]">
          <div class="p-10 flex flex-col gap-4 !text-black px-[40px] py-[80px] w-full">
            <div class="text-xl text-center">注册LangChat</div>
            <Register />
            <n-button text type="info" @click="isRegister = false">已经有账号？去登录</n-button>
          </div>
        </div>
      </div>
    </n-modal>
  </div>
</template>

<style lang="less" scoped></style>
