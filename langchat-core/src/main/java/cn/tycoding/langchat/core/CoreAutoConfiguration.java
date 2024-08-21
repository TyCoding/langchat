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

package cn.tycoding.langchat.core;

import cn.tycoding.langchat.core.properties.LangChatProps;
import cn.tycoding.langchat.core.properties.vectorstore.PgvectorProps;
import cn.tycoding.langchat.core.properties.vectorstore.VectorProps;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * @author tycoding
 * @since 2024/4/15
 */
@Slf4j
@Configuration
@EnableConfigurationProperties({
        LangChatProps.class,
        VectorProps.class,
        PgvectorProps.class,
})
@AllArgsConstructor
public class CoreAutoConfiguration {

}
