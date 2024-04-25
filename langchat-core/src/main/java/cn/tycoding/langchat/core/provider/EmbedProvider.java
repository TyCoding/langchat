package cn.tycoding.langchat.core.provider;

import cn.tycoding.langchat.core.properties.embed.EmbeddingProps;
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
    private final EmbeddingProps embeddingProps;

    public EmbeddingModel embed() {
        String name = embeddingProps.getProvider().getName();
        if (context.containsBean(name)) {
            return (EmbeddingModel) context.getBean(name);
        }

        throw new RuntimeException("No matching embedding model information found, please check the model configuration.");
    }
}
