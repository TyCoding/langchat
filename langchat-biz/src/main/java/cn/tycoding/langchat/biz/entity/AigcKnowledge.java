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

package cn.tycoding.langchat.biz.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * @author tycoding
 * @since 2024/4/15
 */
@Data
@TableName(autoResultMap = true)
public class AigcKnowledge implements Serializable {
    private static final long serialVersionUID = 548724967827903685L;

    /**
     * 主键
     */
    @TableId(type = IdType.ASSIGN_UUID)
    private String id;

    /**
     * 知识库名称
     */
    private String name;

    /**
     * 封面
     */
    private String cover;

    /**
     * 描述
     */
    private String des;

    /**
     * 创建时间
     */
    private String createTime;

    @TableField(exist = false)
    private Integer docsNum = 0;
    @TableField(exist = false)
    private Long totalSize = 0L;
    @TableField(exist = false)
    private List<AigcDocs> docs = new ArrayList<>();
}

