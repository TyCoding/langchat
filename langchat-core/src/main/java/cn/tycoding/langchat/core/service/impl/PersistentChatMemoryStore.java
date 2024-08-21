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

import dev.langchain4j.data.message.ChatMessage;
import dev.langchain4j.data.message.SystemMessage;
import dev.langchain4j.store.memory.chat.ChatMemoryStore;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author tycoding
 * @since 2024/8/15
 */
@Slf4j
public class PersistentChatMemoryStore implements ChatMemoryStore {

    private static final Map<Object, List<ChatMessage>> store = new HashMap<>();
    private static final Map<Object, Boolean> initSystemMessageStore = new HashMap<>();
    private static final Map<Object, Boolean> initMessageStore = new HashMap<>();

    public static void clean(Object memoryId) {
        log.info("clean message memory store to: {}", memoryId);
        store.remove(memoryId);
    }

    public static void init(Object memoryId, SystemMessage message) {
        Boolean isInitSystemMessage = initSystemMessageStore.get(memoryId);
        if (isInitSystemMessage != null && isInitSystemMessage) {
            return;
        }

        List<ChatMessage> list = store.get(memoryId);
        if (list == null) {
            store.put(memoryId, new ArrayList<>(List.of(message)));
        } else {
            list.add(message);
        }
        initSystemMessageStore.put(memoryId, true);
    }

    public static void init(Object memoryId, List<ChatMessage> messages) {
        log.info("initialize message memory store to: {}", memoryId);

        Boolean isInitMessage = initMessageStore.get(memoryId);
        if (isInitMessage != null && isInitMessage) {
            return;
        }

        List<ChatMessage> list = store.get(memoryId);
        if (list == null) {
            store.put(memoryId, messages);
        } else {
            list.addAll(messages);
        }
        initMessageStore.put(memoryId, true);
    }

    @Override
    public List<ChatMessage> getMessages(Object memoryId) {
        List<ChatMessage> list = store.get(memoryId);
        if (list == null) {
            return new ArrayList<>();
        }
        return list;
    }

    @Override
    public void updateMessages(Object memoryId, List<ChatMessage> messages) {
        store.put(memoryId, messages);
    }

    @Override
    public void deleteMessages(Object memoryId) {
        store.remove(memoryId);
    }
}
