package cn.tycoding.langchat.core.service.impl;

import dev.langchain4j.data.message.ChatMessage;
import dev.langchain4j.data.message.UserMessage;
import dev.langchain4j.store.memory.chat.ChatMemoryStore;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author tycoding
 * @since 2024/4/1
 */
@Slf4j
@Component
@AllArgsConstructor
public class ChatMemoryService implements ChatMemoryStore {

    private final Map<Object, List<ChatMessage>> list = new HashMap<>();
    private final Map<Long, Object> suggestions = new HashMap<>();

    public void saveAutoSuggestion(Long id, Object val) {
        suggestions.put(id, val);
    }

    public Object getSuggestion(Long id) {
        return suggestions.get(id);
    }

    @Override
    public List<ChatMessage> getMessages(Object memoryId) {
        List<ChatMessage> chatMessages = list.get(memoryId);
        return chatMessages == null ? new ArrayList<>() : chatMessages;
    }

    @Override
    public void updateMessages(Object memoryId, List<ChatMessage> messages) {
        List<ChatMessage> newestMessages = new ArrayList<>();
        for (int i = messages.size() - 1; i >= 0 && newestMessages.size() < 10; i--) {
            ChatMessage message = messages.get(i);

            if (message instanceof UserMessage) {
                // Add to the newestMessages list until it contains 10 items
                newestMessages.add(0, message);  // Add at the beginning
            }
        }

        if (!newestMessages.isEmpty()) {
            list.put(memoryId, newestMessages);
        }
    }

    @Override
    public void deleteMessages(Object memoryId) {
    }
}
