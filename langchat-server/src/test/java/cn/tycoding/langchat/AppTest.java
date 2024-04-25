package cn.tycoding.langchat;

import dev.langchain4j.data.segment.TextSegment;
import dev.langchain4j.store.embedding.EmbeddingSearchRequest;
import dev.langchain4j.store.embedding.EmbeddingSearchResult;
import dev.langchain4j.store.embedding.filter.Filter;
import dev.langchain4j.store.embedding.milvus.MilvusEmbeddingStore;
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
@RunWith(SpringRunner.class)
@SpringBootTest
public class AppTest {

    @Autowired
    private MilvusEmbeddingStore milvusEmbeddingStore;

    @Test
    public void t1() {
        Filter filter = metadataKey("knowledgeId").isEqualTo("f228b6c9-bce2-4fd0-239s8-fbc3b893e36e");
        EmbeddingSearchResult<TextSegment> search =
                milvusEmbeddingStore.search(EmbeddingSearchRequest.builder().filter(filter).build());
        System.out.println("-----");
    }
}
