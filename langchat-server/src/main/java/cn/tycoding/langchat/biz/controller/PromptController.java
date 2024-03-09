package cn.tycoding.langchat.biz.controller;

import cn.hutool.core.util.StrUtil;
import cn.tycoding.langchat.biz.entity.LcPrompt;
import cn.tycoding.langchat.biz.service.PromptService;
import cn.tycoding.langchat.common.utils.MybatisUtil;
import cn.tycoding.langchat.common.utils.QueryPage;
import cn.tycoding.langchat.common.utils.R;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import java.util.Date;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author tycoding
 * @since 2024/1/19
 */
@RequestMapping("/langchat/prompt")
@RestController
@AllArgsConstructor
public class PromptController {

    private final PromptService promptService;

    @GetMapping("/page")
    public R list(LcPrompt data, QueryPage queryPage) {
        LambdaQueryWrapper<LcPrompt> queryWrapper = Wrappers.<LcPrompt>lambdaQuery()
                .like(!StrUtil.isBlank(data.getName()), LcPrompt::getName, data.getName())
                .orderByDesc(LcPrompt::getCreateTime);
        IPage<LcPrompt> iPage = promptService.page(MybatisUtil.wrap(data, queryPage), queryWrapper);
        iPage.getRecords().forEach(i -> {
            if (i.getPrompt() != null && i.getPrompt().length() >= 50) {
                i.setPrompt(StrUtil.sub(i.getPrompt(), 0, 50) + "...");
            }
        });
        return R.ok(MybatisUtil.getData(iPage));
    }

    @GetMapping("/{id}")
    public R getById(@PathVariable String id) {
        return R.ok(promptService.getById(id));
    }

    @DeleteMapping("/{id}")
    public R del(@PathVariable String id) {
        return R.ok(promptService.removeById(id));
    }

    @PostMapping
    public R add(@RequestBody LcPrompt data) {
        data.setCreateTime(new Date());
        return R.ok(promptService.save(data));
    }

    @PutMapping
    public R update(@RequestBody LcPrompt data) {
        return R.ok(promptService.updateById(data));
    }
}
