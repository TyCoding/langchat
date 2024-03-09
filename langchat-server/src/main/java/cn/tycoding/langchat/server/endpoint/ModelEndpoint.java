package cn.tycoding.langchat.server.endpoint;

import cn.hutool.core.lang.Dict;
import cn.tycoding.langchat.common.utils.R;
import cn.tycoding.langchat.core.enums.ModelConst;
import java.util.Arrays;
import java.util.List;
import lombok.AllArgsConstructor;
import org.springframework.context.ApplicationContext;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author tycoding
 * @since 2024/3/2
 */
@RequestMapping("/langchat/model")
@RestController
@AllArgsConstructor
public class ModelEndpoint {

    @GetMapping("/list")
    public R list() {
        List<Dict> list = Arrays.asList(
                set("ChatGPT", ModelConst.OPENAI),
                set("Ollama", ModelConst.OLLAMA),
                set("Google Gemini", ModelConst.GEMINI),
                set("Azure OpenAI", ModelConst.AZUREOPENAI),
                set("ChatGLM", ModelConst.CHATGLM),
                set("千帆大模型", ModelConst.QIANFAN)
        );
        return R.ok(list);
    }

    private Dict set(String label, String value) {
        return new Dict().set("label", label).set("value", value);
    }
}
