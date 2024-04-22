<template>
  <basicModal @register="modalRegister" style="width: 45%">
    <template #default>
      <BasicForm @register="register" @submit="handleSubmit" class="mt-5">
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
            @update:value="(val) => onMenuChange(val, model, field)"
            filterable
            clearable
            key-field="id"
            label-field="name"
            value-field="id"
            placeholder="请选择上级菜单"
            :options="menuList"
          />
        </template>
        <template #pathSlot="{ model, field }">
          <n-input
            :disabled="model[field] == 'Layout'"
            v-model:value="model[field]"
            placeholder="请输入菜单路径"
          />
        </template>
      </BasicForm>
    </template>
  </basicModal>
</template>
<script lang="ts" setup>
  import { nextTick, ref } from 'vue';
  import { tree as getMenuList, add, update, getById } from '@/api/upms/menu';
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
    if (id != null) {
      setFieldsValue(await getById(id));
    } else {
      let vars = {
        isDisabled: false,
        type: 'menu',
        isKeepalive: false,
        isExt: false,
        parentId: '',
        orderNo: 0,
        path: 'Layout',
        icon: '',
      };
      if (parentId !== undefined) {
        vars.parentId = parentId;
      }
      setFieldsValue(vars);
    }
    menuList.value = await getMenuList({});
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

  function onSelectType(val, model, field) {
    const isHidden = val == 'button';
    const filterSchemas = formSchemas.filter((i) => {
      if (i.field == 'icon' || i.field == 'component') {
        i.isHidden = isHidden;
      }
      if (isHidden && i.field == 'path') {
        model['path'] = '';
        i.isHidden = isHidden;
      }
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

<style scoped lang="less"></style>
