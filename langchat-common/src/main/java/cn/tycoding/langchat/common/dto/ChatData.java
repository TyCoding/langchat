package cn.tycoding.langchat.common.dto;

import java.io.Serializable;
import lombok.Data;

/**
 * @author tycoding
 * @since 2024/2/20
 */
@Data
public class ChatData implements Serializable {

    private static final long serialVersionUID = -2299910927285482191L;

    private String conversationId;

    private String chatId;

    private String promptId;

    private String content;
}
