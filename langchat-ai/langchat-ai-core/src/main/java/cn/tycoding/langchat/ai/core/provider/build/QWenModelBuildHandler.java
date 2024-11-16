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
import cn.tycoding.langchat.common.ai.enums.ChatErrorEnum;
import cn.tycoding.langchat.common.core.exception.ServiceException;
import dev.langchain4j.model.chat.ChatLanguageModel;
import dev.langchain4j.model.chat.StreamingChatLanguageModel;
import dev.langchain4j.model.dashscope.QwenChatModel;
import dev.langchain4j.model.dashscope.QwenEmbeddingModel;
import dev.langchain4j.model.dashscope.QwenStreamingChatModel;
import dev.langchain4j.model.embedding.EmbeddingModel;
import dev.langchain4j.model.image.ImageModel;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

/**
 * @author GB
 * @since 2024-08-19 10:08
 */
@Slf4j
@Component
public class QWenModelBuildHandler implements ModelBuildHandler {

    @Override
    public boolean whetherCurrentModel(AigcModel model) {
        return ProviderEnum.Q_WEN.name().equals(model.getProvider());
    }

    @Override
    public boolean basicCheck(AigcModel model) {
        if (StringUtils.isBlank(model.getApiKey())) {
            throw new ServiceException(ChatErrorEnum.API_KEY_IS_NULL.getErrorCode(),
                    ChatErrorEnum.API_KEY_IS_NULL.getErrorDesc(ProviderEnum.Q_WEN.name(), model.getType()));
        }
        return true;
    }

    @Override
    public StreamingChatLanguageModel buildStreamingChat(AigcModel model) {
        if (!whetherCurrentModel(model)) {
            return null;
        }
        if (!basicCheck(model)) {
            return null;
        }
        try {
            return QwenStreamingChatModel
                    .builder()
                    .apiKey(model.getApiKey())
                    .modelName(model.getModel())
                    .baseUrl(model.getBaseUrl())
                    .maxTokens(model.getResponseLimit())
                    .temperature(Float.parseFloat(model.getTemperature().toString()))
                    .topP(model.getTopP())
                    .build();
        } catch (ServiceException e) {
            log.error(e.getMessage());
            throw e;
        } catch (Exception e) {
            log.error("qian wen streaming chat 配置报错", e);
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
            return QwenChatModel
                    .builder()
                    .apiKey(model.getApiKey())
                    .modelName(model.getModel())
                    .baseUrl(model.getBaseUrl())
                    .enableSearch(true)
                    .maxTokens(model.getResponseLimit())
                    .temperature(Float.parseFloat(model.getTemperature().toString()))
                    .topP(model.getTopP())
                    .build();
        } catch (ServiceException e) {
            log.error(e.getMessage());
            throw e;
        } catch (Exception e) {
            log.error("qian wen chat 配置报错", e);
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
            return QwenEmbeddingModel
                    .builder()
                    .apiKey(model.getApiKey())
                    .modelName(model.getModel())
                    .build();
        } catch (ServiceException e) {
            log.error(e.getMessage());
            throw e;
        } catch (Exception e) {
            log.error("qian wen embedding 配置报错", e);
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
            return null;
        } catch (ServiceException e) {
            log.error(e.getMessage());
            throw e;
        } catch (Exception e) {
            log.error("qian wen image 配置报错", e);
            return null;
        }

    }
}
