package cn.tycoding.langchat.core.service.impl;

import static dev.ai4j.openai4j.image.ImageModel.DALL_E_QUALITY_HD;
import static java.net.Proxy.Type.HTTP;

import cn.hutool.core.io.FileUtil;
import cn.tycoding.langchat.common.component.SpringContextHolder;
import cn.tycoding.langchat.common.dto.ChatData;
import cn.tycoding.langchat.common.event.MessageEvent;
import cn.tycoding.langchat.core.properties.LangChatProps;
import cn.tycoding.langchat.core.properties.OssProps;
import cn.tycoding.langchat.core.service.LangChatService;
import cn.tycoding.langchat.core.utils.ChatReq;
import cn.tycoding.langchat.core.utils.ChatRes;
import cn.tycoding.langchat.core.utils.OssR;
import cn.tycoding.langchat.core.utils.OssUtil;
import cn.tycoding.langchat.core.utils.StreamEmitter;
import dev.langchain4j.data.image.Image;
import dev.langchain4j.data.message.AiMessage;
import dev.langchain4j.model.StreamingResponseHandler;
import dev.langchain4j.model.chat.ChatLanguageModel;
import dev.langchain4j.model.chat.StreamingChatLanguageModel;
import dev.langchain4j.model.input.Prompt;
import dev.langchain4j.model.openai.OpenAiChatModel;
import dev.langchain4j.model.openai.OpenAiImageModel;
import dev.langchain4j.model.openai.OpenAiStreamingChatModel;
import dev.langchain4j.model.output.Response;
import dev.langchain4j.model.output.TokenUsage;
import java.net.InetSocketAddress;
import java.net.Proxy;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * @author tycoding
 * @since 2023/8/29
 */
@Slf4j
@Service
@AllArgsConstructor
public class LangChatServiceImpl implements LangChatService {

    private final LangChatProps props;
    private final OssProps ossProps;

    @Override
    public void chat(StreamEmitter emitter, Prompt prompt, ChatData data) {
        stream(emitter, prompt, data);
    }

    private void stream(StreamEmitter emitter, Prompt prompt, ChatData data) {
        long startTime = System.currentTimeMillis();
        StreamingChatLanguageModel model = OpenAiStreamingChatModel.builder()
                .apiKey(props.getApiKey())
//                .modelName("gpt-4")
                .proxy(new Proxy(HTTP, new InetSocketAddress("127.0.0.1", 7890)))
                .build();

//        OllamaStreamingChatModel model = OllamaStreamingChatModel.builder()
//                .baseUrl("http://localhost:11434/")
//                .modelName("llama2")
//                .build();

        StringBuilder res = new StringBuilder();
        model.generate(prompt.toUserMessage().text(),
                new StreamingResponseHandler<AiMessage>() {
                    @Override
                    public void onNext(String s) {
                        res.append(s);
                        emitter.send(new ChatRes(s));
                    }

                    @Override
                    public void onError(Throwable throwable) {
                        throwable.printStackTrace();
                        emitter.complete();
                    }

                    @Override
                    public void onComplete(Response<AiMessage> response) {
                        TokenUsage tokenUsage = response.tokenUsage();
                        emitter.send(new ChatRes(tokenUsage.totalTokenCount(), startTime));
                        emitter.complete();

                        // save message
                        if (data != null) {
                            data.setContent(res.toString());
                            SpringContextHolder.publishEvent(new MessageEvent(data));
                        }
                    }
                });
    }

    @Override
    public void stream(ChatReq req) {
        stream(req.getEmitter(), req.getPrompt(), null);
    }

    @Override
    public String text(ChatReq req) {
        ChatLanguageModel model = OpenAiChatModel.builder()
                .apiKey(props.getApiKey())
                .modelName("gpt-4")
                .proxy(new Proxy(HTTP, new InetSocketAddress("127.0.0.1", 7890)))
                .build();

        AiMessage content = model.generate(req.getPrompt().toUserMessage()).content();
        return content.text();
    }

    @Override
    public OssR image(ChatReq req) {
        OpenAiImageModel model = OpenAiImageModel
                .builder()
                .proxy(new Proxy(HTTP, new InetSocketAddress("127.0.0.1", 7890)))
                .apiKey(props.getApiKey())
                .quality(DALL_E_QUALITY_HD)
                .logRequests(true)
                .logResponses(true)
                .withPersisting()
                .build();

        Response<Image> response = model.generate(req.getPrompt().text());
        OssR ossR = OssUtil.transfer(ossProps, FileUtil.file(response.content().url()));
        log.info("生成图片, {}", ossR);
        return ossR;
    }
}
