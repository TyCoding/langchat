package cn.tycoding.langchat.core.service;

import cn.tycoding.langchat.biz.service.MessageService;
import dev.langchain4j.data.message.ChatMessage;
import dev.langchain4j.store.memory.chat.ChatMemoryStore;
import java.util.ArrayList;
import java.util.List;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

/**
 * @author tycoding
 * @since 2024/4/1
 */
@Component
@AllArgsConstructor
public class PersistentChatMemoryStore implements ChatMemoryStore {

    private final MessageService messageService;
    private final List<ChatMessage> list = new ArrayList<>();

    @Override
    public List<ChatMessage> getMessages(Object memoryId) {
        return list;
    }

    @Override
    public void updateMessages(Object memoryId, List<ChatMessage> messages) {
        list.addAll(messages);
    }

    @Override
    public void deleteMessages(Object memoryId) {
        System.out.println("del");
    }
}
