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

package cn.tycoding.langchat.common.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.ArrayList;
import java.util.List;

/**
 * @author tycoding
 * @since 2024/1/15
 */
@Data
@ConfigurationProperties("langchat.auth")
public class AuthProps {

    /**
     * 默认忽略拦截的URL集合
     */
    private List<String> skipUrl = new ArrayList();

    private Integer chatLimit = 10;
    private Boolean aigcRegUserIsPerms = true;

    private EmailProps email = new EmailProps();

    /**
     * salt
     */
    private String saltKey = "langchat-salt";
}
