package cn.tycoding.langchat.core.provider;

import cn.tycoding.langchat.biz.component.ProviderEnum;
import dev.langchain4j.data.document.DocumentSplitter;
import dev.langchain4j.data.document.splitter.DocumentSplitters;
import dev.langchain4j.model.azure.AzureOpenAiTokenizer;
import dev.langchain4j.model.embedding.EmbeddingModel;
import dev.langchain4j.model.openai.OpenAiTokenizer;
import lombok.AllArgsConstructor;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

/**
 * @author tycoding
 * @since 2024/3/8
 */
@Component
@AllArgsConstructor
public class EmbedProvider {

    private final ApplicationContext context;

    public static DocumentSplitter splitter(String modelName, String modelProvider) {
        if (ProviderEnum.OPENAI.getModel().equals(modelProvider)) {
            return DocumentSplitters.recursive(100, 0, new OpenAiTokenizer(modelName));
        }
        if (ProviderEnum.AZURE_OPENAI.getModel().equals(modelProvider)) {
            return DocumentSplitters.recursive(100, 0, new AzureOpenAiTokenizer(modelName));
        }
//        if (ProviderEnum.ALIBABA.getModel().equals(modelProvider)) {
//            return new QwenTokenizer(modelName);
//        }
        return DocumentSplitters.recursive(100, 0);
    }

    public EmbeddingModel embed() {
        if (context.containsBean("OpenAiEmbeddingModel")) {
            return (EmbeddingModel) context.getBean("OpenAiEmbeddingModel");
        }
        if (context.containsBean("AzureOpenAiEmbeddingModel")) {
            return (EmbeddingModel) context.getBean("AzureOpenAiEmbeddingModel");
        }
        if (context.containsBean("QianfanEmbeddingModel")) {
            return (EmbeddingModel) context.getBean("QianfanEmbeddingModel");
        }
        if (context.containsBean("QwenEmbeddingModel")) {
            return (EmbeddingModel) context.getBean("QwenEmbeddingModel");
        }
        throw new RuntimeException("No matching embedding model information found, please check the model configuration.");
    }
}
