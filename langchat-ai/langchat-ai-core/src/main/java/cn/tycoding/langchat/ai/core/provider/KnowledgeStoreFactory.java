/*
 * Copyright (c) 2024 LangChat. TyCoding All Rights Reserved.
 *
 * Licensed under the GNU Affero General Public License, Version 3 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *    https://www.gnu.org/licenses/agpl-3.0.html
 *
 * Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package cn.tycoding.langchat.ai.core.provider;

import cn.tycoding.langchat.ai.biz.entity.AigcEmbedStore;
import cn.tycoding.langchat.ai.biz.entity.AigcKnowledge;
import cn.tycoding.langchat.ai.biz.entity.AigcModel;
import cn.tycoding.langchat.ai.biz.service.AigcEmbedStoreService;
import cn.tycoding.langchat.ai.biz.service.AigcKnowledgeService;
import cn.tycoding.langchat.ai.biz.service.AigcModelService;
import jakarta.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

/**
 * @author tycoding
 * @since 2024/10/29
 */
@Slf4j
@Component
public class KnowledgeStoreFactory {

    @Autowired
    private AigcKnowledgeService knowledgeService;
    @Autowired
    private AigcModelService modelService;
    @Autowired
    private AigcEmbedStoreService embedStoreService;

    private final Map<String, AigcKnowledge> knowledgeMap = new ConcurrentHashMap<>();

    @Async
    @PostConstruct
    public void init() {
        knowledgeMap.clear();
        List<AigcKnowledge> list = knowledgeService.list();
        Map<String, List<AigcModel>> modelMap = modelService.list().stream().collect(Collectors.groupingBy(AigcModel::getId));
        Map<String, List<AigcEmbedStore>> storeMap = embedStoreService.list().stream().collect(Collectors.groupingBy(AigcEmbedStore::getId));
        list.forEach(know -> {
            if (know.getEmbedModelId() != null) {
                List<AigcModel> models = modelMap.get(know.getEmbedModelId());
                know.setEmbedModel(models == null ? null : models.get(0));
            }
            if (know.getEmbedStoreId() != null) {
                List<AigcEmbedStore> stores = storeMap.get(know.getEmbedStoreId());
                know.setEmbedStore(stores == null ? null : stores.get(0));
            }
            knowledgeMap.put(know.getId(), know);
        });
    }

    public AigcKnowledge getKnowledge(String knowledgeId) {
        return knowledgeMap.get(knowledgeId);
    }

    public boolean containsKnowledge(String knowledgeId) {
        return knowledgeMap.containsKey(knowledgeId);
    }
}
