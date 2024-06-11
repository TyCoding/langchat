<script setup lang="ts">
  import { SvgIcon } from '@/components/common';
  import { reactive, ref } from 'vue';
  import { t } from '@/locales';
  import { rules } from '@/views/login/data';

  const formRef = ref();
  const loading = ref(false);
  const codeLoading = ref(false);
  const form = reactive({
    username: '',
    code: '',
  });

  const handleSubmit = (e: any) => {};

  function onGetCode() {
    codeLoading.value = true;
  }
</script>

<template>
  <div class="mt-4 login-content-form">
    <n-form ref="formRef" label-placement="left" size="large" :model="form" :rules="rules">
      <n-form-item path="username" class="login-animation1">
        <n-input v-model:value="form.username" :placeholder="t('login.phonePlaceholder')">
          <template #prefix>
            <n-icon size="18" color="#808695">
              <SvgIcon icon="material-symbols:person-outline" />
            </n-icon>
          </template>
        </n-input>
      </n-form-item>
      <n-form-item path="code" class="login-animation2">
        <n-input
          v-model:value="form.code"
          showPasswordOn="click"
          :placeholder="t('login.codePlaceholder')"
        >
          <template #prefix>
            <n-icon size="18" color="#808695">
              <SvgIcon icon="ph:key-duotone" />
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

      <n-form-item class="login-animation3">
        <n-space vertical class="w-full">
          <n-button
            disabled
            type="primary"
            @click="handleSubmit"
            :loading="loading"
            block
            secondary
          >
            {{ t('login.title') }}
          </n-button>
        </n-space>
      </n-form-item>
    </n-form>
  </div>
</template>

<style scoped lang="less"></style>
