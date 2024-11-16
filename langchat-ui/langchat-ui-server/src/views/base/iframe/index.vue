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

<template>
  <n-spin :show="loading">
    <div class="frame">
      <iframe :src="frameSrc" class="frame-iframe" ref="frameRef"></iframe>
    </div>
  </n-spin>
</template>
<script lang="ts" setup>
  import { ref, unref, onMounted, nextTick } from 'vue';
  import { useRoute } from 'vue-router';

  const currentRoute = useRoute();
  const loading = ref(false);
  const frameRef = ref<HTMLFrameElement | null>(null);
  const frameSrc = ref<string>('');

  if (unref(currentRoute.meta)?.frameSrc) {
    frameSrc.value = unref(currentRoute.meta)?.frameSrc as string;
  }

  function hideLoading() {
    loading.value = false;
  }

  function init() {
    nextTick(() => {
      const iframe = unref(frameRef);
      if (!iframe) return;
      const _frame = iframe as any;
      if (_frame.attachEvent) {
        _frame.attachEvent('onload', () => {
          hideLoading();
        });
      } else {
        iframe.onload = () => {
          hideLoading();
        };
      }
    });
  }

  onMounted(() => {
    loading.value = true;
    init();
  });
</script>

<style lang="less" scoped>
  .frame {
    width: 100%;
    height: 100vh;

    &-iframe {
      width: 100%;
      height: 100%;
      overflow: hidden;
      border: 0;
      box-sizing: border-box;
    }
  }
</style>
