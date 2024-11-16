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
import cn.tycoding.langchat.common.core.component.SpringContextHolder;
import dev.langchain4j.store.embedding.elasticsearch.ElasticsearchEmbeddingStore;
import dev.langchain4j.store.embedding.milvus.MilvusEmbeddingStore;
import dev.langchain4j.store.embedding.pgvector.PgVectorEmbeddingStore;
import dev.langchain4j.store.embedding.redis.RedisEmbeddingStore;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.http.Header;
import org.apache.http.HttpHost;
import org.apache.http.message.BasicHeader;
import org.elasticsearch.client.RestClient;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * @author tycoding
 * @since 2024/10/28
 */
@Slf4j
@Component
@AllArgsConstructor
public class EmbeddingStoreInitialize implements ApplicationContextAware {

    private final AigcEmbedStoreService aigcEmbedStoreService;
    private final SpringContextHolder contextHolder;
    private List<AigcEmbedStore> modelStore = new ArrayList<>();

    @Override
    public void setApplicationContext(ApplicationContext context) throws BeansException {
        init();

        modelStore.forEach(i -> log.info("已成功注册Embedding Store：{}， 配置信息：{}", i.getProvider(), i));
    }

    public void init() {
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
                    RedisEmbeddingStore store = builder.build();
                    contextHolder.registerBean(embed.getId(), store);
                }
                if (EmbedStoreEnum.PGVECTOR.name().equalsIgnoreCase(embed.getProvider())) {
                    PgVectorEmbeddingStore store = PgVectorEmbeddingStore.builder()
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
                    contextHolder.registerBean(embed.getId(), store);
                }
                if (EmbedStoreEnum.MILVUS.name().equalsIgnoreCase(embed.getProvider())) {
                    MilvusEmbeddingStore store = MilvusEmbeddingStore.builder()
                            .host(embed.getHost())
                            .port(embed.getPort())
                            .databaseName(embed.getDatabaseName())
                            .dimension(embed.getDimension())
                            .username(embed.getUsername())
                            .password(embed.getPassword())
                            .collectionName(embed.getTableName())
                            .build();
                    contextHolder.registerBean(embed.getId(), store);
                }
                if (EmbedStoreEnum.ELASTICSEARCH.name().equalsIgnoreCase(embed.getProvider())) {
                    RestClient restClient = RestClient.builder(HttpHost.create(embed.getHost()))
                            .setDefaultHeaders(new Header[]{
                                    new BasicHeader("Authorization", "ApiKey " + embed.getPassword())
                            }).build();
                    ElasticsearchEmbeddingStore store = ElasticsearchEmbeddingStore.builder()
                            .restClient(restClient)
                            .indexName(embed.getDatabaseName())
                            .build();
                    contextHolder.registerBean(embed.getId(), store);
                }
                modelStore.add(embed);
            } catch (Exception e) {
                e.printStackTrace();
                log.error("向量数据库初始化失败：[{}] --- [{}]，数据库配置信息：[{}]", embed.getName(), embed.getProvider(), embed);
            }
        });
    }
}
