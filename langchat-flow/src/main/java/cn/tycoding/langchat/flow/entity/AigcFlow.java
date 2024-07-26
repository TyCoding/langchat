/*
 * Project: LangChat
 * Author: TyCoding
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

package cn.tycoding.langchat.flow.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.Date;

/**
 * @author tycoding
 * @since 2024/6/17
 */
@Data
@Accessors(chain = true)
public class AigcFlow implements Serializable {
    private static final long serialVersionUID = -94320446004505219L;

    /**
     * 主键
     */
    @TableId(type = IdType.ASSIGN_UUID)
    private String id;

    /**
     * 模型名称
     */
    private String name;

    /**
     * 流程图
     */
    private String flow;

    /**
     * Flow Chain EL脚本
     */
    private String script;

    /**
     * Flow类型
     */
    private String flowType = "type";

    /**
     * 描述
     */
    private String des;

    /**
     * 是否发布
     */
    private Boolean isPublish = false;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 发布时间
     */
    private Date publishTime;

    /**
     * 更新时间
     */
    private Date updateTime;
}

