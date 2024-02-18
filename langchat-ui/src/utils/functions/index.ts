import { h } from 'vue';
import { NIcon } from 'naive-ui';

export function getCurrentDate() {
  const date = new Date();
  const day = date.getDate();
  const month = date.getMonth() + 1;
  const year = date.getFullYear();
  return `${year}-${month}-${day}`;
}

/**
 * render 图标
 * */
export function renderIcon(icon: any) {
  return () => h(NIcon, null, { default: () => h(icon) });
}
