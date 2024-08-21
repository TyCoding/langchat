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

package cn.tycoding.langchat.app.service.impl;

import cn.hutool.core.util.StrUtil;
import cn.tycoding.langchat.app.entity.AigcApp;
import cn.tycoding.langchat.app.mapper.AigcAppMapper;
import cn.tycoding.langchat.app.service.AigcAppService;
import cn.tycoding.langchat.biz.entity.AigcKnowledge;
import cn.tycoding.langchat.biz.entity.AigcModel;
import cn.tycoding.langchat.biz.service.AigcKnowledgeService;
import cn.tycoding.langchat.biz.service.AigcModelService;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author tycoding
 * @since 2024/7/26
 */
@RequiredArgsConstructor
@Service
public class AigcAppServiceImpl extends ServiceImpl<AigcAppMapper, AigcApp> implements AigcAppService {

    private final AigcModelService aigcModelService;
    private final AigcKnowledgeService aigcKnowledgeService;

    @Override
    public List<AigcApp> list(AigcApp data) {
        List<AigcApp> list = baseMapper.selectList(Wrappers.<AigcApp>lambdaQuery()
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
        return list;
    }

    @Override
    public AigcApp getById(String id) {
        AigcApp app = baseMapper.selectById(id);
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
        return app;
    }
}
