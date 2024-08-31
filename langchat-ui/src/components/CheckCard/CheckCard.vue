<template>
  <n-space :justify="justify">
    <template v-for="item in dataSource" :key="item.key">
      <div style="height: 96px" @click="handleChecked(item)">
        <n-list
          :class="checked == item.key ? 'check-list-checked' : ''"
          bordered
          class="check-list"
        >
          <n-list-item>
            <template #prefix>
              <n-avatar class="!text-black">
                <n-icon>
                  <component :is="item.icon" />
                </n-icon>
              </n-avatar>
            </template>
            <n-thing>
              <template #header>{{ item.title }}</template>
              <template #description>{{ item.label }}</template>
            </n-thing>
            <template #suffix>
              <n-icon :color="checked == item.key ? '#18a058' : '#eee'" size="20">
                <CheckmarkCircle />
              </n-icon>
            </template>
          </n-list-item>
        </n-list>
      </div>
    </template>
  </n-space>
</template>
<script lang="ts">
  import { Component, computed, defineComponent, onMounted, ref } from 'vue';
  import { CheckmarkCircle } from '@vicons/ionicons5';

  export type CheckSource = {
    key: string;
    icon: Component;
    title: string;
    label: string;
    isHidden?: boolean | undefined;
  };

  const props = {
    dataSource: {
      type: Array<CheckSource>,
      default: [],
    },
    defaultChecked: {
      type: String,
      default: '',
    },
    justify: {
      type: String as () =>
        | 'start'
        | 'end'
        | 'center'
        | 'space-around'
        | 'space-between'
        | 'space-evenly',
      default: 'space-between',
    },
  };

  export default defineComponent({
    name: 'CheckCard',
    components: {
      CheckmarkCircle,
    },
    props,
    emits: ['onChecked'],
    setup(props, { emit }) {
      const checked = ref<string>();

      onMounted(() => {
        checked.value = props.defaultChecked;
      });

      function handleChecked(item: CheckSource) {
        checked.value = item.key;
        emit('onChecked', item);
      }

      const dataSource = computed(() => props.dataSource);

      return {
        dataSource,
        checked,
        handleChecked,
      };
    },
  });
</script>
<style lang="less" scoped>
  .check-list {
    width: 320px;
    cursor: pointer;
    outline: none;
    height: 100%;
    .n-list-item {
      height: 100%;
    }
  }
  ::v-deep(.n-list .n-list-item .n-list-item__prefix) {
    height: 100% !important;
  }

  .check-list-checked {
    position: relative;
    border: 1px solid #18a058 !important;
    border-radius: 3px;
  }

  .check-list-checked:after {
    position: absolute;
    top: 2px;
    right: 2px;
    width: 0;
    height: 0;
    border: 6px solid #18a058;
    border-bottom: 6px solid transparent;
    border-left: 6px solid transparent;
    border-top-right-radius: 2px;
    content: '';
  }

  .check-list:hover {
    background: rgb(243, 243, 245);
  }
  ::v-deep(.n-thing-main__description) {
    word-break: break-all;
    overflow-wrap: break-word;
  }
</style>
