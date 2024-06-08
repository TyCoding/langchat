package cn.tycoding.langchat.aigc.controller;

import cn.hutool.core.lang.Dict;
import cn.tycoding.langchat.aigc.mapper.AigcKnowledgeMapper;
import cn.tycoding.langchat.aigc.mapper.AigcMessageMapper;
import cn.tycoding.langchat.aigc.mapper.AigcPromptMapper;
import cn.tycoding.langchat.aigc.mapper.AigcUserMapper;
import cn.tycoding.langchat.common.utils.R;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author tycoding
 * @since 2024/6/8
 */
@RequestMapping("/aigc/statistic")
@RestController
@AllArgsConstructor
public class StatisticsController {

    private final AigcMessageMapper aigcMessageMapper;
    private final AigcUserMapper aigcUserMapper;
    private final AigcKnowledgeMapper aigcKnowledgeMapper;
    private final AigcPromptMapper aigcPromptMapper;

    @GetMapping("/requestBy30")
    public R request30Chart() {
        return R.ok(aigcMessageMapper.getReqChartBy30());
    }

    @GetMapping("/token")
    public R tokenChart() {
        return R.ok(aigcMessageMapper.getTokenChart());
    }

    @GetMapping("/request")
    public R requestChart() {
        return R.ok(aigcMessageMapper.getReqChart());
    }

    @GetMapping("/home")
    public R home() {
        Dict reqData = aigcMessageMapper.getCount();
        Dict totalData = aigcMessageMapper.getTotalSum();
        Dict userData = aigcUserMapper.getCount();
        Long totalKnowledge = aigcKnowledgeMapper.selectCount(Wrappers.query());
        Long totalPrompt = aigcPromptMapper.selectCount(Wrappers.query());
        Dict result = Dict.create();
        result.putAll(reqData);
        result.putAll(totalData);
        result.putAll(userData);
        result.set("totalKnowledge", totalKnowledge).set("totalPrompt", totalPrompt);
        return R.ok(result);
    }
}
