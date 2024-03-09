package cn.tycoding.langchat.core.props;

import cn.tycoding.langchat.core.props.chat.AzureOpenaiProps;
import cn.tycoding.langchat.core.props.chat.ChatglmProps;
import cn.tycoding.langchat.core.props.chat.GeminiProps;
import cn.tycoding.langchat.core.props.chat.HuggingfaceProps;
import cn.tycoding.langchat.core.props.chat.OllamaProps;
import cn.tycoding.langchat.core.props.chat.OpenaiProps;
import cn.tycoding.langchat.core.props.chat.QianfanProps;
import cn.tycoding.langchat.core.props.embed.EmbeddingProps;
import cn.tycoding.langchat.core.props.vectorstore.VectorProps;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @author tycoding
 * @since 2023/11/8
 */
@Data
@ConfigurationProperties(prefix = "langchat")
public class LangChatProps {

    private OpenaiProps openai = new OpenaiProps();

    private OllamaProps ollama = new OllamaProps();

    private GeminiProps gemini = new GeminiProps();

    private AzureOpenaiProps azureOpenai = new AzureOpenaiProps();

    private QianfanProps qianfan = new QianfanProps();

    private ChatglmProps chatglm = new ChatglmProps();

    private HuggingfaceProps huggingface = new HuggingfaceProps();

    private EmbeddingProps embedding = new EmbeddingProps();

    private VectorProps vectorstore = new VectorProps();
}
