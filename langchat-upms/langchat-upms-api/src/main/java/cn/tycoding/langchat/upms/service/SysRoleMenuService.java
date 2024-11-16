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

import cn.tycoding.langchat.upms.entity.SysRoleMenu;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * 角色资源关联表(RoleMenu)表服务接口
 *
 * @author tycoding
 * @since 2024/4/15
 */
public interface SysRoleMenuService extends IService<SysRoleMenu> {

    /**
     * 根据角色ID删除该角色的权限关联信息
     */
    void deleteRoleMenusByRoleId(String roleId);

    /**
     * 根据权限ID删除角色权限关联信息
     */
    void deleteRoleMenusByMenuId(String menuId);
}
