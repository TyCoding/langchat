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
import dev.langchain4j.model.chat.ChatLanguageModel;
import dev.langchain4j.model.chat.StreamingChatLanguageModel;
import dev.langchain4j.model.embedding.DimensionAwareEmbeddingModel;
import dev.langchain4j.model.image.ImageModel;
import dev.langchain4j.model.vertexai.VertexAiGeminiChatModel;
import dev.langchain4j.model.vertexai.VertexAiGeminiStreamingChatModel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * @author GB
 * @since 2024-08-19 10:08
 */
@Slf4j
@Component
public class GeminiModelBuildHandler implements ModelBuildHandler {

    @Override
    public boolean whetherCurrentModel(AigcModel model) {
        return ProviderEnum.GEMINI.name().equals(model.getProvider());
    }

    @Override
    public boolean basicCheck(AigcModel model) {
        if (!StrUtil.isBlank(model.getApiKey())) {
            throw new ServiceException(ChatErrorEnum.API_KEY_IS_NULL.getErrorCode(),
                    ChatErrorEnum.BASE_URL_IS_NULL.getErrorDesc(ProviderEnum.GEMINI.name(), model.getType()));
        }
        if (!StrUtil.isBlank(model.getSecretKey())) {
            throw new ServiceException(ChatErrorEnum.SECRET_KEY_IS_NULL.getErrorCode(),
                    ChatErrorEnum.SECRET_KEY_IS_NULL.getErrorDesc(ProviderEnum.GEMINI.name(), model.getType()));
        }
        return !StrUtil.isBlank(model.getApiKey()) && !StrUtil.isBlank(model.getSecretKey());
    }

    @Override
    public StreamingChatLanguageModel buildStreamingChat(AigcModel model) {
        try {
            if(!whetherCurrentModel(model)){
                return null;
            }
            if (!basicCheck(model)) {
                return null;
            }
            return VertexAiGeminiStreamingChatModel
                    .builder()
                    .project(model.getGeminiProject())
                    .location(model.getGeminiLocation())
                    .modelName(model.getModel())
                    .temperature(Float.parseFloat(model.getTemperature().toString()))
                    .maxOutputTokens(model.getResponseLimit())
                    .logRequests(true)
                    .logResponses(true)
                    .topP(Float.parseFloat(model.getTopP().toString()))
                    .build();
        } catch (ServiceException e) {
            log.error(e.getMessage());
            throw e;
        } catch (Exception e) {
            log.error("Gemini 配置报错", e);
            return null;
        }
    }

    @Override
    public ChatLanguageModel buildChatLanguageModel(AigcModel model) {
        try {
            if(!whetherCurrentModel(model)){
                return null;
            }
            if (!basicCheck(model)) {
                return null;
            }
            return VertexAiGeminiChatModel
                    .builder()
                    .project(model.getGeminiProject())
                    .location(model.getGeminiLocation())
                    .modelName(model.getModel())
                    .temperature(Float.parseFloat(model.getTemperature().toString()))
                    .maxOutputTokens(model.getResponseLimit())
                    .logRequests(true)
                    .logResponses(true)
                    .topP(Float.parseFloat(model.getTopP().toString()))
                    .build();
        } catch (ServiceException e) {
            log.error(e.getMessage());
            throw e;
        } catch (Exception e) {
            log.error("Gemini 配置报错", e);
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
                return null;
            }
            return null;
        } catch (ServiceException e) {
            log.error(e.getMessage());
            throw e;
        } catch (Exception e) {
            return null;
        }

    }

    @Override
    public ImageModel buildImage(AigcModel model) {
        try {
            if(!whetherCurrentModel(model)){
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
            return null;
        }

    }
}
