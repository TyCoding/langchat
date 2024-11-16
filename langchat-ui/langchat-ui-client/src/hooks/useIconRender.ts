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

import { h } from 'vue';
import { SvgIcon } from '@/components/common';

export const useIconRender = () => {
  interface IconConfig {
    icon?: string;
    color?: string;
    fontSize?: number;
  }

  interface IconStyle {
    color?: string;
    fontSize?: string;
  }

  const iconRender = (config: IconConfig) => {
    const { color, fontSize, icon } = config;

    const style: IconStyle = {};

    if (color) style.color = color;

    if (fontSize) style.fontSize = `${fontSize}px`;

    if (!icon) window.console.warn('iconRender: icon is required');

    return () => h(SvgIcon, { icon, style });
  };

  return {
    iconRender,
  };
};
