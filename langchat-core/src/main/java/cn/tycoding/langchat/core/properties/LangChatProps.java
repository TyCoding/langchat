package cn.tycoding.langchat.core.properties;

import cn.tycoding.langchat.core.properties.chat.AzureOpenaiProps;
import cn.tycoding.langchat.core.properties.chat.GeminiProps;
import cn.tycoding.langchat.core.properties.chat.OllamaProps;
import cn.tycoding.langchat.core.properties.chat.OpenaiProps;
import cn.tycoding.langchat.core.properties.embed.EmbeddingProps;
import cn.tycoding.langchat.core.properties.search.WebSearchProps;
import cn.tycoding.langchat.core.properties.vectorstore.VectorProps;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @author tycoding
 * @since 2024/4/15
 */
@Data
@ConfigurationProperties(prefix = "langchat")
public class LangChatProps {

    private OpenaiProps openai = new OpenaiProps();

    private OllamaProps ollama = new OllamaProps();

    private GeminiProps gemini = new GeminiProps();

    private AzureOpenaiProps azureOpenai = new AzureOpenaiProps();

    private EmbeddingProps embedding = new EmbeddingProps();

    private VectorProps vectorstore = new VectorProps();

    private WebSearchProps webSearch = new WebSearchProps();
}
