package cn.tycoding.langchat.server.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.tycoding.langchat.common.dto.ChatData;
import cn.tycoding.langchat.core.service.LangChatService;
import cn.tycoding.langchat.core.utils.ChatReq;
import cn.tycoding.langchat.core.utils.FileEnum;
import cn.tycoding.langchat.core.utils.OssR;
import cn.tycoding.langchat.common.constant.PromptConst;
import cn.tycoding.langchat.server.utils.ChatR;
import cn.tycoding.langchat.server.utils.TextR;
import cn.tycoding.langchat.server.utils.ImageR;
import cn.tycoding.langchat.server.component.PromptStore;
import cn.tycoding.langchat.server.entity.LcOss;
import cn.tycoding.langchat.server.mapper.OssMapper;
import cn.tycoding.langchat.server.service.ChatService;
import dev.langchain4j.model.input.Prompt;
import dev.langchain4j.model.input.PromptTemplate;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

/**
 * @author tycoding
 * @since 2024/1/4
 */
@Slf4j
@Service
@AllArgsConstructor
public class ChatServiceImpl implements ChatService {

    private final LangChatService langChatService;
    private final OssMapper ossMapper;

    @Override
    public void chat(ChatR req) {
        PromptTemplate promptTemplate = PromptTemplate.from(PromptStore.get(PromptConst.CHAT));
        Prompt prompt = promptTemplate.apply(BeanUtil.beanToMap(req, false, true));
        ChatData data = new ChatData();
        BeanUtils.copyProperties(req, data);
        langChatService.chat(req.getEmitter(), prompt, data);
    }

    @Override
    public void stream(TextR req, PromptConst promptConst) {
        PromptTemplate promptTemplate = PromptTemplate.from(PromptStore.get(promptConst));
        Prompt prompt = promptTemplate.apply(BeanUtil.beanToMap(req, false, true));
        langChatService.stream(new ChatReq(prompt, req.getEmitter()));
    }

    @Override
    public String text(TextR req, PromptConst promptConst) {
        PromptTemplate promptTemplate = PromptTemplate.from(PromptStore.get(promptConst));
        Prompt prompt = promptTemplate.apply(BeanUtil.beanToMap(req, false, true));
        return langChatService.text(new ChatReq(prompt));
    }

    @Override
    public LcOss image(ImageR req) {
        PromptTemplate promptTemplate = PromptTemplate.from(PromptStore.get(PromptConst.IMAGE));
        Prompt prompt = promptTemplate.apply(BeanUtil.beanToMap(req, false, true));
        OssR ossR = langChatService.image(new ChatReq(prompt));

        LcOss oss = new LcOss();
        BeanUtils.copyProperties(ossR, oss);
//        oss.setUserId(ClientUtil.getClientId());
        oss.setChannel(FileEnum.OUTPUT.getChannel());
        ossMapper.insert(oss);
        return oss;
    }
}
