package cn.tycoding.langchat.aigc.controller;

import cn.hutool.core.util.StrUtil;
import cn.tycoding.langchat.aigc.component.ProviderRefreshEvent;
import cn.tycoding.langchat.aigc.entity.AigcModel;
import cn.tycoding.langchat.aigc.mapper.AigcModelMapper;
import cn.tycoding.langchat.common.component.SpringContextHolder;
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

    private final AigcModelMapper modelMapper;
    private final SpringContextHolder contextHolder;

    @GetMapping("/list")
    public R<List<AigcModel>> list(AigcModel data) {
        List<AigcModel> list = modelMapper.selectList(Wrappers.<AigcModel>lambdaQuery().eq(AigcModel::getProvider, data.getProvider()));
        list.forEach(this::hide);
        return R.ok(list);
    }

    private void hide(AigcModel model) {
        String key = StrUtil.hide(model.getApiKey(), 5, model.getApiKey().length());
        model.setApiKey(key);
    }

    @GetMapping("/page")
    public R list(AigcModel data, QueryPage queryPage) {
        Page<AigcModel> page = new Page<>(queryPage.getPage(), queryPage.getLimit());
        Page<AigcModel> iPage = modelMapper.selectPage(page, Wrappers.<AigcModel>lambdaQuery().eq(AigcModel::getProvider, data.getProvider()));
        iPage.getRecords().forEach(this::hide);
        return R.ok(MybatisUtil.getData(iPage));
    }

    @GetMapping("/{id}")
    public R<AigcModel> findById(@PathVariable String id) {
        return R.ok(modelMapper.selectById(id));
    }

    @PostMapping
    public R add(@RequestBody AigcModel data) {
        modelMapper.insert(data);
        SpringContextHolder.publishEvent(new ProviderRefreshEvent(data));
        return R.ok();
    }

    @PutMapping
    public R update(@RequestBody AigcModel data) {
        modelMapper.updateById(data);
        SpringContextHolder.publishEvent(new ProviderRefreshEvent(data));
        return R.ok();
    }

    @DeleteMapping("/{id}")
    public R delete(@PathVariable String id) {
        modelMapper.deleteById(id);

        // Delete dynamically registered beans, according to ID
        contextHolder.unregisterBean(id);
        return R.ok();
    }
}

