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

import { GraphNode } from '@vue-flow/core';
import { defineStore } from 'pinia';
import { getPin, Pin } from '@/views/flow/store/get';
import { Component, shallowRef, toRaw } from 'vue';
import { BlankPin } from '@/views/flow/pin';

export interface FlowState {
  data: any; // 当前编辑的流程数据
  nodeId: string; // 当前激活的NodeId，通过useVueFlow().findNode获取实例对象
  pin: Pin | null; // 当前激活节点的pin component
  pinComponent: Component;
  showCard: boolean; // 是否展示Node Card面板
}

export const useFlowStore = defineStore({
  id: 'flow-store',
  state: (): FlowState => ({
    data: undefined,
    nodeId: '',
    pin: null,
    pinComponent: shallowRef(BlankPin),
    showCard: false,
  }),

  actions: {
    cleanNode() {
      this.nodeId = '';
      this.pin = null;
      this.pinComponent = BlankPin;
    },
    setShowCard() {
      this.showCard = !this.showCard;
    },

    initNode(node: GraphNode) {
      this.nodeId = node.id;
      this.pin = {};
      const pin = getPin(node.type, true);
      if (pin !== undefined && pin.component !== undefined) {
        this.pin = pin;
        this.pin.label = String(node.label);
        this.pinComponent = pin.component;
      }
      if (node.data !== undefined && node.data.des !== undefined) {
        this.pin.des = toRaw(node.data.des);
      }
    },

    initPlugin(plugin: Pin) {
      const pin = getPin(plugin.type, false);
      if (pin == undefined || pin.component == undefined) {
        return;
      }
      this.pin = pin;
      this.pinComponent = pin.component;
      if (plugin.des == undefined) {
        this.pin.des = '';
      }
    },
  },
});
