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

/**
 * 这里按需引入lodash的一些方法,方便维护
 */

//  export  {default as xxx} from 'lodash/xxx'

export { default as cloneDeep } from 'lodash/cloneDeep';
export { default as intersection } from 'lodash/intersection';
export { default as get } from 'lodash/get';
export { default as upperFirst } from 'lodash/upperFirst';
export { default as omit } from 'lodash/omit';
export { default as debounce } from 'lodash/debounce';
