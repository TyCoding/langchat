package cn.tycoding.langchat.core.service.impl;

import cn.tycoding.langchat.common.dto.ChatReq;
import cn.tycoding.langchat.common.dto.EmbeddingR;
import cn.tycoding.langchat.core.enums.ModelConst;
import cn.tycoding.langchat.core.provider.EmbedProvider;
import cn.tycoding.langchat.core.provider.ModelProvider;
import cn.tycoding.langchat.core.service.AigcStructColService;
import cn.tycoding.langchat.core.service.AigcStructRowService;
import cn.tycoding.langchat.core.service.Assistant;
import cn.tycoding.langchat.core.service.LangDocService;
import cn.tycoding.langchat.core.tools.StructTools;
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

import java.util.ArrayList;
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
    private final AigcStructColService structColService;
    private final AigcStructRowService structRowService;

    @Override
    public EmbeddingR embeddingText(ChatReq req) {
        TextSegment segment = TextSegment.from(req.getMessage(),
                metadata("knowledgeId", req.getKnowledgeId()));
        EmbeddingModel embeddingModel = new AllMiniLmL6V2EmbeddingModel();
        Embedding embedding = embeddingModel.embed(segment).content();

        String id = milvusEmbeddingStore.add(embedding, segment);
        return new EmbeddingR().setVectorId(id).setText(segment.text());
    }

    @Override
    public List<EmbeddingR> embeddingDocs(ChatReq req) {
//        EmbeddingModel model = provider.embed();
        EmbeddingModel model = new AllMiniLmL6V2EmbeddingModel();

        Document document = FileSystemDocumentLoader.loadDocument(req.getPath(), new ApacheTikaDocumentParser());
        document.metadata().add("knowledgeId", req.getKnowledgeId());

        DocumentSplitter splitter = DocumentSplitters.recursive(
                100,
                0,
                new OpenAiTokenizer(GPT_3_5_TURBO)
        );
        List<TextSegment> segments = splitter.split(document);
        List<Embedding> embeddings = model.embedAll(segments).content();
        List<String> ids = milvusEmbeddingStore.addAll(embeddings, segments);

        List<EmbeddingR> list = new ArrayList<>();
        for (int i = 0; i < ids.size(); i++) {
            list.add(new EmbeddingR().setVectorId(ids.get(i)).setText(segments.get(i).text()));
        }
        return list;
    }

    @Override
    public void embeddingStruct(ChatReq req) {
    }

    @Override
    public TokenStream search(ChatReq req) {
        StreamingChatLanguageModel chatLanguageModel = modelProvider.stream(ModelConst.OPENAI);
//        EmbeddingModel model = provider.embed();
        EmbeddingModel model = new AllMiniLmL6V2EmbeddingModel();
        Function<Query, Filter> filterByUserId = (query) -> metadataKey("knowledgeId").isEqualTo(req.getKnowledgeId());

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

    @Override
    public TokenStream searchStruct(ChatReq req) {
        StreamingChatLanguageModel chatLanguageModel = modelProvider.stream(ModelConst.OPENAI);
        EmbeddingModel model = provider.embed();

        ContentRetriever contentRetriever = EmbeddingStoreContentRetriever.builder()
                .embeddingModel(model)
                .build();

        Assistant assistant = AiServices.builder(Assistant.class)
                .streamingChatLanguageModel(chatLanguageModel)
                .contentRetriever(contentRetriever)
                .tools(new StructTools(req, structColService, structRowService))
                .build();

        return assistant.chat(req.getPrompt().toUserMessage());
    }
}
