package cn.tycoding.langchat.core.enums;

import lombok.Data;

/**
 * @author tycoding
 * @since 2024/1/6
 */
@Data
public class ModelConst {

    public static final String IMAGE_SUFFIX = "_image";
    public static final String TEXT_SUFFIX = "_text";
    public static final String EMBED_SUFFIX = "_embed";

    public static final String OPENAI = "openai";
    public static final String OPENAI_TEXT = OPENAI + TEXT_SUFFIX;
    public static final String OPENAI_IMAGE = OPENAI + IMAGE_SUFFIX;
    public static final String OPENAI_EMBED = OPENAI + EMBED_SUFFIX;

    public static final String OLLAMA = "ollama";
    public static final String OLLAMA_TEXT = OLLAMA + TEXT_SUFFIX;
    public static final String OLLAMA_EMBED = OLLAMA + EMBED_SUFFIX;

    public static final String GEMINI = "gemini";
    public static final String GEMINI_TEXT = GEMINI + TEXT_SUFFIX;
    public static final String GEMINI_IMAGE = GEMINI + IMAGE_SUFFIX;

    public static final String AZUREOPENAI = "azureopenai";
    public static final String AZUREOPENAI_TEXT = AZUREOPENAI + TEXT_SUFFIX;
    public static final String AZUREOPENAI_EMBED = AZUREOPENAI + EMBED_SUFFIX;

    public static final String QIANFAN = "qianfan";
    public static final String QIANFAN_TEXT = QIANFAN + TEXT_SUFFIX;

    public static final String CHATGLM = "chatglm";
    public static final String CHATGLM_TEXT = CHATGLM + TEXT_SUFFIX;

    public static final String PGVECTOR = "pgvector";
    public static final String MILVUS = "milvus";

}
