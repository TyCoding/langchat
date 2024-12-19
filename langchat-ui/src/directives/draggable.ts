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

/*
	需求：实现一个拖拽指令，可在父元素区域任意拖拽元素。

	思路：
		1、设置需要拖拽的元素为absolute，其父元素为relative。
		2、鼠标按下(onmousedown)时记录目标元素当前的 left 和 top 值。
		3、鼠标移动(onmousemove)时计算每次移动的横向距离和纵向距离的变化值，并改变元素的 left 和 top 值
		4、鼠标松开(onmouseup)时完成一次拖拽

	使用：在 Dom 上加上 v-draggable 即可
	<div class="dialog-model" v-draggable></div>
*/
import type { Directive } from 'vue';

interface ElType extends HTMLElement {
  parentNode: any;
}
const draggable: Directive = {
  mounted: function (el: ElType) {
    el.style.cursor = 'move';
    el.style.position = 'absolute';
    el.onmousedown = function (e) {
      const disX = e.pageX - el.offsetLeft;
      const disY = e.pageY - el.offsetTop;
      document.onmousemove = function (e) {
        let x = e.pageX - disX;
        let y = e.pageY - disY;
        const maxX = el.parentNode.offsetWidth - el.offsetWidth;
        const maxY = el.parentNode.offsetHeight - el.offsetHeight;
        if (x < 0) {
          x = 0;
        } else if (x > maxX) {
          x = maxX;
        }

        if (y < 0) {
          y = 0;
        } else if (y > maxY) {
          y = maxY;
        }
        el.style.left = x + 'px';
        el.style.top = y + 'px';
      };
      document.onmouseup = function () {
        document.onmousemove = document.onmouseup = null;
      };
    };
  },
};
export default draggable;
