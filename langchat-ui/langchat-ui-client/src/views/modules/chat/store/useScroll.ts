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

import type { Ref } from 'vue';
import { nextTick, ref } from 'vue';
import { LayoutInst } from 'naive-ui';

type ScrollElement = HTMLDivElement | null;

interface ScrollReturn {
  contentRef: Ref<LayoutInst | null>;
  scrollRef: Ref<ScrollElement>;
  scrollToBottom: () => Promise<void>;
  scrollToTop: () => Promise<void>;
  scrollToBottomIfAtBottom: () => Promise<void>;
}

export function useScroll(): ScrollReturn {
  const scrollRef = ref<ScrollElement>(null);
  const contentRef = ref<LayoutInst | null>(null);

  const scrollToBottom = async () => {
    await nextTick();
    if (contentRef.value && scrollRef.value) {
      contentRef.value.scrollTo({ top: scrollRef.value.scrollHeight + 20 });
    }
  };

  const scrollToTop = async () => {
    await nextTick();
    if (contentRef.value && scrollRef.value) {
      contentRef.value.scrollTo({ top: 0 });
    }
  };

  const scrollToBottomIfAtBottom = async () => {
    await nextTick();
    if (contentRef.value && scrollRef.value) {
      const threshold = 100; // 阈值，表示滚动条到底部的距离阈值
      const distanceToBottom =
        scrollRef.value.scrollHeight - scrollRef.value.scrollTop - scrollRef.value.clientHeight;
      if (distanceToBottom <= threshold) {
        contentRef.value.scrollTo({ top: scrollRef.value.scrollHeight });
      }
    }
  };

  return {
    scrollRef,
    contentRef,
    scrollToBottom,
    scrollToTop,
    scrollToBottomIfAtBottom,
  };
}
