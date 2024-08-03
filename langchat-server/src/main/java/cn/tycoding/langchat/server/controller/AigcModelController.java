/*
 * Copyright (c) 2024 LangChat. TyCoding All Rights Reserved.
 *
 * Licensed under the GNU Affero General Public License, Version 3 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     https://www.gnu.org/licenses/agpl-3.0.html
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package cn.tycoding.langchat.server.controller;

import cn.dev33.satoken.annotation.SaCheckPermission;
import cn.hutool.core.util.StrUtil;
import cn.tycoding.langchat.biz.component.ProviderRefreshEvent;
import cn.tycoding.langchat.biz.entity.AigcModel;
import cn.tycoding.langchat.biz.service.AigcModelService;
import cn.tycoding.langchat.common.annotation.ApiLog;
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
        List<AigcModel> list = modelService.list(Wrappers.<AigcModel>lambdaQuery()
                .eq(StrUtil.isNotBlank(data.getType()), AigcModel::getType, data.getType())
                .eq(StrUtil.isNotBlank(data.getProvider()), AigcModel::getProvider, data.getProvider()));
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
    @ApiLog("添加模型")
    @SaCheckPermission("aigc:model:add")
    public R add(@RequestBody AigcModel data) {
        if (StrUtil.isNotBlank(data.getApiKey()) && data.getApiKey().contains("*")) {
            data.setApiKey(null);
        }
        modelService.save(data);
        SpringContextHolder.publishEvent(new ProviderRefreshEvent(data));
        return R.ok();
    }

    @PutMapping
    @ApiLog("修改模型")
    @SaCheckPermission("aigc:model:update")
    public R update(@RequestBody AigcModel data) {
        if (StrUtil.isNotBlank(data.getApiKey()) && data.getApiKey().contains("*")) {
            data.setApiKey(null);
        }
        modelService.updateById(data);
        SpringContextHolder.publishEvent(new ProviderRefreshEvent(data));
        return R.ok();
    }

    @DeleteMapping("/{id}")
    @ApiLog("删除模型")
    @SaCheckPermission("aigc:model:delete")
    public R delete(@PathVariable String id) {
        modelService.removeById(id);

        // Delete dynamically registered beans, according to ID
        contextHolder.unregisterBean(id);
        return R.ok();
    }
}

