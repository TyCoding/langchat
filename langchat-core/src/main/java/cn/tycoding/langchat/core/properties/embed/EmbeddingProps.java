package cn.tycoding.langchat.core.properties.embed;

import cn.tycoding.langchat.core.enums.ModelConst;
import lombok.Data;
import lombok.Getter;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @author tycoding
 * @since 2023/12/9
 */
@Data
@ConfigurationProperties(prefix = "langchat.embedding")
public class EmbeddingProps {

    private Provider provider = Provider.OPENAI;

    private OpenaiEmbedProps openai = new OpenaiEmbedProps();

    private AzureOpenaiEmbedProps azureopenai = new AzureOpenaiEmbedProps();

    private OllamaEmbedProps ollama = new OllamaEmbedProps();

    @Getter
    public enum Provider {

        OPENAI(ModelConst.OPENAI_EMBED),
        OLLAMA(ModelConst.OLLAMA_EMBED),
        AZUREOPENAI(ModelConst.AZUREOPENAI_EMBED),
        ;

        private final String name;
        Provider(String name) {
            this.name = name;
        }
    }
}
