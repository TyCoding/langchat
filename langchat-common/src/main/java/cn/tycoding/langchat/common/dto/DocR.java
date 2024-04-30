package cn.tycoding.langchat.common.dto;

import cn.tycoding.langchat.common.utils.StreamEmitter;
import dev.langchain4j.model.input.Prompt;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * @author tycoding
 * @since 2024/4/4
 */
@Data
@Accessors(chain = true)
public class DocR {
    private static final long serialVersionUID = 4885562458437352308L;

    private String id;

    private String knowledgeId;
    private String docsId;
    private String path;

    private Prompt prompt;

    private StreamEmitter emitter;

    /**
     * 内容
     */
    private String message;

    /**
     * 模型
     */
    private String model;
}
