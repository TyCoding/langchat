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

export interface ChatState {
  modelId?: string;
  modelName?: string;
  modelProvider?: string;
  isEdit: string; //当前编辑的id
  active: string; //当前激活的id
  siderCollapsed: boolean; //侧边栏展开状态
  sideIsLoading: boolean; //侧边栏加载状态
  chatIsLoading: boolean; //会话窗口加载状态
  conversations: any[]; //左侧会话窗口列表
  curConversation: any; //当前选中的会话窗口
  messages: any[]; //当前选中的消息内容
  apps: any[];
  appId: any;
}
