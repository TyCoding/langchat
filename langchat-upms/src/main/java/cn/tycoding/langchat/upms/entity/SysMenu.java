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

package cn.tycoding.langchat.upms.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * 菜单表(Menu)实体类
 *
 * @author tycoding
 * @since 2024/4/15
 */
@Data
@Accessors(chain = true)
public class SysMenu implements Serializable {
    private static final long serialVersionUID = 427935315704878694L;

    /**
     * 主键
     */
    @TableId(type = IdType.ASSIGN_UUID)
    private String id;

    /**
     * 资源名称
     */
    private String name;

    /**
     * 父级ID
     */
    private String parentId;

    /**
     * 路由地址
     */
    private String path;

    /**
     * 权限标识
     */
    private String perms;

    /**
     * 菜单类型：如button按钮 menu菜单
     */
    private String type;

    /**
     * 菜单图标
     */
    private String icon;

    /**
     * 组件路径
     */
    private String component;

    /**
     * 排序
     */
    private Integer orderNo;

    /**
     * 是否禁用
     */
    private Boolean isDisabled;

    /**
     * 是否外链
     */
    private Boolean isExt;

    /**
     * 是否缓存
     */
    private Boolean isKeepalive;

    /**
     * 是否显示
     */
    private Boolean isShow;
}
