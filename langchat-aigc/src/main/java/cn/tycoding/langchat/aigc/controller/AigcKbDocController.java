package cn.tycoding.langchat.aigc.controller;

import cn.hutool.core.util.StrUtil;
import cn.tycoding.langchat.aigc.entity.AigcKbDoc;
import cn.tycoding.langchat.aigc.service.AigcKbDocService;
import cn.tycoding.langchat.common.utils.MybatisUtil;
import cn.tycoding.langchat.common.utils.QueryPage;
import cn.tycoding.langchat.common.utils.R;
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
@RequestMapping("/aigc/kb/doc")
public class AigcKbDocController {

    private final AigcKbDocService kbDataService;

    @GetMapping("/list")
    public R<List<AigcKbDoc>> list(AigcKbDoc data) {
        return R.ok(kbDataService.list(Wrappers.<AigcKbDoc>lambdaQuery().orderByDesc(AigcKbDoc::getCreateTime)));
    }

    @GetMapping("/page")
    public R list(AigcKbDoc data, QueryPage queryPage) {
        Page<AigcKbDoc> page = new Page<>(queryPage.getPage(), queryPage.getLimit());
        return R.ok(MybatisUtil.getData(kbDataService.page(page, Wrappers.<AigcKbDoc>lambdaQuery()
                .eq(!StrUtil.isBlank(data.getKbId()), AigcKbDoc::getKbId, data.getKbId())
                .eq(!StrUtil.isBlank(data.getDes()), AigcKbDoc::getDes, data.getDes())
                .orderByDesc(AigcKbDoc::getCreateTime)
        )));
    }

    @GetMapping("/{id}")
    public R<AigcKbDoc> findById(@PathVariable String id) {
        return R.ok(kbDataService.getById(id));
    }

    @PostMapping
    public R add(@RequestBody AigcKbDoc data) {
        data.setCreateTime(String.valueOf(System.currentTimeMillis()));
        kbDataService.save(data);
        return R.ok();
    }

    @PutMapping
    public R update(@RequestBody AigcKbDoc data) {
        kbDataService.updateById(data);
        return R.ok();
    }

    @DeleteMapping("/{id}")
    public R delete(@PathVariable String id) {
        kbDataService.removeById(id);
        return R.ok();
    }
}

