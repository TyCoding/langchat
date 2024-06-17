package cn.tycoding.langchat.core.component;

import cn.tycoding.langchat.core.properties.LangChatProps;
import com.alibaba.fastjson2.JSON;
import dev.langchain4j.model.openai.OpenAiChatModel;
import dev.langchain4j.model.openai.OpenAiEmbeddingModel;
import dev.langchain4j.model.openai.OpenAiImageModel;
import dev.langchain4j.model.openai.OpenAiStreamingChatModel;
import lombok.AllArgsConstructor;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import static cn.tycoding.langchat.core.consts.ModelConst.*;
import static cn.tycoding.langchat.core.consts.PropConst.OPENAI_CON;

/**
 * @author tycoding
 * @since 2024/3/8
 */
@Configuration
@AllArgsConstructor
public class OpenaiComponent {

    private final LangChatProps props;

    @Bean(OPENAI_GPT_3T)
    @ConditionalOnProperty(value = OPENAI_CON, matchIfMissing = false)
    public OpenAiStreamingChatModel openAiStreamingChatModel_3t() {
        OpenAiStreamingChatModel.OpenAiStreamingChatModelBuilder builder =
                JSON.parseObject(JSON.toJSONString(props.getOpenai()), OpenAiStreamingChatModel.OpenAiStreamingChatModelBuilder.class);
        builder.modelName(OPENAI_GPT_3T);
        return builder.build();
    }

    @Bean(OPENAI_GPT_4)
    @ConditionalOnProperty(OPENAI_CON)
    public OpenAiStreamingChatModel openAiStreamingChatModel_4() {
        OpenAiStreamingChatModel.OpenAiStreamingChatModelBuilder builder =
                JSON.parseObject(JSON.toJSONString(props.getOpenai()), OpenAiStreamingChatModel.OpenAiStreamingChatModelBuilder.class);
        builder.modelName(OPENAI_GPT_4);
        return builder.build();
    }

    @Bean(OPENAI_GPT_4T)
    @ConditionalOnProperty(OPENAI_CON)
    public OpenAiStreamingChatModel openAiStreamingChatModel_4t() {
        OpenAiStreamingChatModel.OpenAiStreamingChatModelBuilder builder =
                JSON.parseObject(JSON.toJSONString(props.getOpenai()), OpenAiStreamingChatModel.OpenAiStreamingChatModelBuilder.class);
        builder.modelName(OPENAI_GPT_4T);
        return builder.build();
    }

    @Bean(OPENAI_GPT_4O)
    @ConditionalOnProperty(OPENAI_CON)
    public OpenAiStreamingChatModel openAiStreamingChatModel_4o() {
        OpenAiStreamingChatModel.OpenAiStreamingChatModelBuilder builder =
                JSON.parseObject(JSON.toJSONString(props.getOpenai()), OpenAiStreamingChatModel.OpenAiStreamingChatModelBuilder.class);
        builder.modelName(OPENAI_GPT_4O);
        return builder.build();
    }

    @Bean(OPENAI_GPT_3T + TEXT_SUFFIX)
    @ConditionalOnProperty(OPENAI_CON)
    public OpenAiChatModel openAiChatModel_3t() {
        OpenAiChatModel.OpenAiChatModelBuilder builder =
                JSON.parseObject(JSON.toJSONString(props.getOpenai()), OpenAiChatModel.OpenAiChatModelBuilder.class);
        builder.modelName(OPENAI_GPT_3T);
        return builder.build();
    }

    @Bean(OPENAI_GPT_4 + TEXT_SUFFIX)
    @ConditionalOnProperty(OPENAI_CON)
    public OpenAiChatModel openAiChatModel_4() {
        OpenAiChatModel.OpenAiChatModelBuilder builder =
                JSON.parseObject(JSON.toJSONString(props.getOpenai()), OpenAiChatModel.OpenAiChatModelBuilder.class);
        builder.modelName(OPENAI_GPT_4);
        return builder.build();
    }

    @Bean(OPENAI_GPT_4T + TEXT_SUFFIX)
    @ConditionalOnProperty(OPENAI_CON)
    public OpenAiChatModel openAiChatModel_4t() {
        OpenAiChatModel.OpenAiChatModelBuilder builder =
                JSON.parseObject(JSON.toJSONString(props.getOpenai()), OpenAiChatModel.OpenAiChatModelBuilder.class);
        builder.modelName(OPENAI_GPT_4T);
        return builder.build();
    }

    @Bean(OPENAI_GPT_4O + TEXT_SUFFIX)
    @ConditionalOnProperty(OPENAI_CON)
    public OpenAiChatModel openAiChatModel_4o() {
        OpenAiChatModel.OpenAiChatModelBuilder builder =
                JSON.parseObject(JSON.toJSONString(props.getOpenai()), OpenAiChatModel.OpenAiChatModelBuilder.class);
        builder.modelName(OPENAI_GPT_4O);
        return builder.build();
    }

    @Bean(OPENAI_IMAGE_2)
    @ConditionalOnProperty(OPENAI_CON)
    public OpenAiImageModel openAiImageModel_2() {
        OpenAiImageModel.OpenAiImageModelBuilder builder =
                JSON.parseObject(JSON.toJSONString(props.getOpenai()), OpenAiImageModel.OpenAiImageModelBuilder.class);
        builder.modelName(OPENAI_IMAGE_2);
        return builder.build();
    }

    @Bean(OPENAI_IMAGE_3)
    @ConditionalOnProperty(OPENAI_CON)
    public OpenAiImageModel openAiImageModel_3() {
        OpenAiImageModel.OpenAiImageModelBuilder builder =
                JSON.parseObject(JSON.toJSONString(props.getOpenai()), OpenAiImageModel.OpenAiImageModelBuilder.class);
        builder.modelName(OPENAI_IMAGE_3);
        return builder.build();
    }

    @Bean(OPENAI_EMBED_3S)
    @ConditionalOnProperty(OPENAI_CON)
    public OpenAiEmbeddingModel openAiEmbeddingModel_3s() {
        OpenAiEmbeddingModel.OpenAiEmbeddingModelBuilder builder =
                JSON.parseObject(JSON.toJSONString(props.getOpenai()), OpenAiEmbeddingModel.OpenAiEmbeddingModelBuilder.class);
        builder.modelName(OPENAI_EMBED_3S);
        return builder.build();
    }

    @Bean(OPENAI_EMBED_3L)
    @ConditionalOnProperty(OPENAI_CON)
    public OpenAiEmbeddingModel openAiEmbeddingModel_3l() {
        OpenAiEmbeddingModel.OpenAiEmbeddingModelBuilder builder =
                JSON.parseObject(JSON.toJSONString(props.getOpenai()), OpenAiEmbeddingModel.OpenAiEmbeddingModelBuilder.class);
        builder.modelName(OPENAI_EMBED_3L);
        return builder.build();
    }
}
