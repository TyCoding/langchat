package cn.tycoding.langchat.core.consts;

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

    public static final String OPENAI_GPT_3T = "gpt-3.5-turbo";
    public static final String OPENAI_GPT_4 = "gpt-4";
    public static final String OPENAI_GPT_4T = "gpt-4-turbo";
    public static final String OPENAI_GPT_4O = "gpt-4o";
    public static final String OPENAI_EMBED_3S = "text-embedding-3-small";
    public static final String OPENAI_EMBED_3L = "text-embedding-3-large";
    public static final String OPENAI_IMAGE_2 = "dall-e-2";
    public static final String OPENAI_IMAGE_3 = "dall-e-3";

    public static final String GEMINI_F = "gemini-1.5-flash";
    public static final String GEMINI_P = "gemini-1.5-pro";

    public static final String AZUREOPENAI_GPT_3T = "gpt-3.5-turbo";
    public static final String AZUREOPENAI_GPT_4 = "gpt-4";
    public static final String AZUREOPENAI_GPT_4T = "gpt-4-turbo";
    public static final String AZUREOPENAI_GPT_4O = "gpt-4o";
    public static final String AZUREOPENAI_EMBED_3S = "text-embedding-3-small";
    public static final String AZUREOPENAI_EMBED_3L = "text-embedding-3-large";
    public static final String AZUREOPENAI_IMAGE_2 = "dall-e-2";
    public static final String AZUREOPENAI_IMAGE_3 = "dall-e-3";

    public static final String OLLAMA = "ollama";
    public static final String OLLAMA_TEXT = OLLAMA + TEXT_SUFFIX;
    public static final String OLLAMA_EMBED = OLLAMA + EMBED_SUFFIX;

    public static final String GEMINI = "gemini";
    public static final String[] GEMINI_MODELS = new String[]{"gemini-1.5-flash", "gemini-1.5-pro"};
    public static final String GEMINI_TEXT = GEMINI + TEXT_SUFFIX;
    public static final String GEMINI_IMAGE = GEMINI + IMAGE_SUFFIX;

    public static final String AZUREOPENAI = "azureopenai";
    public static final String AZUREOPENAI_TEXT = AZUREOPENAI + TEXT_SUFFIX;
    public static final String AZUREOPENAI_EMBED = AZUREOPENAI + EMBED_SUFFIX;

    public static final String PGVECTOR = "pgvector";
    public static final String MILVUS = "milvus";

}
