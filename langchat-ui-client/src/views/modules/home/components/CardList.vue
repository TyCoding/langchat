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
  import { NEmpty } from 'naive-ui';
  import { Bot } from '@/api/models';
  import { useRouter } from 'vue-router';
  import { t } from '@/locales';
  import { useChatStore } from '@/views/modules/chat/store/useChatStore';

  interface Props {
    list: Array<Bot>;
  }
  const props = defineProps<Props>();
  const chatStore = useChatStore();
  const router = useRouter();

  async function onUse(id: string) {
    chatStore.selectPromptId = id;
    await router.push('/chat');
  }
</script>

<template>
  <n-grid :x-gap="12" :y-gap="12" class="mt-4" cols="1 400:2 1200:3 1300:4">
    <n-grid-item v-for="item in props.list" :key="item">
      <n-card content-style="padding:0px" hoverable>
        <div>
          <n-thing class="inline-block bg-white dark:bg-[#34373f] p-4 rounded-[2px] cursor-pointer">
            <template #avatar>
              <n-avatar :size="80">
                <SvgIcon v-if="item.icon !== null" :icon="item.icon" class="text-2xl" />
                <span v-else class="text-2xl">{{ String(item.name).substring(0, 1) }}</span>
              </n-avatar>
            </template>
            <template #header>
              <n-ellipsis class="text-[18px]" style="max-width: 200px">
                {{ item.name }}
              </n-ellipsis>
            </template>
            <template #description>
              <n-ellipsis :line-clamp="3" class="text-[14px] text-gray-400 h-[68px]">
                {{ item.prompt }}
                <template #tooltip>
                  <div style="width: 400px">
                    {{ item.prompt }}
                  </div>
                </template>
              </n-ellipsis>
            </template>
            <template #footer>
              <div class="flex justify-between items-center">
                <div class="flex gap-1">
                  <n-tag v-for="tag in item.tags" :key="tag" size="small">{{ tag }}</n-tag>
                </div>
                <n-button round secondary size="small" type="success" @click="onUse(item.id)">
                  {{ t('home.use') }}
                </n-button>
              </div>
            </template>
          </n-thing>
        </div>
      </n-card>
    </n-grid-item>
  </n-grid>

  <n-empty v-if="props.list.length == 0" :description="t('home.empty')" class="mt-4" />
</template>

<style lang="less" scoped></style>
