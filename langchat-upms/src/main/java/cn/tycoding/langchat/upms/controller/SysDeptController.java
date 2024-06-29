package cn.tycoding.langchat.upms.controller;

import cn.dev33.satoken.annotation.SaCheckPermission;
import cn.hutool.core.lang.tree.Tree;
import cn.tycoding.langchat.common.annotation.ApiLog;
import cn.tycoding.langchat.common.utils.R;
import cn.tycoding.langchat.upms.entity.SysDept;
import cn.tycoding.langchat.upms.service.SysDeptService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 部门表(Dept)表控制层
 *
 * @author tycoding
 * @since 2024/4/15
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/upms/dept")
public class SysDeptController {

    private final SysDeptService sysDeptService;

    @GetMapping("/list")
    public R<List<SysDept>> list(SysDept sysDept) {
        return R.ok(sysDeptService.list(sysDept));
    }

    @GetMapping("/tree")
    public R<List<Tree<Object>>> tree(SysDept sysDept) {
        return R.ok(sysDeptService.tree(sysDept));
    }

    @GetMapping("/{id}")
    public R<SysDept> findById(@PathVariable Long id) {
        return R.ok(sysDeptService.getById(id));
    }

    @PostMapping
    @ApiLog("新增部门")
    @SaCheckPermission("upms:dept:add")
    public R add(@RequestBody SysDept sysDept) {
        sysDept.setParentId(sysDept.getParentId() == null ? 0L : sysDept.getParentId());
        sysDeptService.save(sysDept);
        return R.ok();
    }

    @PutMapping
    @ApiLog("修改部门")
    @SaCheckPermission("upms:dept:update")
    public R update(@RequestBody SysDept sysDept) {
        sysDept.setParentId(sysDept.getParentId() == null ? 0L : sysDept.getParentId());
        sysDeptService.updateById(sysDept);
        return R.ok();
    }

    @DeleteMapping("/{id}")
    @ApiLog("删除部门")
    @SaCheckPermission("upms:dept:delete")
    public R delete(@PathVariable Long id) {
        sysDeptService.delete(id);
        return R.ok();
    }
}
