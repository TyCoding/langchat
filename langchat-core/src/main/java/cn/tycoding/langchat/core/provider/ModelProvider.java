package cn.tycoding.langchat.core.provider;

import dev.langchain4j.model.chat.ChatLanguageModel;
import dev.langchain4j.model.chat.StreamingChatLanguageModel;
import dev.langchain4j.model.image.ImageModel;
import lombok.AllArgsConstructor;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

/**
 * @author tycoding
 * @since 2024/3/8
 */
@Component
@AllArgsConstructor
public class ModelProvider {

    private final ApplicationContext context;

    /**
     * Gets the Model of the streaming type
     */
    public StreamingChatLanguageModel stream(String model) {
        if (context.containsBean(model)) {
            return (StreamingChatLanguageModel) context.getBean(model);
        } else {
            throw new RuntimeException("No matching model information found, please check the model configuration.");
        }
    }

    public ChatLanguageModel text(String model) {
        if (context.containsBean(model)) {
            return (ChatLanguageModel) context.getBean(model);
        } else {
            throw new RuntimeException("No matching model information found, please check the model configuration.");
        }
    }

    public ImageModel image(String model) {
        if (context.containsBean(model)) {
            return (ImageModel) context.getBean(model);
        } else {
            throw new RuntimeException("No matching model information found, please check the model configuration.");
        }
    }
}
