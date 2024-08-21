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

package cn.tycoding.langchat.core.provider.build;

import cn.hutool.core.lang.Pair;
import cn.hutool.core.util.StrUtil;
import cn.tycoding.langchat.biz.component.ProviderEnum;
import cn.tycoding.langchat.biz.entity.AigcModel;
import cn.tycoding.langchat.common.enums.ChatErrorEnum;
import cn.tycoding.langchat.common.exception.ServiceException;
import cn.tycoding.langchat.core.consts.EmbedConst;
import dev.langchain4j.model.azure.AzureOpenAiChatModel;
import dev.langchain4j.model.azure.AzureOpenAiEmbeddingModel;
import dev.langchain4j.model.azure.AzureOpenAiImageModel;
import dev.langchain4j.model.azure.AzureOpenAiStreamingChatModel;
import dev.langchain4j.model.chat.ChatLanguageModel;
import dev.langchain4j.model.chat.StreamingChatLanguageModel;
import dev.langchain4j.model.embedding.DimensionAwareEmbeddingModel;
import dev.langchain4j.model.image.ImageModel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * @author GB
 * @since 2024-08-19 10:08
 */
@Slf4j
@Component
public class AzureOenAIModelBuildHandler implements ModelBuildHandler {

    @Override
    public boolean whetherCurrentModel(AigcModel model) {
        return ProviderEnum.AZURE_OPENAI.name().equals(model.getProvider());
    }

    @Override
    public boolean basicCheck(AigcModel model) {
        return !StrUtil.isBlank(model.getApiKey());
    }

    @Override
    public StreamingChatLanguageModel buildStreamingChat(AigcModel model) {
        try {
            if (!whetherCurrentModel(model)) {
                return null;
            }
            if (!basicCheck(model)) {
                throw new ServiceException(ChatErrorEnum.API_KEY_IS_NULL.getErrorCode(),
                        ChatErrorEnum.API_KEY_IS_NULL.getErrorDesc(ProviderEnum.AZURE_OPENAI.name(), model.getType()));
            }
            return AzureOpenAiStreamingChatModel
                    .builder()
                    .apiKey(model.getApiKey())
                    .endpoint(model.getEndpoint())
                    .deploymentName(model.getAzureDeploymentName())
                    .maxTokens(model.getResponseLimit())
                    .temperature(model.getTemperature())
                    .logRequestsAndResponses(true)
                    .topP(model.getTopP())
                    .build();
        } catch (ServiceException e) {
            log.error(e.getMessage());
            throw e;
        } catch (Exception e) {
            log.error("AzureOenAI chat 配置报错", e);
            return null;
        }
    }

    @Override
    public ChatLanguageModel buildChatLanguageModel(AigcModel model) {
        if (!whetherCurrentModel(model)) {
            return null;
        }
        if (!basicCheck(model)) {
            throw new ServiceException(ChatErrorEnum.API_KEY_IS_NULL.getErrorCode(),
                    ChatErrorEnum.API_KEY_IS_NULL.getErrorDesc(ProviderEnum.AZURE_OPENAI.name(), model.getType()));
        }
        try {
            return AzureOpenAiChatModel.builder()
                    .apiKey(model.getApiKey())
                    .endpoint(model.getEndpoint())
                    .deploymentName(model.getAzureDeploymentName())
                    .maxTokens(model.getResponseLimit())
                    .temperature(model.getTemperature())
                    .logRequestsAndResponses(true)
                    .topP(model.getTopP()).build();
        } catch (ServiceException e) {
            log.error(e.getMessage());
            throw e;
        } catch (Exception e) {
            log.error("AzureOenAI chat 配置报错", e);
            return null;
        }

    }

    @Override
    public Pair<String, DimensionAwareEmbeddingModel> buildEmbedding(AigcModel model) {
        try {
            if (!whetherCurrentModel(model)) {
                return null;
            }
            if (!basicCheck(model)) {
                throw new ServiceException(ChatErrorEnum.API_KEY_IS_NULL.getErrorCode(),
                        ChatErrorEnum.API_KEY_IS_NULL.getErrorDesc(ProviderEnum.AZURE_OPENAI.name(), model.getType()));
            }
            AzureOpenAiEmbeddingModel openAiEmbeddingModel = AzureOpenAiEmbeddingModel
                    .builder()
                    .apiKey(model.getApiKey())
                    .deploymentName(model.getBaseUrl())
                    .logRequestsAndResponses(true)
                    .build();
            return Pair.of(EmbedConst.CLAZZ_NAME_AZURE_OPENAI, openAiEmbeddingModel);
        } catch (ServiceException e) {
            log.error(e.getMessage());
            throw e;
        } catch (Exception e) {
            log.error("AzureOenAI embedding 配置报错", e);
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
                throw new ServiceException(ChatErrorEnum.API_KEY_IS_NULL.getErrorCode(),
                        ChatErrorEnum.API_KEY_IS_NULL.getErrorDesc(ProviderEnum.AZURE_OPENAI.name(), model.getType()));
            }
            return AzureOpenAiImageModel
                    .builder()
                    .apiKey(model.getApiKey())
                    .deploymentName(model.getAzureDeploymentName())
                    .size(model.getImageSize())
                    .quality(model.getImageQuality())
                    .style(model.getImageStyle())
                    .logRequestsAndResponses(true)
                    .build();
        } catch (Exception e) {
            log.error("AzureOenAI image 配置报错", e);
            return null;
        }

    }
}
