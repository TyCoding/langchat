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

package cn.tycoding.langchat.core.provider;

import cn.tycoding.langchat.biz.component.ProviderEnum;
import cn.tycoding.langchat.core.consts.EmbedConst;
import dev.langchain4j.data.document.DocumentSplitter;
import dev.langchain4j.data.document.splitter.DocumentSplitters;
import dev.langchain4j.model.azure.AzureOpenAiTokenizer;
import dev.langchain4j.model.embedding.EmbeddingModel;
import dev.langchain4j.model.openai.OpenAiTokenizer;
import lombok.AllArgsConstructor;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

/**
 * @author tycoding
 * @since 2024/3/8
 */
@Component
@AllArgsConstructor
public class EmbedProvider {

    private final ApplicationContext context;

    public static DocumentSplitter splitter(String modelName, String modelProvider) {
        if (ProviderEnum.OPENAI.name().equals(modelProvider)) {
            return DocumentSplitters.recursive(100, 0, new OpenAiTokenizer(modelName));
        }
        if (ProviderEnum.AZURE_OPENAI.name().equals(modelProvider)) {
            return DocumentSplitters.recursive(100, 0, new AzureOpenAiTokenizer(modelName));
        }
        return DocumentSplitters.recursive(100, 0);
    }

    public EmbeddingModel embed() {
        if (context.containsBean(EmbedConst.CLAZZ_NAME_OPENAI)) {
            return (EmbeddingModel) context.getBean(EmbedConst.CLAZZ_NAME_OPENAI);
        }
        if (context.containsBean(EmbedConst.CLAZZ_NAME_AZURE_OPENAI)) {
            return (EmbeddingModel) context.getBean(EmbedConst.CLAZZ_NAME_AZURE_OPENAI);
        }
        if (context.containsBean(EmbedConst.CLAZZ_NAME_QIANFAN)) {
            return (EmbeddingModel) context.getBean(EmbedConst.CLAZZ_NAME_QIANFAN);
        }
        if (context.containsBean(EmbedConst.CLAZZ_NAME_QIANWEN)) {
            return (EmbeddingModel) context.getBean(EmbedConst.CLAZZ_NAME_QIANWEN);
        }
        if (context.containsBean(EmbedConst.CLAZZ_NAME_OLLAMA)) {
            return (EmbeddingModel) context.getBean(EmbedConst.CLAZZ_NAME_OLLAMA);
        }
        throw new RuntimeException("No matching embedding model information found, please check the model configuration.");
    }
}
