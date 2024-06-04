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

    private String model;

    private String message;

    private Long conversationId;

    private Long userId;

    private String username;

    private String chatId;

    private Long promptId;

    private String promptText;

    private Long knowledgeId;

    private Long docsId;

    private String path;

    private String role;

    private Prompt prompt;

    private StreamEmitter emitter;
}
