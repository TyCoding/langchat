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

package cn.tycoding.langchat.common.auth.config;

import cn.dev33.satoken.session.SaSession;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * Jackson 定制版 SaSession，忽略 timeout 等属性的序列化
 *
 * @author click33
 * @since 1.34.0
 */
@JsonIgnoreProperties({"timeout"})
public class TokenDaoCustomized extends SaSession {
    private static final long serialVersionUID = -7600983549653130681L;

    public TokenDaoCustomized() {
        super();
    }

    /**
     * 构建一个Session对象
     *
     * @param id Session的id
     */
    public TokenDaoCustomized(String id) {
        super(id);
    }

}
