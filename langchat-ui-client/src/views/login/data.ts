import { isBlank } from '@/utils/is';
import { t } from '@/locales';

export const rules = {
  email: {
    key: 'email',
    required: true,
    trigger: ['blur'],
    validator: (rule: any, value: string) => {
      if (isBlank(value)) {
        return new Error(t('login.namePlaceholder'));
      }
      if (!/^\w+([-+.]\w+)*@\w+([-.]\w+)*\.\w+([-.]\w+)*$/.test(value)) {
        return new Error(t('login.emailFormatErr'));
      }
      return;
    },
  },
  code: {
    key: 'code',
    required: true,
    trigger: ['blur'],
    validator: (rule: any, value: string) => {
      if (isBlank(value)) {
        return new Error(t('login.codePlaceholder'));
      }
      if (String(value).length !== 6) {
        return new Error(t('login.codeFormatErr'));
      }
      return true;
    },
  },
  password: {
    key: 'password',
    required: true,
    trigger: ['blur'],
    validator: (rule: any, value: string) => {
      if (isBlank(value)) {
        return new Error(t('login.passPlaceholder'));
      }
      if (value.length < 6) {
        return new Error(t('login.passFormat'));
      }
      return true;
    },
  },
};
