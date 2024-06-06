package cn.tycoding.langchat.core.consts;

/**
 * @author tycoding
 * @since 2024/1/6
 */
public interface ModelConst {

    String IMAGE_SUFFIX = "_image";
    String TEXT_SUFFIX = "_text";
    String EMBED_SUFFIX = "_embed";

    String OPENAI_GPT_3T = "gpt-3.5-turbo";
    String OPENAI_GPT_4 = "gpt-4";
    String OPENAI_GPT_4T = "gpt-4-turbo";
    String OPENAI_GPT_4O = "gpt-4o";
    String OPENAI_EMBED_3S = "text-embedding-3-small";
    String OPENAI_EMBED_3L = "text-embedding-3-large";
    String OPENAI_IMAGE_2 = "dall-e-2";
    String OPENAI_IMAGE_3 = "dall-e-3";

    String GEMINI_F = "gemini-1.5-flash";
    String GEMINI_P = "gemini-1.5-pro";

    String AZUREOPENAI_GPT_3T = "gpt-3.5-turbo";
    String AZUREOPENAI_GPT_4 = "gpt-4";
    String AZUREOPENAI_GPT_4T = "gpt-4-turbo";
    String AZUREOPENAI_GPT_4O = "gpt-4o";
    String AZUREOPENAI_EMBED_3S = "text-embedding-3-small";
    String AZUREOPENAI_EMBED_3L = "text-embedding-3-large";
    String AZUREOPENAI_IMAGE_2 = "dall-e-2";
    String AZUREOPENAI_IMAGE_3 = "dall-e-3";

    String OLLAMA = "ollama";
    String OLLAMA_TEXT = OLLAMA + TEXT_SUFFIX;
    String OLLAMA_EMBED = OLLAMA + EMBED_SUFFIX;

    String GEMINI = "gemini";
    String GEMINI_IMAGE = GEMINI + IMAGE_SUFFIX;


}
