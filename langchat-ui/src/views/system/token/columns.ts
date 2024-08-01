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

import { BasicColumn } from '@/components/Table';

export const columns: BasicColumn<any>[] = [
  {
    title: '令牌',
    key: 'token',
    align: 'center',
  },
  {
    title: '账户',
    key: 'username',
    align: 'center',
  },
  {
    title: '姓名',
    key: 'realName',
    align: 'center',
  },
  {
    title: '过期时间',
    key: 'expiration',
    align: 'center',
  },
];
