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

package cn.tycoding.langchat.core.service.impl;

import dev.langchain4j.data.embedding.Embedding;
import dev.langchain4j.data.segment.TextSegment;
import dev.langchain4j.model.embedding.EmbeddingModel;
import dev.langchain4j.rag.content.Content;
import dev.langchain4j.rag.content.retriever.ContentRetriever;
import dev.langchain4j.rag.query.Query;
import dev.langchain4j.spi.model.embedding.EmbeddingModelFactory;
import dev.langchain4j.store.embedding.EmbeddingMatch;
import dev.langchain4j.store.embedding.EmbeddingSearchRequest;
import dev.langchain4j.store.embedding.EmbeddingSearchResult;
import dev.langchain4j.store.embedding.EmbeddingStore;
import dev.langchain4j.store.embedding.filter.Filter;
import lombok.Builder;

import java.util.*;
import java.util.function.Function;

import static dev.langchain4j.internal.Utils.getOrDefault;
import static dev.langchain4j.internal.ValidationUtils.*;
import static dev.langchain4j.spi.ServiceHelper.loadFactories;
import static java.util.stream.Collectors.toList;

/**
 * @author tycoding
 * @since 2024/8/16
 */
public class EmbeddingStoreContentRetrieverCustom implements ContentRetriever {


    public static final Function<Query, Integer> DEFAULT_MAX_RESULTS = (query) -> 3;
    public static final Function<Query, Double> DEFAULT_MIN_SCORE = (query) -> 0.0;
    public static final Function<Query, Filter> DEFAULT_FILTER = (query) -> null;

    public static final String DEFAULT_DISPLAY_NAME = "Default";
    private static final Map<Object, List<EmbeddingMatch<TextSegment>>> sourceMap = new HashMap<>();
    private final EmbeddingStore<TextSegment> embeddingStore;
    private final EmbeddingModel embeddingModel;
    private final Function<Query, Integer> maxResultsProvider;
    private final Function<Query, Double> minScoreProvider;
    private final Function<Query, Filter> filterProvider;
    private final String displayName;
    private final Object memoryId;

    public EmbeddingStoreContentRetrieverCustom(Object memoryId,
                                                EmbeddingStore<TextSegment> embeddingStore,
                                                EmbeddingModel embeddingModel) {
        this(
                memoryId,
                DEFAULT_DISPLAY_NAME,
                embeddingStore,
                embeddingModel,
                DEFAULT_MAX_RESULTS,
                DEFAULT_MIN_SCORE,
                DEFAULT_FILTER
        );
    }

    public EmbeddingStoreContentRetrieverCustom(Object memoryId,
                                                EmbeddingStore<TextSegment> embeddingStore,
                                                EmbeddingModel embeddingModel,
                                                int maxResults) {
        this(
                memoryId,
                DEFAULT_DISPLAY_NAME,
                embeddingStore,
                embeddingModel,
                (query) -> maxResults,
                DEFAULT_MIN_SCORE,
                DEFAULT_FILTER
        );
    }

    public EmbeddingStoreContentRetrieverCustom(Object memoryId,
                                                EmbeddingStore<TextSegment> embeddingStore,
                                                EmbeddingModel embeddingModel,
                                                Integer maxResults,
                                                Double minScore) {
        this(
                memoryId,
                DEFAULT_DISPLAY_NAME,
                embeddingStore,
                embeddingModel,
                (query) -> maxResults,
                (query) -> minScore,
                DEFAULT_FILTER
        );
    }

    @Builder
    private EmbeddingStoreContentRetrieverCustom(Object memoryId,
                                                 String displayName,
                                                 EmbeddingStore<TextSegment> embeddingStore,
                                                 EmbeddingModel embeddingModel,
                                                 Function<Query, Integer> dynamicMaxResults,
                                                 Function<Query, Double> dynamicMinScore,
                                                 Function<Query, Filter> dynamicFilter) {
        this.memoryId = memoryId;
        this.displayName = getOrDefault(displayName, DEFAULT_DISPLAY_NAME);
        this.embeddingStore = ensureNotNull(embeddingStore, "embeddingStore");
        this.embeddingModel = ensureNotNull(
                getOrDefault(embeddingModel, EmbeddingStoreContentRetrieverCustom::loadEmbeddingModel),
                "embeddingModel"
        );
        this.maxResultsProvider = getOrDefault(dynamicMaxResults, DEFAULT_MAX_RESULTS);
        this.minScoreProvider = getOrDefault(dynamicMinScore, DEFAULT_MIN_SCORE);
        this.filterProvider = getOrDefault(dynamicFilter, DEFAULT_FILTER);
    }

    private static EmbeddingModel loadEmbeddingModel() {
        Collection<EmbeddingModelFactory> factories = loadFactories(EmbeddingModelFactory.class);
        if (factories.size() > 1) {
            throw new RuntimeException("Conflict: multiple embedding models have been found in the classpath. " +
                    "Please explicitly specify the one you wish to use.");
        }

        for (EmbeddingModelFactory factory : factories) {
            return factory.create();
        }

        return null;
    }

    /**
     * Creates an instance of an {@code EmbeddingStoreContentRetrieverCustom} from the specified {@link EmbeddingStore}
     * and {@link EmbeddingModel} found through SPI (see {@link EmbeddingModelFactory}).
     */
    public static EmbeddingStoreContentRetrieverCustom from(EmbeddingStore<TextSegment> embeddingStore) {
        return builder().embeddingStore(embeddingStore).build();
    }

    public static List<Map<String, Object>> getMetadata(String memoryId) {
        List<EmbeddingMatch<TextSegment>> sources = sourceMap.get(memoryId);
        if (sources == null || sources.isEmpty()) {
            return null;
        }
        List<Map<String, Object>> list = new ArrayList<>();
        sources.forEach(i -> {
            list.add(i.embedded().metadata().toMap());
        });
        return list;
    }

    @Override
    public List<Content> retrieve(Query query) {

        Embedding embeddedQuery = embeddingModel.embed(query.text()).content();

        EmbeddingSearchRequest searchRequest = EmbeddingSearchRequest.builder()
                .queryEmbedding(embeddedQuery)
                .maxResults(maxResultsProvider.apply(query))
                .minScore(minScoreProvider.apply(query))
                .filter(filterProvider.apply(query))
                .build();

        EmbeddingSearchResult<TextSegment> searchResult = embeddingStore.search(searchRequest);
        sourceMap.put(memoryId, searchResult.matches());
        return searchResult.matches().stream()
                .map(EmbeddingMatch::embedded)
                .map(Content::from)
                .collect(toList());
    }

    @Override
    public String toString() {
        return "EmbeddingStoreContentRetrieverCustom{" +
                "displayName='" + displayName + '\'' +
                '}';
    }

    public static class EmbeddingStoreContentRetrieverCustomBuilder {
        public EmbeddingStoreContentRetrieverCustomBuilder maxResults(Integer maxResults) {
            if (maxResults != null) {
                dynamicMaxResults = (query) -> ensureGreaterThanZero(maxResults, "maxResults");
            }
            return this;
        }

        public EmbeddingStoreContentRetrieverCustomBuilder minScore(Double minScore) {
            if (minScore != null) {
                dynamicMinScore = (query) -> ensureBetween(minScore, 0, 1, "minScore");
            }
            return this;
        }

        public EmbeddingStoreContentRetrieverCustomBuilder filter(Filter filter) {
            if (filter != null) {
                dynamicFilter = (query) -> filter;
            }
            return this;
        }
    }
}
