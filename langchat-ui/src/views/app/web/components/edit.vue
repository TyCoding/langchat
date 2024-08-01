<!--
  - Copyright (c) 2024 LangChat. TyCoding All Rights Reserved.
  -
  - Licensed under the GNU Affero General Public License, Version 3 (the "License");
  - you may not use this file except in compliance with the License.
  - You may obtain a copy of the License at
  -
  -     https://www.gnu.org/licenses/agpl-3.0.html
  -
  - Unless required by applicable law or agreed to in writing, software
  - distributed under the License is distributed on an "AS IS" BASIS,
  - WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
  - See the License for the specific language governing permissions and
  - limitations under the License.
  -->

<script lang="ts" setup>
  import { onMounted, ref, toRaw } from 'vue';
  import SvgIcon from '@/components/SvgIcon/index.vue';
  import { isNullOrWhitespace } from '@/utils/is';
  import { add, generateKey, getById, update } from '@/api/app/appWeb';
  import { list as getModelList } from '@/api/aigc/model';
  import { useDialog, useMessage } from 'naive-ui';
  import { useRouter } from 'vue-router';
  import { copyToClip } from '@/utils/copy';
  import ModelSelect from '@/views/app/ModelSelect.vue';
  import KnowledgeSelect from '@/views/app/KnowledgeSelect.vue';
  import PromptSelect from '@/views/app/PromptSelect.vue';

  const emit = defineEmits(['reload']);
  const formRef = ref();
  const form = ref<any>({});
  const ms = useMessage();
  const dialog = useDialog();
  const router = useRouter();
  const apiKey = ref('');
  const isExpired = ref(false);
  const isLimit = ref(false);
  const modelOptions = ref([]);

  onMounted(async () => {
    const id = router.currentRoute.value.params.id;
    const data = await getById(id);
    if (data.apiKey === undefined || data.apiKey === null) {
      data.apiKey = await generateKey();
    }
    apiKey.value = data.apiKey;
    if (data.expired == null) {
      isExpired.value = true;
    }
    data.apiKey =
      data.apiKey.slice(0, 13) +
      data.apiKey.slice(13, -4).replace(/./g, '*') +
      data.apiKey.slice(-4);
    form.value = { ...data };

    modelOptions.value = await getModelList({});
  });

  async function onSubmit(e: MouseEvent) {
    e.preventDefault();
    formRef.value?.validate(async (errors) => {
      if (!errors) {
        const data = { ...toRaw(form.value) };
        if (isNullOrWhitespace(data.id)) {
          await add(data);
          emit('reload');
          ms.success('新增成功');
        } else {
          await update(data);
          emit('reload');
          ms.success('修改成功');
        }
      } else {
        ms.error('请完善表单');
      }
    });
  }
  const rules = {
    name: {
      required: true,
      trigger: ['blur', 'change'],
      message: '请输入应用名称',
    },
    apiKey: {
      required: true,
      trigger: ['blur', 'change'],
      message: '请输入应用Key',
    },
    modelId: {
      required: true,
      trigger: ['blur', 'change'],
      message: '请选择关联模型',
    },
    knowledgeId: {
      required: true,
      trigger: ['blur', 'change'],
      message: '请选择关联知识库',
    },
    promptId: {
      required: true,
      trigger: ['blur', 'change'],
      message: '请选择关联提示词',
    },
  };

  function resetKey() {
    dialog.warning({
      title: '提示！',
      content: '你确定重置Key吗？删除后原Key将立即失效是，请谨慎操作',
      positiveText: '是',
      negativeText: '否',
      onPositiveClick: async () => {
        const data = await generateKey();
        form.value.apiKey = data.apiKey;
      },
    });
  }

  async function onCopy() {
    await copyToClip(apiKey.value);
    ms.success('Api Key复制成功');
  }

  function onSelectKnowledge(val) {
    form.value.knowledgeId = val.id;
  }
  function onSelectModel(val) {
    form.value.modelId = val.id;
  }
  function onSelectPrompt(val) {
    form.value.promptId = val.id;
  }

  function onCheckExpired(val: boolean) {
    if (val) {
      form.value.expired = null;
    }
  }
  function onUpdateExpired(val) {
    if (val == null) {
      isExpired.value = true;
      form.value.expired = null;
    } else {
      isExpired.value = false;
    }
  }
  function onCheckLimit(val: boolean) {
    if (val) {
      form.value.reqLimit = null;
    }
  }
  function onUpdateLimit(val) {
    if (val == null) {
      isLimit.value = true;
      form.value.reqLimit = null;
    } else {
      isLimit.value = false;
    }
  }
</script>

<template>
  <div class="bg-white p-4 rounded">
    <n-form ref="formRef" :model="form" :rules="rules" label-placement="left" label-width="auto">
      <n-form-item label="应用名称" path="name">
        <n-input v-model:value="form.name" placeholder="请输入应用名称" />
      </n-form-item>
      <n-form-item label="请求Key" path="apiKey">
        <n-input v-model:value="form.apiKey" disabled placeholder="请输入Key">
          <template #suffix>
            <n-button text type="info" @click="onCopy">
              <SvgIcon class="text-xl" icon="mingcute:copy-3-fill" />
            </n-button>
          </template>
        </n-input>
        &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
        <n-button secondary size="small" type="primary" @click="resetKey">重置Key</n-button>
      </n-form-item>

      <n-form-item label="关联模型" path="modelId">
        <ModelSelect v-if="form.modelId !== undefined" :id="form.modelId" @update="onSelectModel" />
      </n-form-item>
      <n-form-item label="关联知识库" path="knowledgeId">
        <KnowledgeSelect
          v-if="form.knowledgeId !== undefined"
          :id="form.knowledgeId"
          @update="onSelectKnowledge"
        />
      </n-form-item>
      <n-form-item label="关联知提示词" path="promptId">
        <PromptSelect
          v-if="form.promptId !== undefined"
          :id="form.promptId"
          @update="onSelectPrompt"
        />
      </n-form-item>

      <n-form-item label="请求限额 / 天" path="limit">
        <n-slider
          v-model:value="form.reqLimit"
          :default-value="100"
          :max="1000"
          :min="10"
          :step="100"
          @update:value="onUpdateLimit"
        />
        &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
        <n-input-number v-model:value="form.reqLimit" size="small" @update:value="onUpdateLimit" />
        &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
        <n-checkbox v-model:checked="isLimit" class="w-[200px]" @update:checked="onCheckLimit">
          不限制
        </n-checkbox>
      </n-form-item>
      <n-form-item label="Key过期时间" path="expired">
        <n-date-picker
          v-model:value="form.expired"
          clearable
          format="yyyy-MM-dd"
          type="date"
          @update:value="onUpdateExpired"
        />
        &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
        <n-checkbox v-model:checked="isExpired" @update:checked="onCheckExpired"> 长期 </n-checkbox>
      </n-form-item>
      <n-form-item label="应用描述" path="des">
        <n-input v-model:value="form.des" placeholder="请输入应用描述" type="textarea" />
      </n-form-item>
      <n-form-item>
        <n-button attr-type="button" class="mx-4" type="info" @click="onSubmit">保存配置</n-button>
      </n-form-item>
    </n-form>
  </div>
</template>

<style lang="less" scoped></style>
