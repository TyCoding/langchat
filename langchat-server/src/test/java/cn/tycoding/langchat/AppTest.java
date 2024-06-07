package cn.tycoding.langchat;

import cn.tycoding.langchat.core.properties.chat.OpenaiProps;
import cn.tycoding.langchat.core.properties.search.WebSearchProps;
import dev.langchain4j.store.embedding.filter.Filter;
import dev.langchain4j.web.search.WebSearchRequest;
import dev.langchain4j.web.search.WebSearchResults;
import dev.langchain4j.web.search.google.customsearch.GoogleCustomWebSearchEngine;
import dev.langchain4j.web.search.tavily.TavilyWebSearchEngine;
import io.milvus.client.MilvusServiceClient;
import io.milvus.grpc.DescribeCollectionResponse;
import io.milvus.grpc.GetCollectionStatisticsResponse;
import io.milvus.grpc.SearchResults;
import io.milvus.grpc.ShowCollectionsResponse;
import io.milvus.param.R;
import io.milvus.param.collection.DescribeCollectionParam;
import io.milvus.param.collection.GetCollectionStatisticsParam;
import io.milvus.param.collection.ShowCollectionsParam;
import io.milvus.param.dml.SearchParam;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static dev.langchain4j.store.embedding.filter.MetadataFilterBuilder.metadataKey;

/**
 * @author tycoding
 * @since 2024/4/4
 */
@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest
public class AppTest {

    @Autowired
    private MilvusServiceClient milvusClient;

    @Autowired
    private GoogleCustomWebSearchEngine googleCustomWebSearchEngine;
    @Autowired
    private TavilyWebSearchEngine tavilyWebSearchEngine;
    @Autowired
    private WebSearchProps props;
    @Autowired
    private OpenaiProps openaiProps;

    @Test
    public void t1() {
        Filter filter = metadataKey("knowledgeId").isEqualTo("f228b6c9-bce2-4fd0-239s8-fbc3b893e36e");

        R<ShowCollectionsResponse> respShowCollections = milvusClient.showCollections(
                ShowCollectionsParam.newBuilder().build()
        );
        R<DescribeCollectionResponse> respDescribeCollection = milvusClient.describeCollection(
                // Return the name and schema of the collection.
                DescribeCollectionParam.newBuilder()
                        .withCollectionName("test3")
                        .build()
        );
        R<GetCollectionStatisticsResponse> respCollectionStatistics = milvusClient.getCollectionStatistics(
                // Return the statistics information of the collection.
                GetCollectionStatisticsParam.newBuilder()
                        .withCollectionName("test3")
                        .build()
        );


        SearchParam searchParam = SearchParam.newBuilder()
                .withCollectionName("test3")
                .withVectorFieldName("text")
                .withTopK(1000)
                .build();
        R<SearchResults> search = milvusClient.search(searchParam);

        System.out.println("-----");
    }

    @Test
    public void t2() {
        WebSearchResults results = tavilyWebSearchEngine.search(WebSearchRequest.builder()
                        .maxResults(3)
                .searchTerms("What's the date today?").build());
        log.info("result: {}", results);
    }

}
