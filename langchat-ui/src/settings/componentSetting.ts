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

export default {
  table: {
    apiSetting: {
      // 当前页的字段名
      pageField: 'page',
      // 每页数量字段名
      sizeField: 'limit',
      // 接口返回的数据字段名
      listField: 'rows',
      // 接口返回总页数字段名
      totalField: 'total',
      //总数字段名
      countField: 'total',
    },
    //默认分页数量
    defaultPageSize: 10,
    //可切换每页数量集合
    pageSizes: [10, 20, 30, 40, 50],
  },
  upload: {
    //考虑接口规范不同
    apiSetting: {
      // 集合字段名
      infoField: 'data',
      // 图片地址字段名
      imgField: 'photo',
    },
    //最大上传图片大小
    maxSize: 2,
    //图片上传类型
    fileType: ['image/png', 'image/jpg', 'image/jpeg', 'image/gif', 'image/svg+xml'],
  },
};
