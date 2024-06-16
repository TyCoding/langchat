package cn.tycoding.langchat.core.provider;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.util.StrUtil;
import cn.tycoding.langchat.aigc.entity.AigcModel;
import cn.tycoding.langchat.aigc.service.AigcModelService;
import cn.tycoding.langchat.common.component.SpringContextHolder;
import cn.tycoding.langchat.core.consts.ProviderEnum;
import cn.tycoding.langchat.core.properties.chat.OpenaiProps;
import com.alibaba.fastjson2.JSON;
import dev.langchain4j.model.openai.OpenAiStreamingChatModel;
import java.util.List;
import lombok.AllArgsConstructor;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.annotation.Configuration;

/**
 * @author tycoding
 * @since 2024/6/16
 */
@Configuration
@AllArgsConstructor
public class ProviderInitialize implements ApplicationContextAware {

    private final AigcModelService aigcModelService;
    private final SpringContextHolder contextHolder;

    @Override
    public void setApplicationContext(ApplicationContext context) throws BeansException {
        init();
    }

    public void init() {
        List<AigcModel> list = aigcModelService.list();
        list.forEach(model -> {
            String provider = model.getProvider();
            if (ProviderEnum.OPENAI.getModel().equals(provider)) {
                if (StrUtil.isBlank(model.getApiKey())) {
                    return;
                }
                OpenaiProps props = new OpenaiProps()
                        .setApiKey(model.getApiKey())
                        .setBaseUrl(model.getBaseUrl())
                        .setModelName(model.getModel())
                        .setMaxTokens(model.getResponseLimit())
                        .setTemperature(model.getTemperature())
                        .setTopP(model.getTopP());
                OpenAiStreamingChatModel.OpenAiStreamingChatModelBuilder builder =
                        JSON.parseObject(JSON.toJSONString(props), OpenAiStreamingChatModel.OpenAiStreamingChatModelBuilder.class);
                BeanUtil.copyProperties(props, builder);
                OpenAiStreamingChatModel build = builder.build();
                contextHolder.registerBean(model.getId(), build);
            }

            if (ProviderEnum.GOOGLE.getModel().equals(provider)) {

            }

            if (ProviderEnum.OLLAMA.getModel().equals(provider)) {

            }

            if (ProviderEnum.BAIDU.getModel().equals(provider)) {

            }

            if (ProviderEnum.ALIBABA.getModel().equals(provider)) {

            }
        });
    }
}
