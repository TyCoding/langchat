package cn.tycoding.langchat.core.service;

import cn.tycoding.langchat.common.dto.ChatReq;
import cn.tycoding.langchat.common.dto.ImageR;
import dev.langchain4j.data.image.Image;
import dev.langchain4j.model.output.Response;
import dev.langchain4j.service.TokenStream;

/**
 * @author tycoding
 * @since 2024/3/8
 */
public interface LangChatService {

    TokenStream chat(ChatReq req);

    String text(ChatReq req);

    Response<Image> image(ImageR req);
}
