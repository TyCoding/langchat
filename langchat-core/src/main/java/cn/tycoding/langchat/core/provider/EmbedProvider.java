package cn.tycoding.langchat.core.provider;

import dev.langchain4j.model.embedding.AllMiniLmL6V2EmbeddingModel;
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

    public EmbeddingModel embed(String model) {
        if (context.containsBean(model)) {
            return (EmbeddingModel) context.getBean(model);
        }
        throw new RuntimeException("No matching embedding model information found, please check the model configuration.");
    }

    public EmbeddingModel embed() {
        return new AllMiniLmL6V2EmbeddingModel();
    }
}
