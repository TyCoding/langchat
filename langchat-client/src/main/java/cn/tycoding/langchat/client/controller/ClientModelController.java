package cn.tycoding.langchat.client.controller;

import cn.tycoding.langchat.biz.entity.AigcModel;
import cn.tycoding.langchat.biz.entity.AigcPrompt;
import cn.tycoding.langchat.biz.service.AigcModelService;
import cn.tycoding.langchat.biz.service.AigcPromptService;
import cn.tycoding.langchat.common.utils.R;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author tycoding
 * @since 2024/1/19
 */
@RequestMapping("/client/prompt")
@RestController
@AllArgsConstructor
public class ClientModelController {

    private final AigcPromptService aigcPromptService;
    private final AigcModelService aigcModelService;

    @GetMapping("/prompt/list")
    public R list(AigcPrompt data) {
        List<AigcPrompt> list = aigcPromptService.list(Wrappers.<AigcPrompt>lambdaQuery().orderByDesc(AigcPrompt::getCreateTime));
        return R.ok(list);
    }

    @GetMapping("/getChatModels")
    public R<List<AigcModel>> getChatModels() {
        List<AigcModel> list = aigcModelService.getChatModels();
        list.forEach(i -> i.setApiKey(null));
        return R.ok(list);
    }
}
