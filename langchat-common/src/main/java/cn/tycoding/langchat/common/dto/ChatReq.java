package cn.tycoding.langchat.common.dto;

import cn.tycoding.langchat.common.utils.StreamEmitter;
import dev.langchain4j.model.input.Prompt;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * @author tycoding
 * @since 2024/1/30
 */
@Data
@Accessors(chain = true)
public class ChatReq {

    private String modelId;
    private String modelName;
    private String modelProvider;

    private String message;

    private String conversationId;

    private String userId;

    private String username;

    private String chatId;

    private String promptId;

    private String promptText;

    private String docsName;

    private String knowledgeId;

    private String docsId;

    private String path;

    private String role;

    private Boolean isGoogleSearch = false;

    private Prompt prompt;

    private StreamEmitter emitter;
}
