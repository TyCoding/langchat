package cn.tycoding.langchat.aigc.controller;

import cn.hutool.core.util.StrUtil;
import cn.tycoding.langchat.aigc.entity.AigcKbFile;
import cn.tycoding.langchat.aigc.service.AigcKbFileService;
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
@RequestMapping("/aigc/kb/file")
public class AigcKbFileController {

    private final AigcKbFileService kbFileService;

    @GetMapping("/list")
    public R<List<AigcKbFile>> list(AigcKbFile data) {
        return R.ok(kbFileService.list(Wrappers.<AigcKbFile>lambdaQuery().orderByDesc(AigcKbFile::getCreateTime)));
    }

    @GetMapping("/page")
    public R list(AigcKbFile data, QueryPage queryPage) {
        Page<AigcKbFile> page = new Page<>(queryPage.getPage(), queryPage.getLimit());
        return R.ok(MybatisUtil.getData(kbFileService.page(page, Wrappers.<AigcKbFile>lambdaQuery()
                .eq(!StrUtil.isBlank(data.getKbId()), AigcKbFile::getKbId, data.getKbId())
                .orderByDesc(AigcKbFile::getCreateTime)
        )));
    }

    @GetMapping("/{id}")
    public R<AigcKbFile> findById(@PathVariable String id) {
        return R.ok(kbFileService.getById(id));
    }

    @PostMapping
    public R add(@RequestBody AigcKbFile data) {
        data.setCreateTime(String.valueOf(System.currentTimeMillis()));
        kbFileService.save(data);
        return R.ok();
    }

    @PutMapping
    public R update(@RequestBody AigcKbFile data) {
        kbFileService.updateById(data);
        return R.ok();
    }

    @DeleteMapping("/{id}")
    public R delete(@PathVariable String id) {
        kbFileService.removeById(id);
        return R.ok();
    }
}

