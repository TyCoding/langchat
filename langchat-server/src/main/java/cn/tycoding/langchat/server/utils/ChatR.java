package cn.tycoding.langchat.server.utils;

import cn.tycoding.langchat.core.utils.StreamEmitter;
import cn.tycoding.langchat.server.entity.LcMessage;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author tycoding
 * @since 2024/2/20
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class ChatR extends LcMessage {
    private static final long serialVersionUID = 2838308353711307727L;

    private StreamEmitter emitter;
}
