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

package cn.tycoding.langchat.app.controller;

import cn.dev33.satoken.annotation.SaCheckPermission;
import cn.hutool.core.lang.Dict;
import cn.hutool.core.util.StrUtil;
import cn.tycoding.langchat.app.entity.AigcApp;
import cn.tycoding.langchat.app.service.AigcAppService;
import cn.tycoding.langchat.app.store.AppStore;
import cn.tycoding.langchat.biz.entity.AigcKnowledge;
import cn.tycoding.langchat.biz.entity.AigcModel;
import cn.tycoding.langchat.biz.service.AigcKnowledgeService;
import cn.tycoding.langchat.biz.service.AigcModelService;
import cn.tycoding.langchat.common.annotation.ApiLog;
import cn.tycoding.langchat.common.utils.MybatisUtil;
import cn.tycoding.langchat.common.utils.QueryPage;
import cn.tycoding.langchat.common.utils.R;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
@RequestMapping("/aigc/app")
public class AigcAppController {

    private final AigcAppService aigcAppService;
    private final AigcModelService aigcModelService;
    private final AigcKnowledgeService aigcKnowledgeService;
    private final AppStore appStore;

    @GetMapping("/list")
    public R<List<AigcApp>> list(AigcApp data) {
        List<AigcApp> list = aigcAppService.list(Wrappers.<AigcApp>lambdaQuery()
                .like(StrUtil.isNotBlank(data.getName()), AigcApp::getName, data.getName()));

        Map<String, List<AigcModel>> modelMap = aigcModelService.list(new AigcModel()).stream().collect(Collectors.groupingBy(AigcModel::getId));
        Map<String, List<AigcKnowledge>> knowledgeMap = aigcKnowledgeService.list().stream().collect(Collectors.groupingBy(AigcKnowledge::getId));
        list.forEach(i -> {
            List<AigcModel> models = modelMap.get(i.getModelId());
            if (models != null) {
                i.setModel(models.get(0));
            }
            if (i.getKnowledgeIds() != null) {
                List<AigcKnowledge> knowledges = new ArrayList<>();
                i.getKnowledgeIds().forEach(k -> {
                    List<AigcKnowledge> items = knowledgeMap.get(k);
                    if (items != null) {
                        knowledges.add(items.get(0));
                    }
                });
                i.setKnowledges(knowledges);
            }
        });
        return R.ok(list);
    }

    @GetMapping("/page")
    public R<Dict> page(AigcApp data, QueryPage queryPage) {
        return R.ok(MybatisUtil.getData(aigcAppService.page(MybatisUtil.wrap(data, queryPage),
                Wrappers.<AigcApp>lambdaQuery()
                        .like(StringUtils.isNotEmpty(data.getName()), AigcApp::getName, data.getName())
        )));
    }

    @GetMapping("/{id}")
    public R<AigcApp> findById(@PathVariable String id) {
        AigcApp app = aigcAppService.getById(id);
        if (app != null) {
            String modelId = app.getModelId();
            if (modelId != null) {
                app.setModel(aigcModelService.selectById(modelId));
            }
            List<String> knowledgeIds = app.getKnowledgeIds();
            if (knowledgeIds != null && !knowledgeIds.isEmpty()) {
                app.setKnowledges(aigcKnowledgeService.list(Wrappers.<AigcKnowledge>lambdaQuery().in(AigcKnowledge::getId, knowledgeIds)));
            }
        }
        return R.ok(app);
    }

    @PostMapping
    @ApiLog("新增应用")
    @SaCheckPermission("aigc:app:add")
    public R add(@RequestBody AigcApp data) {
        data.setCreateTime(new Date());
        data.setSaveTime(new Date());
        aigcAppService.save(data);
        appStore.init();
        return R.ok();
    }

    @PutMapping
    @ApiLog("修改应用")
    @SaCheckPermission("aigc:app:update")
    public R update(@RequestBody AigcApp data) {
        data.setSaveTime(new Date());
        aigcAppService.updateById(data);
        appStore.init();
        return R.ok();
    }

    @DeleteMapping("/{id}")
    @ApiLog("删除应用")
    @SaCheckPermission("aigc:app:delete")
    public R delete(@PathVariable String id) {
        aigcAppService.removeById(id);
        appStore.init();
        return R.ok();
    }
}