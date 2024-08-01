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

import type { DialogOptions } from 'naive-ui/lib/dialog';
/**
 * @description: 弹窗对外暴露的方法
 */
export interface ModalMethods {
  setProps: (props) => void;
  openModal: () => void;
  closeModal: () => void;
  setSubLoading: (status) => void;
}

/**
 * 支持修改，DialogOptions 參數
 */
export type ModalProps = DialogOptions;

export type RegisterFn = (ModalInstance: ModalMethods) => void;

export type UseModalReturnType = [RegisterFn, ModalMethods];
