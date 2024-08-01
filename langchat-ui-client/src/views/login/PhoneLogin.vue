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
  import { reactive, ref } from 'vue';
  import { t } from '@/locales';
  import { rules } from '@/views/login/data';
  import { useMessage } from 'naive-ui';

  const formRef = ref();
  const message = useMessage();
  const loading = ref(false);
  const codeLoading = ref(false);
  const form = reactive({
    username: '',
    code: '',
  });

  const handleSubmit = (e: any) => {};

  function onGetCode() {
    message.warning('暂时未接入短信登录方式');
    // codeLoading.value = true;
  }
</script>

<template>
  <div class="mt-4 login-content-form">
    <n-form ref="formRef" :model="form" :rules="rules" label-placement="left" size="large">
      <n-form-item class="login-animation1" path="username">
        <n-input v-model:value="form.username" :placeholder="t('login.phonePlaceholder')">
          <template #prefix>
            <n-icon color="#808695" size="18">
              <SvgIcon icon="material-symbols:person-outline" />
            </n-icon>
          </template>
        </n-input>
      </n-form-item>
      <n-form-item class="login-animation2" path="code">
        <n-input
          v-model:value="form.code"
          :placeholder="t('login.codePlaceholder')"
          showPasswordOn="click"
        >
          <template #prefix>
            <n-icon color="#808695" size="18">
              <SvgIcon icon="ph:key-duotone" />
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

      <n-form-item class="login-animation3">
        <n-space class="w-full" vertical>
          <n-button
            :loading="loading"
            block
            disabled
            secondary
            type="primary"
            @click="handleSubmit"
          >
            {{ t('login.title') }}
          </n-button>
        </n-space>
      </n-form-item>
    </n-form>
  </div>
</template>

<style lang="less" scoped></style>
