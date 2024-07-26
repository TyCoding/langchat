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

package cn.tycoding.langchat.flow.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

import java.io.Serializable;

/**
 * @author tycoding
 * @since 2024/6/17
 */
@Data
public class AigcFlowScript implements Serializable {
    private static final long serialVersionUID = -94320446004505219L;

    /**
     * 主键
     */
    @TableId(type = IdType.ASSIGN_UUID)
    private String id;

    /**
     * Flow类型
     */
    private String flowType;

    /**
     * 脚本ID
     */
    private String scriptId;

    /**
     * 脚本类型
     */
    private String scriptType;

    /**
     * 脚本名称
     */
    private String flowName;

    /**
     * 脚本内容
     */
    private String scriptData;

    /**
     * 创建时间
     */
    private String createTime;
}

