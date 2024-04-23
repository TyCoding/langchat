package cn.tycoding.langchat.aigc.controller;

import cn.hutool.core.util.StrUtil;
import cn.tycoding.langchat.aigc.entity.AigcKb;
import cn.tycoding.langchat.aigc.service.AigcKbService;
import cn.tycoding.langchat.common.utils.MybatisUtil;
import cn.tycoding.langchat.common.utils.QueryPage;
import cn.tycoding.langchat.common.utils.R;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author tycoding
 * @since 2024/4/15
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/aigc/kb")
public class AigcKbController {

    private final AigcKbService kbService;

    @GetMapping("/list")
    public R<List<AigcKb>> list(AigcKb data) {
        return R.ok(kbService.list(Wrappers.<AigcKb>lambdaQuery().orderByDesc(AigcKb::getCreateTime)));
    }

    @GetMapping("/page")
    public R list(AigcKb data, QueryPage queryPage) {
        Page<AigcKb> page = new Page<>(queryPage.getPage(), queryPage.getLimit());
        LambdaQueryWrapper<AigcKb> queryWrapper = Wrappers.<AigcKb>lambdaQuery()
                .like(!StrUtil.isBlank(data.getName()), AigcKb::getName, data.getName())
                .orderByDesc(AigcKb::getCreateTime);
        return R.ok(MybatisUtil.getData(kbService.page(page, queryWrapper)));
    }

    @GetMapping("/{id}")
    public R<AigcKb> findById(@PathVariable String id) {
        return R.ok(kbService.getById(id));
    }

    @PostMapping
    public R add(@RequestBody AigcKb data) {
        data.setCreateTime(String.valueOf(System.currentTimeMillis()));
        kbService.save(data);
        return R.ok();
    }

    @PutMapping
    public R update(@RequestBody AigcKb data) {
        kbService.updateById(data);
        return R.ok();
    }

    @DeleteMapping("/{id}")
    public R delete(@PathVariable String id) {
        kbService.removeById(id);
        return R.ok();
    }
}

