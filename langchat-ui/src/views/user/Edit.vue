<script setup lang="ts">
  import { ref } from 'vue';
  import { getById, add, update } from '@/api/user';
  import { useMessage } from 'naive-ui';
  import { t } from '@/locales';

  const message = useMessage();
  const emit = defineEmits(['fetch']);
  const form = ref({
    id: undefined,
    username: '',
    password: '',
    email: '',
    avatar: '',
    status: '',
  });
  const formRef = ref();
  const show = ref(false);
  const rules = {
    username: {
      required: true,
      message: t('user.usernameTips'),
      trigger: ['input', 'blur'],
    },
    password: {
      required: true,
      message: t('user.passwordTips'),
      trigger: ['input', 'blur'],
    },
    email: {
      required: true,
      message: t('user.emailTips'),
      trigger: ['input', 'blur'],
    },
  };

  async function onShow(id: string) {
    if (id !== undefined) {
      form.value = await getById(id);
    }
    show.value = true;
  }

  async function onSubmit(e: any) {
    e.preventDefault();
    formRef.value?.validate(async (errors: any) => {
      if (!errors) {
        if (form.value.id) {
          await update(form.value);
        } else {
          await add(form.value);
        }
        emit('fetch');
        onClose();
      } else {
        message.error(t('user.formValidate'));
      }
    });
  }

  function onClose() {
    form.value = {
      id: undefined,
      username: '',
      password: '',
      email: '',
      avatar: '',
      status: '',
    };
    show.value = false;
  }

  defineExpose({ onShow });
</script>

<template>
  <div>
    <n-modal
      v-model:show="show"
      preset="card"
      style="width: 40%"
      :title="t('common.editModal')"
      @after-leave="onClose"
    >
      <div class="mt-5">
        <n-form
          ref="formRef"
          :model="form"
          :rules="rules"
          label-placement="left"
          label-width="auto"
          require-mark-placement="right-hanging"
        >
          <n-form-item :label="t('user.username')" path="username">
            <n-input v-model:value="form.username" :placeholder="t('user.usernameTips')" />
          </n-form-item>
          <n-form-item :label="t('user.password')" path="password">
            <n-input
              v-model:value="form.password"
              type="password"
              :placeholder="t('user.passwordTips')"
            />
          </n-form-item>
          <n-form-item :label="t('user.email')" path="email">
            <n-input v-model:value="form.email" :placeholder="t('user.emailTips')" />
          </n-form-item>
          <n-form-item :label="t('user.avatar')" path="avatar">
            <n-input v-model:value="form.avatar" :placeholder="t('user.avatarTips')" />
          </n-form-item>
        </n-form>
      </div>
      <template #footer>
        <div class="flex justify-end gap-2">
          <n-button @click="onSubmit" type="success" secondary>{{ t('common.confirm') }}</n-button>
          <n-button @click="onClose" secondary>{{ t('common.cancel') }}</n-button>
        </div>
      </template>
    </n-modal>
  </div>
</template>

<style scoped lang="less"></style>
