package cn.tycoding.langchat.core.component;

import cn.tycoding.langchat.core.properties.LangChatProps;
import com.alibaba.fastjson2.JSON;
import dev.langchain4j.model.azure.AzureOpenAiChatModel;
import dev.langchain4j.model.azure.AzureOpenAiEmbeddingModel;
import dev.langchain4j.model.azure.AzureOpenAiImageModel;
import dev.langchain4j.model.azure.AzureOpenAiStreamingChatModel;
import lombok.AllArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import static cn.tycoding.langchat.core.consts.ModelConst.*;
import static cn.tycoding.langchat.core.consts.PropConst.AZUREOPENAI_CON;

/**
 * @author tycoding
 * @since 2024/3/8
 */
@Configuration
@AllArgsConstructor
public class AzureOpenaiComponent {

    private final LangChatProps props;

    @Bean(AZUREOPENAI_GPT_3T)
    @ConditionalOnProperty(AZUREOPENAI_CON)
    public AzureOpenAiStreamingChatModel azureOpenAiStreamingChatModel_3t() {
        AzureOpenAiStreamingChatModel.Builder builder =
                JSON.parseObject(JSON.toJSONString(props.getOpenai()), AzureOpenAiStreamingChatModel.Builder.class);
        BeanUtils.copyProperties(props.getAzureOpenai(), builder);
        builder.deploymentName(AZUREOPENAI_GPT_3T);
        return builder.build();
    }

    @Bean(AZUREOPENAI_GPT_4)
    @ConditionalOnProperty(AZUREOPENAI_CON)
    public AzureOpenAiStreamingChatModel azureOpenAiStreamingChatModel_4() {
        AzureOpenAiStreamingChatModel.Builder builder =
                JSON.parseObject(JSON.toJSONString(props.getOpenai()), AzureOpenAiStreamingChatModel.Builder.class);
        BeanUtils.copyProperties(props.getAzureOpenai(), builder);
        builder.deploymentName(AZUREOPENAI_GPT_4);
        return builder.build();
    }

    @Bean(AZUREOPENAI_GPT_4T)
    @ConditionalOnProperty(AZUREOPENAI_CON)
    public AzureOpenAiStreamingChatModel azureOpenAiStreamingChatModel_4t() {
        AzureOpenAiStreamingChatModel.Builder builder =
                JSON.parseObject(JSON.toJSONString(props.getOpenai()), AzureOpenAiStreamingChatModel.Builder.class);
        BeanUtils.copyProperties(props.getAzureOpenai(), builder);
        builder.deploymentName(AZUREOPENAI_GPT_4T);
        return builder.build();
    }

    @Bean(AZUREOPENAI_GPT_4O)
    @ConditionalOnProperty(AZUREOPENAI_CON)
    public AzureOpenAiStreamingChatModel azureOpenAiStreamingChatModel_4o() {
        AzureOpenAiStreamingChatModel.Builder builder =
                JSON.parseObject(JSON.toJSONString(props.getOpenai()), AzureOpenAiStreamingChatModel.Builder.class);
        BeanUtils.copyProperties(props.getAzureOpenai(), builder);
        builder.deploymentName(AZUREOPENAI_GPT_4O);
        return builder.build();
    }

    @Bean(AZUREOPENAI_GPT_3T + TEXT_SUFFIX)
    @ConditionalOnProperty(AZUREOPENAI_CON)
    public AzureOpenAiChatModel azureOpenAiChatModel_3t() {
        AzureOpenAiChatModel.Builder builder =
                JSON.parseObject(JSON.toJSONString(props.getOpenai()), AzureOpenAiChatModel.Builder.class);
        BeanUtils.copyProperties(props.getAzureOpenai(), builder);
        builder.deploymentName(AZUREOPENAI_GPT_3T);
        return builder.build();
    }

    @Bean(AZUREOPENAI_GPT_4 + TEXT_SUFFIX)
    @ConditionalOnProperty(AZUREOPENAI_CON)
    public AzureOpenAiChatModel azureOpenAiChatModel_4() {
        AzureOpenAiChatModel.Builder builder =
                JSON.parseObject(JSON.toJSONString(props.getOpenai()), AzureOpenAiChatModel.Builder.class);
        BeanUtils.copyProperties(props.getAzureOpenai(), builder);
        builder.deploymentName(AZUREOPENAI_GPT_4);
        return builder.build();
    }

    @Bean(AZUREOPENAI_GPT_4T + TEXT_SUFFIX)
    @ConditionalOnProperty(AZUREOPENAI_CON)
    public AzureOpenAiChatModel azureOpenAiChatModel_4t() {
        AzureOpenAiChatModel.Builder builder =
                JSON.parseObject(JSON.toJSONString(props.getOpenai()), AzureOpenAiChatModel.Builder.class);
        BeanUtils.copyProperties(props.getAzureOpenai(), builder);
        builder.deploymentName(AZUREOPENAI_GPT_4T);
        return builder.build();
    }

    @Bean(AZUREOPENAI_GPT_4O + TEXT_SUFFIX)
    @ConditionalOnProperty(AZUREOPENAI_CON)
    public AzureOpenAiChatModel azureOpenAiChatModel_4o() {
        AzureOpenAiChatModel.Builder builder =
                JSON.parseObject(JSON.toJSONString(props.getOpenai()), AzureOpenAiChatModel.Builder.class);
        BeanUtils.copyProperties(props.getAzureOpenai(), builder);
        builder.deploymentName(AZUREOPENAI_GPT_4O);
        return builder.build();
    }

    @Bean(AZUREOPENAI_IMAGE_2)
    @ConditionalOnProperty(AZUREOPENAI_CON)
    public AzureOpenAiImageModel azureOpenAiImageModel_2() {
        AzureOpenAiImageModel.Builder builder =
                JSON.parseObject(JSON.toJSONString(props.getOpenai()), AzureOpenAiImageModel.Builder.class);
        BeanUtils.copyProperties(props.getAzureOpenai(), builder);
        builder.deploymentName(AZUREOPENAI_IMAGE_2);
        return builder.build();
    }

    @Bean(AZUREOPENAI_IMAGE_3)
    @ConditionalOnProperty(AZUREOPENAI_CON)
    public AzureOpenAiImageModel azureOpenAiImageModel_3() {
        AzureOpenAiImageModel.Builder builder =
                JSON.parseObject(JSON.toJSONString(props.getOpenai()), AzureOpenAiImageModel.Builder.class);
        BeanUtils.copyProperties(props.getAzureOpenai(), builder);
        builder.deploymentName(AZUREOPENAI_IMAGE_3);
        return builder.build();
    }

    @Bean(AZUREOPENAI_EMBED_3S)
    @ConditionalOnProperty(AZUREOPENAI_CON)
    public AzureOpenAiEmbeddingModel openAiEmbeddingModel_3s() {
        AzureOpenAiEmbeddingModel.Builder builder =
                JSON.parseObject(JSON.toJSONString(props.getOpenai()), AzureOpenAiEmbeddingModel.Builder.class);
        BeanUtils.copyProperties(props.getAzureOpenai(), builder);
        builder.deploymentName(AZUREOPENAI_EMBED_3S);
        return builder.build();
    }

    @Bean(AZUREOPENAI_EMBED_3L)
    @ConditionalOnProperty(AZUREOPENAI_CON)
    public AzureOpenAiEmbeddingModel openAiEmbeddingModel_3l() {
        AzureOpenAiEmbeddingModel.Builder builder =
                JSON.parseObject(JSON.toJSONString(props.getOpenai()), AzureOpenAiEmbeddingModel.Builder.class);
        BeanUtils.copyProperties(props.getAzureOpenai(), builder);
        builder.deploymentName(AZUREOPENAI_EMBED_3L);
        return builder.build();
    }
}
