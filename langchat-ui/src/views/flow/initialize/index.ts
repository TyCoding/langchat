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

interface R {
  des: string;
  script: string;
  flow: Object;
}

export function initializeTemplate(key: string): R | undefined {
  switch (key) {
    case 'blank':
      return blankTemplate;
    default:
      break;
  }
  return undefined;
}

const blankTemplate: R = {
  des: '这是一个空的模版，仅包含最基础的节点，你可以在此模版上自由设计！',
  script: 'THEN(StartComponent,AiGenTextComponent,EndComponent)',
  flow: [
    { id: '1', type: 'Start', position: { x: 216, y: 190 } },
    { id: '2', type: 'End', position: { x: 778, y: 220 } },
    { id: '3', label: 'Assist', type: 'Assist', position: { x: 432, y: 112 } },
    { id: 'e1-2', source: '1', target: '3', type: 'custom', animated: true },
    { id: 'e1-4', source: '3', target: '2', type: 'custom', animated: true },
  ],
};
