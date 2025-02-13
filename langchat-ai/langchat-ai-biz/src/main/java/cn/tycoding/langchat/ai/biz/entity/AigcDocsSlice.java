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

package cn.tycoding.langchat.ai.biz.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.Date;

/**
 * @author tycoding
 * @since 2024/4/15
 */
@Data
@Accessors(chain = true)
public class AigcDocsSlice implements Serializable {
    private static final long serialVersionUID = -3093489071059867065L;

    /**
     * 主键
     */
    @TableId(type = IdType.ASSIGN_UUID)
    private String id;

    /**
     * 向量库的ID
     */
    private String vectorId;

    /**
     * 文档ID
     */
    private String docsId;

    /**
     * 知识库ID
     */
    private String knowledgeId;

    /**
     * 文档名称
     */
    private String name;

    /**
     * 切片内容
     */
    private String content;

    /**
     * 字符数量
     */
    private Integer wordNum;

    /**
     * 是否Embedding
     */
    private Boolean status = false;

    /**
     * 创建时间
     */
    private Date createTime;
}

