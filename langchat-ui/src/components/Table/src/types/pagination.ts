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

export interface PaginationProps {
  page?: number; //受控模式下的当前页
  itemCount?: number; //总条数
  pageCount?: number; //总页数
  pageSize?: number; //受控模式下的分页大小
  pageSizes?: number[]; //每页条数， 可自定义
  showSizePicker?: boolean; //是否显示每页条数的选择器
  showQuickJumper?: boolean; //是否显示快速跳转
  prefix?: any; //分页前缀
}
