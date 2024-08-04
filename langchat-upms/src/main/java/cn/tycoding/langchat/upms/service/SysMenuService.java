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

package cn.tycoding.langchat.upms.service;

import cn.tycoding.langchat.upms.dto.MenuTree;
import cn.tycoding.langchat.upms.entity.SysMenu;
import cn.tycoding.langchat.upms.entity.SysRole;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * 菜单表(Menu)表服务接口
 *
 * @author tycoding
 * @since 2024/4/15
 */
public interface SysMenuService extends IService<SysMenu> {

    /**
     * 构建菜单Tree树
     */
    List<MenuTree<SysMenu>> tree(SysMenu sysMenu);

    /**
     * 构建左侧权限菜单
     */
    List<MenuTree<SysMenu>> build(String userId);

    /**
     * 根据用户ID查询权限信息
     */
    List<SysMenu> getUserMenuList(List<SysRole> sysRoleList);

    /**
     * 条件查询
     */
    List<SysMenu> list(SysMenu sysMenu);

    /**
     * 新增
     */
    void add(SysMenu sysMenu);

    /**
     * 修改
     */
    void update(SysMenu sysMenu);

    /**
     * 删除
     */
    void delete(String id);

}
