package cn.tycoding.langchat.server.service.impl;

import cn.hutool.core.util.StrUtil;
import cn.tycoding.langchat.biz.entity.SysMessage;
import cn.tycoding.langchat.biz.entity.SysOss;
import cn.tycoding.langchat.biz.mapper.OssMapper;
import cn.tycoding.langchat.biz.service.MessageService;
import cn.tycoding.langchat.common.constant.RoleEnum;
import cn.tycoding.langchat.common.dto.ChatReq;
import cn.tycoding.langchat.common.dto.ChatRes;
import cn.tycoding.langchat.common.dto.ImageR;
import cn.tycoding.langchat.common.dto.TextR;
import cn.tycoding.langchat.common.utils.StreamEmitter;
import cn.tycoding.langchat.core.service.LangChatService;
import cn.tycoding.langchat.server.service.ChatService;
import dev.langchain4j.data.image.Image;
import dev.langchain4j.model.output.Response;
import dev.langchain4j.model.output.TokenUsage;
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
    private final MessageService messageService;
    private final OssMapper ossMapper;

    @Override
    public void chat(ChatReq req) {
        StreamEmitter emitter = req.getEmitter();
        long startTime = System.currentTimeMillis();
        StringBuilder text = new StringBuilder();

        try {
            langChatService.stream(req)
                    .onNext(e -> {
                        text.append(e);
                        emitter.send(new ChatRes(e));
                    })
                    .onComplete(e -> {
                        TokenUsage tokenUsage = e.tokenUsage();
                        emitter.send(new ChatRes(tokenUsage.totalTokenCount(), startTime));
                        emitter.complete();

                        // save message
                        if (StrUtil.isNotBlank(req.getConversationId())) {
                            req.setMessage(text.toString());
                            saveMessage(req);
                        }
                    })
                    .onError((e) -> {
                        emitter.error(e.getMessage());
                    })
                    .start();
        } catch (Exception e) {
            e.printStackTrace();
            emitter.error(e.getMessage());
            throw new RuntimeException("Ai Request Error");
        }
    }

    private void saveMessage(ChatReq req) {
        SysMessage message = new SysMessage();
        BeanUtils.copyProperties(req, message);
        message.setRole(RoleEnum.ASSISTANT.getName());
        messageService.addMessage(message);
    }


    @Override
    public void stream(TextR req) {
    }

    @Override
    public String text(TextR req) {
        return langChatService.text(req).content().text();
    }

    @Override
    public SysOss image(ImageR req) {
        Response<Image> image = langChatService.image(req);

        SysOss oss = new SysOss();
//        ossMapper.insert(oss);
        return oss;
    }
}
