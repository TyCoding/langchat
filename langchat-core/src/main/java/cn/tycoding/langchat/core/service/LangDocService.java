package cn.tycoding.langchat.core.service;

import cn.tycoding.langchat.biz.entity.SysOss;
import cn.tycoding.langchat.common.dto.DocR;
import dev.langchain4j.service.TokenStream;

/**
 * @author tycoding
 * @since 2024/4/4
 */
public interface LangDocService {

    void embedText();

    void embedDoc(SysOss req);

    TokenStream search(DocR req);

}
