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

import cn.tycoding.langchat.common.utils.QueryPage;
import cn.tycoding.langchat.upms.dto.SysRoleDTO;
import cn.tycoding.langchat.upms.entity.SysRole;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * 角色表(Role)表服务接口
 *
 * @author tycoding
 * @since 2024/4/15
 */
public interface SysRoleService extends IService<SysRole> {

    /**
     * 分页、条件查询
     */
    IPage<SysRole> page(SysRole role, QueryPage queryPage);

    /**
     * 根据用户ID查询其关联的所有角色
     */
    List<SysRole> findRolesByUserId(String id);

    /**
     * 根据ID查询
     */
    SysRoleDTO findById(String roleId);

    /**
     * 新增角色
     */
    void add(SysRoleDTO sysRole);

    /**
     * 修改角色
     */
    void update(SysRoleDTO sysRole);

    /**
     * 删除
     */
    void delete(String id);
}
