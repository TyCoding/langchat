package cn.tycoding.langchat.aigc.controller;

import cn.tycoding.langchat.aigc.entity.AigcModel;
import cn.tycoding.langchat.aigc.mapper.AigcModelMapper;
import cn.tycoding.langchat.common.utils.MybatisUtil;
import cn.tycoding.langchat.common.utils.QueryPage;
import cn.tycoding.langchat.common.utils.R;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import java.util.List;
import lombok.RequiredArgsConstructor;
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
 * @since 2024/4/15
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/aigc/model")
public class AigcModelController {

    private final AigcModelMapper docsMapper;

    @GetMapping("/list")
    public R<List<AigcModel>> list(AigcModel data) {
        return R.ok(docsMapper.selectList(Wrappers.<AigcModel>lambdaQuery()));
    }

    @GetMapping("/page")
    public R list(AigcModel data, QueryPage queryPage) {
        Page<AigcModel> page = new Page<>(queryPage.getPage(), queryPage.getLimit());
        return R.ok(MybatisUtil.getData(docsMapper.selectPage(page, Wrappers.<AigcModel>lambdaQuery()
        )));
    }

    @GetMapping("/{id}")
    public R<AigcModel> findById(@PathVariable String id) {
        return R.ok(docsMapper.selectById(id));
    }

    @PostMapping
    public R add(@RequestBody AigcModel data) {
        docsMapper.insert(data);
        return R.ok();
    }

    @PutMapping
    public R update(@RequestBody AigcModel data) {
        docsMapper.updateById(data);
        return R.ok();
    }

    @DeleteMapping("/{id}")
    public R delete(@PathVariable String id) {
        docsMapper.deleteById(id);
        return R.ok();
    }
}

