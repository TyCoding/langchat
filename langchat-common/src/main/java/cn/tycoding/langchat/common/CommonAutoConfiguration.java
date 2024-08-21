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

package cn.tycoding.langchat.common;

import cn.tycoding.langchat.common.oss.SpringFileStorageProperties;
import cn.tycoding.langchat.common.properties.AuthProps;
import cn.tycoding.langchat.common.properties.ChatProps;
import cn.tycoding.langchat.common.properties.EmailProps;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * @author tycoding
 * @since 2024/1/15
 */
@Configuration
@EnableConfigurationProperties({
        AuthProps.class,
        EmailProps.class,
        ChatProps.class,
        SpringFileStorageProperties.class
})
public class CommonAutoConfiguration {

}
