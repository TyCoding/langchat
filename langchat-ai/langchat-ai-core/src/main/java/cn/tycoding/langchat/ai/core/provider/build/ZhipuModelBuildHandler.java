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

package cn.tycoding.langchat.ai.core.provider.build;

import cn.tycoding.langchat.ai.biz.entity.AigcModel;
import cn.tycoding.langchat.ai.core.consts.ProviderEnum;
import cn.tycoding.langchat.ai.core.properties.LangChatProps;
import cn.tycoding.langchat.common.ai.enums.ChatErrorEnum;
import cn.tycoding.langchat.common.core.exception.ServiceException;
import dev.langchain4j.model.chat.ChatLanguageModel;
import dev.langchain4j.model.chat.StreamingChatLanguageModel;
import dev.langchain4j.model.embedding.EmbeddingModel;
import dev.langchain4j.model.image.ImageModel;
import dev.langchain4j.model.zhipu.ZhipuAiChatModel;
import dev.langchain4j.model.zhipu.ZhipuAiEmbeddingModel;
import dev.langchain4j.model.zhipu.ZhipuAiImageModel;
import dev.langchain4j.model.zhipu.ZhipuAiStreamingChatModel;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

import java.time.Duration;

/**
 * @author GB
 * @since 2024-08-19
 */
@Slf4j
@Component
@AllArgsConstructor
public class ZhipuModelBuildHandler implements ModelBuildHandler {

    private final LangChatProps props;

    @Override
    public boolean whetherCurrentModel(AigcModel model) {
        return ProviderEnum.ZHIPU.name().equals(model.getProvider());
    }

    @Override
    public boolean basicCheck(AigcModel model) {
        if (StringUtils.isBlank(model.getApiKey())) {
            throw new ServiceException(ChatErrorEnum.API_KEY_IS_NULL.getErrorCode(),
                    ChatErrorEnum.API_KEY_IS_NULL.getErrorDesc(ProviderEnum.ZHIPU.name(), model.getType()));
        }
        return true;
    }

    @Override
    public StreamingChatLanguageModel buildStreamingChat(AigcModel model) {
        try {
            if (!whetherCurrentModel(model)) {
                return null;
            }
            if (!basicCheck(model)) {
                return null;
            }
            return ZhipuAiStreamingChatModel
                    .builder()
                    .apiKey(model.getApiKey())
                    .baseUrl(model.getBaseUrl())
                    .model(model.getModel())
                    .maxToken(model.getResponseLimit())
                    .temperature(model.getTemperature())
                    .topP(model.getTopP())
                    .logRequests(true)
                    .logResponses(true)
                    .callTimeout(Duration.ofMinutes(2))
                    .connectTimeout(Duration.ofMinutes(2))
                    .writeTimeout(Duration.ofMinutes(2))
                    .readTimeout(Duration.ofMinutes(2))
                    .build();
        } catch (ServiceException e) {
            log.error(e.getMessage());
            throw e;
        } catch (Exception e) {
            log.error("zhipu streaming chat 配置报错", e);
            return null;
        }

    }

    @Override
    public ChatLanguageModel buildChatLanguageModel(AigcModel model) {
        try {
            if (!whetherCurrentModel(model)) {
                return null;
            }
            if (!basicCheck(model)) {
                return null;
            }
            return ZhipuAiChatModel
                    .builder()
                    .apiKey(model.getApiKey())
                    .baseUrl(model.getBaseUrl())
                    .model(model.getModel())
                    .maxToken(model.getResponseLimit())
                    .temperature(model.getTemperature())
                    .topP(model.getTopP())
                    .logRequests(true)
                    .logResponses(true)
                    .callTimeout(Duration.ofMinutes(2))
                    .connectTimeout(Duration.ofMinutes(2))
                    .writeTimeout(Duration.ofMinutes(2))
                    .readTimeout(Duration.ofMinutes(2))
                    .build();
        } catch (ServiceException e) {
            log.error(e.getMessage());
            throw e;
        } catch (Exception e) {
            log.error("zhipu chat 配置报错", e);
            return null;
        }

    }

    @Override
    public EmbeddingModel buildEmbedding(AigcModel model) {
        try {
            if (!whetherCurrentModel(model)) {
                return null;
            }
            if (!basicCheck(model)) {
                return null;
            }
            return ZhipuAiEmbeddingModel
                    .builder()
                    .apiKey(model.getApiKey())
                    .model(model.getModel())
                    .baseUrl(model.getBaseUrl())
                    .logRequests(true)
                    .logResponses(true)
                    .callTimeout(Duration.ofMinutes(2))
                    .connectTimeout(Duration.ofMinutes(2))
                    .writeTimeout(Duration.ofMinutes(2))
                    .readTimeout(Duration.ofMinutes(2))
                    .dimensions(1024)
                    .build();
        } catch (ServiceException e) {
            log.error(e.getMessage());
            throw e;
        } catch (Exception e) {
            log.error("zhipu embedding 配置报错", e);
            return null;
        }
    }

    @Override
    public ImageModel buildImage(AigcModel model) {
        try {
            if (!whetherCurrentModel(model)) {
                return null;
            }
            if (!basicCheck(model)) {
                return null;
            }
            return ZhipuAiImageModel
                    .builder()
                    .apiKey(model.getApiKey())
                    .model(model.getModel())
                    .baseUrl(model.getBaseUrl())
                    .logRequests(true)
                    .logResponses(true)
                    .callTimeout(Duration.ofMinutes(2))
                    .connectTimeout(Duration.ofMinutes(2))
                    .writeTimeout(Duration.ofMinutes(2))
                    .readTimeout(Duration.ofMinutes(2))
                    .build();
        } catch (ServiceException e) {
            log.error(e.getMessage());
            throw e;
        } catch (Exception e) {
            log.error("zhipu image 配置报错", e);
            return null;
        }
    }
}
