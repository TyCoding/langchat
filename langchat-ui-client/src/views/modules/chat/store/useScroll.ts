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
      contentRef.value.scrollTo({ top: scrollRef.value.scrollHeight });
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
