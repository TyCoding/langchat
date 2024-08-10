
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

package cn.tycoding.langchat.app.entity;

import cn.tycoding.langchat.biz.entity.AigcKnowledge;
import cn.tycoding.langchat.biz.entity.AigcModel;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.handlers.FastjsonTypeHandler;
import lombok.Data;
import lombok.experimental.Accessors;
import org.apache.ibatis.type.JdbcType;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author tycoding
 * @since 2024/7/26
 */
@Data
@TableName(autoResultMap = true)
@Accessors(chain = true)
public class AigcApp implements Serializable {
    private static final long serialVersionUID = -94917153262781949L;

    /**
     * 主键
     */
    @TableId(type = IdType.ASSIGN_UUID)
    private String id;
    private String modelId;

    @TableField(typeHandler = FastjsonTypeHandler.class, jdbcType = JdbcType.VARCHAR)
    private List<String> knowledgeIds;

    /**
     * 名称
     */
    private String name;
    private String cover;

    /**
     * Prompt
     */
    private String prompt;

    /**
     * 应用描述
     */
    private String des;

    /**
     * 创建时间
     */
    private Date saveTime;
    private Date createTime;

    @TableField(exist = false)
    private AigcModel model;
    @TableField(exist = false)
    private List<AigcKnowledge> knowledges = new ArrayList<>();
}
