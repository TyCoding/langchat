<script setup lang="ts">
  import { computed, ref, watch } from 'vue';
  import { SvgIcon } from '@/components/common';
  import { t } from '@/locales';

  const props = defineProps<{
    loading: boolean;
    genText: string;
  }>();
  const loading = computed(() => {
    return props.loading;
  });
  const emit = defineEmits(['generate', 'case']);
  const text = ref('');
  const gen = ref('');

  watch(
    () => props.genText,
    (val) => {
      gen.value = val;
    }
  );

  function onCase() {
    text.value = `Sequence diagrams`;
    gen.value = `sequenceDiagram
    participant Alice
    participant Bob
    Alice->>John: Hello John, how are you?
    loop HealthCheck
        John->>John: Fight against hypochondria
    end
    Note right of John: Rational thoughts <br/>prevail!
    John-->>Alice: Great!
    John->>Bob: How about you?
    Bob-->>John: Jolly good!
		`;
    emit('case', gen.value);
  }

  function onGenerate() {
    emit('generate', text.value);
  }
</script>

<template>
  <div class="p-4">
    <div class="pb-2">{{ t('mindmap.des') }}</div>
    <n-input
      :disabled="loading"
      v-model:value="text"
      :placeholder="t('mindmap.inputTips')"
      type="textarea"
      :rows="6"
    />
    <div class="mt-2 mb-2">
      <n-button :loading="loading" @click="onGenerate" type="success" secondary block>
        <template #icon>
          <SvgIcon class="text-lg" icon="ion:sparkles-outline" />
        </template>
        {{ t('mindmap.confirm') }}
      </n-button>
    </div>

    <div class="mt-6">
      <div class="flex flex-wrap justify-between items-center mb-2">
        <div>{{ t('mindmap.output') }}</div>
        <n-button @click="onCase" type="success" text>{{ t('mindmap.example') }}</n-button>
      </div>
    </div>
    <n-input
      v-model:value="gen"
      :placeholder="t('mindmap.outputTips')"
      type="textarea"
      :rows="16"
    />
  </div>
</template>

<style scoped lang="less"></style>
