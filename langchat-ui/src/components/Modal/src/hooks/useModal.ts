/*
 * Copyright (c) 2024 LangChat. TyCoding All Rights Reserved.
 *
 * Licensed under the GNU Affero General Public License, Version 3 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     https://www.gnu.org/licenses/agpl-3.0.html
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

import { ref, unref, watch } from 'vue';
import { isProdMode } from '@/utils/env';
import { ModalMethods, UseModalReturnType } from '../type';
import { getDynamicProps } from '@/utils';
import { tryOnUnmounted } from '@vueuse/core';

export function useModal(props: any): UseModalReturnType {
  const modalRef = ref<Nullable<ModalMethods>>(null);
  // const currentInstance = getCurrentInstance();

  const getInstance = () => {
    const instance = unref(modalRef.value);
    if (!instance) {
      console.error('useModal instance is undefined!');
    }
    return instance;
  };

  const register = (modalInstance: ModalMethods) => {
    isProdMode() &&
      tryOnUnmounted(() => {
        modalRef.value = null;
      });
    modalRef.value = modalInstance;
    // currentInstance?.emit('register', modalInstance);

    watch(
      () => props,
      () => {
        props && modalInstance.setProps(getDynamicProps(props));
      },
      {
        immediate: true,
        deep: true,
      }
    );
  };

  const methods: ModalMethods = {
    setProps: (props): void => {
      getInstance()?.setProps(props);
    },
    openModal: () => {
      getInstance()?.openModal();
    },
    closeModal: () => {
      getInstance()?.closeModal();
    },
    setSubLoading: (status) => {
      getInstance()?.setSubLoading(status);
    },
  };

  return [register, methods];
}
