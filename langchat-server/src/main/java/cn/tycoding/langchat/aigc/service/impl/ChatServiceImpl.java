package cn.tycoding.langchat.aigc.service.impl;

import cn.tycoding.langchat.aigc.entity.AigcMessage;
import cn.tycoding.langchat.aigc.entity.AigcOss;
import cn.tycoding.langchat.aigc.service.AigcMessageService;
import cn.tycoding.langchat.aigc.service.ChatService;
import cn.tycoding.langchat.common.constant.RoleEnum;
import cn.tycoding.langchat.common.dto.ChatReq;
import cn.tycoding.langchat.common.dto.ChatRes;
import cn.tycoding.langchat.common.dto.ImageR;
import cn.tycoding.langchat.common.utils.ServletUtil;
import cn.tycoding.langchat.common.utils.StreamEmitter;
import cn.tycoding.langchat.core.service.LangChatService;
import cn.tycoding.langchat.core.service.LangDocService;
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
    private final LangDocService langDocService;
    private final AigcMessageService aigcMessageService;

    @Override
    public void chat(ChatReq req) {
        StreamEmitter emitter = req.getEmitter();
        long startTime = System.currentTimeMillis();
        StringBuilder text = new StringBuilder();

        // save user message
        req.setRole(RoleEnum.USER.getName());
        saveMessage(req, 0, 0);

        try {
            langChatService.chat(req)
                    .onNext(e -> {
                        text.append(e);
                        emitter.send(new ChatRes(e));
                    })
                    .onComplete((e) -> {
                        TokenUsage tokenUsage = e.tokenUsage();
                        emitter.send(new ChatRes(tokenUsage.totalTokenCount(), startTime));
                        emitter.complete();

                        // save message
                        if (req.getConversationId() != null) {
                            req.setMessage(text.toString());
                            req.setRole(RoleEnum.ASSISTANT.getName());
                            saveMessage(req, tokenUsage.inputTokenCount(),
                                    tokenUsage.outputTokenCount());
                        }
                    })
                    .onError((e) -> {
                        emitter.error(e.getMessage());
                        throw new RuntimeException(e.getMessage());
                    })
                    .start();
        } catch (Exception e) {
            e.printStackTrace();
            emitter.error(e.getMessage());
            throw new RuntimeException(e.getMessage());
        }
    }

    @Override
    public void docsChat(ChatReq req) {
        long startTime = System.currentTimeMillis();
        StreamEmitter emitter = req.getEmitter();
        StringBuilder text = new StringBuilder();

        // save user message
        req.setRole(RoleEnum.USER.getName());
        saveMessage(req, 0, 0);

        try {
            langDocService.chat(req)
                    .onNext(e -> {
                        text.append(e);
                        emitter.send(new ChatRes(e));
                    })
                    .onComplete(e -> {
                        TokenUsage tokenUsage = e.tokenUsage();
                        emitter.send(new ChatRes(tokenUsage.totalTokenCount(), startTime));
                        emitter.complete();

                        // save message
                        if (req.getConversationId() != null) {
                            req.setMessage(text.toString());
                            req.setRole(RoleEnum.ASSISTANT.getName());
                            saveMessage(req, tokenUsage.inputTokenCount(),
                                    tokenUsage.outputTokenCount());
                        }
                    })
                    .onError((e) -> {
                        e.printStackTrace();
                        emitter.error(e.getMessage());
                    })
                    .start();
        } catch (Exception e) {
            e.printStackTrace();
            emitter.error(e.getMessage());
            throw new RuntimeException(e.getMessage());
        }
    }

    private void saveMessage(ChatReq req, Integer inputToken, Integer outputToken) {
        AigcMessage message = new AigcMessage();
        BeanUtils.copyProperties(req, message);
        message.setIp(ServletUtil.getIpAddr());
        message.setPromptTokens(inputToken);
        message.setTokens(outputToken);
        aigcMessageService.addMessage(message);
    }

    @Override
    public void singleChat(ChatReq req) {
        StreamEmitter emitter = req.getEmitter();
        long startTime = System.currentTimeMillis();
        try {
            langChatService.chat(req)
                    .onNext(e -> {
                        emitter.send(new ChatRes(e));
                    })
                    .onComplete(e -> {
                        TokenUsage tokenUsage = e.tokenUsage();
                        emitter.send(new ChatRes(tokenUsage.totalTokenCount(), startTime));
                        emitter.complete();
                    })
                    .onError((e) -> {
                        emitter.error(e.getMessage());
                    })
                    .start();
        } catch (Exception e) {
            e.printStackTrace();
            emitter.error(e.getMessage());
            throw new RuntimeException(e.getMessage());
        }
    }

    @Override
    public String text(ChatReq req) {
        String text;
        try {
            text = langChatService.text(req);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e.getMessage());
        }
        return text;
    }

    @Override
    public AigcOss image(ImageR req) {
        Response<Image> res = langChatService.image(req);

        String path = res.content().url().toString();
        AigcOss oss = new AigcOss();
        oss.setUrl(path);
        return oss;
    }


}
