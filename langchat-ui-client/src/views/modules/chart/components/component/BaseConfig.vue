<script setup lang="ts">
  const props = defineProps<{
    option: any;
  }>();
</script>

<template>
  <n-collapse-item title="标题组件">
    <template #header-extra>
      <n-switch size="small" @click.stop v-model:value="option!.title.show" :default-value="true" />
    </template>
    <div class="pl-[22px] text-xs flex gap-2 flex-col">
      <div class="flex flex-row justify-center items-center">
        <div class="w-[62px] text-gray-400">标题名称</div>
        <n-input v-model:value="option!.title.text" size="small" />
      </div>
      <n-grid :cols="2" :x-gap="6" :y-gap="8">
        <n-gi>
          <div class="flex flex-row justify-center items-center">
            <div class="w-[74px] text-gray-400">顶部距离</div>
            <n-input size="small" v-model:value="option!.title.top" />
          </div>
        </n-gi>
        <n-gi>
          <div class="flex flex-row justify-center items-center">
            <div class="w-[74px] text-gray-400">底部距离</div>
            <n-input size="small" v-model:value="option!.title.bottom" />
          </div>
        </n-gi>
        <n-gi>
          <div class="flex flex-row justify-center items-center">
            <div class="w-[74px] text-gray-400">左侧距离</div>
            <n-input size="small" v-model:value="option!.title.left" />
          </div>
        </n-gi>
        <n-gi>
          <div class="flex flex-row justify-center items-center">
            <div class="w-[74px] text-gray-400">右侧距离</div>
            <n-input size="small" v-model:value="option!.title.right" />
          </div>
        </n-gi>
      </n-grid>
    </div>
  </n-collapse-item>

  <n-collapse-item title="系列颜色">
    <div class="pl-[22px] text-xs flex gap-2 flex-row">
      <n-color-picker
        size="small"
        v-model:value="option!.color"
        :swatches="['#FFFFFF', '#18A058', '#2080F0', '#F0A020', 'rgba(208, 48, 80, 1)']"
      />
    </div>
  </n-collapse-item>
  <n-collapse-item title="图例组件">
    <template #header-extra>
      <n-switch
        size="small"
        @click.stop
        v-model:value="option!.legend.show"
        :default-value="true"
      />
    </template>
    <div class="pl-[22px] text-xs flex gap-2 flex-col">
      <div class="flex flex-row justify-center items-center">
        <div class="w-[62px] text-gray-400">位置布局</div>
        <n-select
          size="small"
          v-model:value="option!.legend.orient"
          :options="[
            { value: 'horizontal', label: 'horizontal' },
            { value: 'vertical', label: 'vertical' },
          ]"
        />
      </div>
      <n-grid :cols="2" :x-gap="6" :y-gap="8">
        <n-gi>
          <div class="flex flex-row justify-center items-center">
            <div class="w-[74px] text-gray-400">顶部距离</div>
            <n-input size="small" v-model:value="option!.legend.top" />
          </div>
        </n-gi>
        <n-gi>
          <div class="flex flex-row justify-center items-center">
            <div class="w-[74px] text-gray-400">底部距离</div>
            <n-input size="small" v-model:value="option!.legend.bottom" />
          </div>
        </n-gi>
        <n-gi>
          <div class="flex flex-row justify-center items-center">
            <div class="w-[74px] text-gray-400">左侧距离</div>
            <n-input size="small" v-model:value="option!.legend.left" />
          </div>
        </n-gi>
        <n-gi>
          <div class="flex flex-row justify-center items-center">
            <div class="w-[74px] text-gray-400">右侧距离</div>
            <n-input size="small" v-model:value="option!.legend.right" />
          </div>
        </n-gi>
      </n-grid>
    </div>
  </n-collapse-item>
  <n-collapse-item title="提示框组件">
    <template #header-extra>
      <n-switch
        size="small"
        @click.stop
        v-model:value="option!.tooltip.show"
        :default-value="true"
      />
    </template>
    <div class="pl-[22px] text-xs flex gap-2 flex-col">
      <div class="flex flex-row justify-center items-center">
        <div class="w-[62px] text-gray-400">触发类型</div>
        <n-select
          size="small"
          v-model:value="option!.legend.trigger"
          :options="[
            { value: 'item', label: '数据项图形触发' },
            { value: 'axis', label: '坐标轴触发' },
            { value: 'none', label: '不触发' },
          ]"
        />
      </div>
      <n-grid :cols="2" :x-gap="6" :y-gap="8">
        <n-gi>
          <div class="flex flex-row justify-center items-center">
            <div class="w-[74px] text-gray-400">顶部距离</div>
            <n-input size="small" v-model:value="option!.legend.top" />
          </div>
        </n-gi>
        <n-gi>
          <div class="flex flex-row justify-center items-center">
            <div class="w-[74px] text-gray-400">底部距离</div>
            <n-input size="small" v-model:value="option!.legend.bottom" />
          </div>
        </n-gi>
        <n-gi>
          <div class="flex flex-row justify-center items-center">
            <div class="w-[74px] text-gray-400">左侧距离</div>
            <n-input size="small" v-model:value="option!.legend.left" />
          </div>
        </n-gi>
        <n-gi>
          <div class="flex flex-row justify-center items-center">
            <div class="w-[74px] text-gray-400">右侧距离</div>
            <n-input size="small" v-model:value="option!.legend.right" />
          </div>
        </n-gi>
      </n-grid>
    </div>
  </n-collapse-item>
</template>

<style scoped lang="less"></style>
