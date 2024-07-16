package cn.tycoding.langchat.core.provider;

import cn.hutool.core.util.StrUtil;
import cn.tycoding.langchat.aigc.component.ProviderEnum;
import cn.tycoding.langchat.aigc.entity.AigcModel;
import cn.tycoding.langchat.aigc.service.AigcModelService;
import cn.tycoding.langchat.common.component.SpringContextHolder;
import dev.langchain4j.model.azure.AzureOpenAiEmbeddingModel;
import dev.langchain4j.model.azure.AzureOpenAiImageModel;
import dev.langchain4j.model.azure.AzureOpenAiStreamingChatModel;
import dev.langchain4j.model.dashscope.QwenEmbeddingModel;
import dev.langchain4j.model.dashscope.QwenStreamingChatModel;
import dev.langchain4j.model.ollama.OllamaStreamingChatModel;
import dev.langchain4j.model.openai.OpenAiEmbeddingModel;
import dev.langchain4j.model.openai.OpenAiImageModel;
import dev.langchain4j.model.openai.OpenAiStreamingChatModel;
import dev.langchain4j.model.qianfan.QianfanEmbeddingModel;
import dev.langchain4j.model.qianfan.QianfanStreamingChatModel;
import dev.langchain4j.model.vertexai.VertexAiGeminiStreamingChatModel;
import dev.langchain4j.model.zhipu.ZhipuAiStreamingChatModel;
import lombok.AllArgsConstructor;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.annotation.Configuration;

import java.util.List;

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
        List<AigcModel> list = aigcModelService.list();
        list.forEach(model -> {
            // Uninstall previously registered beans before registering them
            contextHolder.unregisterBean(model.getId());

            String provider = model.getProvider();
            if (ProviderEnum.OPENAI.getModel().equals(provider)) {
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
                        .topP(model.getTopP())
                        .build();
                contextHolder.registerBean(model.getId(), build);
            }

            if (ProviderEnum.AZURE_OPENAI.getModel().equals(provider)) {
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
                        .topP(model.getTopP())
                        .build();
                contextHolder.registerBean(model.getId(), build);
            }

            if (ProviderEnum.GOOGLE.getModel().equals(provider)) {
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
                        .topP(Float.parseFloat(model.getTopP().toString()))
                        .build();
                contextHolder.registerBean(model.getId(), build);
            }

            if (ProviderEnum.OLLAMA.getModel().equals(provider)) {
                OllamaStreamingChatModel build = OllamaStreamingChatModel
                        .builder()
                        .baseUrl(model.getBaseUrl())
                        .modelName(model.getModel())
                        .temperature(model.getTemperature())
                        .topP(model.getTopP())
                        .build();
                contextHolder.registerBean(model.getId(), build);
            }

            if (ProviderEnum.BAIDU.getModel().equals(provider)) {
                QianfanStreamingChatModel build = QianfanStreamingChatModel
                        .builder()
                        .apiKey(model.getApiKey())
                        .secretKey(model.getSecretKey())
                        .modelName(model.getModel())
                        .baseUrl(model.getBaseUrl())
                        .temperature(model.getTemperature())
                        .topP(model.getTopP())
                        .build();
                contextHolder.registerBean(model.getId(), build);
            }

            if (ProviderEnum.ALIBABA.getModel().equals(provider)) {
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

            if (ProviderEnum.ALIBABA.getModel().equals(provider)) {
                QwenStreamingChatModel build = QwenStreamingChatModel
                        .builder()
                        .apiKey(model.getApiKey())
                        .modelName(model.getModel())
                        .maxTokens(model.getResponseLimit())
                        .temperature(Float.parseFloat(model.getTemperature().toString()))
                        .topP(model.getTopP())
                        .build();
                contextHolder.registerBean(model.getId(), build);
            }

            if (ProviderEnum.ZHIPU.getModel().equals(provider)) {
                ZhipuAiStreamingChatModel build = ZhipuAiStreamingChatModel
                        .builder()
                        .apiKey(model.getApiKey())
                        .baseUrl(model.getBaseUrl())
                        .model(model.getModel())
                        .maxToken(model.getResponseLimit())
                        .temperature(model.getTemperature())
                        .topP(model.getTopP())
                        .build();
                contextHolder.registerBean(model.getId(), build);
            }

            if (ProviderEnum.TEXT_IMAGE.getModel().equals(provider)) {
                if (ProviderEnum.OPENAI.getModel().equals(model.getModelType())) {
                    OpenAiImageModel build = OpenAiImageModel
                            .builder()
                            .apiKey(model.getApiKey())
                            .baseUrl(model.getBaseUrl())
                            .modelName(model.getModel())
                            .size(model.getImageSize())
                            .quality(model.getImageQuality())
                            .style(model.getImageStyle())
                            .build();
                    contextHolder.registerBean(model.getId(), build);
                }

                if (ProviderEnum.AZURE_OPENAI.getModel().equals(model.getModelType())) {
                    AzureOpenAiImageModel build = AzureOpenAiImageModel
                            .builder()
                            .apiKey(model.getApiKey())
                            .deploymentName(model.getAzureDeploymentName())
                            .size(model.getImageSize())
                            .quality(model.getImageQuality())
                            .style(model.getImageStyle())
                            .build();
                    contextHolder.registerBean(model.getId(), build);
                }
            }

            if (ProviderEnum.EMBEDDING.getModel().equals(provider)) {
                if (ProviderEnum.OPENAI.getModel().equals(model.getModelType())) {
                    OpenAiEmbeddingModel build = OpenAiEmbeddingModel
                            .builder()
                            .apiKey(model.getApiKey())
                            .baseUrl(model.getBaseUrl())
                            .modelName(model.getModel())
                            .dimensions(model.getDimensions())
                            .build();
                    contextHolder.registerBean("OpenAiEmbeddingModel", build);
                }

                if (ProviderEnum.AZURE_OPENAI.getModel().equals(model.getModelType())) {
                    AzureOpenAiEmbeddingModel build = AzureOpenAiEmbeddingModel
                            .builder()
                            .apiKey(model.getApiKey())
                            .deploymentName(model.getBaseUrl())
                            .build();
                    contextHolder.registerBean("AzureOpenAiEmbeddingModel", build);
                }

                if (ProviderEnum.BAIDU.getModel().equals(model.getModelType())) {
                    QianfanEmbeddingModel build = QianfanEmbeddingModel
                            .builder()
                            .apiKey(model.getApiKey())
                            .modelName(model.getModel())
                            .secretKey(model.getSecretKey())
                            .build();
                    contextHolder.registerBean("QianfanEmbeddingModel", build);
                }

                if (ProviderEnum.ALIBABA.getModel().equals(model.getModelType())) {
                    QwenEmbeddingModel build = QwenEmbeddingModel
                            .builder()
                            .apiKey(model.getApiKey())
                            .modelName(model.getModel())
                            .build();
                    contextHolder.registerBean("QwenEmbeddingModel", build);
                }
            }
        });
    }
}
