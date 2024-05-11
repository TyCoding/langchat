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

    private String conversationId;

    private String userId;

    private String username;

    private String chatId;

    private String promptId;

    private String role;

    private Prompt prompt;

    private StreamEmitter emitter;

    public ChatReq() {
    }

    public ChatReq(StreamEmitter emitter) {
        this.emitter = emitter;
    }

    public ChatReq(Prompt prompt) {
        this.prompt = prompt;
    }

    public ChatReq(Prompt prompt, StreamEmitter emitter) {
        this.prompt = prompt;
        this.emitter = emitter;
    }

    public ChatReq(String message, StreamEmitter emitter) {
        this.message = message;
        this.emitter = emitter;
    }
}
