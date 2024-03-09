package cn.tycoding.langchat.core.dto;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * @author tycoding
 * @since 2024/1/29
 */
@Data
@Accessors(chain = true)
public class ChatRes {

    private boolean isDone = false;

    private String message;

    private Integer usedToken;

    private long time;

    public ChatRes(String content) {
        this.message = content;
    }

    public ChatRes(Integer usedToken, long startTime) {
        this.isDone = true;
        this.usedToken = usedToken;
        this.time = System.currentTimeMillis() - startTime;
    }
}
