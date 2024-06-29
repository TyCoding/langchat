<script setup lang="ts">
  import { ref } from 'vue';
  import { v4 as uuidv4 } from 'uuid';
  import { ChatbubblesOutline, NavigateOutline, RefreshOutline } from '@vicons/ionicons5';
  import { exec as flowExec } from '@/api/aigc/flow';
  import { useNotification } from 'naive-ui';
  import { useRouter } from 'vue-router';

  const emits = defineEmits(['focus-active']);

  const middleRef = ref();
  const router = useRouter();
  const notification = useNotification();
  const content = ref('');
  const loading = ref(false);

  function handleFocus() {
    emits('focus-active');
  }

  const messages = ref<
    {
      id: string;
      role: 'human' | 'ai';
      content: string;
      type: 'q' | 'a' | 'loading' | 'error';
    }[]
  >([]);

  async function handleSubmit() {
    loading.value = true;

    try {
      let id = uuidv4();
      messages.value.push(
        {
          id: uuidv4(),
          role: 'human',
          content: content.value,
          type: 'q',
        },
        {
          id: id,
          role: 'ai',
          content: '',
          type: 'loading',
        }
      );
      const items = messages.value.filter((i) => i.id == id);
      await flowExec(
        String(router.currentRoute.value.params.id),
        {
          content: content.value,
        },
        ({ event }) => {
          console.log('response', event);
          const { responseText } = event.target;

          try {
            const data = JSON.parse(responseText);
            notification.error({
              duration: 5000,
              content: data.message,
              meta: '请检查Flow流程设计。',
            });
            items[0].type = 'error';
            items[0].content = 'Error！ ';
          } catch (e) {
            items[0].content = responseText;
            items[0].type = 'a';
          }

          scrollToBottom();
        }
      );
      content.value = '';
      loading.value = false;
    } finally {
      loading.value = false;
      scrollToBottom();
    }
  }

  const scrollToBottom = () => {
    const middleElement = middleRef.value;
    if (middleElement) {
      middleElement.scrollTop = middleElement.scrollHeight;
    }
  };

  function handleEnter(event: KeyboardEvent) {
    if (event.key === 'Enter' && event.ctrlKey) {
    } else if (event.key === 'Enter') {
      event.preventDefault();
      handleSubmit();
    }
  }
</script>

<template>
  <div class="container relative h-full card-shadow rounded-xl mb-2">
    <div class="top absolute top-0 left-0 w-full h-10 z-10 border-b border-1">
      <div class="w-full flex justify-between items-center p-2 absolute">
        <div>
          <n-badge type="success" dot />
          <div class="inline-block ml-2">会话测试</div>
        </div>
        <n-button text class="mr-2">
          <n-icon size="16" color="#18a058">
            <RefreshOutline />
          </n-icon>
        </n-button>
      </div>
    </div>

    <div
      ref="middleRef"
      class="middle absolute top-10 left-0 w-full bottom-[65px] z-0 overflow-y-auto"
    >
      <div v-if="messages.length == 0" class="flex-1 flex mt-5 h-full justify-center">
        <div class="w-1/2 flex flex-col justify-center text-xs items-center gap-2">
          <n-icon size="40" color="#e4e4e7">
            <ChatbubblesOutline />
          </n-icon>
          <div class="text-[#69696b]">输入内容开始测试会话...</div>
        </div>
      </div>
      <div v-else class="flex-1 overflow-y-auto mb-1">
        <div class="h-full w-full flex flex-col space-y-3 relative p-2 pl-4 pr-4 mt-2">
          <template v-for="item in messages" :key="item">
            <div
              v-if="item.role == 'human'"
              class="flex justify-end p-1.5 rounded select-text self-end"
              style="background: #d2f9d1"
              >{{ item.content }}
            </div>
            <div
              v-if="item.role == 'ai'"
              class="flex justify-start items-center rounded self-start min-w-[40px] min-h-[33px]"
              :style="
                item.type === 'error' ? 'color: #d03050;background:#d0305029' : 'background:#f4f6f8'
              "
            >
              <div v-if="item.type === 'loading'" class="flex justify-center items-center w-[55px]">
                <span class="dot-pulse"></span>
              </div>
              <div class="p-1.5" v-else>{{ item.content }}</div>
            </div>
          </template>
        </div>
      </div>
    </div>

    <div class="bottom absolute bottom-4 left-0 w-full h-[60px] z-10">
      <div class="pl-5 pr-5 flex justify-center items-center space-x-2 w-full">
        <n-input
          @focus="handleFocus"
          v-model:value="content"
          class="w-full rounded-2xl px-1 py-0.5 text-xs"
          :disabled="loading"
          @keypress="handleEnter"
        >
          <template #suffix>
            <n-button @click="handleSubmit" :loading="loading" size="small" text>
              <template #icon>
                <n-icon color="#18a058">
                  <NavigateOutline />
                </n-icon>
              </template>
            </n-button>
          </template>
        </n-input>
      </div>
    </div>
  </div>
</template>

<style scoped lang="less"></style>
