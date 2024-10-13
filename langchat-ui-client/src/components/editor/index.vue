<template>
  <div class="relative w-full overflow-hidden" style="">
    <editor
      :id="tinymceId"
      v-model="content"
      :enabled="enabled"
      :init="init"
      placeholder="你可以再上方输入文章描述让AI生成，或者直接在这里粘贴或编辑富文本内容，支持识别Word、Pdf等文本格式~"
    />
    <div class="bottom-0 right-0 px-5 py-1 z-10 absolute bg-gray-100 m-2 rounded-lg">
      共 <span class="text-[15px] font-medium">{{ wordCount }}</span> 字
    </div>
  </div>
</template>

<script setup>
  import { computed, nextTick, onMounted, reactive, ref, watch } from 'vue'; //全屏
  import tinymce from 'tinymce/tinymce';
  import Editor from '@tinymce/tinymce-vue';
  import 'tinymce/icons/default/icons';
  import 'tinymce/models/dom'; // 一定要引入
  import 'tinymce/themes/silver'; // 界面UI主题
  import 'tinymce/plugins/image';
  import 'tinymce/plugins/table';
  import 'tinymce/plugins/lists'; // 列表插件
  import 'tinymce/plugins/wordcount'; // 文字计数
  import 'tinymce/plugins/preview'; // 预览
  import 'tinymce/plugins/emoticons'; // emoji表情
  import 'tinymce/plugins/emoticons/js/emojis.js'; //必须引入这个文件才有表情图库
  import 'tinymce/plugins/code'; // 编辑源码
  import 'tinymce/plugins/link'; // 链接插件
  import 'tinymce/plugins/advlist'; //高级列表
  import 'tinymce/plugins/codesample'; //代码示例
  import 'tinymce/plugins/autoresize'; // 自动调整编辑器大小
  import 'tinymce/plugins/quickbars'; // 光标处快捷提示
  import 'tinymce/plugins/nonbreaking'; //插入不间断空格
  import 'tinymce/plugins/searchreplace'; //查找替换
  import 'tinymce/plugins/autolink'; //自动链接
  import 'tinymce/plugins/directionality'; //文字方向
  import 'tinymce/plugins/visualblocks'; //显示元素范围
  import 'tinymce/plugins/visualchars'; //显示不可见字符
  import 'tinymce/plugins/charmap'; // 特殊符号
  import 'tinymce/plugins/insertdatetime'; //插入日期时间
  import 'tinymce/plugins/importcss'; //引入自定义样式的css文件
  import 'tinymce/plugins/accordion'; // 可折叠数据手风琴模式
  import 'tinymce/plugins/anchor'; //锚点
  import 'tinymce/plugins/fullscreen';
  import 'tinymce/plugins/autosave';
  import { asBlob } from 'html-docx-js-typescript';
  import { saveAs } from 'file-saver';
  import html2pdf from 'html2pdf.js';

  const emits = defineEmits(['update:value', 'setHtml']);
  //这里我选择将数据定义在props里面，方便在不同的页面也可以配置出不同的编辑器，当然也可以直接在组件中直接定义
  const props = defineProps({
    text: {
      type: String,
      default: () => {
        return '';
      },
    },
    baseUrl: {
      type: String,
      default: '',
    },
    enabled: {
      type: Boolean,
      default: true,
    },
    // 编辑器初始可编辑状态
    editable_root: {
      type: Boolean,
      default: true,
    },
    external_plugins: {
      supercode: 'tinymce/plugins/supercode', // 设置插件路径
    },
    plugins: {
      type: [String, Array],
      // default: 'importcss autoresize searchreplace autolink directionality code visualblocks visualchars fullscreen image link table charmap nonbreaking anchor insertdatetime advlist lists wordcount charmap quickbars emoticons wordcount',
      default:
        'supercode importcss autoresize searchreplace autolink directionality code visualblocks visualchars fullscreen image link table charmap nonbreaking anchor insertdatetime advlist lists wordcount charmap quickbars codesample',
    },
    knwlgId: {
      type: String,
    },
    toolbar: {
      type: [String, Array, Boolean],
      default:
        // 'undo redo | accordion accordionremove | fontfamily blocks fontsize| bold italic underline strikethrough ltr rtl  | align numlist bullist | link image | table | lineheight outdent indent| forecolor backcolor removeformat | charmap emoticons | anchor codesample',
        'removeformat exportWord exportPdf | blocks bold italic underline strikethrough align numlist bullist table codesample | fontsizeinput |  forecolor backcolor | charmap lineheight outdent indent',
    },
    readonly: {
      type: Boolean,
      default: false,
    },
    minHeight: {
      type: Number,
      default: 630,
    },
  });
  const loading = ref(false);
  const tinymceId = ref('vue-tinymce-' + +new Date() + ((Math.random() * 1000).toFixed(0) + ''));
  const wordCount = ref(0);

  //定义一个对象 init初始化
  const init = reactive({
    selector: '#' + tinymceId.value, //富文本编辑器的id,
    language_url: '/tinymce/langs/zh_CN.js', // 语言包的路径，具体路径看自己的项目
    language: 'zh_CN',
    base_url: '/tinymce',
    skin_url: '/tinymce/skins/ui/oxide', // skin路径，具体路径看自己的项目
    editable_root: props.editable_root,
    branding: false, // 是否禁用“Powered by TinyMCE”
    promotion: false, //去掉 upgrade
    // toolbar_sticky: true,
    // toolbar_sticky_offset: 100,
    // menubar: 'edit view insert format tools table',
    menubar: false,
    paste_data_images: true, //允许粘贴图像
    image_dimensions: false, //去除宽高属性
    plugins: props.plugins, //这里的数据是在props里面就定义好了的
    toolbar: props.toolbar, //这里的数据是在props里面就定义好了的
    // 取消图片资源路径转换
    convert_urls: false,
    ui_mode: 'split',
    // table边框位0是否展示网格线
    // visual: false,
    // 超链接默认打开方式
    link_default_target: '_blank',
    link_context_toolbar: true,
    // 默认快捷菜单
    quickbars_insert_toolbar: '',
    // 选中图片的快捷提示
    // quickbars_image_toolbar: 'alignleft aligncenter alignright | rotateleft rotateright | imageoptions',
    editimage_toolbar: 'rotateleft rotateright | flipv fliph | editimage imageoptions',
    // 文字样式 toolbar - fontfamily
    // font_family_formats: 'Arial=arial,helvetica,sans-serif; 宋体=SimSun; 微软雅黑=Microsoft Yahei; Impact=impact,chicago;', //字体
    font_size_formats: '11px 12px 14px 16px 18px 24px 36px 48px 64px 72px', //文字大小
    image_caption: true,
    editimage_cors_hosts: ['picsum.photos'],
    noneditable_class: 'mceNonEditable',
    toolbar_mode: 'floating', // 工具栏模式 floating / sliding / scrolling / wrap
    // 默认样式
    content_style:
      'body { font-family:Helvetica,Arial,sans-serif; font-size:16px }p {margin:3px; line-height:24px;}',
    image_advtab: true,
    importcss_append: true,
    paste_webkit_styles: 'all',
    paste_merge_formats: true,
    nonbreaking_force_tab: false,
    paste_auto_cleanup_on_paste: false,
    file_picker_types: 'file',
    // 选中文字的快捷提示
    // quickbars_selection_toolbar: 'bold italic | quicklink h2 h3 blockquote quickimage quicktable',
    // 编辑器高度自适应
    autoresize_bottom_margin: 20,
    autoresize_overflow_padding: 16,
    min_height: props.minHeight,
    content_css: '/tinymce/skins/content/default/content.css', //以css文件方式自定义可编辑区域的css样式，css文件需自己创建并引入
    setup: function (editor) {
      editor.ui.registry.addButton('exportWord', {
        icon: 'export-word',
        text: '导出Word',
        onAction: function () {
          downloadWord();
        },
      });
      editor.ui.registry.addButton('exportPdf', {
        icon: 'export-pdf',
        text: '导出PDF',
        onAction: function () {
          downloadPdf();
        },
      });
    },
    //图片上传
    images_upload_handler: function (blobInfo, progress) {
      new Promise((resolve, reject) => {
        let file = blobInfo.blob();
        if (file.size / 1024 / 1024 > 200) {
          reject({
            message: '上传失败，图片大小请控制在 200M 以内',
            remove: true,
          });
        }
        const formData = new FormData();
        formData.append('file', file);
        console.log(formData);
        axios
          .post('/api/upload/upload', formData, {
            headers: {
              'Content-Type': 'multipart/form-data',
            },
            onUploadProgress: (progressEvent) => {
              progress(Math.round((progressEvent.loaded / progressEvent.total) * 100));
            },
          })
          .then((res) => {
            resolve(res.data.url);
          })
          .catch();
      });
    },
    init_instance_callback: (editor) => {
      const wordcount = tinymce.activeEditor.plugins.wordcount;
      wordCount.value = wordcount.body.getCharacterCount();
    },
  });

  function downloadWord() {
    asBlob(getContent()).then((data) => {
      saveAs(data, 'Document.docx');
    });
  }

  function downloadPdf() {
    const element = document.createElement('div');
    element.innerHTML = getContent();
    const options = {
      margin: [5, 10],
      filename: 'Document.pdf',
      image: { type: 'jpeg', quality: 0.98, crossOrigin: 'anonymous' },
      html2canvas: { scale: 2, useCORS: true },
      jsPDF: { unit: 'mm', format: 'a4', orientation: 'portrait' },
      pagebreak: { mode: ['avoid-all', 'css', 'legacy'] },
    };
    html2pdf().from(element).set(options).save();
  }

  // 外部传递进来的数据变化
  const content = computed({
    get() {
      return props.text;
    },
    set(val) {
      const wordcount = tinymce.activeEditor.plugins.wordcount;
      wordCount.value = wordcount.body.getCharacterCount();
      emits('update:value', val);
    },
  });

  //监听富文本中的数据变化
  watch(
    () => content.value,
    () => {
      emits('setHtml', tinymce.activeEditor.getContent({ format: 'text' }), content.value);
    }
  );

  // 设置编辑器只读模式
  watch(
    () => props.readonly,
    (newValue, oldValue) => {
      nextTick(() => {
        tinymce.activeEditor.mode.set(newValue ? 'readonly' : 'design');
        let iframeDom = document.querySelector('iframe');
        iframeDom && (iframeDom.contentWindow.document.body.style.margin = newValue ? 0 : '16px');
      });
    },
    { immediate: true }
  );

  //初始化编辑器
  onMounted(() => {
    tinymce.init({});
  });

  // 设置值
  const setContent = (content) => {
    tinymce.activeEditor.setContent(content);
  };

  // 获取值
  const getContent = () => {
    return tinymce.activeEditor.getContent();
  };

  defineExpose({
    setContent,
    getContent,
  });

  const height = 1;
</script>

<style lang="less" scoped>
  @import '@/styles/lib/index';
  :deep(.tox-tinymce) {
    border: 0 !important;
    border-radius: 4px;
    height: calc(100vh - 100px) !important;
    .tox-edit-area {
      height: calc(100vh - 100px);
    }

    .tox-toolbar__primary {
      width: 100% !important;
    }

    .tox-statusbar {
      display: none;
    }

    .tox-tbtn,
    .tox-tbtn__select-label {
      cursor: pointer !important;
    }
  }
</style>
