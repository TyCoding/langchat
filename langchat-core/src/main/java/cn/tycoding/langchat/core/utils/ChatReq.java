package cn.tycoding.langchat.core.utils;

import dev.langchain4j.model.input.Prompt;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * @author tycoding
 * @since 2023/12/30
 */
@Data
@Accessors(chain = true)
public class ChatReq {

    private String botId;

    private String content;

    private String promptText;

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

    public ChatReq(String content, StreamEmitter emitter) {
        this.content = content;
        this.emitter = emitter;
    }

    public ChatReq(String content, String promptText, StreamEmitter emitter) {
        this.content = content;
        this.promptText = promptText;
        this.emitter = emitter;
    }
}
