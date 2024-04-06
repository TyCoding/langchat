package cn.tycoding.langchat.core.service;

import cn.tycoding.langchat.common.dto.DocR;
import cn.tycoding.langchat.common.dto.OssR;
import dev.langchain4j.service.TokenStream;

/**
 * @author tycoding
 * @since 2024/4/4
 */
public interface LangDocService {

    void embedText();

    void embedDoc(OssR req);

    TokenStream search(DocR req);

}
