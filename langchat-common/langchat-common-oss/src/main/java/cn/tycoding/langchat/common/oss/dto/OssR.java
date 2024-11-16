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

package cn.tycoding.langchat.common.oss.dto;

import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.Date;

/**
 * OSS 文件对象
 *
 * @author tycoding
 * @since 2024/1/30
 */
@Data
@Accessors(chain = true)
public class OssR implements Serializable {
    private static final long serialVersionUID = 5117927170776709434L;

    private String ossId;
    private String url;
    private Long size;
    private String filename;
    private String originalFilename;
    private String basePath;
    private String path;
    private String ext;
    private String contentType;
    private String platform;
    private Date createTime;
}
