<template>
  <n-popover placement="bottom-end" trigger="click">
    <template #trigger>
      <n-input v-model:value="modelValue" clearable readonly placeholder="点击选择图标">
        <template #suffix>
          <component :is="selectedIcon" class="ml-2 mr-2 cursor-pointer text-black" />
        </template>
      </n-input>
    </template>
    <template #header>
      <n-input v-model:value="searchValue" size="small" placeholder="搜索图标" />
    </template>
    <div
      v-if="iconsList.length > 0"
      class="grid grid-cols-9 h-auto overflow-hidden"
      style="height: 200px"
    >
      <span
        v-for="iconItem in iconsList"
        :key="iconItem.key"
        @click="handleChange(iconItem)"
        class="hover:bg-gray-100 cursor-pointer text-xl p-1 flex justify-center items-center"
      >
        <component :is="iconItem.value" />
      </span>
    </div>
    <n-pagination
      size="small"
      v-model:page="curPage"
      :page-count="totalPages"
      @update:page="onUpdatePage"
      @update:page-size="onUpdatePageSize"
      class="mt-1.5 mb-1.5"
    />
  </n-popover>
</template>

<script lang="ts" setup>
  import { computed, ref, watch } from 'vue';
  import { constantRouterIcon } from '@/router/icons';

  defineOptions({ name: 'IconSelect' });

  interface Props {
    /** 选中的图标 */
    value: string;
  }
  /** 图标列表 */
  const icons = Object.entries(constantRouterIcon).map(([key, value]) => ({ key, value }));

  const props = withDefaults(defineProps<Props>(), {
    value: '',
  });

  interface Emits {
    (e: 'update:value', val: string): void;
  }

  const emit = defineEmits<Emits>();

  const show = false;
  const selectedIcon = ref();

  const modelValue = computed({
    get() {
      if (props.value === '') {
        // eslint-disable-next-line vue/no-side-effects-in-computed-properties
        selectedIcon.value = constantRouterIcon.AppsOutline();
      } else {
        // eslint-disable-next-line vue/no-side-effects-in-computed-properties
        selectedIcon.value = constantRouterIcon[props.value];
      }
      return props.value;
    },
    set(val: string) {
      emit('update:value', val);
    },
  });

  const searchValue = ref('');

  const curPage = ref(1);
  const pageSize = 54;
  const totalPages = computed(() => {
    const regex = new RegExp(searchValue.value, 'i'); // 不区分大小写的正则表达式
    const filteredIcons = icons.filter((v) => regex.test(v.key));
    const totalItems = filteredIcons.length;
    return Math.ceil(totalItems / pageSize);
  });
  const iconsList = computed(() => {
    const regex = new RegExp(searchValue.value, 'i'); // 不区分大小写的正则表达式
    const filteredIcons = icons.filter((v) => regex.test(v.key));
    const start = (curPage.value - 1) * pageSize;
    const end = start + pageSize;
    return filteredIcons.slice(start, end);
  });

  function onUpdatePage(page: number) {
    curPage.value = page;
  }

  function onUpdatePageSize(pageSize: number) {
    curPage.value = pageSize;
  }

  // 监听搜索值的变化，每次搜索值改变时重置当前页码为第一页
  watch(searchValue, () => {
    curPage.value = 1;
  });

  function handleChange(iconItem: any) {
    modelValue.value = iconItem.key;
    selectedIcon.value = iconItem.value;
  }
</script>

<style lang="less" scoped>
  :deep(.n-input-wrapper) {
    padding-right: 0 !important;
  }
</style>
