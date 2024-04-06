package cn.tycoding.langchat.core.service.impl;

import static dev.langchain4j.data.document.Metadata.metadata;
import static dev.langchain4j.model.openai.OpenAiModelName.GPT_3_5_TURBO;
import static dev.langchain4j.store.embedding.filter.MetadataFilterBuilder.metadataKey;

import cn.hutool.core.bean.BeanUtil;
import cn.tycoding.langchat.common.dto.DocR;
import cn.tycoding.langchat.common.dto.OssR;
import cn.tycoding.langchat.core.EmbedProvider;
import cn.tycoding.langchat.core.ModelProvider;
import cn.tycoding.langchat.core.enums.ModelConst;
import cn.tycoding.langchat.core.service.Assistant;
import cn.tycoding.langchat.core.service.LangDocService;
import dev.langchain4j.data.document.Document;
import dev.langchain4j.data.document.DocumentSplitter;
import dev.langchain4j.data.document.loader.FileSystemDocumentLoader;
import dev.langchain4j.data.document.parser.apache.pdfbox.ApachePdfBoxDocumentParser;
import dev.langchain4j.data.document.splitter.DocumentSplitters;
import dev.langchain4j.data.embedding.Embedding;
import dev.langchain4j.data.segment.TextSegment;
import dev.langchain4j.model.chat.ChatLanguageModel;
import dev.langchain4j.model.embedding.EmbeddingModel;
import dev.langchain4j.model.openai.OpenAiTokenizer;
import dev.langchain4j.rag.content.retriever.ContentRetriever;
import dev.langchain4j.rag.content.retriever.EmbeddingStoreContentRetriever;
import dev.langchain4j.rag.query.Query;
import dev.langchain4j.service.AiServices;
import dev.langchain4j.service.TokenStream;
import dev.langchain4j.store.embedding.filter.Filter;
import dev.langchain4j.store.embedding.milvus.MilvusEmbeddingStore;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * @author tycoding
 * @since 2024/4/4
 */
@Slf4j
@Service
@AllArgsConstructor
public class LangDocServiceImpl implements LangDocService {

    private final EmbedProvider provider;
    private final ModelProvider modelProvider;
    private final MilvusEmbeddingStore milvusEmbeddingStore;

    @Override
    public void embedText() {
        TextSegment user1Info = TextSegment.from("My favorite color is green",
                metadata("userId", "1"));
    }

    @Override
    public void embedDoc(OssR req) {
        EmbeddingModel model = provider.embed();
        Document document = FileSystemDocumentLoader.loadDocument(req.getUrl(),
                new ApachePdfBoxDocumentParser());
        Map<String, Object> beanMap = BeanUtil.beanToMap(req);
        beanMap.forEach((k, v) -> {
            document.metadata().add(k, v);
        });

        DocumentSplitter splitter = DocumentSplitters.recursive(
                100,
                0,
                new OpenAiTokenizer(GPT_3_5_TURBO)
        );
        List<TextSegment> segments = splitter.split(document);
        List<Embedding> embeddings = model.embedAll(segments).content();
        milvusEmbeddingStore.addAll(embeddings, segments);
    }

    @Override
    public TokenStream search(DocR req) {
        ChatLanguageModel chatLanguageModel = modelProvider.text(ModelConst.OPENAI_TEXT);
        EmbeddingModel model = provider.embed();
        Function<Query, Filter> filterByUserId = (query) -> metadataKey("id").isEqualTo(
                req.getId());

        ContentRetriever contentRetriever = EmbeddingStoreContentRetriever.builder()
                .embeddingStore(milvusEmbeddingStore)
                .embeddingModel(model)
                .dynamicFilter(filterByUserId)
                .build();

        Assistant assistant = AiServices.builder(Assistant.class)
                .chatLanguageModel(chatLanguageModel)
                .contentRetriever(contentRetriever)
                .build();

        return assistant.chat("Which animal?");
    }
}
