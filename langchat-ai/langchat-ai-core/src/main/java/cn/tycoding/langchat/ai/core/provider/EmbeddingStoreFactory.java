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

package cn.tycoding.langchat.ai.core.provider;

import cn.hutool.core.util.StrUtil;
import cn.tycoding.langchat.ai.biz.entity.AigcEmbedStore;
import cn.tycoding.langchat.ai.biz.service.AigcEmbedStoreService;
import cn.tycoding.langchat.ai.core.consts.EmbedStoreEnum;
import dev.langchain4j.community.store.embedding.redis.RedisEmbeddingStore;
import dev.langchain4j.data.segment.TextSegment;
import dev.langchain4j.store.embedding.EmbeddingStore;
import dev.langchain4j.store.embedding.milvus.MilvusEmbeddingStore;
import dev.langchain4j.store.embedding.pgvector.PgVectorEmbeddingStore;
import jakarta.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author tycoding
 * @since 2024/10/28
 */
@Slf4j
@Component
public class EmbeddingStoreFactory {

    @Autowired
    private AigcEmbedStoreService aigcEmbedStoreService;

    private final List<AigcEmbedStore> modelStore = new ArrayList<>();
    private final Map<String, EmbeddingStore<TextSegment>> embedStoreMap = new ConcurrentHashMap<>();

    @Async
    @PostConstruct
    public void init() {
        modelStore.clear();
        List<AigcEmbedStore> list = aigcEmbedStoreService.list();
        list.forEach(embed -> {
            try {
                if (EmbedStoreEnum.REDIS.name().equalsIgnoreCase(embed.getProvider())) {
                    RedisEmbeddingStore.Builder builder = RedisEmbeddingStore.builder()
                            .host(embed.getHost())
                            .port(embed.getPort())
                            .indexName(embed.getDatabaseName())
                            .dimension(embed.getDimension());
                    if (StrUtil.isNotBlank(embed.getUsername()) && StrUtil.isNotBlank(embed.getPassword())) {
                        builder.user(embed.getUsername()).password(embed.getPassword());
                    }
                    EmbeddingStore<TextSegment> store = builder.build();
                    embedStoreMap.put(embed.getId(), store);
                }
                if (EmbedStoreEnum.PGVECTOR.name().equalsIgnoreCase(embed.getProvider())) {
                    EmbeddingStore<TextSegment> store = PgVectorEmbeddingStore.builder()
                            .host(embed.getHost())
                            .port(embed.getPort())
                            .database(embed.getDatabaseName())
                            .dimension(embed.getDimension())
                            .user(embed.getUsername())
                            .password(embed.getPassword())
                            .table(embed.getTableName())
                            .indexListSize(1)
                            .useIndex(true)
                            .createTable(true)
                            .dropTableFirst(false)
                            .build();
                    embedStoreMap.put(embed.getId(), store);
                }
                if (EmbedStoreEnum.MILVUS.name().equalsIgnoreCase(embed.getProvider())) {
                    EmbeddingStore<TextSegment> store = MilvusEmbeddingStore.builder()
                            .host(embed.getHost())
                            .port(embed.getPort())
                            .databaseName(embed.getDatabaseName())
                            .dimension(embed.getDimension())
                            .username(embed.getUsername())
                            .password(embed.getPassword())
                            .collectionName(embed.getTableName())
                            .build();
                    embedStoreMap.put(embed.getId(), store);
                }
                modelStore.add(embed);
            } catch (Exception e) {
                e.printStackTrace();
                log.error("向量数据库初始化失败：[{}] --- [{}]，数据库配置信息：[{}]", embed.getName(), embed.getProvider(), embed);
            }
        });

        modelStore.forEach(i -> log.info("已成功注册Embedding Store：{}， 配置信息：{}", i.getProvider(), i));
    }

    public EmbeddingStore<TextSegment> getEmbeddingStore(String embeddingId) {
        return embedStoreMap.get(embeddingId);
    }

    public boolean containsEmbeddingStore(String embeddingId) {
        return embedStoreMap.containsKey(embeddingId);
    }
}
