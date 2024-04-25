package cn.tycoding.langchat.aigc.service.impl;

import cn.hutool.core.util.StrUtil;
import cn.tycoding.langchat.aigc.entity.AigcMessage;
import cn.tycoding.langchat.aigc.entity.AigcOss;
import cn.tycoding.langchat.aigc.service.AigcMessageService;
import cn.tycoding.langchat.aigc.service.ChatService;
import cn.tycoding.langchat.common.constant.RoleEnum;
import cn.tycoding.langchat.common.dto.*;
import cn.tycoding.langchat.common.utils.StreamEmitter;
import cn.tycoding.langchat.core.enums.ModelConst;
import cn.tycoding.langchat.core.service.LangChatService;
import cn.tycoding.langchat.core.service.LangDocService;
import dev.langchain4j.data.image.Image;
import dev.langchain4j.data.message.AiMessage;
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
        AigcMessage message = new AigcMessage();
        BeanUtils.copyProperties(req, message);
        message.setRole(RoleEnum.ASSISTANT.getName());
        log.info("保存消息：{}", message);
        aigcMessageService.addMessage(message);
    }


    @Override
    public void stream(TextR req) {
        StreamEmitter emitter = req.getEmitter();
        long startTime = System.currentTimeMillis();
        ChatReq chat = new ChatReq().setModel(ModelConst.OPENAI).setPrompt(req.getPrompt());

        try {
            langChatService.stream(chat)
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
            throw new RuntimeException("Ai Request Error");
        }
    }

    @Override
    public String text(TextR req) {
        return langChatService.text(req).content().text();
    }

    @Override
    public AigcOss image(ImageR req) {
        if (req.getModel().equals(ModelConst.GEMINI_IMAGE)) {
            Response<AiMessage> text = langChatService.textImage(
                    new TextR().setPrompt(req.getPrompt()).setModel(req.getModel()));
            log.info("生成图片：{}", text);
        } else {
            Response<Image> image = langChatService.image(req);
            log.info("生成图片：{}", image);
        }

        AigcOss oss = new AigcOss();
//        ossMapper.insert(oss);
        return oss;
    }

    @Override
    public void docsChat(DocR req) {
        long startTime = System.currentTimeMillis();
        StreamEmitter emitter = req.getEmitter();
        try {
            langDocService.search(req)
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
            throw new RuntimeException("Ai Request Error");
        }
    }

    @Override
    public void docsEmbed(AigcOss req) {
        langDocService.embedDoc(null);
    }
}
