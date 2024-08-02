<script setup lang="ts">
  import { ref, onMounted, onUnmounted, watch } from 'vue';

  const props = defineProps({
    topPercent: {
      type: Number,
      default: 50,
    },
    sliderWidth: {
      type: Number,
      default: 10,
    },
    width: {
      type: String,
      default: '100%',
    },
    height: {
      type: String,
      default: '100%',
    },
    sliderColor: {
      type: String,
      default: '#989da285',
    },
    sliderBgColor: {
      type: String,
      default: '#f5f7f9',
    },
    sliderHoverColor: {
      type: String,
      default: '#989DA290',
    },
    sliderBgHoverColor: {
      type: String,
      default: '#2e33380d',
    },
  });

  const emit = defineEmits(['isDragging', 'dragging', 'resizeRow']);

  const top = ref(props.topPercent);
  const isDragging = ref(false);
  let containerHeight = 0;

  const dragerRef = ref();
  let time = Date.now();
  onMounted(() => {
    containerHeight = dragerRef.value.offsetHeight;
  });

  watch(
    () => props.topPercent,
    (val) => {
      top.value = val;
    }
  );

  onUnmounted(() => {
    document.onmousemove = null;
    document.onmouseup = null;
    document.ontouchmove = null;
    document.ontouchend = null;
  });

  const mobileDragRow = (e) => {
    document.body.style.overflow = 'hidden';
    document.body.style.overscrollBehaviorY = 'contain';
    e.stopPropagation();
    let oldPos = e.changedTouches[0].clientY;
    let oldPosPercent = top.value;
    let newPos = 0;
    let newPosPercent = 0;
    isDragging.value = true;
    emit('isDragging', isDragging.value);
    document.ontouchmove = sliderDrag;
    document.ontouchend = cancelSliderDrag;

    function sliderDrag(e) {
      if (time && Date.now() - time < 40) return;
      time = Date.now();
      e.stopPropagation();
      newPos = e.changedTouches[0].clientY;
      const movingDistancePercent = parseFloat(
        (((oldPos - newPos) / containerHeight) * 100).toFixed(3)
      );
      newPosPercent = oldPosPercent - movingDistancePercent;
      if (newPosPercent <= 0) {
        top.value = 0;
      } else if (newPosPercent >= 100) {
        top.value = 100;
      } else {
        top.value = newPosPercent;
      }
      emit('dragging', top.value);
    }

    function cancelSliderDrag() {
      isDragging.value = false;
      emit('isDragging', isDragging.value);
      document.body.style.overflow = '';
      document.body.style.overscrollBehaviorY = '';
      document.ontouchmove = null;
      document.ontouchend = null;
    }
  };

  const dragRow = (e) => {
    e.preventDefault();
    e.stopPropagation();
    let oldPos = e.clientY;
    let oldPosPercent = top.value;
    let newPos = 0;
    let newPosPercent = 0;
    isDragging.value = true;
    emit('isDragging', isDragging.value);
    document.onmousemove = sliderDrag;
    document.onmouseup = cancelSliderDrag;

    function sliderDrag(e) {
      if (time && Date.now() - time < 40) return;
      time = Date.now();
      e.stopPropagation();
      newPos = e.clientY;
      const movingDistancePercent = parseFloat(
        (((oldPos - newPos) / containerHeight) * 100).toFixed(3)
      );
      newPosPercent = oldPosPercent - movingDistancePercent;
      if (newPosPercent <= 0) {
        top.value = 0;
      } else if (newPosPercent >= 100) {
        top.value = 100;
      } else {
        top.value = newPosPercent;
      }
      emit('dragging', top.value);
    }

    function cancelSliderDrag() {
      isDragging.value = false;
      emit('isDragging', isDragging.value);
      document.onmouseup = null;
      document.onmousemove = null;
    }
  };
</script>

<template>
  <div class="drager_row" ref="dragerRef" :style="{ width: width, height: height }">
    <div class="drager_top" :style="{ height: top + '%' }">
      <div>
        <slot name="top"></slot>
      </div>
    </div>
    <div
      class="slider_row"
      @touchstart.passive="mobileDragRow"
      @mousedown="dragRow"
      :style="{
        height: sliderWidth + 'px',
        marginTop: -sliderWidth / 2 + 'px',
        marginBottom: -sliderWidth / 2 + 'px',
      }"
    ></div>
    <div class="drager_bottom" :style="`height: calc(${100 - top}% - 10px);`">
      <div>
        <slot name="bottom"></slot>
      </div>
    </div>
  </div>
</template>

<style scoped lang="less">
  .drager_row {
    overflow: hidden;
    box-sizing: border-box;
    display: flex;
    flex-direction: column;
  }

  .drager_row * {
    box-sizing: border-box;
    //transition: height 0.1s ease;
  }

  .drager_row > div {
    width: 100%;
  }

  .drager_top {
    margin-bottom: 5px;
  }

  .drager_top > div {
    height: 100%;
    overflow: hidden;
  }

  .drager_bottom {
    margin-top: 5px;
  }

  .drager_bottom > div {
    height: 100%;
    overflow: hidden;
  }

  .drager_row > .slider_row {
    transition: background 0.2s;
    position: relative;
    z-index: 1;
    cursor: row-resize;
    //background: v-bind('sliderBgColor');
  }

  .drager_row > .slider_row:before {
    transition: background-color 0.2s;
    position: absolute;
    left: 50%;
    top: 45%;
    transform: translateX(-50%);
    content: '';
    display: block;
    height: 2px;
    width: 24%;
    min-width: 30px;
    max-width: 70px;
    background-color: v-bind('sliderColor');
  }

  .drager_row > .slider_row:hover:before,
  .drager_row > .slider_row:hover:after,
  .drager_row > .slider_row:active:before,
  .drager_row > .slider_row:active:after {
    background-color: v-bind('sliderHoverColor');
  }

  .drager_row > .slider_row:hover,
  .drager_row > .slider_row:active {
    background: v-bind('sliderBgHoverColor');
  }
</style>
