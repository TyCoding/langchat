package cn.tycoding.langchat.core.provider;

import dev.langchain4j.model.embedding.EmbeddingModel;
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
