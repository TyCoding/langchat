package cn.tycoding.langchat.aigc.controller;

import cn.dev33.satoken.annotation.SaCheckPermission;
import cn.hutool.core.util.StrUtil;
import cn.tycoding.langchat.aigc.component.ProviderRefreshEvent;
import cn.tycoding.langchat.aigc.entity.AigcModel;
import cn.tycoding.langchat.aigc.service.AigcModelService;
import cn.tycoding.langchat.common.component.SpringContextHolder;
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
@RequestMapping("/aigc/model")
public class AigcModelController {

    private final AigcModelService modelService;
    private final SpringContextHolder contextHolder;

    @GetMapping("/list")
    public R<List<AigcModel>> list(AigcModel data) {
        List<AigcModel> list = modelService.list(Wrappers.<AigcModel>lambdaQuery().eq(AigcModel::getProvider, data.getProvider()));
        list.forEach(this::hide);
        return R.ok(list);
    }

    @GetMapping("/getChatModels")
    public R<List<AigcModel>> getChatModels() {
        List<AigcModel> list = modelService.getChatModels();
        list.forEach(this::hide);
        return R.ok(list);
    }

    @GetMapping("/getImageModels")
    public R<List<AigcModel>> getImageModels() {
        List<AigcModel> list = modelService.getImageModels();
        list.forEach(this::hide);
        return R.ok(list);
    }

    @GetMapping("/getEmbeddingModels")
    public R<List<AigcModel>> getEmbeddingModels() {
        List<AigcModel> list = modelService.getEmbeddingModels();
        list.forEach(this::hide);
        return R.ok(list);
    }

    private void hide(AigcModel model) {
        if (StrUtil.isBlank(model.getApiKey())) {
            return;
        }
        String key = StrUtil.hide(model.getApiKey(), 3, model.getApiKey().length() - 4);
        model.setApiKey(key);
    }

    @GetMapping("/page")
    public R list(AigcModel data, QueryPage queryPage) {
        Page<AigcModel> page = new Page<>(queryPage.getPage(), queryPage.getLimit());
        Page<AigcModel> iPage = modelService.page(page, Wrappers.<AigcModel>lambdaQuery().eq(AigcModel::getProvider, data.getProvider()));
        iPage.getRecords().forEach(this::hide);
        return R.ok(MybatisUtil.getData(iPage));
    }

    @GetMapping("/{id}")
    public R<AigcModel> findById(@PathVariable String id) {
        AigcModel model = modelService.getById(id);
        hide(model);
        return R.ok(model);
    }

    @PostMapping
    @SaCheckPermission("aigc:model:add")
    public R add(@RequestBody AigcModel data) {
        modelService.save(data);
        SpringContextHolder.publishEvent(new ProviderRefreshEvent(data));
        return R.ok();
    }

    @PutMapping
    @SaCheckPermission("aigc:model:update")
    public R update(@RequestBody AigcModel data) {
        modelService.updateById(data);
        SpringContextHolder.publishEvent(new ProviderRefreshEvent(data));
        return R.ok();
    }

    @DeleteMapping("/{id}")
    @SaCheckPermission("aigc:model:delete")
    public R delete(@PathVariable String id) {
        modelService.removeById(id);

        // Delete dynamically registered beans, according to ID
        contextHolder.unregisterBean(id);
        return R.ok();
    }
}

