package cn.tycoding.langchat.core.utils;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * @author tycoding
 * @since 2023/12/29
 */
@Data
@Accessors(chain = true)
public class ChatRes {

    private boolean isDone = false;

    private String content;

    private Integer usedToken;

    private long time;

    public ChatRes(String content) {
        this.content = content;
    }

    public ChatRes(Integer usedToken, long startTime) {
        this.isDone = true;
        this.usedToken = usedToken;
        this.time = System.currentTimeMillis() - startTime;
    }
}
