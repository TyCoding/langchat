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
  import { nextTick, ref } from 'vue';
  import { add, getById, tree as getMenuList, update } from '@/api/upms/menu';
  import { useMessage } from 'naive-ui';
  import { formSchemas, typeOptions } from './columns';
  import { basicModal, useModal } from '@/components/Modal';
  import { BasicForm, useForm } from '@/components/Form';
  import { isNullOrWhitespace } from '@/utils/is';

  const emit = defineEmits(['reload']);
  const message = useMessage();

  const [
    modalRegister,
    { openModal: openModal, closeModal: closeModal, setSubLoading: setSubLoading },
  ] = useModal({
    title: '新增/编辑',
    closable: true,
    maskClosable: false,
    showCloseBtn: false,
    showSubBtn: false,
  });

  const [register, { setFieldsValue, getFieldsValue, clearValidate, setProps }] = useForm({
    gridProps: { cols: 2 },
    labelWidth: 120,
    layout: 'horizontal',
    submitButtonText: '提交',
    schemas: formSchemas,
  });

  const menuList = ref();
  async function show(id?: string, parentId?: string) {
    openModal();
    await nextTick();
    let vars: any = {};
    if (id != null) {
      vars = await getById(id);
      setFieldsValue(vars);
    } else {
      vars = {
        isDisabled: false,
        type: 'menu',
        isKeepalive: false,
        isExt: false,
        parentId: '',
        orderNo: 0,
        path: '',
        icon: '',
      };
      if (parentId !== undefined) {
        vars.parentId = parentId;
      } else {
        vars.component = 'Layout';
      }
      setFieldsValue(vars);
    }
    menuList.value = await getMenuList({});
    onSelectType(vars.type, vars);
  }

  async function handleSubmit(values: any) {
    if (values !== false) {
      if (isNullOrWhitespace(values.id)) {
        await add(values);
        closeModal();
        emit('reload');
        message.success('新增成功');
      } else {
        await update(values);
        closeModal();
        emit('reload');
        message.success('修改成功');
      }
    } else {
      setSubLoading(false);
      message.error('请完善表单');
    }
  }

  function onSelectIcon(val) {
    const data = getFieldsValue();
    data.icon = val;
    setFieldsValue(data);
  }

  function onSelectType(val, model) {
    const isHidden = val == 'button';
    const filterSchemas = formSchemas.filter((i) => {
      if (i.field == 'icon' || i.field == 'component') {
        i.isHidden = isHidden;
      }
      if (isHidden && i.field == 'path') {
        model['path'] = '';
        i.isHidden = isHidden;
      }
      clearValidate();
      return true;
    });
    setProps({ schemas: filterSchemas });
  }

  function onMenuChange(val, model, field) {
    val == null ? (model['path'] = 'Layout') : (model['path'] = '');
    clearValidate();
  }

  defineExpose({ show });
</script>

<template>
  <basicModal style="width: 45%" @register="modalRegister">
    <template #default>
      <BasicForm class="mt-5" @register="register" @submit="handleSubmit">
        <template #iconSlot="{ model, field }">
          <IconPicker :value="model[field]" @update:value="onSelectIcon" />
        </template>
        <template #typeSlot="{ model, field }">
          <n-radio-group
            v-model:value="model[field]"
            @update:value="(val) => onSelectType(val, model, field)"
          >
            <n-space>
              <n-radio v-for="item in typeOptions" :key="item.value" :value="item.value">
                {{ item.label }}
              </n-radio>
            </n-space>
          </n-radio-group>
        </template>
        <template #menuSlot="{ model, field }">
          <n-tree-select
            v-model:value="model[field]"
            :options="menuList"
            clearable
            filterable
            key-field="id"
            label-field="name"
            placeholder="请选择上级菜单"
            value-field="id"
            @update:value="(val) => onMenuChange(val, model, field)"
          />
        </template>
        <template #componentSlot="{ model, field }">
          <n-input
            v-model:value="model[field]"
            :disabled="model[field] === 'Layout'"
            placeholder="请输入组件路径"
          />
        </template>
      </BasicForm>
    </template>
  </basicModal>
</template>

<style lang="less" scoped></style>
