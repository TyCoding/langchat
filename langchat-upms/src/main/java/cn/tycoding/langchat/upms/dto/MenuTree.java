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

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * 按照前端路由格式封装
 *
 * @author tycoding
 * @since 2024/4/15
 */
@Data
@Accessors(chain = true)
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class MenuTree<T> implements Serializable {
    private static final long serialVersionUID = 547891924677981054L;

    /**
     * 节点ID
     */
    private String id;

    /**
     * 父节点ID
     */
    private String parentId;

    /**
     * 路由名称
     */
    private String name;

    /**
     * 路由地址
     */
    private String path;

    /**
     * 类型
     */
    private String type;

    /**
     * 组件路径
     */
    private String component;

    /**
     * 权限标识
     */
    private String perms;

    /**
     * 重定向地址
     */
    private String redirect;

    /**
     * icon && title 信息
     */
    private MenuMeta meta;

    /**
     * 排序
     */
    private Integer orderNo;

    /**
     * 是否禁用
     */
    private Boolean disabled;

    /**
     * 是否外链
     */
    private Boolean isExt;

    /**
     * 是否缓存
     */
    private Boolean keepalive;

    /**
     * 是否缓存
     */
    private Boolean show;

    /**
     * 子节点
     */
    private List<MenuTree<T>> children = new ArrayList<>();

    @Data
    public static class MenuMeta {
        private String title;
        private String icon;
        public MenuMeta(String title, String icon) {
            this.title = title;
            this.icon = icon;
        }
    }
}
