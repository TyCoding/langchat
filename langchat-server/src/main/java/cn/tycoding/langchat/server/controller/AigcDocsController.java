package cn.tycoding.langchat.server.controller;

import cn.dev33.satoken.annotation.SaCheckPermission;
import cn.tycoding.langchat.biz.entity.AigcDocs;
import cn.tycoding.langchat.biz.mapper.AigcDocsMapper;
import cn.tycoding.langchat.common.annotation.ApiLog;
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
                .eq(data.getKnowledgeId() != null, AigcDocs::getKnowledgeId, data.getKnowledgeId())
                .eq(data.getSliceStatus() != null, AigcDocs::getSliceStatus, data.getSliceStatus())
                .orderByDesc(AigcDocs::getCreateTime)
        )));
    }

    @GetMapping("/{id}")
    public R<AigcDocs> findById(@PathVariable String id) {
        return R.ok(docsMapper.selectById(id));
    }

    @PostMapping
    @ApiLog("新增文档")
    @SaCheckPermission("aigc:docs:add")
    public R add(@RequestBody AigcDocs data) {
        docsMapper.insert(data);
        return R.ok();
    }

    @PutMapping
    @ApiLog("修改文档")
    @SaCheckPermission("aigc:docs:update")
    public R update(@RequestBody AigcDocs data) {
        docsMapper.updateById(data);
        return R.ok();
    }

    @DeleteMapping("/{id}")
    @ApiLog("删除文档")
    @SaCheckPermission("aigc:docs:delete")
    public R delete(@PathVariable String id) {
        docsMapper.deleteById(id);
        return R.ok();
    }
}

