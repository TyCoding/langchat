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
