import { createApp } from 'vue';
import App from './App.vue';
import { setupI18n } from './locales';
import { setupAssets, setupScrollbarStyle } from './plugins';
import { setupStore } from './store';
import { setupRouter } from './router';
import { setupNaive } from '@/plugins/naive';
import FileViewer from '@flyfish-group/file-viewer3';

// 导入样式
import '@flyfish-group/file-viewer3/dist/style.css';

async function bootstrap() {
  const app = createApp(App);
  setupAssets();

  setupScrollbarStyle();

  setupStore(app);

  setupI18n(app);

  // register global naive-ui components
  setupNaive(app);

  await setupRouter(app);

  app.use(FileViewer);

  app.mount('#app');
}

bootstrap();
