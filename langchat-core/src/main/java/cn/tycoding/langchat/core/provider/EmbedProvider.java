package cn.tycoding.langchat.core.provider;

import cn.tycoding.langchat.biz.component.ProviderEnum;
import cn.tycoding.langchat.core.consts.EmbedConst;
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
        if (context.containsBean(EmbedConst.CLAZZ_NAME_OPENAI)) {
            return (EmbeddingModel) context.getBean(EmbedConst.CLAZZ_NAME_OPENAI);
        }
        if (context.containsBean(EmbedConst.CLAZZ_NAME_AZURE_OPENAI)) {
            return (EmbeddingModel) context.getBean(EmbedConst.CLAZZ_NAME_AZURE_OPENAI);
        }
        if (context.containsBean(EmbedConst.CLAZZ_NAME_QIANFAN)) {
            return (EmbeddingModel) context.getBean(EmbedConst.CLAZZ_NAME_QIANFAN);
        }
        if (context.containsBean(EmbedConst.CLAZZ_NAME_QIANWEN)) {
            return (EmbeddingModel) context.getBean(EmbedConst.CLAZZ_NAME_QIANWEN);
        }
        if (context.containsBean(EmbedConst.CLAZZ_NAME_OLLAMA)) {
            return (EmbeddingModel) context.getBean(EmbedConst.CLAZZ_NAME_OLLAMA);
        }
        throw new RuntimeException("No matching embedding model information found, please check the model configuration.");
    }
}
