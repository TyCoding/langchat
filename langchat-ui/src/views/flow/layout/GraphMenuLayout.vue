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

<script setup lang="ts">
  import { nextTick, ref } from 'vue';

  const contextmenu = ref({
    show: false,
    x: 0,
    y: 0,
    options: [
      {
        label: 'Standard Node',
        key: 'standard',
      },
      {
        label: 'Paste',
        key: 'paste',
      },
      {
        label: 'divider',
        type: 'divider',
      },
      {
        label: 'New Text',
        key: 'text',
      },
    ],
  });

  function show(e: PointerEvent) {
    contextmenu.value.show = false;
    e.preventDefault();
    nextTick().then(() => {
      contextmenu.value.show = true;
      contextmenu.value.x = e.clientX;
      contextmenu.value.y = e.clientY;
    });
  }

  function handleGraphMenuSelect(key: string) {
    contextmenu.value.show = false;

    if (key === 'standard') {
    }
    if (key === 'paste') {
    }
  }

  defineExpose({ show });
</script>

<template>
  <!-- 画布空白区域的右键菜单 -->
  <n-dropdown
    class="custom-dropdown"
    size="small"
    trigger="manual"
    :x="contextmenu.x"
    :y="contextmenu.y"
    :options="contextmenu.options"
    :show="contextmenu.show"
    @clickoutside="contextmenu.show = false"
    @select="handleGraphMenuSelect"
  />
</template>

<style scoped lang="less"></style>
