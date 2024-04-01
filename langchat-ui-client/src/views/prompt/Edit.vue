<script setup lang="ts">
  import { ref } from 'vue';
  import { t } from '@/locales';
  import { getById, add, update } from '@/api/prompt';

  const emit = defineEmits(['fetch']);
  const form = ref({
    id: undefined,
    name: '',
    prompt: '',
  });
  const show = ref(false);
  const rules = ref([]);

  async function onShow(id: string) {
    if (id !== undefined) {
      form.value = await getById(id);
    }
    show.value = true;
  }

  async function onSubmit() {
    if (form.value.id) {
      await update(form.value);
    } else {
      await add(form.value);
    }
    emit('fetch');
    onClose();
  }

  function onClose() {
    form.value = {
      id: undefined,
      name: '',
      prompt: '',
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
          <n-form-item :label="t('prompt.name')" path="name">
            <n-input v-model:value="form.name" :placeholder="t('prompt.nameTips')" />
          </n-form-item>
          <n-form-item :label="t('prompt.prompt')" path="prompt">
            <n-input
              v-model:value="form.prompt"
              :placeholder="t('common.promptTips')"
              type="textarea"
              :autosize="{
                minRows: 12,
                maxRows: 12,
              }"
            />
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
