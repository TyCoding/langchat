import { NModal } from 'naive-ui';

export const basicProps = {
  ...NModal.props,
  // 确认按钮文字
  subBtuText: {
    type: String,
    default: '确认',
  },
  // 取消按钮文字
  subCloseText: {
    type: String,
    default: '取消',
  },
  showIcon: {
    type: Boolean,
    default: false,
  },
  width: {
    type: Number,
    default: 446,
  },
  title: {
    type: String,
    default: '',
  },
  maskClosable: {
    type: Boolean,
    default: false,
  },
  preset: {
    type: String,
    default: 'dialog',
  },
  showCloseBtn: {
    type: Boolean,
    default: true,
  },
  showSubBtn: {
    type: Boolean,
    default: true,
  },
};
