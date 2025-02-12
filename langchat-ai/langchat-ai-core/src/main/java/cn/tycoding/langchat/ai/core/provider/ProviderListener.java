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

package cn.tycoding.langchat.ai.core.provider;

import cn.tycoding.langchat.ai.biz.component.EmbeddingRefreshEvent;
import cn.tycoding.langchat.ai.biz.component.ProviderRefreshEvent;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

/**
 * @author tycoding
 * @since 2024/6/16
 */
@Slf4j
@Component
@AllArgsConstructor
public class ProviderListener {

    private final ModelStoreFactory providerInitialize;
    private final EmbeddingStoreFactory embeddingStoreInitialize;

    @EventListener
    public void providerEvent(ProviderRefreshEvent event) {
        log.info("refresh provider beans begin......");
        providerInitialize.init();
        log.info("refresh provider beans success......");
    }

    @EventListener
    public void providerEvent(EmbeddingRefreshEvent event) {
        log.info("refresh embedding beans begin......");
        embeddingStoreInitialize.init();
        log.info("refresh embedding beans success......");
    }
}
