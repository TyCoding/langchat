package cn.tycoding.langchat.aigc.controller;

import cn.hutool.core.util.StrUtil;
import cn.tycoding.langchat.aigc.entity.AigcDocs;
import cn.tycoding.langchat.aigc.mapper.AigcDocsMapper;
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
@RequestMapping("/aigc/docs")
public class AigcDocsController {

    private final AigcDocsMapper docsMapper;

    @GetMapping("/list")
    public R<List<AigcDocs>> list(AigcDocs data) {
        return R.ok(docsMapper.selectList(Wrappers.<AigcDocs>lambdaQuery().orderByDesc(AigcDocs::getCreateTime)));
    }

    @GetMapping("/page")
    public R list(AigcDocs data, QueryPage queryPage) {
        Page<AigcDocs> page = new Page<>(queryPage.getPage(), queryPage.getLimit());
        return R.ok(MybatisUtil.getData(docsMapper.selectPage(page, Wrappers.<AigcDocs>lambdaQuery()
                .eq(!StrUtil.isBlank(data.getKnowledgeId()), AigcDocs::getKnowledgeId, data.getKnowledgeId())
                .eq(data.getSliceStatus() != null, AigcDocs::getSliceStatus, data.getSliceStatus())
                .orderByDesc(AigcDocs::getCreateTime)
        )));
    }

    @GetMapping("/{id}")
    public R<AigcDocs> findById(@PathVariable String id) {
        return R.ok(docsMapper.selectById(id));
    }

    @PostMapping
    public R add(@RequestBody AigcDocs data) {
        docsMapper.insert(data);
        return R.ok();
    }

    @PutMapping
    public R update(@RequestBody AigcDocs data) {
        docsMapper.updateById(data);
        return R.ok();
    }

    @DeleteMapping("/{id}")
    public R delete(@PathVariable String id) {
        docsMapper.deleteById(id);
        return R.ok();
    }
}

