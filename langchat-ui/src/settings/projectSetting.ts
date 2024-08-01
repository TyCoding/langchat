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

const setting = {
  //导航模式 vertical 左侧菜单模式 horizontal 顶部菜单模式
  navMode: 'vertical',
  //导航风格 dark 暗色侧边栏 light 白色侧边栏 header-dark 暗色顶栏
  navTheme: 'dark',
  // 是否处于移动端模式
  isMobile: false,
  //顶部
  headerSetting: {
    //背景色
    bgColor: '#fff',
    //固定顶部
    fixed: true,
    //显示重载按钮
    isReload: true,
  },
  //页脚
  showFooter: false,
  //多标签
  multiTabsSetting: {
    //背景色
    bgColor: '#fff',
    //是否显示
    show: true,
    //固定多标签
    fixed: true,
  },
  //菜单
  menuSetting: {
    //最小宽度
    minMenuWidth: 64,
    //菜单宽度
    menuWidth: 200,
    //固定菜单
    fixed: true,
    //分割菜单
    mixMenu: false,
    //触发移动端侧边栏的宽度
    mobileWidth: 800,
    // 折叠菜单
    collapsed: false,
  },
  //面包屑
  crumbsSetting: {
    //是否显示
    show: false,
    //显示图标
    showIcon: false,
  },
  //菜单权限模式 FIXED 前端固定路由  BACK 动态获取
  permissionMode: 'BACK',
  //是否开启路由动画
  isPageAnimate: true,
  //路由动画类型
  pageAnimateType: 'zoom-fade',
};
export default setting;
