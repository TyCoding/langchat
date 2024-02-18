package cn.tycoding.langchat.core.service.impl;

import static dev.langchain4j.model.openai.OpenAiModelName.GPT_3_5_TURBO;
import static java.net.Proxy.Type.HTTP;
import static java.util.stream.Collectors.joining;

import cn.hutool.core.lang.Dict;
import cn.tycoding.langchat.core.properties.LangChatProps;
import cn.tycoding.langchat.core.properties.PgVectorProps;
import cn.tycoding.langchat.core.service.LangDocService;
import cn.tycoding.langchat.core.store.PgVectorStore;
import cn.tycoding.langchat.core.utils.ChatReq;
import cn.tycoding.langchat.core.utils.ChatRes;
import dev.langchain4j.data.document.Document;
import dev.langchain4j.data.document.DocumentSplitter;
import dev.langchain4j.data.document.loader.FileSystemDocumentLoader;
import dev.langchain4j.data.document.parser.apache.pdfbox.ApachePdfBoxDocumentParser;
import dev.langchain4j.data.document.splitter.DocumentSplitters;
import dev.langchain4j.data.embedding.Embedding;
import dev.langchain4j.data.message.AiMessage;
import dev.langchain4j.data.segment.TextSegment;
import dev.langchain4j.model.StreamingResponseHandler;
import dev.langchain4j.model.chat.StreamingChatLanguageModel;
import dev.langchain4j.model.embedding.EmbeddingModel;
import dev.langchain4j.model.input.Prompt;
import dev.langchain4j.model.input.PromptTemplate;
import dev.langchain4j.model.openai.OpenAiEmbeddingModel;
import dev.langchain4j.model.openai.OpenAiStreamingChatModel;
import dev.langchain4j.model.openai.OpenAiTokenizer;
import dev.langchain4j.model.output.Response;
import dev.langchain4j.model.output.TokenUsage;
import dev.langchain4j.store.embedding.EmbeddingMatch;
import dev.langchain4j.store.embedding.EmbeddingStore;
import dev.langchain4j.store.embedding.pgvector.PgVectorEmbeddingStore;
import java.net.InetSocketAddress;
import java.net.Proxy;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * @author tycoding
 * @since 2024/1/5
 */
@Slf4j
@Service
@AllArgsConstructor
public class LangDocServiceImpl implements LangDocService {

    private final LangChatProps langChatProps;
    private final PgVectorStore pgVectorStore;

    @Override
    public void stream(ChatReq req) {
        long startTime = System.currentTimeMillis();
        EmbeddingModel embeddingModel = OpenAiEmbeddingModel.withApiKey(langChatProps.getApiKey());
        EmbeddingStore<TextSegment> embeddingStore = PgVectorEmbeddingStore.builder()
                .host("39.106.32.39")
                .port(8192)
                .user("root")
                .password("root")
                .table("test2")
                .database("test")
                .dimension(1536)
//                .indexListSize(100)
//                .createTable(true)
//                .dropTableFirst(true)
                .build();

        Embedding questionEmbedding = embeddingModel.embed(req.getContent()).content();
        int maxResults = 10;
        double minScore = 0;
        List<EmbeddingMatch<TextSegment>> relevantEmbeddings = embeddingStore.findRelevant(questionEmbedding, maxResults, minScore);
        String document = relevantEmbeddings.stream()
                .map(match -> match.embedded().text())
                .collect(joining("\n\n"));

        PromptTemplate promptTemplate = PromptTemplate.from(req.getPromptText());
        Prompt prompt = promptTemplate.apply(
                Dict.create().set("content", req.getContent()).set("document", document));

        StreamingChatLanguageModel model = OpenAiStreamingChatModel.builder()
                .apiKey(langChatProps.getApiKey())
                .modelName("gpt-4")
                .proxy(new Proxy(HTTP, new InetSocketAddress("127.0.0.1", 7890)))
                .build();

        model.generate(prompt.toUserMessage().text(), new StreamingResponseHandler<AiMessage>() {
            @Override
            public void onNext(String s) {
                req.getEmitter().send(new ChatRes(s));
            }

            @Override
            public void onError(Throwable throwable) {
                req.getEmitter().complete();
            }

            @Override
            public void onComplete(Response<AiMessage> response) {
                TokenUsage tokenUsage = response.tokenUsage();
                req.getEmitter().send(new ChatRes(tokenUsage.totalTokenCount(), startTime));

                req.getEmitter().complete();
            }
        });
    }

    @Override
    public void embedding(String path, Long key) {
        EmbeddingModel embeddingModel = OpenAiEmbeddingModel.withApiKey(langChatProps.getApiKey());
        String tableName = "file_" + key;
        EmbeddingStore<TextSegment> embeddingStore = PgVectorEmbeddingStore.builder()
                .host("39.106.32.39")
                .port(8192)
                .user("root")
                .password("root")
                .table(tableName)
                .database("test")
                .dimension(1536)
                .indexListSize(100)
                .createTable(true)
//                .dropTableFirst(true)
                .build();

        try {
            Document document = FileSystemDocumentLoader.loadDocument(path, new ApachePdfBoxDocumentParser());
            // Split document into segments 100 tokens each
            DocumentSplitter splitter = DocumentSplitters.recursive(
                    100,
                    0,
                    new OpenAiTokenizer(GPT_3_5_TURBO)
            );
            List<TextSegment> segments = splitter.split(document);
            List<Embedding> embeddings = embeddingModel.embedAll(segments).content();
            embeddingStore.addAll(embeddings, segments);
        } catch (Exception e) {
            e.printStackTrace();
            delDoc(key);
        }
    }

    @Override
    public void delDoc(Long key) {
        String tableName = "file_" + key;
        PgVectorProps props = new PgVectorProps()
                .setHost("39.106.32.39")
                .setPort(8192)
                .setUser("root")
                .setPassword("root")
                .setDatabase("test")
                .setTable(tableName);
        pgVectorStore.delTable(props);
    }
}
