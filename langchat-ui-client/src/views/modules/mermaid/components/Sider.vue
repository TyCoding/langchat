<script setup lang="ts">
  import { computed, ref, watch } from 'vue';
  import { SvgIcon } from '@/components/common';
  import { t } from '@/locales';

  const props = defineProps<{
    loading: boolean;
    mermaidText: string;
  }>();
  const loading = computed(() => {
    return props.loading;
  });
  const emit = defineEmits(['generate', 'case']);
  const text = ref('');
  const gen = ref('');

  watch(
    () => props.mermaidText,
    (val) => {
      gen.value = val;
    }
  );

  function onCase() {
    text.value = `Sequence diagrams`;
    gen.value = `
stateDiagram-v2
[*] --> LoginScreen
LoginScreen --> EnterUsername: Username Entered
LoginScreen --> EnterPassword: Password Entered
EnterUsername --> ValidateUsername: Validating
EnterPassword --> ValidatePassword: Validating
ValidateUsername --> InvalidUsername: Invalid
ValidatePassword --> InvalidPassword: Invalid
ValidateUsername --> ValidatedUsername: Valid
ValidatePassword --> ValidatedPassword: Valid
ValidatedUsername --> SubmitForm: Ready to Submit
ValidatedPassword --> SubmitForm
SubmitForm --> LoginSuccess: Success
SubmitForm --> LoginFailure: Failure
InvalidUsername --> LoginScreen: Retry
InvalidPassword --> LoginScreen: Retry
		`;
    emit('case', gen.value);
  }

  function onGenerate() {
    emit('generate', text.value);
  }
</script>

<template>
  <div class="p-4">
    <n-input
      :disabled="loading"
      v-model:value="text"
      :placeholder="t('mermaid.inputTips')"
      type="textarea"
      :rows="6"
    />
    <div class="mt-2 mb-2">
      <n-button :loading="loading" @click="onGenerate" type="success" secondary block>
        <template #icon>
          <SvgIcon class="text-lg" icon="ion:sparkles-outline" />
        </template>
        {{ t('mermaid.confirm') }}
      </n-button>
    </div>

    <div class="mt-6">
      <div class="flex flex-wrap justify-between items-center mb-2">
        <div>{{ t('mermaid.output') }}</div>
        <n-button @click="onCase" type="success" text>{{ t('mermaid.example') }}</n-button>
      </div>
    </div>
    <n-input
      v-model:value="gen"
      :placeholder="t('mermaid.outputTips')"
      type="textarea"
      :rows="16"
    />
  </div>
</template>

<style scoped lang="less"></style>
