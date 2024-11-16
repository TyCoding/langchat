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

package cn.tycoding.langchat.upms.dto;

import cn.tycoding.langchat.upms.entity.SysDept;
import cn.tycoding.langchat.upms.entity.SysRole;
import cn.tycoding.langchat.upms.entity.SysUser;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.List;
import java.util.Set;

/**
 * 自定义Oauth2 授权成功后存储的用户数据
 *
 * @author tycoding
 * @since 2024/4/15
 */
@Data
@Accessors(chain = true)
public class UserInfo extends SysUser implements Serializable {
    private static final long serialVersionUID = 547891924677981054L;

    /**
     * 用户所属部门
     */
    private SysDept dept;

    /**
     * 部门名称
     */
    private String deptName;

    /**
     * 角色ID列表
     */
    private List<String> roleIds;

    /**
     * 用户角色列表
     */
    private List<SysRole> roles;

    /**
     * 用户权限标识
     */
    private Set<String> perms;
}
