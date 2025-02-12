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

package cn.tycoding.langchat.ai.core.provider;

import cn.hutool.core.util.ObjectUtil;
import dev.langchain4j.model.chat.ChatLanguageModel;
import dev.langchain4j.model.chat.StreamingChatLanguageModel;
import dev.langchain4j.model.image.ImageModel;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

/**
 * @author tycoding
 * @since 2024/3/8
 */
@Component
@AllArgsConstructor
public class ModelProvider {

    private final ModelStoreFactory modelStoreFactory;

    public StreamingChatLanguageModel stream(String modelId) {
        StreamingChatLanguageModel streamingChatModel = modelStoreFactory.getStreamingChatModel(modelId);
        if (ObjectUtil.isNotEmpty(streamingChatModel)) {
            return streamingChatModel;
        }
        throw new RuntimeException("没有匹配到模型，请检查模型配置！");
    }

    public ChatLanguageModel text(String modelId) {
        ChatLanguageModel chatLanguageModel = modelStoreFactory.getChatLanguageModel(modelId);
        if (ObjectUtil.isNotEmpty(chatLanguageModel)) {
            return chatLanguageModel;
        }
        throw new RuntimeException("没有匹配到模型，请检查模型配置！");
    }

    public ImageModel image(String modelId) {
        ImageModel imageModel = modelStoreFactory.getImageModel(modelId);
        if (ObjectUtil.isNotEmpty(imageModel)) {
            return imageModel;
        }
        throw new RuntimeException("没有匹配到模型，请检查模型配置！");
    }
}
