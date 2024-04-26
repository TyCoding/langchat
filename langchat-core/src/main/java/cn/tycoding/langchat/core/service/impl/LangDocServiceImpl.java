package cn.tycoding.langchat.core.service.impl;

import cn.tycoding.langchat.common.dto.DocR;
import cn.tycoding.langchat.common.dto.EmbeddingR;
import cn.tycoding.langchat.core.enums.ModelConst;
import cn.tycoding.langchat.core.provider.EmbedProvider;
import cn.tycoding.langchat.core.provider.ModelProvider;
import cn.tycoding.langchat.core.service.LangDocService;
import cn.tycoding.langchat.core.service.Assistant;
import dev.langchain4j.data.document.Document;
import dev.langchain4j.data.document.DocumentSplitter;
import dev.langchain4j.data.document.loader.FileSystemDocumentLoader;
import dev.langchain4j.data.document.parser.apache.tika.ApacheTikaDocumentParser;
import dev.langchain4j.data.document.splitter.DocumentSplitters;
import dev.langchain4j.data.embedding.Embedding;
import dev.langchain4j.data.segment.TextSegment;
import dev.langchain4j.model.chat.StreamingChatLanguageModel;
import dev.langchain4j.model.embedding.AllMiniLmL6V2EmbeddingModel;
import dev.langchain4j.model.embedding.EmbeddingModel;
import dev.langchain4j.model.openai.OpenAiTokenizer;
import dev.langchain4j.rag.content.retriever.ContentRetriever;
import dev.langchain4j.rag.content.retriever.EmbeddingStoreContentRetriever;
import dev.langchain4j.rag.query.Query;
import dev.langchain4j.service.AiServices;
import dev.langchain4j.service.TokenStream;
import dev.langchain4j.store.embedding.filter.Filter;
import dev.langchain4j.store.embedding.milvus.MilvusEmbeddingStore;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.function.Function;

import static dev.langchain4j.data.document.Metadata.metadata;
import static dev.langchain4j.model.openai.OpenAiModelName.GPT_3_5_TURBO;
import static dev.langchain4j.store.embedding.filter.MetadataFilterBuilder.metadataKey;

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
    public EmbeddingR embeddingText(DocR req) {
        TextSegment segment = TextSegment.from(req.getMessage(),
                metadata("knowledgeId", req.getKnowledgeId()));
        EmbeddingModel embeddingModel = new AllMiniLmL6V2EmbeddingModel();
        Embedding embedding = embeddingModel.embed(segment).content();

        String id = milvusEmbeddingStore.add(embedding, segment);
        return new EmbeddingR().setVectorId(id).setText(segment.text());
    }

    @Override
    public void embeddingDocs(DocR req) {
        EmbeddingModel model = provider.embed();

        Document document = FileSystemDocumentLoader.loadDocument(req.getPath(), new ApacheTikaDocumentParser());
        document.metadata().add("id", req.getId());

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
    public void embeddingExcel(DocR req) {

    }

    @Override
    public TokenStream search(DocR req) {
        StreamingChatLanguageModel chatLanguageModel = modelProvider.stream(ModelConst.OPENAI);
        EmbeddingModel model = provider.embed();
        Function<Query, Filter> filterByUserId = (query) -> metadataKey("id").isEqualTo(req.getId());

        ContentRetriever contentRetriever = EmbeddingStoreContentRetriever.builder()
                .embeddingStore(milvusEmbeddingStore)
                .embeddingModel(model)
                .dynamicFilter(filterByUserId)
                .build();

        Assistant assistant = AiServices.builder(Assistant.class)
                .streamingChatLanguageModel(chatLanguageModel)
                .contentRetriever(contentRetriever)
                .build();

        return assistant.chat(req.getPrompt().toUserMessage());
    }
}
