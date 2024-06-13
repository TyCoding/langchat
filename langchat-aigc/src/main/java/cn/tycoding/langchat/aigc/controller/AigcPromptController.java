package cn.tycoding.langchat.aigc.controller;

import cn.hutool.core.util.StrUtil;
import cn.tycoding.langchat.aigc.entity.AigcPrompt;
import cn.tycoding.langchat.aigc.service.AigcPromptService;
import cn.tycoding.langchat.common.utils.MybatisUtil;
import cn.tycoding.langchat.common.utils.QueryPage;
import cn.tycoding.langchat.common.utils.R;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

/**
 * @author tycoding
 * @since 2024/1/19
 */
@RequestMapping("/aigc/prompt")
@RestController
@AllArgsConstructor
public class AigcPromptController {

    private final AigcPromptService aigcPromptService;

    @GetMapping("/page")
    public R list(AigcPrompt data, QueryPage queryPage) {
        LambdaQueryWrapper<AigcPrompt> queryWrapper = Wrappers.<AigcPrompt>lambdaQuery()
                .like(!StrUtil.isBlank(data.getName()), AigcPrompt::getName, data.getName())
                .orderByDesc(AigcPrompt::getCreateTime);
        IPage<AigcPrompt> iPage = aigcPromptService.page(MybatisUtil.wrap(data, queryPage), queryWrapper);
        return R.ok(MybatisUtil.getData(iPage));
    }

    @GetMapping("/list")
    public R list(AigcPrompt data) {
        List<AigcPrompt> list = aigcPromptService.list(Wrappers.<AigcPrompt>lambdaQuery().orderByDesc(AigcPrompt::getCreateTime));
        return R.ok(list);
    }

    @GetMapping("/{id}")
    public R getById(@PathVariable String id) {
        return R.ok(aigcPromptService.getById(id));
    }

    @DeleteMapping("/{id}")
    public R del(@PathVariable String id) {
        return R.ok(aigcPromptService.removeById(id));
    }

    @PostMapping
    public R add(@RequestBody AigcPrompt data) {
        data.setCreateTime(new Date());
        return R.ok(aigcPromptService.save(data));
    }

    @PutMapping
    public R update(@RequestBody AigcPrompt data) {
        return R.ok(aigcPromptService.updateById(data));
    }
}
