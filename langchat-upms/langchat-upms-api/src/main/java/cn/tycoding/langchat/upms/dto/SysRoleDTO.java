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

import cn.tycoding.langchat.upms.entity.SysRole;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.util.List;

/**
 * SysRole DTO封装
 *
 * @author tycoding
 * @since 2024/4/15
 */
@Data
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = true)
public class SysRoleDTO extends SysRole {
    private static final long serialVersionUID = -5792577217091151552L;

    /**
     * 菜单ID集合
     */
    private List<String> menuIds;
}
