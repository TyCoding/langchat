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

import cn.hutool.core.util.StrUtil;
import cn.tycoding.langchat.ai.biz.entity.AigcModel;
import cn.tycoding.langchat.ai.core.consts.ProviderEnum;
import cn.tycoding.langchat.ai.core.properties.LangChatProps;
import cn.tycoding.langchat.common.ai.enums.ChatErrorEnum;
import cn.tycoding.langchat.common.core.exception.ServiceException;
import dev.langchain4j.model.chat.ChatLanguageModel;
import dev.langchain4j.model.chat.StreamingChatLanguageModel;
import dev.langchain4j.model.embedding.EmbeddingModel;
import dev.langchain4j.model.image.ImageModel;
import dev.langchain4j.model.openai.OpenAiChatModel;
import dev.langchain4j.model.openai.OpenAiEmbeddingModel;
import dev.langchain4j.model.openai.OpenAiImageModel;
import dev.langchain4j.model.openai.OpenAiStreamingChatModel;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * @author tycoding
 * @since 2024-08-19 10:08
 */
@Slf4j
@Component
@AllArgsConstructor
public class OpenAIModelBuildHandler implements ModelBuildHandler {

    private final LangChatProps props;

    /**
     * 合并处理支持OpenAI接口的模型
     */
    @Override
    public boolean whetherCurrentModel(AigcModel model) {
        String provider = model.getProvider();
        return ProviderEnum.OPENAI.name().equals(provider) ||
                ProviderEnum.GEMINI.name().equals(provider) ||
                ProviderEnum.CLAUDE.name().equals(provider) ||
                ProviderEnum.AZURE_OPENAI.name().equals(provider) ||
                ProviderEnum.DOUYIN.name().equals(provider) ||
                ProviderEnum.YI.name().equals(provider) ||
                ProviderEnum.SILICON.name().equals(provider) ||
                ProviderEnum.DEEPSEEK.name().equals(provider) ||
                ProviderEnum.SPARK.name().equals(provider)
                ;
    }

    @Override
    public boolean basicCheck(AigcModel model) {
        String apiKey = model.getApiKey();
        if (StrUtil.isBlank(apiKey)) {
            throw new ServiceException(ChatErrorEnum.API_KEY_IS_NULL.getErrorCode(),
                    ChatErrorEnum.API_KEY_IS_NULL.getErrorDesc(model.getProvider().toUpperCase(), model.getType()));
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
            return OpenAiStreamingChatModel
                    .builder()
                    .apiKey(model.getApiKey())
                    .baseUrl(model.getBaseUrl())
                    .modelName(model.getModel())
                    .maxTokens(model.getResponseLimit())
                    .temperature(model.getTemperature())
                    .logRequests(true)
                    .logResponses(true)
                    .topP(model.getTopP())
                    .build();
        } catch (ServiceException e) {
            log.error(e.getMessage());
            throw e;
        } catch (Exception e) {
            log.error(model.getProvider() + " Streaming Chat 模型配置报错", e);
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
            return OpenAiChatModel
                    .builder()
                    .apiKey(model.getApiKey())
                    .baseUrl(model.getBaseUrl())
                    .modelName(model.getModel())
                    .maxTokens(model.getResponseLimit())
                    .temperature(model.getTemperature())
                    .logRequests(true)
                    .logResponses(true)
                    .topP(model.getTopP())
                    .build();
        } catch (ServiceException e) {
            log.error(e.getMessage());
            throw e;
        } catch (Exception e) {
            log.error(model.getProvider() + " Chat 模型配置报错", e);
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
            OpenAiEmbeddingModel openAiEmbeddingModel = OpenAiEmbeddingModel
                    .builder()
                    .apiKey(model.getApiKey())
                    .baseUrl(model.getBaseUrl())
                    .modelName(model.getModel())
                    .dimensions(model.getDimension())
                    .logRequests(true)
                    .logResponses(true)
                    .dimensions(1024)
                    .build();
            return openAiEmbeddingModel;
        } catch (ServiceException e) {
            log.error(e.getMessage());
            throw e;
        } catch (Exception e) {
            log.error(model.getProvider() + " Embedding 模型配置报错", e);
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
            return OpenAiImageModel
                    .builder()
                    .apiKey(model.getApiKey())
                    .baseUrl(model.getBaseUrl())
                    .modelName(model.getModel())
                    .size(model.getImageSize())
                    .quality(model.getImageQuality())
                    .style(model.getImageStyle())
                    .logRequests(true)
                    .logResponses(true)
                    .build();
        } catch (ServiceException e) {
            log.error(e.getMessage());
            throw e;
        } catch (Exception e) {
            log.error(model.getProvider() + " Image 模型配置报错", e);
            return null;
        }


    }
}
