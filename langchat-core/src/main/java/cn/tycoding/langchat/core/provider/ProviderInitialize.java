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

package cn.tycoding.langchat.core.provider;

import cn.hutool.core.util.StrUtil;
import cn.tycoding.langchat.biz.component.ModelTypeEnum;
import cn.tycoding.langchat.biz.component.ProviderEnum;
import cn.tycoding.langchat.biz.entity.AigcModel;
import cn.tycoding.langchat.biz.service.AigcModelService;
import cn.tycoding.langchat.common.component.SpringContextHolder;
import cn.tycoding.langchat.core.consts.EmbedConst;
import dev.langchain4j.model.anthropic.AnthropicStreamingChatModel;
import dev.langchain4j.model.azure.AzureOpenAiEmbeddingModel;
import dev.langchain4j.model.azure.AzureOpenAiImageModel;
import dev.langchain4j.model.azure.AzureOpenAiStreamingChatModel;
import dev.langchain4j.model.dashscope.QwenEmbeddingModel;
import dev.langchain4j.model.dashscope.QwenStreamingChatModel;
import dev.langchain4j.model.ollama.OllamaEmbeddingModel;
import dev.langchain4j.model.ollama.OllamaStreamingChatModel;
import dev.langchain4j.model.openai.OpenAiEmbeddingModel;
import dev.langchain4j.model.openai.OpenAiImageModel;
import dev.langchain4j.model.openai.OpenAiStreamingChatModel;
import dev.langchain4j.model.qianfan.QianfanEmbeddingModel;
import dev.langchain4j.model.qianfan.QianfanStreamingChatModel;
import dev.langchain4j.model.vertexai.VertexAiGeminiStreamingChatModel;
import dev.langchain4j.model.zhipu.ZhipuAiEmbeddingModel;
import dev.langchain4j.model.zhipu.ZhipuAiImageModel;
import dev.langchain4j.model.zhipu.ZhipuAiStreamingChatModel;
import lombok.AllArgsConstructor;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.annotation.Configuration;

import java.util.List;
import java.util.Objects;

/**
 * @author tycoding
 * @since 2024/6/16
 */
@Configuration
@AllArgsConstructor
public class ProviderInitialize implements ApplicationContextAware {

    private final AigcModelService aigcModelService;
    private final SpringContextHolder contextHolder;

    @Override
    public void setApplicationContext(ApplicationContext context) throws BeansException {
        init();
    }

    public void init() {
        // un register embedding model
        contextHolder.unregisterBean(EmbedConst.CLAZZ_NAME_OPENAI);
        contextHolder.unregisterBean(EmbedConst.CLAZZ_NAME_AZURE_OPENAI);
        contextHolder.unregisterBean(EmbedConst.CLAZZ_NAME_QIANFAN);
        contextHolder.unregisterBean(EmbedConst.CLAZZ_NAME_ZHIPU);
        contextHolder.unregisterBean(EmbedConst.CLAZZ_NAME_QIANWEN);
        contextHolder.unregisterBean(EmbedConst.CLAZZ_NAME_OLLAMA);

        List<AigcModel> list = aigcModelService.list();
        list.forEach(model -> {
            if (Objects.equals(model.getBaseUrl(), "")) {
                model.setBaseUrl(null);
            }
            // Uninstall previously registered beans before registering them
            contextHolder.unregisterBean(model.getId());

            chatHandler(model);
            embeddingHandler(model);
            imageHandler(model);
        });
    }

    private void chatHandler(AigcModel model) {
        String type = model.getType();
        String provider = model.getProvider();

        if (ModelTypeEnum.CHAT.name().equals(type)) {
            if (ProviderEnum.OPENAI.name().equals(provider)) {
                if (StrUtil.isBlank(model.getApiKey())) {
                    return;
                }
                OpenAiStreamingChatModel build =  OpenAiStreamingChatModel
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
                contextHolder.registerBean(model.getId(), build);
            }
            if (ProviderEnum.AZURE_OPENAI.name().equals(provider)) {
                if (StrUtil.isBlank(model.getApiKey())) {
                    return;
                }
                AzureOpenAiStreamingChatModel build =  AzureOpenAiStreamingChatModel
                        .builder()
                        .apiKey(model.getApiKey())
                        .endpoint(model.getEndpoint())
                        .deploymentName(model.getAzureDeploymentName())
                        .maxTokens(model.getResponseLimit())
                        .temperature(model.getTemperature())
                        .logRequestsAndResponses(true)
                        .topP(model.getTopP())
                        .build();
                contextHolder.registerBean(model.getId(), build);
            }
            if (ProviderEnum.GEMINI.name().equals(provider)) {
                if (StrUtil.isBlank(model.getApiKey()) || StrUtil.isBlank(model.getSecretKey())) {
                    return;
                }
                VertexAiGeminiStreamingChatModel build = VertexAiGeminiStreamingChatModel
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
                contextHolder.registerBean(model.getId(), build);
            }
            if (ProviderEnum.OLLAMA.name().equals(provider)) {
                OllamaStreamingChatModel build = OllamaStreamingChatModel
                        .builder()
                        .baseUrl(model.getBaseUrl())
                        .modelName(model.getModel())
                        .temperature(model.getTemperature())
                        .topP(model.getTopP())
                        .logRequests(true)
                        .logResponses(true)
                        .build();
                contextHolder.registerBean(model.getId(), build);
            }
            if (ProviderEnum.CLAUDE.name().equals(provider)) {
                if (!model.getBaseUrl().endsWith("/")) {
                    model.setBaseUrl(model.getBaseUrl() + "/");
                }
                AnthropicStreamingChatModel build = AnthropicStreamingChatModel
                        .builder()
                        .apiKey(model.getApiKey())
                        .baseUrl(model.getBaseUrl())
                        .modelName(model.getModel())
                        .temperature(model.getTemperature())
                        .topP(model.getTopP())
                        .logRequests(true)
                        .logResponses(true)
                        .build();
                contextHolder.registerBean(model.getId(), build);
            }
            if (ProviderEnum.Q_FAN.name().equals(provider)) {
                QianfanStreamingChatModel build = QianfanStreamingChatModel
                        .builder()
                        .apiKey(model.getApiKey())
                        .secretKey(model.getSecretKey())
                        .modelName(model.getModel())
                        .baseUrl(model.getBaseUrl())
                        .temperature(model.getTemperature())
                        .topP(model.getTopP())
                        .logRequests(true)
                        .logResponses(true)
                        .build();
                contextHolder.registerBean(model.getId(), build);
            }
            if (ProviderEnum.Q_WEN.name().equals(provider)) {
                QwenStreamingChatModel build = QwenStreamingChatModel
                        .builder()
                        .apiKey(model.getApiKey())
                        .modelName(model.getModel())
                        .baseUrl(model.getBaseUrl())
                        .maxTokens(model.getResponseLimit())
                        .temperature(Float.parseFloat(model.getTemperature().toString()))
                        .topP(model.getTopP())
                        .build();
                contextHolder.registerBean(model.getId(), build);
            }
            if (ProviderEnum.ZHIPU.name().equals(provider)) {
                ZhipuAiStreamingChatModel build = ZhipuAiStreamingChatModel
                        .builder()
                        .apiKey(model.getApiKey())
                        .baseUrl(model.getBaseUrl())
                        .model(model.getModel())
                        .maxToken(model.getResponseLimit())
                        .temperature(model.getTemperature())
                        .topP(model.getTopP())
                        .logRequests(true)
                        .logResponses(true)
                        .build();
                contextHolder.registerBean(model.getId(), build);
            }
        }
    }

    private void embeddingHandler(AigcModel model) {
        String type = model.getType();
        String provider = model.getProvider();

        if (ModelTypeEnum.EMBEDDING.name().equals(type)) {
            if (ProviderEnum.OPENAI.name().equals(provider)) {
                OpenAiEmbeddingModel build = OpenAiEmbeddingModel
                        .builder()
                        .apiKey(model.getApiKey())
                        .baseUrl(model.getBaseUrl())
                        .modelName(model.getModel())
                        .dimensions(model.getDimensions())
                        .logRequests(true)
                        .logResponses(true)
                        .build();
                contextHolder.registerBean(EmbedConst.CLAZZ_NAME_OPENAI, build);
            }
            if (ProviderEnum.AZURE_OPENAI.name().equals(provider)) {
                AzureOpenAiEmbeddingModel build = AzureOpenAiEmbeddingModel
                        .builder()
                        .apiKey(model.getApiKey())
                        .deploymentName(model.getBaseUrl())
                        .logRequestsAndResponses(true)
                        .build();
                contextHolder.registerBean(EmbedConst.CLAZZ_NAME_AZURE_OPENAI, build);
            }
            if (ProviderEnum.Q_FAN.name().equals(provider)) {
                QianfanEmbeddingModel build = QianfanEmbeddingModel
                        .builder()
                        .apiKey(model.getApiKey())
                        .modelName(model.getModel())
                        .secretKey(model.getSecretKey())
                        .logRequests(true)
                        .logResponses(true)
                        .build();
                contextHolder.registerBean(EmbedConst.CLAZZ_NAME_QIANFAN, build);
            }
            if (ProviderEnum.Q_WEN.name().equals(provider)) {
                QwenEmbeddingModel build = QwenEmbeddingModel
                        .builder()
                        .apiKey(model.getApiKey())
                        .modelName(model.getModel())
                        .build();
                contextHolder.registerBean(EmbedConst.CLAZZ_NAME_QIANWEN, build);
            }
            if (ProviderEnum.ZHIPU.name().equals(provider)) {
                ZhipuAiEmbeddingModel build = ZhipuAiEmbeddingModel
                        .builder()
                        .apiKey(model.getApiKey())
                        .model(model.getModel())
                        .baseUrl(model.getBaseUrl())
                        .logRequests(true)
                        .logResponses(true)
                        .build();
                contextHolder.registerBean(EmbedConst.CLAZZ_NAME_ZHIPU, build);
            }
            if (ProviderEnum.OLLAMA.name().equals(provider)) {
                OllamaEmbeddingModel build = OllamaEmbeddingModel
                        .builder()
                        .baseUrl(model.getBaseUrl())
                        .modelName(model.getModel())
                        .logRequests(true)
                        .logResponses(true)
                        .build();
                contextHolder.registerBean(EmbedConst.CLAZZ_NAME_OLLAMA, build);
            }
        }
    }

    private void imageHandler(AigcModel model) {
        String type = model.getType();
        String provider = model.getProvider();

        if (ModelTypeEnum.TEXT_IMAGE.name().equals(type)) {
            if (ProviderEnum.OPENAI.name().equals(provider)) {
                OpenAiImageModel build = OpenAiImageModel
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
                contextHolder.registerBean(model.getId(), build);
            }
            if (ProviderEnum.AZURE_OPENAI.name().equals(provider)) {
                AzureOpenAiImageModel build = AzureOpenAiImageModel
                        .builder()
                        .apiKey(model.getApiKey())
                        .deploymentName(model.getAzureDeploymentName())
                        .size(model.getImageSize())
                        .quality(model.getImageQuality())
                        .style(model.getImageStyle())
                        .logRequestsAndResponses(true)
                        .build();
                contextHolder.registerBean(model.getId(), build);
            }
            if (ProviderEnum.ZHIPU.name().equals(provider)) {
                ZhipuAiImageModel build = ZhipuAiImageModel
                        .builder()
                        .apiKey(model.getApiKey())
                        .model(model.getModel())
                        .baseUrl(model.getBaseUrl())
                        .logRequests(true)
                        .logResponses(true)
                        .build();
                contextHolder.registerBean(model.getId(), build);
            }
        }
    }
}
